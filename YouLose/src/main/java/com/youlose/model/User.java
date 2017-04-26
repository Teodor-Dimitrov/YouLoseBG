package com.youlose.model;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class User {

	private String email;
	private String password;
	private String name;
	private int userID;
	private String profilePicture;
	private HashMap<String, Playlist> userPlaylist;

	public User() {
		this.userPlaylist= new HashMap<>();
		this.userPlaylist.put("liked", new Playlist());
		this.userPlaylist.put("uploaded", new Playlist());
		this.userPlaylist.put("watched", new Playlist());
		this.userPlaylist.put("watchLater", new Playlist());
		this.userPlaylist.put("subsrib", new Playlist());
	
	
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

	public String getProfilePicture() {
		return profilePicture;
	}

	public void setProfilePicture(String profilePicture) {
		this.profilePicture = profilePicture;
	}

}
