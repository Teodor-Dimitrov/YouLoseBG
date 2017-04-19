package com.youlose.model;

import java.util.ArrayList;

public class Category {

	private int categoryID;
	private String name;
	private ArrayList<Video> videos;

	public Category() {
		this.videos = new ArrayList<>();
	}

	public int getCategoryID() {
		return categoryID;
	}

	public void setCategoryID(int categoryID) {
		this.categoryID = categoryID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
