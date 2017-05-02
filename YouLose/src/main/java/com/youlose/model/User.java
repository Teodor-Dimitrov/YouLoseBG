package com.youlose.model;

import java.io.File;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import javax.xml.bind.DatatypeConverter;

public class User {

	private String email;
	private String password;
	private String name;
	private long userID;
	private String profilePicture;
	private HashMap<String, Playlist> userPlaylist;
	public static final String DEFAULT_PROFILE_PIC = "defaultPic.png";
	private  HashMap<String, User> subscribers;

	public HashMap<String, Playlist> getUserPlaylist() {
		return userPlaylist;
	}

	public void setUserPlaylist(HashMap<String, Playlist> userPlaylist) {
		this.userPlaylist = userPlaylist;
	}

	public HashMap<String, User> getSubscribers() {
		return subscribers;
	}

	public void setSubscribers(HashMap<String, User> subscribers) {
		this.subscribers = subscribers;
	}

	public User() {
		this.userPlaylist= new HashMap<>();
		this.userPlaylist.put("liked", new Playlist());
		this.userPlaylist.put("uploaded", new Playlist());
		this.userPlaylist.put("watched", new Playlist());
		this.userPlaylist.put("watchLater", new Playlist());
		this.subscribers = new HashMap<>();
	
	
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

	public long getUserID() {
		return userID;
	}

	public void setUserID(long userID) {
		this.userID = userID;
	}

	public String getProfilePicture() {
		return profilePicture;
	}

	public void setProfilePicture(String profilePicture) {
		this.profilePicture = profilePicture;
	}

	public void addToPlaylistByName(String name, Video video){
		if(this.userPlaylist.containsKey(name)){
			this.userPlaylist.get(name).addVideoToPlaylist(video);
		}
		System.out.println("no such playlist");
	}
	public Set<String> getPlaylists(){
		return this.userPlaylist.keySet();
	}
	
	public static String hashPass(String password) throws NoSuchAlgorithmException{
		MessageDigest m = MessageDigest.getInstance("MD5");
		m.update(password.getBytes());
		byte[] dig = m.digest();
		String hashtext = DatatypeConverter.printHexBinary(dig).toLowerCase();
		
		return hashtext;
	}
	
}
