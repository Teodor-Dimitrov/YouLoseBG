package com.youlose.model;

import java.awt.List;
import java.io.File;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;

public class Video {

	private int id;
	private String name;
	private File video;
	private int views;
	private int likes;
	private int dislikes;
	private ArrayList<Category> cats;
	private LocalDateTime date;
	private String path;
	private String description;
	/// taka si mislq ne sam sig i v db go nqma  ,noooo TODO
	private int publisher;
	
	public Video() {
		
	}


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public File getVideo() {
		return video;
	}

	public void setVideo(File video) {
		this.video = video;
	}

	public int getViews() {
		return views;
	}

	public void setViews(int views) {
		this.views = views;
	}

	public int getLikes() {
		return likes;
	}

	public void setLikes(int likes) {
		this.likes = likes;
	}

	public int getDislikes() {
		return dislikes;
	}

	public void setDislikes(int dislikes) {
		this.dislikes = dislikes;
	}

	public LocalDateTime getDate() {
		return date;
	}

	public void setDate(LocalDateTime date) {
		this.date = date;
	}
	
	public void setPath(String path){
		this.path= path;
	}
	
	public void setDescription(String description){
		this.description = description;
	}
	
	public void setPublisher(int publisher){
		this.publisher= publisher;
	}

	public int getPublisher(){
		return this.publisher;
	}
	
	public String getDescription() {
		return description;
	}
}
