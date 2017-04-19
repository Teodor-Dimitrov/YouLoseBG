package com.youlose.controller;

import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.youlose.model.Playlist;

@RestController
public class PlaylistService {

	@ResponseBody
	@RequestMapping(value = "/{user}/{playlist}", method = RequestMethod.GET)
	public Playlist generatePlaylist(HttpSession s){
		Playlist playlist=null;
		return playlist;
	}
}
