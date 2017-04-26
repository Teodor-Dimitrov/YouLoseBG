package com.youlose.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.youlose.model.Playlist;
import com.youlose.model.Video;

public class PlaylistDAO {
	private static PlaylistDAO instance;
	
	private PlaylistDAO(){
		
	}

	public synchronized static PlaylistDAO getInstance() {
		if (instance == null) {
			instance = new PlaylistDAO();
		}
		return instance;
	}
	

	public int createPlaylist(int userID, String name) {
		String sql = "insert into mydb.playlists (user_id, title) VALUES (?,?) ";
		int playlistID = 0;
		PreparedStatement ps = null;
		try {
			ps = DBManager.getInstance().getConnection().prepareStatement(sql);
			ps.setInt(1, userID);
			ps.setString(2, name);
			ps.executeUpdate();
			ResultSet rs = ps.getGeneratedKeys();
			while (rs.next()) {
				playlistID = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return playlistID;
	}
	
	public void addVideoToPlayList(int playlistID, int videoID) throws SQLException {
		String sql = "insert into mydb.playlist_videos (videos_video_playlist_id, playlists_playlist_id) VALUES (?,?)";
		PreparedStatement ps = null;
		ps = DBManager.getInstance().getConnection().prepareStatement(sql);
		ps.setInt(1, videoID);
		ps.setInt(2, playlistID);
		ps.executeUpdate();
	}
	
	public void removeVideoFromPlaylist(int videoID) throws SQLException{
		String sql = "DELEte from mydb.playlist_videos where videos_video_playlist_id = ?";
		PreparedStatement ps = null;
		ps = DBManager.getInstance().getConnection().prepareStatement(sql);
		ps.setInt(1, videoID);
		ps.executeUpdate();
	}
	
	public ArrayList<Playlist> getUserPlaylists(int userID) throws SQLException {
		String sql = "SELECT playlist_id, name, user_id FROM mydb.playlists where users_user_id = ?;";

		ArrayList<Playlist> playlists = new ArrayList<>();
		PreparedStatement ps = null;
		try {
			ps = DBManager.getInstance().getConnection().prepareStatement(sql);
			ps.setInt(1, userID);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {

				Playlist playlist = new Playlist();
				playlist.setName(rs.getString("name"));
				playlist.setUserID(rs.getInt("user_id"));
				playlist.setPlaylistID(rs.getInt("playlist_id"));
				playlists.add(playlist);
			}

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

		return playlists;
	}
	
	public ArrayList<Video> getVideosOFPlaylist(int playlistID) {

		String sql = "SELECT v.video_id, v.name, v.path, v.views, v.date, v.description"
				+ "FROM mydb.videos v join mydb.playlist_videos s"
				+ " using(video_id) where s.playlists_playlist_id = ?;";

		ArrayList<Video> videos = new ArrayList<Video>();
		PreparedStatement ps = null;
		try {
			ps = DBManager.getInstance().getConnection().prepareStatement(sql);
			ps.setInt(1, playlistID);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Video video = new Video();
				video.setDate(rs.getTimestamp("path").toLocalDateTime());
				video.setDescription(rs.getString("description"));
				video.setId(rs.getInt("video_id"));
				video.setName(rs.getString("name"));
				video.setViews(rs.getInt("views"));
				video.setPath(rs.getString("path"));
				videos.add(video);
			}

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return videos;
	}
	
	 public boolean existPlaylist(String name , int userID) throws SQLException {
			String sql = "SELECT user_id,name FROM mydb.playlist WHERE user_id=? AND name=?;";
			PreparedStatement ps = null;

				ps = DBManager.getInstance().getConnection().prepareStatement(sql);
				ps.setInt(1, userID);
				ps.setString(2, name);
				ResultSet rs = ps.executeQuery();
			
				if(rs.next()){
					return false;
				}

				return true;
				
			}
}
