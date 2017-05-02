package com.youlose.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.sql.SQLException;

import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.boot.autoconfigure.web.ServerProperties.Session;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.youlose.dao.UserDAO;
import com.youlose.model.User;

@Controller
@MultipartConfig
public class PictureController {

	private static final String FILE_LOCATION = "D:" + File.separator + "Uploads" + File.separator + "pictures"
			+ File.separator;
	
	@RequestMapping(value="/{username}/image/{fileName}", method=RequestMethod.GET)
	@ResponseBody
	public void prepareForUpload(@PathVariable("fileName") String fileName, HttpServletResponse resp, Model model) throws IOException {
		File file = new File(FILE_LOCATION + fileName+".png");
		Files.copy(file.toPath(), resp.getOutputStream());
	}
	
	@RequestMapping(value="/{username}/changeProfilePicture", method=RequestMethod.POST)
	public String receiveUpload(@PathVariable("username") String username, @RequestParam("profilePicture") MultipartFile multiPartFile, Model model, HttpSession s) throws IOException{
		User user = (User) s.getAttribute("user");
		String type = multiPartFile.getContentType();
		type = type.substring(6);
		String imageName = user.getUserID() + "_profilePic."+type;
		File fileOnDisk = new File(FILE_LOCATION + imageName);
		Files.copy(multiPartFile.getInputStream(), fileOnDisk.toPath(), StandardCopyOption.REPLACE_EXISTING);
		try{
			UserDAO.getInstance().editProfilePicture(user, imageName);
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		s.setAttribute("filename", imageName);
		return "redirect:/"+username+"/profile";
	}
}
