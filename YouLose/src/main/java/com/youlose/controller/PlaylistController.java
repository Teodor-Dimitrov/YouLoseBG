package com.youlose.controller;

import java.sql.SQLException;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.youlose.dao.PlaylistDAO;
import com.youlose.model.User;
import com.youlose.model.Video;

@Controller
public class PlaylistController {

	
	
	@RequestMapping(value="/addVideoToPlaylist", method = RequestMethod.POST)
	public void addVideoToPlaylist(Model model,HttpSession session,
			@RequestParam(value = "playlistId") int playlistID) {
		System.out.println("video added to playlist " + playlistID);
		User currentUser = (User) session.getAttribute("user");
		Video video = (Video)session.getAttribute("videoToPlaylist");
		try {
			PlaylistDAO.getInstance().addVideoToPlayList(playlistID,video.getId());
			System.out.println("added");
		} catch (SQLException e) {
			System.out.println("Cant add to playlist");
		}
	}
}
