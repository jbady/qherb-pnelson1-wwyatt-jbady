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
					// retrieve all attributes from Users table
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
					
					// check if the User was found
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
	private Post loadPost(Post post, ResultSet resultSet, int index) throws SQLException {
		post.setPostId(resultSet.getInt(index++));
		post.setPosterId(resultSet.getInt(index++));
		post.setName(resultSet.getString(index++), resultSet.getString(index++));
		post.setTimePosted(resultSet.getString(index++));
		post.setTitle(resultSet.getString(index++));
		post.setDescription(resultSet.getString(index++));
		
		return post;
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
						"	poster_id integer," +
						"	timePosted varchar(40)," +
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

					insertUser = conn.prepareStatement("insert into users (username, firstname, lastname, email, password, userType, userPic, userSite, userBio, userMajor, userStatus, userInterests, userSkills) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
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
					insertPost = conn.prepareStatement("insert into posts (poster_id, timePosted, title, description) "
							+ " values (?, ?, ?, ?)");
					for (Post post : postList) {
						insertPost.setInt(1, post.getPosterId());
						insertPost.setString(2, post.getTimePosted());
						insertPost.setString(3, post.getTitle());
						insertPost.setString(4, post.getDescription());
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
		return executeTransaction(new Transaction<ArrayList<Post>>() {
			@Override
			public ArrayList<Post> execute(Connection conn) throws SQLException {
				ArrayList<Post> posts = new ArrayList<Post>();
				PreparedStatement stmt = null;
				ResultSet resultSet = null;							
				try {
					conn.setAutoCommit(true);
					
					stmt = conn.prepareStatement("select * from posts where title like '%' || ? || '%'");
					stmt.setString(1, title);
					
					resultSet = stmt.executeQuery();
					
					while(resultSet.next()) {
							Post nPost = new Post();
							loadPost(nPost, resultSet, 1);
							posts.add(nPost);
					}
				}finally {
					DBUtil.closeQuietly(resultSet);
					DBUtil.closeQuietly(stmt);
				}
				return posts;
			}
		});
	}

	@Override
	public ArrayList<Post> findPostsByUserName(String userName) {
		// TODO Auto-generated method stub
		return executeTransaction(new Transaction<ArrayList<Post>>() {
			@Override
			public ArrayList<Post> execute(Connection conn) throws SQLException {
				ArrayList<Post> posts = new ArrayList<Post>();
				int user_id;
				PreparedStatement stmt = null;
				PreparedStatement stmt1 = null;
				ResultSet resultSet = null;		
				ResultSet resultSet1 = null;
				try {
					conn.setAutoCommit(true);
					
					stmt = conn.prepareStatement("select users.user_id from users where users.username like '%' || ? || '%'");
					stmt.setString(1, userName);
					
					resultSet = stmt.executeQuery();
					
					while(resultSet.next()) {
							user_id = resultSet.getInt(1);
							
							try{
								conn.setAutoCommit(true);
								stmt1 = conn.prepareStatement(
										"select * from posts where poster_id = ?"
										);
							
								stmt1.setInt(1, user_id);
								resultSet1 = stmt1.executeQuery();
								
								while(resultSet1.next()) {
									Post nPost = new Post();
									nPost = loadPost(nPost, resultSet1,1);
									posts.add(nPost);
								}
							}finally {
								DBUtil.closeQuietly(resultSet1);
								DBUtil.closeQuietly(stmt1);
							}
					}
				}finally {
					DBUtil.closeQuietly(resultSet);
					DBUtil.closeQuietly(stmt);
				}
				return posts;
			}
		});
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
					
					stmt = conn.prepareStatement("insert into users (username, firstname, lastname, email, password, userType, userPic, userSite, userBio, userMajor, userStatus, userInterests, userSkills) "
							+ " values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
							
					stmt.setString(1, username);
					stmt.setString(2, userFirstName);
					stmt.setString(3, userLastName);
					stmt.setString(4, email);
					stmt.setString(5, password);
					stmt.setString(6, userType);
					stmt.setString(7, "https://i.imgur.com/46FYTE7.png");
					stmt.setString(8, "N/A");
					stmt.setString(9, bio);
					stmt.setString(10, major);
					stmt.setString(11, status);
					stmt.setString(12, interest);
					stmt.setString(13, skills);
					
					stmt.execute();
					
					conn.setAutoCommit(true);
					stmt1 = conn.prepareStatement(
							"select * from users "
							+ "where username = ? and email = ? and password = ?"
							);
					stmt1.setString(1, username);
					stmt1.setString(2, email);
					stmt1.setString(3, password);
					
					resultSet = stmt1.executeQuery();

					if(resultSet.next()==true) {
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

	@Override
	public ArrayList<Post> findAllPosts() {
		return executeTransaction(new Transaction<ArrayList<Post>>() {
			@Override
			public ArrayList<Post> execute(Connection conn) throws SQLException {
				ArrayList<Post> posts = new ArrayList<Post>();
				PreparedStatement stmt = null;
				ResultSet resultSet = null;							
				try {
					conn.setAutoCommit(true);
					
					stmt = conn.prepareStatement(
							"SELECT posts.post_id, users.user_id, users.firstName, users.LastName, posts.timePosted, posts.title, posts.description " + 
							"FROM users, posts " + 
							"WHERE users.user_id = posts.poster_id"
							);
					
					resultSet = stmt.executeQuery();
					
					while(resultSet.next()) {
							Post nPost = new Post();
							loadPost(nPost, resultSet, 1);
							posts.add(nPost);
					}
				}finally {
					DBUtil.closeQuietly(resultSet);
					DBUtil.closeQuietly(stmt);
				}
				return posts;
			}
		});
	}

	@Override
	public Post insertNewPost(int poster_id, String timePosted, String title, String description) {
		// TODO Auto-generated method stub
		return executeTransaction(new Transaction<Post>() {
			@Override
			public Post execute(Connection conn) throws SQLException {
				Post nPost = new Post();
				PreparedStatement stmt = null;
				PreparedStatement stmt1 = null;
				PreparedStatement stmt2 = null;
				ResultSet resultSet = null;
				ResultSet resultSet1 = null;
				
						try {
							
							conn.setAutoCommit(true);
							
							stmt1 = conn.prepareStatement("insert into posts (poster_id, timeposted, title, description) values (?, ?, ?, ?)");
							stmt1.setInt(1, poster_id);
							stmt1.setString(2, timePosted);
							stmt1.setString(3, title);
							stmt1.setString(4, description);
							
							stmt1.execute();
							
							stmt2 = conn.prepareStatement("select * from posts where poster_id = ? and title = ?");
							
							stmt2.setInt(1, poster_id);
							stmt2.setString(2, title);
							
							resultSet1 = stmt2.executeQuery();
							
							if(resultSet1.next()) {
								nPost = loadPost(nPost, resultSet1, 1);
							}
						}finally {
							DBUtil.closeQuietly(resultSet1);
							DBUtil.closeQuietly(stmt1);
							DBUtil.closeQuietly(stmt2);
						}return nPost;
					}
				
		});
	}
}
