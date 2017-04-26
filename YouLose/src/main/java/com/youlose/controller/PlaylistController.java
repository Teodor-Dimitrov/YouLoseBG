package com.youlose.controller;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.youlose.dao.PlaylistDAO;
import com.youlose.model.User;
import com.youlose.model.Video;

@Controller
public class PlaylistController {

	
	@RequestMapping(value = "/addPlaylist", method = RequestMethod.POST)
	public String addPlaylist(
		
		@RequestParam(value = "description") String name,
		HttpServletRequest request, HttpSession session) {
		User user = (User)session.getAttribute("user");
		Video video = (Video)session.getAttribute("videoToPlaylist");
		if (validatePlaylist(name, request, session)) {
			try {
				int playlist_id = PlaylistDAO.getInstance().createPlaylist(2, name);			
				PlaylistDAO.getInstance().addVideoToPlayList(playlist_id, video.getId());
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
		}
		return "videoPlay";
         
	}
	
	
	@RequestMapping(value="/addVideoToPlaylist", method = RequestMethod.POST)
	public void addVideoToPlaylist(Model model,HttpSession session,
			@RequestParam(value = "playlistId") int playlistID) {
		System.out.println("video added to playlist " + playlistID);
		User currentUser = (User) session.getAttribute("user");
		Video video = (Video)session.getAttribute("videoToPlaylist");
		try {
			PlaylistDAO.getInstance().addVideoToPlayList(playlistID,video.getId());
			} catch (SQLException e) {
			System.out.println("Error while adding to playlist");
			System.out.println(e.getMessage());
		}
	}
	
	@ResponseBody
	@RequestMapping(value="/validatePlaylist", method = RequestMethod.POST)
	public boolean validatePlaylist(
			
			@RequestParam(value = "name") String name,
			HttpServletRequest request, HttpSession session){
		User user = (User)session.getAttribute("user");
		System.out.println(name);
		
		boolean isValid = false;
		try {
			isValid = PlaylistDAO.getInstance().existPlaylist(name, user.getUserID());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return isValid;
	}
	
	@RequestMapping(value = "/playlistVideos", method = RequestMethod.GET)
	public String getPlaylist(
		@PathVariable(value = "playlistId") int playlistID,Model model,
		HttpServletRequest request, HttpSession session) {
		User user = (User)session.getAttribute("user");
		System.out.println(user);
		ArrayList<Video> videos = PlaylistDAO.getInstance().getVideosOFPlaylist(playlistID);
		model.addAttribute("videosInPlaylist", videos);
		return "playlistVideos";
         
	}
	
	
}
