package dao;

public class PlaylistDAO {
	private static PlaylistDAO instance;

	public synchronized static PlaylistDAO getInstance() {
		if (instance == null) {
			instance = new PlaylistDAO();
		}
		return instance;
	}
	
	
}
