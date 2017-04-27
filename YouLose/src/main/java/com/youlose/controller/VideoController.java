package com.youlose.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.sql.SQLException;
import java.time.LocalDateTime;

import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
@SessionAttributes("filename")
@MultipartConfig
public class VideoController {

	private static final String FILE_LOC = "D:" + File.separator + "Uploads" + File.separator + "videos"
			+ File.separator;

	@RequestMapping(value = "/uploadVideo", method = RequestMethod.POST)
	public String uploadVideo(@RequestParam("videoFile") MultipartFile multiPartFile,
			@RequestParam("videoName") String name, @RequestParam("userId") int userID,
			@RequestParam("description") String description, HttpSession session, Model model) throws IOException {

		User user = (User) session.getAttribute("user");
		System.out.println("offf");
		File fileD = new File(FILE_LOC + name + ".mp4");
		fileD.createNewFile();
		Files.copy(multiPartFile.getInputStream(), fileD.toPath(), StandardCopyOption.REPLACE_EXISTING);
		Video video = new Video();
		String videoPath = multiPartFile.getOriginalFilename();
		video.setDescription(description);
		video.setDate(LocalDateTime.now());
		video.setName(name);
		video.setPath(FILE_LOC + name + ".mp4");
		video.setViews(1);

		try {
			VideoDAO.getInstance().addVideo(video, user);

		} catch (SQLException e) {
			System.out.println("Problem uploading video in DB");
			e.printStackTrace();
			return "error";
		}

		session.setAttribute("photo", FILE_LOC + multiPartFile.getOriginalFilename());
		model.addAttribute("filename", multiPartFile.getOriginalFilename());
		return "videoUpload";

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
