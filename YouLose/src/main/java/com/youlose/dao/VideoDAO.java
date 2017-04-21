package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

import model.Comment;
import model.Playlist;
import model.User;
import model.Video;

public class VideoDAO {

	private static VideoDAO instance;
	private static final HashMap<String, Video> allVideos = new HashMap<>();//


	public synchronized VideoDAO getInstance() {
		if (instance == null) {
			instance = new VideoDAO();
		}
		return instance;
	}

	public HashMap<String, Video> getAllVideos() throws SQLException {
		String sql = "SELECT videos_id, name, path, views,date,description FROM videos;";
		if (allVideos.isEmpty()) {
			PreparedStatement st;
		
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

	public HashMap<String, Video> searchAllByString(String partOfName) throws SQLException {
		String sql = "SELECT videos_id, name, path, views,date,description FROM videos WHERE name " + "like '"
				+ partOfName + "';";
		if (allVideos.isEmpty()) {
			PreparedStatement st;
		
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

	public void addVideo(Video video, User user) throws SQLException {// TODO ADD PUBLISHER TO DB
													// ???
		Connection con = DBManager.getInstance().getConnection();
		PreparedStatement st;
		
			st = con.prepareStatement("INSERT INTO videos (name, path, views,date,description) VALUES (?,?,?,?,?);");
			st.setString(1, video.getName());
			st.setString(2, video.getName());
			st.setInt(3, video.getViews());
			// TODO st.setTimestamp(4, video.getDate());
			// st.setPublisher(.....,video.getPublisher());
			st.setString(5, video.getDescription());
			st.executeUpdate();
		

	}

	public void likeVideo(Video video, User user) throws SQLException {
		Connection con = DBManager.getInstance().getConnection();
		PreparedStatement st;
	
			st = con.prepareStatement(
					"INSERT INTO video_liked_or_disliked (users_user_id, videos_videos_id,like_or_dislike) VALUES (?,?,?);");
			st.setInt(1, video.getId());
			st.setInt(2, user.getUserID());
			// TODO LIKES OR DISLIKES ?? LIKES -1 DISLIKES -2
			st.setInt(3, 1);
			st.executeUpdate();
		
	}

	public void dislikeVideo(Video video, User user) throws SQLException {
		Connection con = DBManager.getInstance().getConnection();
		PreparedStatement st;
	
			st = con.prepareStatement(
					"INSERT INTO video_liked_or_disliked (users_user_id, videos_videos_id,like_or_dislike) VALUES (?,?,?);");
			st.setInt(1, video.getId());
			st.setInt(2, user.getUserID());
			// TODO LIKES OR DISLIKES ?? LIKES -1 DISLIKES -2
			st.setInt(3, 2);
			st.executeUpdate();
		

	}

	public void removeLikeFromVideo(Video video, User user) throws SQLException {
		Connection con = DBManager.getInstance().getConnection();
		PreparedStatement st;
		
			st = con.prepareStatement("DELETE FROM video_liked_or_disliked WHERE users_user_id=" + user.getUserID()
					+ " AND videos_videos_id=" + video.getId() + " AND like_or_dislike=1 ;");
			st.executeUpdate();
		
	}

	public void removeDislikeFromVideo(Video video, User user) {
		Connection con = DBManager.getInstance().getConnection();
		PreparedStatement st;
		try {
			st = con.prepareStatement("DELETE FROM video_liked_or_disliked WHERE users_user_id=" + user.getUserID()
					+ " AND videos_videos_id=" + video.getId() + " AND like_or_dislike=2 ;");
			st.executeUpdate();
		} catch (SQLException e) {
			System.out.println(" NOT deleted dislike");
			e.printStackTrace();
		}
	}

	public void deleteVideo(Video video, User user) throws SQLException {
		if (user.getUserID() == video.getPublisher()) {
			Connection con = DBManager.getInstance().getConnection();
			PreparedStatement st;
				st = con.prepareStatement("DELETE FROM videos WHERE videos_id=" + video.getId() + ";");
				st.executeUpdate();
		}
	}

	public void addVideoToPlayList(Video video, Playlist playlist) throws SQLException {
		Connection con = DBManager.getInstance().getConnection();
		PreparedStatement st;
		
			st = con.prepareStatement(
					"INSERT INTO playlist_videos (videos_videos_id,playlists_playlist_id) VALUES (?,?);");
			st.setInt(1, video.getId());
			st.setInt(2, playlist.getPlaylistID());
			st.executeUpdate();
	
	}

	public void removeVideoFromPlaylist(Video video, Playlist playlist) throws SQLException {
		Connection con = DBManager.getInstance().getConnection();
		PreparedStatement st;
		
			st = con.prepareStatement("DELETE FROM playlist_videos WHERE videos_videos_id= " + video.getId()
					+ " playlists_playlist_id=" + playlist.getPlaylistID() + ";");

			st.executeUpdate();
		
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
	
	
	
	public void deleteComment(Comment comment) throws SQLException{
		Connection con = DBManager.getInstance().getConnection();
		PreparedStatement st = con.prepareStatement("DELETE FROM comments WHERE comment_id= " +comment.getCommentID()+ ";");
			st.executeUpdate();
		
	}
}
