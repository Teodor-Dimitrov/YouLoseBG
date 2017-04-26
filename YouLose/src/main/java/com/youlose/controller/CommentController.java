package com.youlose.controller;

import java.sql.SQLException;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.youlose.dao.CommentDAO;
import com.youlose.model.User;
import com.youlose.model.Video;

@Controller
public class CommentController {

	@ResponseBody
	@RequestMapping(value = "/comment", method = RequestMethod.POST)
	public void comment(Model model, HttpSession session, @RequestParam(value = "comment") String comment) {
		User user = (User) session.getAttribute("currentUser");
		Video video = (Video) session.getAttribute("videoToPlaylist");
		CommentDAO.getInstance().addComment(comment, user.getUserID(), video.getId());
		System.out.println("ima komentarrrrrrrrrrrrrr");
	}

	@ResponseBody
	@RequestMapping(value = "/likeComment", method = RequestMethod.POST)
	public void likeComment(Model model, HttpSession session, @RequestParam(value = "commentId") int commentId) {

		User user = (User) session.getAttribute("currentUser");
		try {
			CommentDAO.getInstance().likeComment(user.getUserID(), commentId);
			System.out.println("Opaaa");
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	@ResponseBody
	@RequestMapping(value = "/dislikeComment", method = RequestMethod.POST)
	public void dislikeComment(Model model, HttpSession session,
			@RequestParam(value = "commentId") int commentId) {

		User user = (User) session.getAttribute("currentUser");
		Video video = (Video) session.getAttribute("videoToPlaylist");
		try {
			CommentDAO.getInstance().dislikeComment(user.getUserID(), commentId);
			System.out.println("dislike commenta");

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
}
