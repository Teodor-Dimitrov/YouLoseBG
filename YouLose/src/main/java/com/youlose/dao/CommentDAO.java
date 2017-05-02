package com.youlose.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;

import com.youlose.model.Comment;
import com.youlose.model.User;

public class CommentDAO {

	private static CommentDAO instance;
	private ArrayList<Comment> allComents = new ArrayList<>();

	public synchronized static CommentDAO getInstance() {
		if (instance == null) {
			instance = new CommentDAO();
		}
		return instance;
	}

	public synchronized boolean addComment(String content, int userID, int videoID) throws SQLException {
		String sql = "INSERT into mydb.comments (users_user_comment_id, content, posted, videos_video_comment_id) "
				+ "values (?,?,?,?)";
		PreparedStatement ps = null;
		Instant instant = Instant.now();
		Timestamp time = java.sql.Timestamp.from(instant);
		
			ps = DBManager.getInstance().getConnection().prepareStatement(sql);
			ps.setInt(1, userID);
			ps.setString(2, content);
			ps.setTimestamp(3, time);
			ps.setInt(4, videoID);
			int rows = ps.executeUpdate();
			if (rows > 0) {
				return true;
			}

		
		return false;
	}

	public ArrayList<Comment> getAllComments(int videoID) throws SQLException {
		String sql = "select d.comment_id,d.users_user_comment_id,d.content,d.posted,d.videos_video_comment_id,"
				+ "c.username from mydb.comments d join "
				+ "mydb.users c using(users_user_comment_id) where d.videos_video_comment_id=?;";
		ArrayList<Comment> comments = new ArrayList<Comment>();
		PreparedStatement ps = null;

		
			ps = DBManager.getInstance().getConnection().prepareStatement(sql);
			ps.setInt(1, videoID);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Comment comment = new Comment();
				comment.setPostedDate(rs.getTimestamp("c.upload_time").toLocalDateTime());
				comment.setContent(rs.getString("d.content"));
				comment.setUserID(rs.getInt("d.users_user_comment_id"));
				comment.setVideoID(rs.getInt("d.videos_video_comment_id"));
				comments.add(comment);
			}
		
		return comments;
	}

	// 1 for like
	public void likeComment(int userID, int commentID) throws SQLException {
		String sql = "INSERT INTO comment_liked_disliked (comments_comment_id, users_user_comment_liked_id,like_or_dislike) VALUES (?,?,?);";
		PreparedStatement ps = DBManager.getInstance().getConnection().prepareStatement(sql);
		ps.setInt(1, userID);
		ps.setInt(2, commentID);
		ps.setInt(3, 1);
		ps.executeUpdate();

	}

	// 2 for dislike
	public void dislikeComment(int commentID, int userID) throws SQLException {
		String sql = "INSERT INTO comment_liked_disliked (comments_comment_id, users_user_comment_liked_id,like_or_dislike) VALUES (?,?,?);";
		PreparedStatement ps = DBManager.getInstance().getConnection().prepareStatement(sql);
		ps.setInt(1, userID);
		ps.setInt(2, commentID);
		ps.setInt(3, 2);
		ps.executeUpdate();
	}
}