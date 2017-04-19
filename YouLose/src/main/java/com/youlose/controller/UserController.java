package com.youlose.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class UserController {

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String showLogInForm(){
		
		
		return "login";
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String logIn(HttpServletRequest req, HttpSession s){
		String username = (String) req.getAttribute("name");
		String password = (String) req.getAttribute("password");
		if(UserDAO.login(username, password)){
			s.setAttribute("user", username);
			s.setAttribute("logged", true);
			return "main";
		}
		return "invalidLogin";
	}
	
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String showRegisterForm(){
		
		
		return "register";
	}
	
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String register(HttpServletRequest req, HttpSession s){
		String username = (String) req.getAttribute("name");
		String password = (String) req.getAttribute("password");
		if(UserDAO.login(username, password)){
			s.setAttribute("user", username);
			s.setAttribute("logged", true);
			return "main";
		}
		return "register";
}
