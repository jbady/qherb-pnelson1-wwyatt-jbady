package edu.ycp.cs320.entrelink.userdb.persist;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
//import java.util.Collections;
import java.util.List;
import edu.ycp.cs320.entrelink.model.Post;
import edu.ycp.cs320.entrelink.model.User;
import edu.ycp.cs320.sqldemo.DBUtil;

public class DerbyDatabase implements IDatabase {
	static {
		try {
			Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
		} catch (Exception e) {
			throw new IllegalStateException("Could not load Derby driver");
		}
	}
	
	private interface Transaction<ResultType> {
		public ResultType execute(Connection conn) throws SQLException;
	}

	private static final int MAX_ATTEMPTS = 10;

	@Override
	public User findUserByEmailOrUsername(final String username) {
		return executeTransaction(new Transaction<User>() {
			@Override
			public User execute(Connection conn) throws SQLException {
				PreparedStatement stmt = null;
				ResultSet resultSet = null;
				
				try {
					// retrieve all attributes from both Books and Authors tables
					stmt = conn.prepareStatement(
							"select users.* " +
							"  from users " +
							" where users.username = ? or " +
							" users.email = ?"
					);
					stmt.setString(1, username);
					stmt.setString(2, username);
					
					User result = new User();
					
					resultSet = stmt.executeQuery();
					
					// for testing that a result was returned
					Boolean found = false;
					
					while (resultSet.next()) {
						found = true;
						
						// create new User object
						// retrieve attributes from resultSet starting with index 1
						User user = new User();
						loadUser(user, resultSet, 1);
						
						result = user;
					}
					
					// check if the title was found
					if (!found) {
						System.out.println("<" + username + "> was not found in the users table");
					}
					
					return result;
				} finally {
					DBUtil.closeQuietly(resultSet);
					DBUtil.closeQuietly(stmt);
				}
			}
		});
	}
	
	private void loadUser(User user, ResultSet resultSet, int index) throws SQLException {
		user.setUserId(resultSet.getInt(index++));
		user.setUsername(resultSet.getString(index++));
		user.setUserFirstName(resultSet.getString(index++));
		user.setUserLastName(resultSet.getString(index++));
		user.setEmail(resultSet.getString(index++));
		user.setPassword(resultSet.getString(index++));
		user.setUserType(resultSet.getString(index++));
		user.setProfilePic(resultSet.getString(index++));
		user.setWebsite(resultSet.getString(index++));
		user.setBio(resultSet.getString(index++));
		user.setMajor(resultSet.getString(index++));
		user.setStatus(resultSet.getString(index++));
		user.setInterests(resultSet.getString(index++));
		user.setSkills(resultSet.getString(index++));
	}
	
	public<ResultType> ResultType executeTransaction(Transaction<ResultType> txn) {
		try {
			return doExecuteTransaction(txn);
		} catch (SQLException e) {
			throw new PersistenceException("Transaction failed", e);
		}
	}
	
	public<ResultType> ResultType doExecuteTransaction(Transaction<ResultType> txn) throws SQLException {
		Connection conn = connect();
		
		try {
			int numAttempts = 0;
			boolean success = false;
			ResultType result = null;
			
			while (!success && numAttempts < MAX_ATTEMPTS) {
				try {
					result = txn.execute(conn);
					conn.commit();
					success = true;
				} catch (SQLException e) {
					if (e.getSQLState() != null && e.getSQLState().equals("41000")) {
						// Deadlock: retry (unless max retry count has been reached)
						numAttempts++;
					} else {
						// Some other kind of SQLException
						throw e;
					}
				}
			}
			
			if (!success) {
				throw new SQLException("Transaction failed (too many retries)");
			}
			
			// Success!
			return result;
		} finally {
			DBUtil.closeQuietly(conn);
		}
	}

	private Connection connect() throws SQLException {
		Connection conn = DriverManager.getConnection("jdbc:derby:test.db;create=true");
		
		// Set autocommit to false to allow execution of
		// multiple queries/statements as part of the same transaction.
		conn.setAutoCommit(false);
		
		return conn;
	}
	
	public void createTables() {
		executeTransaction(new Transaction<Boolean>() {
			@Override
			public Boolean execute(Connection conn) throws SQLException {
				PreparedStatement stmt1 = null;
				PreparedStatement stmt2 = null;
				
				try {
					stmt1 = conn.prepareStatement(
						"create table users (" +
						"	user_id integer primary key " +
						"		generated always as identity (start with 1, increment by 1), " +									
						"	username varchar(40)," +
						"	firstname varchar(40)," +
						"	lastname varchar(40)," +
						"	email varchar(40)," +
						"	password varchar(40)," +
						"	userType varchar(10)," +
						"	userPic varchar(585)," +
						"	userSite varchar(585)," +
						"	userBio varchar(500)," +
						"	userMajor varchar(500)," +
						"	userStatus varchar(500)," +
						"	userInterests varchar(500)," +
						"	userSkills varchar(500) " +
						")"
					);	
					stmt1.executeUpdate();
					
					stmt2 = conn.prepareStatement(
						"create table posts (" +
						"	post_id integer primary key " +
						"		generated always as identity (start with 1, increment by 1), " +
						"	poster_id varchar(4)," +
						"	name varchar(40)," +
						"	timePosted varchar(10)," +
						"	title varchar(50)," +
						"	description varchar(1000)" +
						")"
					);
					stmt2.executeUpdate();
					
					return true;
				} finally {
					DBUtil.closeQuietly(stmt1);
					DBUtil.closeQuietly(stmt2);
				}
			}
		});
	}
	
	public void loadInitialData() {
		executeTransaction(new Transaction<Boolean>() {
			@Override
			public Boolean execute(Connection conn) throws SQLException {
				List<User> userList;
				List<Post> postList;
				
				try {
					userList = InitialData.getUsers();
					postList = InitialData.getPosts();
				} catch (IOException e) {
					throw new SQLException("Couldn't read initial data", e);
				}

				PreparedStatement insertUser   = null;
				PreparedStatement insertPost   = null;

				try {
					// populate users table
					insertUser = conn.prepareStatement("insert into users (username, firstname, lastname, email, password, "
							+ " userType, userPic, userSite, userBio) "
							+ " values (?, ?, ?, ?, ?, ?, ?, ?, ?)");
					for (User user : userList) {
						insertUser.setString(1, user.getUsername());
						insertUser.setString(2, user.getUserFirstName());
						insertUser.setString(3, user.getUserLastName());
						insertUser.setString(4, user.getEmail());
						insertUser.setString(5, user.getPassword());
						insertUser.setString(6, user.getUserType());
						insertUser.setString(7, user.getProfilePic());
						insertUser.setString(8, user.getWebsite());
						insertUser.setString(9, user.getBio());
						insertUser.setString(10, user.getMajor());
						insertUser.setString(11, user.getStatus());
						insertUser.setString(12, user.getInterests());
						insertUser.setString(13, user.getSkills());
						insertUser.addBatch();
					}
					insertUser.executeBatch();
					
					// populate posts table
					insertPost = conn.prepareStatement("insert into posts (poster_id, name, timePosted, title, description) "
							+ " values (?, ?, ?, ?, ?)");
					for (Post post : postList) {
						insertPost.setInt(1, post.getPosterId());
						insertPost.setString(2, post.getName());
						insertPost.setInt(3, post.getTimePosted());
						insertPost.setString(4, post.getTitle());
						insertPost.setString(5, post.getDescription());
						insertPost.addBatch();
					}
					insertPost.executeBatch();

					return true;
				} finally {
					DBUtil.closeQuietly(insertUser);
					DBUtil.closeQuietly(insertPost);
				}
			}
		});
	}
	
	// The main method creates the database tables and loads the initial data.
	public static void main(String[] args) throws IOException {
		System.out.println("Creating tables...");
		DerbyDatabase db = new DerbyDatabase();
		db.createTables();
		
		System.out.println("Loading initial data...");
		db.loadInitialData();
		
		System.out.println("Success!");
	}

	@Override
	public ArrayList<Post> findPostsByTags(ArrayList<String> tags) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addPostToPostList(Post post) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addMultiplePostsToPostList(ArrayList<Post> posts) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ArrayList<Post> findPostsByTitle(String title) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Post> findPostsByUserName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User insertNewUser(String username, String password, String userFirstName, String userLastName, String email,
			String userType, String bio, String major, String status, String interest, String skills) {
		// TODO Auto-generated method stub
		return executeTransaction(new Transaction<User>() {
			@Override
			public User execute(Connection conn) throws SQLException {
				User nUser = null;
				PreparedStatement stmt = null;
				PreparedStatement stmt1 = null;
				ResultSet resultSet = null;							
				try {
					conn.setAutoCommit(true);
					
					stmt = conn.prepareStatement(
							"insert into users (username, password, userFirstName,"
							+ " userLastName, email, userType, bio, major, status, interest, skills)"
							+ " values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)"
							);
							
					stmt.setString(1, username);
					stmt.setString(2, password);
					stmt.setString(3, userFirstName);
					stmt.setString(4, userLastName);
					stmt.setString(5, email);
					stmt.setString(6, userType);
					stmt.setString(7, bio);
					stmt.setString(8, major);
					stmt.setString(9, status);
					stmt.setString(10, interest);
					stmt.setString(11, skills);
					
					stmt.executeQuery();
					
					stmt1 = conn.prepareStatement(
							"select * from users "
							+ "where username = ? and email = ? and password = ?"
							);
					stmt1.setString(1, username);
					stmt1.setString(2, email);
					stmt1.setString(3, password);
					
					resultSet = stmt1.executeQuery();

					if(resultSet.next()) {
						System.out.println("user has been created");
						nUser = new User(username, password, userFirstName, userLastName, email, 
								userType, bio, major, status, interest, skills);
					}
				}finally {
					DBUtil.closeQuietly(resultSet);
					DBUtil.closeQuietly(stmt1);
					DBUtil.closeQuietly(stmt);
				}
				
				
				return nUser;
			}
		});
	}
}
