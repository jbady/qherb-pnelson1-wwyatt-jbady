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

import edu.ycp.cs320.entrelink.model.Message;
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
	// LOAD USERS
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
	private void loadPost(Post post, ResultSet resultSet, int index) throws SQLException {
		post.setPostId(resultSet.getInt(index++));
		post.setPosterId(resultSet.getInt(index++));
		post.setTimePosted(resultSet.getString(index++));
		post.setTitle(resultSet.getString(index++));
		post.setDescription(resultSet.getString(index++));
		post.setPostType(resultSet.getInt(index++));
		post.setTags(resultSet.getString(index++));
	}
	
	private void loadMessage(Message m, ResultSet resultSet, int index) throws SQLException {
		m.setMessageId(resultSet.getInt(index++));
		m.setSender(resultSet.getInt(index++));
		m.setRecipient(resultSet.getInt(index++));
		m.setDate(resultSet.getString(index++));
		m.setSubject(resultSet.getString(index++));
		m.setBody(resultSet.getString(index++));
		m.setRead(resultSet.getInt(index++));
		m.setRecipientName(resultSet.getString(index++) + " " + resultSet.getString(index++));
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
				PreparedStatement stmt3 = null;
				
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
						"	description varchar(1000)," +
						"	postType integer," +
						"   tags varchar(200)" +
						")"
					);
					stmt2.executeUpdate();
					
					stmt3 = conn.prepareStatement(
						"create table messages (" +
						"   msgId integer primary key " +
						"      generated always as identity (start with 1, increment by 1), " +
						"   sender integer," +
						"   recipient integer," +
						"   dateSent varchar(40)," +
						"   subject varchar(50)," +
						"   body varchar(500)," +
						"   opened integer" +
						")"
					);
					stmt3.executeUpdate();
					
					return true;
				} finally {
					DBUtil.closeQuietly(stmt1);
					DBUtil.closeQuietly(stmt2);
					DBUtil.closeQuietly(stmt3);
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
				List<Message> msgList;
				
				try {
					userList = InitialData.getUsers();
					postList = InitialData.getPosts();
					msgList = InitialData.getMessages();
				} catch (IOException e) {
					throw new SQLException("Couldn't read initial data", e);
				}

				PreparedStatement insertUser   = null;
				PreparedStatement insertPost   = null;
				PreparedStatement insertMessage   = null;

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
					insertPost = conn.prepareStatement("insert into posts (poster_id, timePosted, title, description, postType, tags) "
							+ " values (?, ?, ?, ?, ?, ?)");
					for (Post post : postList) {
						insertPost.setInt(1, post.getPosterId());
						insertPost.setString(2, post.getTimePosted());
						insertPost.setString(3, post.getTitle());
						insertPost.setString(4, post.getDescription());
						insertPost.setInt(5, post.getPostType());
						insertPost.setString(6,  post.getTags());
						insertPost.addBatch();
					}
					insertPost.executeBatch();

					// populate messages table
					insertMessage = conn.prepareStatement(
							"insert into messages (sender, recipient, dateSent, subject, body, opened) " +
							" values (?, ?, ?, ?, ?, ?)");
					
					for (Message message : msgList) {
						insertMessage.setInt(1, message.getSender());
						insertMessage.setInt(2, message.getRecipient());
						insertMessage.setString(3, message.getDate());
						insertMessage.setString(4, message.getSubject());
						insertMessage.setString(5, message.getBody());
						insertMessage.setInt(6, message.getRead());
						insertMessage.addBatch();
					}
					
					insertMessage.executeBatch();
					
					return true;
				} finally {
					DBUtil.closeQuietly(insertUser);
					DBUtil.closeQuietly(insertPost);
					DBUtil.closeQuietly(insertMessage);
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
					
					stmt = conn.prepareStatement("select * from posts where posts.title like '%' || ? || '%'");
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
										"select * from posts where posts.poster_id = ?"
										);
							
								stmt1.setInt(1, user_id);
								resultSet1 = stmt1.executeQuery();
								
								while(resultSet1.next()) {
									Post nPost = new Post();
									loadPost(nPost, resultSet1,1);
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
			String userType) {
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
					stmt.setString(9, "N/A");
					stmt.setString(10, "N/A");
					stmt.setString(11, "N/A");
					stmt.setString(12, "N/A");
					stmt.setString(13, "N/A");
					
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
						nUser = new User();
						nUser.setUsername(username);
						nUser.setPassword(password);
						nUser.setUserFirstName(userFirstName);
						nUser.setUserLastName(userLastName);
						nUser.setEmail(email);
						nUser.setUserType(userType);
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
							"SELECT posts.post_id, users.user_id, users.firstName, users.LastName, posts.timePosted, posts.title, posts.description, posts.postType, posts.tags " + 
							"FROM users, posts " + 
							"WHERE users.user_id = posts.poster_id AND (posts.postType = 0 OR posts.postType = 1) " +
							"ORDER BY posts.post_id DESC"
							);
					
					resultSet = stmt.executeQuery();
					
					while(resultSet.next()) {
							Post nPost = new Post();
							int index =1;
							nPost.setPostId(resultSet.getInt(index++));
							nPost.setPosterId(resultSet.getInt(index++));
							nPost.setName(resultSet.getString(index++), resultSet.getString(index++));
							nPost.setTimePosted(resultSet.getString(index++));
							nPost.setTitle(resultSet.getString(index++));
							nPost.setDescription(resultSet.getString(index++));
							nPost.setPostType(resultSet.getInt(index++));
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
	public ArrayList<Post> findAllBusinessPosts() {
		return executeTransaction(new Transaction<ArrayList<Post>>() {
			@Override
			public ArrayList<Post> execute(Connection conn) throws SQLException {
				ArrayList<Post> posts = new ArrayList<Post>();
				PreparedStatement stmt = null;
				ResultSet resultSet = null;							
				try {
					conn.setAutoCommit(true);
					
					stmt = conn.prepareStatement(
							"SELECT posts.post_id, users.user_id, users.firstName, users.LastName, posts.timePosted, posts.title, posts.description, posts.postType, posts.tags " + 
							"FROM users, posts " + 
							"WHERE users.user_id = posts.poster_id AND posts.postType = 2" +
							"ORDER BY posts.post_id DESC"
							);
					
					resultSet = stmt.executeQuery();
					
					while(resultSet.next()) {
						Post nPost = new Post();
						int index =1;
						nPost.setPostId(resultSet.getInt(index++));
						nPost.setPosterId(resultSet.getInt(index++));
						nPost.setName(resultSet.getString(index++), resultSet.getString(index++));
						nPost.setTimePosted(resultSet.getString(index++));
						nPost.setTitle(resultSet.getString(index++));
						nPost.setDescription(resultSet.getString(index++));
						nPost.setPostType(resultSet.getInt(index++));
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
	public Post insertNewPost(int poster_id, String timePosted, String title, String description, int postType, String tags) {
		// TODO Auto-generated method stub
		return executeTransaction(new Transaction<Post>() {
			@Override
			public Post execute(Connection conn) throws SQLException {
				Post nPost = new Post();
				PreparedStatement stmt1 = null;
				PreparedStatement stmt2 = null;
				ResultSet resultSet1 = null;
				
						try {
							
							conn.setAutoCommit(true);
							
							stmt1 = conn.prepareStatement("insert into posts (poster_id, timePosted, title, description, postType, tags) values (?, ?, ?, ?, ?, ?)");
							stmt1.setInt(1, poster_id);
							stmt1.setString(2, timePosted);
							stmt1.setString(3, title);
							stmt1.setString(4, description);
							stmt1.setInt(5, postType);
							stmt1.setString(6, tags);
							stmt1.executeUpdate();
							
							//stmt2 = conn.prepareStatement("select * from posts where posts.poster_id = ? and posts.title = ?");
							stmt2 = conn.prepareStatement(
									"SELECT * " + 
									"FROM  posts " + 
									"WHERE posts.poster_id = ? and posts.title = ?" +
									"ORDER BY posts.post_id DESC"
									);
							
							stmt2.setInt(1, poster_id);
							stmt2.setString(2, title);
							
							resultSet1 = stmt2.executeQuery();
							
							if(resultSet1.next()) {
								loadPost(nPost, resultSet1, 1);
							}
						}finally {
							DBUtil.closeQuietly(resultSet1);
							DBUtil.closeQuietly(stmt1);
							DBUtil.closeQuietly(stmt2);
						}return nPost;
					}
				
		});
	}

	@Override
	public Boolean deleteSinglePost(int poster_id, String title) {
		return executeTransaction(new Transaction<Boolean>() {
			@Override
			public Boolean execute(Connection conn) throws SQLException {
				PreparedStatement stmt = null;
				
				try {
					
					conn.setAutoCommit(true);
					
					stmt = conn.prepareStatement("delete from posts where posts.poster_id = ? and posts.title = ?");
					
					stmt.setInt(1, poster_id);
					stmt.setString(2, title);
					
					return stmt.execute();
				}finally {
					DBUtil.closeQuietly(stmt);
				}
			}
		});
	}

	@Override
	public Boolean deleteAllUserPosts(int poster_id) {
		return executeTransaction(new Transaction<Boolean>() {
			@Override
			public Boolean execute(Connection conn) throws SQLException {
				PreparedStatement stmt = null;
				
				try {
					
					conn.setAutoCommit(true);
					
					stmt = conn.prepareStatement("delete from posts where posts.poster_id = ?");
					
					stmt.setInt(1, poster_id);
					
					return stmt.execute();
				}finally {
					DBUtil.closeQuietly(stmt);
				}
			}
		});
	}

	@Override
	public Boolean deleteUser(String username, String email) {
		return executeTransaction(new Transaction<Boolean>() {
			@Override
			public Boolean execute(Connection conn) throws SQLException {
				PreparedStatement stmt = null;
				
				try {
					
					conn.setAutoCommit(true);
					
					stmt = conn.prepareStatement("delete from users where users.username = ? and users.email = ?");
					
					stmt.setString(1, username);
					stmt.setString(2, email);
					
					return stmt.execute();
				}finally {
					DBUtil.closeQuietly(stmt);
				}
			}
		});
	}
	@Override
	public User changeUserBio(String username, String bio) {
		// TODO Auto-generated method stub
		return executeTransaction(new Transaction<User>() {
			@Override
			public User execute(Connection conn) throws SQLException {
				PreparedStatement stmt = null;
				PreparedStatement stmt1 = null;
				ResultSet resultSet = null;
				ResultSet resultSet2 = null;
				PreparedStatement stmt2 = null;
				
				try {
					conn.setAutoCommit(true);
					
					stmt1 = conn.prepareStatement("select user_id from users where username = ?");
					
					stmt1.setString(1, username);
					
					resultSet = stmt1.executeQuery();
					if(resultSet.next()) {
						
					
						stmt = conn.prepareStatement("update users set userbio = ? where user_id = ?");
					
						stmt.setString(1, bio);
						stmt.setInt(2, resultSet.getInt(1));
						stmt.execute();
					}
				}finally{
					DBUtil.closeQuietly(resultSet);
					DBUtil.closeQuietly(stmt1);
					DBUtil.closeQuietly(stmt);
				}
				try {
					conn.setAutoCommit(true);
					stmt2 = conn.prepareStatement("select * from users where username = ?");
					
					stmt2.setString(1, username);
					
					resultSet2 = stmt2.executeQuery();
					
					if(resultSet2.next()) {
						User nUser = new User();
						loadUser(nUser, resultSet2, 1);
						return nUser;
					}else {
						return null;
					}
				}finally {
					DBUtil.closeQuietly(resultSet2);
					DBUtil.closeQuietly(stmt2);
				}
			}
		});
		
	}
	public ArrayList<Message> getAllMessagesForLoggedInUser(int userId) {
		return executeTransaction(new Transaction<ArrayList<Message>>() {
			@Override
			public ArrayList<Message> execute(Connection conn) throws SQLException {
				ArrayList<Message> messages = new ArrayList<Message>();
				PreparedStatement stmt = null;
				ResultSet resultSet = null;							
				try {
					conn.setAutoCommit(true);
					
					stmt = conn.prepareStatement(
							"SELECT messages.msgId, messages.sender, messages.recipient, messages.dateSent, messages.subject, messages.body, messages.opened, users.firstName, users.lastName " +
							"FROM messages, users " +
							"WHERE messages.recipient = ? AND messages.sender = users.user_id " +
							"ORDER BY messages.msgId DESC"
							);
					
					stmt.setInt(1, userId);
					resultSet = stmt.executeQuery();
					
					while(resultSet.next()) {
							Message nMessage = new Message();
							loadMessage(nMessage, resultSet, 1);
							messages.add(nMessage);
					}
				}finally {
					DBUtil.closeQuietly(resultSet);
					DBUtil.closeQuietly(stmt);
				}
				return messages;
			}
		});
	}
	@Override
	public User changeUserPic(String username, String pic) {
		// TODO Auto-generated method stub
		return executeTransaction(new Transaction<User>() {
			@Override
			public User execute(Connection conn) throws SQLException {
				PreparedStatement stmt = null;
				PreparedStatement stmt1 = null;
				ResultSet resultSet = null;
				ResultSet resultSet2 = null;
				PreparedStatement stmt2 = null;
				
				try {
					conn.setAutoCommit(true);
					
					stmt1 = conn.prepareStatement("select user_id from users where username = ?");
					
					stmt1.setString(1, username);
					
					resultSet = stmt1.executeQuery();
					if(resultSet.next()) {
						stmt = conn.prepareStatement("update users set userpic = ? where user_id = ?");
						
						stmt.setString(1, pic);
						stmt.setInt(2, resultSet.getInt(1));
						stmt.execute();
					}
					
				}finally{
					DBUtil.closeQuietly(resultSet);
					DBUtil.closeQuietly(stmt1);
					DBUtil.closeQuietly(stmt);
				}
				try {
					conn.setAutoCommit(true);
					stmt2 = conn.prepareStatement("select * from users where username = ?");
					
					stmt2.setString(1, username);
					
					resultSet2 = stmt2.executeQuery();
					
					if(resultSet2.next()) {
						User nUser = new User();
						loadUser(nUser, resultSet2, 1);
						return nUser;
					}else {
						return null;
					}
				}finally {
					DBUtil.closeQuietly(resultSet2);
					DBUtil.closeQuietly(stmt2);
				}
			}
		});
		
	}

	@Override
	public User changeUserWebsite(String username, String website) {
		return executeTransaction(new Transaction<User>() {
			@Override
			public User execute(Connection conn) throws SQLException {
				PreparedStatement stmt = null;
				PreparedStatement stmt1 = null;
				ResultSet resultSet = null;
				ResultSet resultSet2 = null;
				PreparedStatement stmt2 = null;
				
				try {
					conn.setAutoCommit(true);
					
					stmt1 = conn.prepareStatement("select user_id from users where username = ?");
					
					stmt1.setString(1, username);
					
					resultSet = stmt1.executeQuery();
					
					if(resultSet.next()) {
						stmt = conn.prepareStatement("update users set usersite = ? where user_id = ?");
						
						stmt.setString(1, website);
						stmt.setInt(2, resultSet.getInt(1));
						stmt.execute();
					}
				}finally{
					DBUtil.closeQuietly(resultSet);
					DBUtil.closeQuietly(stmt1);
					DBUtil.closeQuietly(stmt);
				}
				try {
					conn.setAutoCommit(true);
					stmt2 = conn.prepareStatement("select * from users where username = ?");
					
					stmt2.setString(1, username);
					
					resultSet2 = stmt2.executeQuery();
					
					if(resultSet2.next()) {
						User nUser = new User();
						loadUser(nUser, resultSet2, 1);
						return nUser;
					}else {
						return null;
					}
				}finally {
					DBUtil.closeQuietly(resultSet2);
					DBUtil.closeQuietly(stmt2);
				}
			}
		});
		
	}
	@Override
	public User changeUserMajor(String username, String major) {
		// TODO Auto-generated method stub
		return executeTransaction(new Transaction<User>() {
			@Override
			public User execute(Connection conn) throws SQLException {
				PreparedStatement stmt = null;
				PreparedStatement stmt1 = null;
				ResultSet resultSet = null;
				ResultSet resultSet2 = null;
				PreparedStatement stmt2 = null;
				
				try {
					conn.setAutoCommit(true);
					
					stmt1 = conn.prepareStatement("select user_id from users where username = ?");
					
					stmt1.setString(1, username);
					
					resultSet = stmt1.executeQuery();
					
					if(resultSet.next()) {
						
						stmt = conn.prepareStatement("update users set usermajor = ? where user_id = ?");
					
						stmt.setString(1, major);
						stmt.setInt(2, resultSet.getInt(1));
						stmt.execute();
					}
				}finally{
					DBUtil.closeQuietly(resultSet);
					DBUtil.closeQuietly(stmt1);
					DBUtil.closeQuietly(stmt);
				}
				try {
					conn.setAutoCommit(true);
					stmt2 = conn.prepareStatement("select * from users where username = ?");
					
					stmt2.setString(1, username);
					
					resultSet2 = stmt2.executeQuery();
					
					if(resultSet2.next()) {
						User nUser = new User();
						loadUser(nUser, resultSet2, 1);
						return nUser;
					}else {
						return null;
					}
				}finally {
					DBUtil.closeQuietly(resultSet2);
					DBUtil.closeQuietly(stmt2);
				}
			}
		});
		
	}
	@Override
	public User changeUserStatus(String username, String status) {
		// TODO Auto-generated method stub
		return executeTransaction(new Transaction<User>() {
			@Override
			public User execute(Connection conn) throws SQLException {
				PreparedStatement stmt = null;
				PreparedStatement stmt1 = null;
				ResultSet resultSet = null;
				ResultSet resultSet2 = null;
				PreparedStatement stmt2 = null;
				
				try {
					conn.setAutoCommit(true);
					
					stmt1 = conn.prepareStatement("select user_id from users where username = ?");
					
					stmt1.setString(1, username);
					
					resultSet = stmt1.executeQuery();
					
					if(resultSet.next()) {
						stmt = conn.prepareStatement("update users set userstatus = ? where user_id = ?");
					
						stmt.setString(1, status);
						stmt.setInt(2, resultSet.getInt(1));
						stmt.execute();
					}
					
				}finally{
					DBUtil.closeQuietly(resultSet);
					DBUtil.closeQuietly(stmt1);
					DBUtil.closeQuietly(stmt);
				}
				try {
					conn.setAutoCommit(true);
					stmt2 = conn.prepareStatement("select * from users where username = ?");
					
					stmt2.setString(1, username);
					
					resultSet2 = stmt2.executeQuery();
					
					if(resultSet2.next()) {
						User nUser = new User();
						loadUser(nUser, resultSet2, 1);
						return nUser;
					}else {
						return null;
					}
				}finally {
					DBUtil.closeQuietly(resultSet2);
					DBUtil.closeQuietly(stmt2);
				}
			}
		});
		
	}
	@Override
	public User changeUserInterests(String username, String interests) {
		// TODO Auto-generated method stub
		return executeTransaction(new Transaction<User>() {
			@Override
			public User execute(Connection conn) throws SQLException {
				PreparedStatement stmt = null;
				PreparedStatement stmt1 = null;
				ResultSet resultSet = null;
				ResultSet resultSet2 = null;
				PreparedStatement stmt2 = null;
				
				try {
					conn.setAutoCommit(true);
					
					stmt1 = conn.prepareStatement("select user_id from users where username = ?");
					
					stmt1.setString(1, username);
					
					resultSet = stmt1.executeQuery();
					
					if(resultSet.next()) {
						stmt = conn.prepareStatement("update users set userinterests = ? where user_id = ?");
						stmt.setString(1, interests);
						stmt.setInt(2, resultSet.getInt(1));
						stmt.execute();
					}					
				}finally{
					DBUtil.closeQuietly(resultSet);
					DBUtil.closeQuietly(stmt1);
					DBUtil.closeQuietly(stmt);
				}
				try {
					conn.setAutoCommit(true);
					stmt2 = conn.prepareStatement("select * from users where username = ?");
					
					stmt2.setString(1, username);
					
					resultSet2 = stmt2.executeQuery();
					
					if(resultSet2.next()) {
						User nUser = new User();
						loadUser(nUser, resultSet2, 1);
						return nUser;
					}else {
						return null;
					}
				}finally {
					DBUtil.closeQuietly(resultSet2);
					DBUtil.closeQuietly(stmt2);
				}
			}
		});
		
	}
	@Override
	public User changeUserSkills(String username, String skills) {
		// TODO Auto-generated method stub
		return executeTransaction(new Transaction<User>() {
			@Override
			public User execute(Connection conn) throws SQLException {
				PreparedStatement stmt = null;
				PreparedStatement stmt1 = null;
				ResultSet resultSet = null;
				ResultSet resultSet2 = null;
				PreparedStatement stmt2 = null;
				
				try {
					conn.setAutoCommit(true);
					
					stmt1 = conn.prepareStatement("select user_id from users where username = ?");
					
					stmt1.setString(1, username);
					
					resultSet = stmt1.executeQuery();
					
					if(resultSet.next()) {
						stmt = conn.prepareStatement("update users set userskills = ? where user_id = ?");
						
						stmt.setString(1, skills);
						stmt.setInt(2, resultSet.getInt(1));
						stmt.execute();
					}
					
				}finally{
					DBUtil.closeQuietly(resultSet);
					DBUtil.closeQuietly(stmt1);
					DBUtil.closeQuietly(stmt);
				}
				try {
					conn.setAutoCommit(true);
					stmt2 = conn.prepareStatement("select * from users where username = ?");
					
					stmt2.setString(1, username);
					
					resultSet2 = stmt2.executeQuery();
					
					if(resultSet2.next()) {
						User nUser = new User();
						loadUser(nUser, resultSet2, 1);
						return nUser;
					}else {
						return null;
					}
				}finally {
					DBUtil.closeQuietly(resultSet2);
					DBUtil.closeQuietly(stmt2);
				}
			}
		});
		
	}
}
