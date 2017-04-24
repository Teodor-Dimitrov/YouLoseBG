package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Playlist;

public class PlaylistDAO {
	private static PlaylistDAO instance;
	
	private PlaylistDAO{
		
	}

	public synchronized static PlaylistDAO getInstance() {
		if (instance == null) {
			instance = new PlaylistDAO();
		}
		return instance;
	}
	

	public int createPlaylist(int userID, String name) {
		String sql = "insert into mydb.playlists (user_id, title) VALUES (?,?,?) ";
		int playlistID = 0;
		PreparedStatement ps = null;
		try {
			ps = DBManager.getInstance().getConnection().prepareStatement(sql);
			ps.setInt(1, userID);
			ps.setString(2, name);
			ps.executeUpdate();
			ResultSet rs = ps();
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
}
