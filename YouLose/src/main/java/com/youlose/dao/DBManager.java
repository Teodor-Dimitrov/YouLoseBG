package com.youlose.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBManager {
	private static DBManager instance;
	private static final String DB_IP = "localhost";
	private static final String DB_PORT = "3306";
	private static final String DB_NAME = "userlose";
	private static final String DB_USER = "root";
	private static final String DB_PASS = "Sh0wt1me";
	private Connection con = null;

	private DBManager() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println("Driver not found");
		}
		try {
			String connectionString = String.format("jdbc:mysql://%s:%s/%s", DB_IP, DB_PORT, DB_NAME);
			con = DriverManager.getConnection(connectionString, DB_USER, DB_PASS);
		} catch (SQLException e) {
			System.out.println("Error conneting to db " + e.getMessage());
		}
	}

	public static synchronized DBManager getInstance() {
		if (instance == null) {
			instance = new DBManager();
		}
		return instance;
	}

	public Connection getConnection() {
		if(con == null){
		}
		return con;
	}
	
	public void closeConnection(){
		
	}

}
