package com.youlose.controller;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.youlose.dao.UserDAO;

import com.youlose.model.User;

@Controller
public class UserController {

	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String showMainPage(){
		return "main";
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String showLogInForm(){
		return "login";
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String logIn(HttpServletRequest req, HttpSession s){
		String email = req.getParameter("email");
		String password = req.getParameter("password");
		if(UserDAO.getInstance().loginValid(email, password)){
			s.setAttribute("user", UserDAO.getInstance().getAllUsers().get(email));
			return "redirect:/index";
		}
		return "invalidLogin";
	}
	
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String showRegisterForm(){
		return "register";
	}
	
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String register(HttpServletRequest req, HttpSession s){
		String username = req.getParameter("username");
		String email = req.getParameter("email");
		String password = req.getParameter("password");
		String confPassword = req.getParameter("password2");
		System.out.println(username);
		System.out.println(email);
		System.out.println(password);
		System.out.println(confPassword);
		
		String msg = UserDAO.getInstance().validateRegistration(email, username, password, confPassword);
		if(msg.equals("Registration successful")){
			User newUser = new User();
			newUser.setEmail(email);
			newUser.setName(username);
			newUser.setPassword(password);
			newUser.setProfilePicture(User.DEFAULT_PROFILE_PIC);
			UserDAO.getInstance().save(newUser);
			return "main";
		}
		
		return "register";
	}
	
	
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logOut(HttpSession s){
		s.invalidate();
		return "main";
	}
	
	@RequestMapping(value = "/subscribers", method = RequestMethod.GET)
	public String getSubscribers(HttpSession s){
		boolean logged = false;
		if(logged = (Boolean) s.getAttribute("logged")){
			String user = (String) s.getAttribute("name");
			String link = "/"+user+"/subscribers";
			return "redirect:" +link;
		}
		return "login";
	}
	
	@RequestMapping(value = "/subscriptions", method = RequestMethod.GET)
	public String getSubscribtions(HttpSession s){
		boolean logged = false;
		if(logged = (Boolean) s.getAttribute("logged")){
			String user = (String) s.getAttribute("name");
			String link = "/"+user+"/subscriptions";
			return "redirect:" +link;
		}
		return "login";
	}
	
	@RequestMapping(value = "/playlist/{playlistName}", method = RequestMethod.GET)
	public String getLikedVideos(HttpSession s, 
			@PathVariable(value="playlistName") String playlistName){
		boolean logged = false;
		if(logged = (Boolean) s.getAttribute("logged")){
			String user = (String) s.getAttribute("name");
			String link = "/"+user+"/"+playlistName;
			//TODO restrict user from registering with name="playlist"
			return "redirect:" +link;
		}
		return "login";
	}
	
	@RequestMapping(value = "/profile", method = RequestMethod.GET)
	public String viewProfile(HttpSession s){
		if(s.getAttribute("user")==null){
			return "main";
		}
		
		return "profile";
	}
	
	
	@RequestMapping(value="/subscribe", method = RequestMethod.POST)
	public void subscribeUser(Model model,HttpSession session){
		User currentUser = (User) session.getAttribute("user");
		User subscribUser = null;
		subscribUser = UserDAO.getInstance().getUser((User)session.getAttribute("usernameSubscribe"));
		if(UserDAO.getInstance().subscribeUser(currentUser.getUserID(),subscribUser.getUserID())){
			System.out.println("subscribe is done");
		} else {
			return;
		}

	}
	
}
