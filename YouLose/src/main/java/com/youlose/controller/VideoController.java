package com.youlose.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.HashMap;

import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;

import com.youlose.dao.CommentDAO;
import com.youlose.dao.VideoDAO;
import com.youlose.model.User;
import com.youlose.model.Video;

@Controller
@MultipartConfig
public class VideoController {

	private static final String FILE_LOC = "D:" + File.separator + "Uploads" + File.separator + "videos"
			+ File.separator;
	private String openedVid;

	@RequestMapping(value = "main", method = RequestMethod.POST)
	public String uploadVideo(@RequestParam("video") MultipartFile multiPartFile,
			@RequestParam("videoName") String name,
			@RequestParam("description") String description,
			HttpSession session,
			Model model) throws IOException {
		User user = (User) session.getAttribute("user");
		if (user!=null) {			
		openedVid = user.getUserID() + "_" + name +".mp4";
		File fileD = new File(FILE_LOC + openedVid);
		fileD.createNewFile();
		Files.copy(multiPartFile.getInputStream(), fileD.toPath(), StandardCopyOption.REPLACE_EXISTING);
		Video video = new Video();
		//String videoPath = multiPartFile.getOriginalFilename();
		video.setDescription(description);
		video.setDate(LocalDateTime.now());
		video.setName(name);
		video.setPath(openedVid);
		video.setViews(1);
		try {
			VideoDAO.getInstance().addVideo(video, user);

		} catch (SQLException e) {
			System.out.println("Problem uploading video in DB");
			e.printStackTrace();
			return "errorPage";
		}

		session.setAttribute("videoPath", openedVid);
		model.addAttribute("video", video);
		System.out.println(openedVid);
		HashMap<String, Video> allVideos;
		try {
			allVideos = VideoDAO.getInstance().getAllVideos();
			model.addAttribute("allVideos", allVideos);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "videoPlay";
		}else{
			return "main";
		}
	}
	
	@RequestMapping(value = "video/{videoPath}", method = RequestMethod.GET)
	@ResponseBody
	public void playVideo(@PathVariable("videoPath") String fileName, HttpServletResponse resp, Model model, HttpSession session) throws IOException{
		User user =(User) session.getAttribute("user");
		String video = (String) session.getAttribute("videoPath");
		File file = new File(FILE_LOC + video);
		Files.copy(file.toPath(), resp.getOutputStream());
	}
	
	
	@RequestMapping(value = "openVideo", method = RequestMethod.GET)
	public String openVideo(HttpServletRequest req, HttpSession s){
		String videoPath = req.getParameter("videoPath");
		s.setAttribute("videoPath", videoPath);
		System.out.println(videoPath);
		return "videoPlay";
	}
	

	@ResponseBody
	@RequestMapping(value = "/likeVideo", method = RequestMethod.POST)
	public void likeVideo(Model model, HttpSession session, @RequestParam(value = "videoID") int videoID) {

		User user = (User) session.getAttribute("user");
		try {
			VideoDAO.getInstance().likeVideo(videoID, user.getUserID());
			System.out.println("Opaaa");
		} catch (SQLException e) {
			System.out.println("cant like to db");
			System.out.println(e.getMessage());
		}
	}

	@ResponseBody
	@RequestMapping(value = "/dislikeVideo", method = RequestMethod.POST)
	public void dislikeVideo(Model model, HttpSession session, @RequestParam(value = "videoID") int videoID) {

		User user = (User) session.getAttribute("user");
		try {
			VideoDAO.getInstance().dislikeVideo(videoID, user.getUserID());
			System.out.println("dislike videooooooooooooo is doneee");

		} catch (SQLException e) {
			System.out.println("cant dislike to db");
			System.out.println(e.getMessage());
		}
	}

}
