package com.youlose.dao;

import static org.mockito.Matchers.contains;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;

import java.awt.List;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.Map.Entry;
import java.util.function.ToDoubleBiFunction;

import org.hibernate.validator.internal.util.privilegedactions.NewInstance;

import com.youlose.model.EmailSender;
import com.youlose.model.User;
import com.youlose.model.Video;

public class UserDAO {
	private EmailSender check = new EmailSender();
	private static UserDAO instance;
	private HashMap<String, User> users = new HashMap<>();
	
	public UserDAO(){
		
	}

	public synchronized static UserDAO getInstance() {
		if (instance == null) {
			instance = new UserDAO();
		}
		return instance;
	}

	public synchronized boolean save(User user) throws SQLException, NoSuchAlgorithmException {

		PreparedStatement statement = null;
			
		    Connection con = DBManager.getInstance().getConnection();
		    
			String sql = "INSERT INTO users (email,username,password,profile_picture)" + "VALUES(?,?,md5(?),?);";
			statement = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			
        
			statement.setString(1, user.getEmail());
			statement.setString(2, user.getName());
			statement.setString(3, user.getPassword());
			statement.setString(4, user.getProfilePicture());
			
			int rowsAffected = statement.executeUpdate();
			ResultSet rs = statement.getGeneratedKeys();
			while(rs.next()){
				user.setUserID(rs.getInt(1));
			} 
		
			for(String playlist : user.getPlaylists()){
				PlaylistDAO.getInstance().createPlaylist(user.getUserID(), playlist);
			}
			if (rowsAffected > 0) {
				System.out.println("Saving user is successful!!");
				users.put(user.getEmail(), user);
				return true;
			}
		return false;
	}

	public boolean loginValid(String email, String password) throws SQLException, NoSuchAlgorithmException {
		PreparedStatement ps = null;
		String sql = "SELECT email, password " + "FROM users WHERE email = ? AND password = ?;";

			ps = DBManager.getInstance().getConnection().prepareStatement(sql);

			ps.setString(1, email);
			ps.setString(2, User.hashPass(password));

			ResultSet rs = ps.executeQuery();

			if (!(rs.next())) {
				System.out.println("Wrong data.");
				return false;
			}

		
		return true;
	}

	// get of user from cashcollection
	public User getUser(User user) {
		for (Entry<String, User> entry : users.entrySet()) {
			if (entry.getValue().equals(user)) {
				return entry.getValue();
			}
		}
		return null;
	}

	public HashMap<String, User> getAllUsers() throws SQLException {
		if (users != null) {
			Statement st = null;
		
				st = DBManager.getInstance().getConnection().createStatement();

				ResultSet resultSet = st
						.executeQuery("SELECT user_id, password, email, username, profile_picture FROM users;");
				while (resultSet.next()) {
					User user = new User();
					user.setEmail(resultSet.getString("email"));
					user.setPassword(resultSet.getString("password"));
					user.setUserID(resultSet.getInt("user_id"));
					user.setName(resultSet.getString("username"));
					user.setProfilePicture(resultSet.getString("profile_picture"));
					users.put(user.getEmail(), user);
				}

			
			return users;
		} else {
			return users;
		}
	}

	public boolean subscribeUser(long subscriberID, long subscribedID) throws SQLException {
		if (subscribedID != 0) {
			String sql = "INSERT into user_subscribers (users_user_id, users_subscriber_id) values (?,?);";
			PreparedStatement ps = null;
			
				ps = DBManager.getInstance().getConnection().prepareStatement(sql);
				ps.setLong(1, subscribedID);
				ps.setLong(2, subscriberID);
				int rows = ps.executeUpdate();
				if (rows > 0) {
					return true;
				}
			
			return false;
		}
		return false;
	}

	public ArrayList<Long> getSubscribers(long l) throws SQLException {
		String sql = "SELECT users_subscriber_id from user_subscribers where users_user_id = ?;";
		ArrayList<Long> subscribers = new ArrayList<>();
		PreparedStatement ps = null;

		
			ps = DBManager.getInstance().getConnection().prepareStatement(sql);
			ps.setLong(1, l);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				subscribers.add(rs.getLong("users_subscriber_id"));
			}
		
		return subscribers;
	}

	public ArrayList<Integer> getSubscribing(int subscriberID) throws SQLException{
		String sql = "SELECT users_user_id from user_subscribers where user_subscriber_id = ?;";
		ArrayList<Integer> subscribing = new ArrayList<>();
		PreparedStatement ps = null;
	
			ps = DBManager.getInstance().getConnection().prepareStatement(sql);
			ps.setInt(1, subscriberID);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				subscribing.add(rs.getInt("users_user_id"));
			}
		
		return subscribing;
	}

	public boolean unSubscribeUser(long subscriberID, long subscribedID) throws SQLException {
		if (subscribedID != 0) {
			String sql = "DELETE from user_subscribers WHERE users_subscriber_id = ? AND users_user_id = ?;";
			PreparedStatement ps = null;

			
				ps = DBManager.getInstance().getConnection().prepareStatement(sql);
				ps.setLong(1, subscriberID);
				ps.setLong(2, subscribedID);

				int rows = ps.executeUpdate();

				if (rows > 0) {
					return true;
				}
			
			return false;
		}
		return false;
	}

	public void editPassword(int userID, String newPassword) throws SQLException {
		String sql = "UPDATE users SET password = ? WHERE user_id=?";
		PreparedStatement ps = null;
	
			ps = DBManager.getInstance().getConnection().prepareStatement(sql);
			ps.setString(1, newPassword);
			ps.setInt(2, userID);
			ps.executeUpdate();
		
	}

	public void addProfilePicture(String username, String profilepic) throws SQLException {
		String sql = "UPDATE users SET profile_picture = ? where username = ?;";
		PreparedStatement ps = null;
		try {
			ps = DBManager.getInstance().getConnection().prepareStatement(sql);
			ps.setString(1, profilepic);
			ps.setString(2, username);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void editProfilePicture(int userID, String newPhoto) throws SQLException {
		String sql = "UPDATE users SET profile_picture = ? WHERE user_id =?";
		PreparedStatement ps = null;
		
			ps = DBManager.getInstance().getConnection().prepareStatement(sql);

			ps.setString(1, newPhoto);
			ps.setInt(2, userID);
			ps.executeUpdate();
		
	}

	public void editName(int userID, String newName) throws SQLException {
		String sql = "UPDATE users SET username = ? WHERE user_id = ?";
		PreparedStatement ps = null;
		
			ps = DBManager.getInstance().getConnection().prepareStatement(sql);

			ps.setString(1, newName);
			ps.setInt(2, userID);
			ps.executeUpdate();
		
	}

	public String validateRegistration(String email, String name, String password, String confPass) {
		String msg = "Registration successful";
		if (!password.equals(confPass)) {
			msg = "Passwords not matching";
			System.out.println("pass match");
		}
		if (users.containsKey(email)) {
			System.out.println("tuka?");
			msg = "A user with this email already exists";
		}
		for (User u : users.values()) {
			if (u.getName().equals(name)) {
				msg = "A user with this email already exists";
			}
		}
		return msg;
	}
	
	public HashMap<String, User> searchAllUsersByString(String partOfName) throws SQLException {
		String sql = "SELECT user_id, password, email, username, profile_picture FROM users WHERE name " + "like '%"
				+ partOfName + "'%;";
		PreparedStatement st = null;
		if (users.isEmpty()) {
			st = DBManager.getInstance().getConnection().prepareStatement(sql);
			ResultSet res = st.executeQuery();
			while (res.next()) {
				User user = new User();
				user.setUserID(res.getInt("user_id"));
				user.setPassword(res.getString("password"));
				user.setEmail(res.getString("email"));
				user.setName(res.getString("username"));
				user.setProfilePicture(res.getString("profile_picture"));
				users.put(user.getName(), user);
			}
		}
		return users;
	}
	
	public User getUserById(Long userId) throws SQLException{
		String sql = "SELECT user_id, password, email, username, profile_picture FROM users WHERE id="+ userId + ";";
		PreparedStatement st = null;
		User user = new User();
			st = DBManager.getInstance().getConnection().prepareStatement(sql);
			ResultSet res = st.executeQuery();
			while (res.next()) {
				user.setUserID(res.getInt("user_id"));
				user.setPassword(res.getString("password"));
				user.setEmail(res.getString("email"));
				user.setName(res.getString("username"));
				user.setProfilePicture(res.getString("profile_picture"));
				
	}
			return  user;
}
	public void addVideoToPlaylist(Video video, User user, String playlist) throws SQLException{
		PreparedStatement statement = null;
		String sql = "INSERT INTO playlist_videos(videos_video_playlist_id, playlists_playlist_id) VALUE (?,?);";
		statement = DBManager.getInstance().getConnection().prepareStatement(sql);
		statement.setLong(1, video.getId());
		statement.setLong(2, user.getUserID());
		
		statement.executeUpdate();
	}
}
