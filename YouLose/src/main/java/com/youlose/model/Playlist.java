package com.youlose.model;

import java.util.ArrayList;

public class Playlist {

	private long playlistID;
	private long userID;
	private String name;
	private ArrayList<Video> videos;

	public Playlist() {
		this.videos = new ArrayList<>();
	}

	public long getPlaylistID() {
		return playlistID;
	}

	public void setPlaylistID(long playlistID) {
		this.playlistID = playlistID;
	}

	public long getUserID() {
		return userID;
	}

	public void setUserID(long userID) {
		this.userID = userID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void addVideoToPlaylist(Video video){
		videos.add(video);
	}
}
