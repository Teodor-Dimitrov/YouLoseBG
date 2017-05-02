package com.youlose.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import com.youlose.model.Comment;
import com.youlose.model.Playlist;
import com.youlose.model.User;
import com.youlose.model.Video;

public class VideoDAO {

	private static VideoDAO instance;
	private static final HashMap<String, Video> allVideos = new HashMap<>();//

	public synchronized static VideoDAO getInstance() {
		if (instance == null) {
			instance = new VideoDAO();
		}
		return instance;
	}

	public HashMap<String, Video> getAllVideos() throws SQLException {
		String sql = "SELECT video_id, name, path, views,date,description FROM videos;";
		PreparedStatement st = null;
		if (allVideos.isEmpty()) {
			st = DBManager.getInstance().getConnection().prepareStatement(sql);
			ResultSet res = st.executeQuery();
			while (res.next()) {
				Video video = new Video();
				video.setId(res.getLong("video_id"));
				video.setName(res.getString("name"));
				video.setPath(res.getString("path"));
				video.setViews(res.getInt("views"));
				video.setDate(res.getTimestamp("date").toLocalDateTime());
				video.setDescription(res.getString("description"));
				allVideos.put(video.getName(), video);
			}

		}
		return allVideos;
	}

	public HashMap<String, Video> searchAllByString(String partOfName) throws SQLException {
		String sql = "SELECT videos_id, name, path, views,date,description FROM videos WHERE name " + "like '%"
				+ partOfName + "'%;";

		PreparedStatement st = null;
		if (allVideos.isEmpty()) {
			st = DBManager.getInstance().getConnection().prepareStatement(sql);
			ResultSet res = st.executeQuery();
			while (res.next()) {
				Video video = new Video();
				video.setId(res.getInt("videos_id"));
				video.setName(res.getString("name"));
				video.setPath(res.getString("path"));
				video.setViews(res.getInt("views"));
				video.setDate(res.getTimestamp("date").toLocalDateTime());
				video.setDescription(res.getString("description"));
				allVideos.put(video.getName(), video);
			}

		}
		return allVideos;
	}

	public synchronized long addVideo(Video video, User user) throws SQLException {
		String sql = "INSERT INTO videos (name, path, views,date,description) VALUES (?,?,?,?,?);";
		Instant instant = Instant.now();
		Timestamp time = java.sql.Timestamp.from(instant);
		PreparedStatement ps = null;
		ps = DBManager.getInstance().getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
		ps.setString(1, video.getName());
		ps.setString(2, video.getPath());
		ps.setInt(3, video.getViews());
		ps.setTimestamp(4, time);
		ps.setString(5, video.getDescription());
		
		ps.executeUpdate();
		ResultSet rs = ps.getGeneratedKeys();
		rs.next();
		video.setId(rs.getLong(1));
		UserDAO.getInstance().addVideoToPlaylist(video, user, "uploaded");
		return video.getId();
	}

	public synchronized void likeVideo(long videoID, long userID) throws SQLException {
		String sql = "INSERT INTO video_liked_or_disliked (users_user_id, videos_videos_id,like_or_dislike) VALUES (?,?,?);";
		PreparedStatement ps = null;

		ps = DBManager.getInstance().getConnection().prepareStatement(sql);
		ps.setLong(1, userID);
		ps.setLong(2, videoID);
		ps.setInt(3, 1);
		int rows = ps.executeUpdate();
		if (rows > 0) {
			System.out.println("laiknato e");
		}

	}

	public synchronized void dislikeVideo(long videoID, long userID) throws SQLException {
		String sql = "INSERT INTO video_liked_or_disliked (users_user_id, videos_videos_id,like_or_dislike) VALUES (?,?,?);";
		PreparedStatement ps = null;

		ps = DBManager.getInstance().getConnection().prepareStatement(sql);
		ps.setLong(1, userID);
		ps.setLong(2, videoID);
		ps.setInt(3, 2);
		int rows = ps.executeUpdate();
		if (rows > 0) {
			System.out.println("dislaiknato e");
		}

	}

	public synchronized void removeLikeFromVideo(Video video, User user) throws SQLException {

		String sql = "DELETE FROM video_liked_or_disliked WHERE users_user_id=? AND videos_videos_id=? AND like_or_dislike=1 ;";
		PreparedStatement ps = null;

		ps = DBManager.getInstance().getConnection().prepareStatement(sql);
		ps.setLong(1, user.getUserID());
		ps.setLong(2, video.getId());
		int rows = ps.executeUpdate();
		if (rows > 0) {
			System.out.println("mahnat e like-a");
		}

	}

	public synchronized void removeDislikeFromVideo(Video video, User user) throws SQLException {
		String sql = "DELETE FROM video_liked_or_disliked WHERE users_user_id=? AND videos_videos_id=? AND like_or_dislike=2 ;";
		PreparedStatement ps = null;

		ps = DBManager.getInstance().getConnection().prepareStatement(sql);
		ps.setLong(1, user.getUserID());
		ps.setLong(2, video.getId());
		int rows = ps.executeUpdate();
		if (rows > 0) {
			System.out.println("mahnat e dislike-a");
		}

	}

	public void deleteVideo(Video video, User user) throws SQLException {
		if (user.getUserID() == video.getPublisher()) {
			String sql = "DELETE FROM videos WHERE videos_id=?;";
			PreparedStatement ps = null;
			ps = DBManager.getInstance().getConnection().prepareStatement(sql);
			ps.setLong(1, video.getId());
			int rows = ps.executeUpdate();
			if (rows > 0) {
				for (Entry<String, Video> entry : allVideos.entrySet()) {
					if (entry.getValue().equals(video)) {
						allVideos.remove(entry.getKey(), video);
					}
					
				}
				System.out.println("iztrih go");
			}
		}
	}


	public void viewVideo(Video video) throws SQLException {
		String sql = "SELECT views FROM videos WHERE videos_id=" + video.getId() + ";";
		if (allVideos.isEmpty()) {
			PreparedStatement st = DBManager.getInstance().getConnection().prepareStatement(sql);
			ResultSet res = st.executeQuery();
			while (res.next()) {
				video.setId((res.getInt("videos_id") + 1));
			}

		}
	}

	public void deleteComment(Comment comment) throws SQLException {
		Connection con = DBManager.getInstance().getConnection();
		PreparedStatement st = con.prepareStatement("DELETE FROM comments WHERE comment_id= " + comment.getCommentID() + ";");
		st.executeUpdate();

	}
}
