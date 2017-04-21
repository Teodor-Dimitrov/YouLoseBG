package dao;

public class CommentDAO {

	private static CommentDAO instance;

	public synchronized static CommentDAO getInstance() {
		if (instance == null) {
			instance = new CommentDAO();
		}
		return instance;
	}
}
