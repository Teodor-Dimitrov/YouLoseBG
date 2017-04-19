package com.youlose.model;

import java.util.ArrayList;

public class Playlist {

	private int playlistID;
	private int userID;
	private String name;
	private ArrayList<Video> videos;

	public Playlist() {
		this.videos = new ArrayList<>();
	}

	public int getPlaylistID() {
		return playlistID;
	}

	public void setPlaylistID(int playlistID) {
		this.playlistID = playlistID;
	}

	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
