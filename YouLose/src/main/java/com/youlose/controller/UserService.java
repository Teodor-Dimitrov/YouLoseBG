package com.youlose.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.youlose.model.User;

@RestController
public class UserService {

	@ResponseBody
	@RequestMapping(value = "/{user}/{resultList}", method = RequestMethod.GET)
	public ArrayList<User> generateUsers(@PathVariable(value="user") String user,
			@PathVariable(value="resultList") String resultList){
		ArrayList<User> selectedUsers = new ArrayList<>();
		if(user.equals("searchresults")){
			selectedUsers = UserDAO.getInstance().searchUsers(resultList);
		}
		if(UserDAO.getInstance().getAll().contains(user)){
			selectedUsers = UserDAO.getInstance().getPlaylist(resultList);
		}
		return selectedUsers;
	}
}
