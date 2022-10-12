package com.example.instaclone_backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.instaclone_backend.dto.VideoResponseDto;
import com.example.instaclone_backend.model.User;
import com.example.instaclone_backend.service.TokenService;
import com.example.instaclone_backend.service.VideoService;

@RequestMapping("/video")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class VideoController {
	@Autowired
	VideoService videoService;
	@Autowired
	TokenService tokenService;
	
	@GetMapping("/list/{userId}")
	public List<VideoResponseDto> getAllVideo(@PathVariable("userId") Integer userId,
			@RequestParam(name = "token", required = false) String token) {
		if (token != null) {
			// authenticate the user
			tokenService.authenticate(token);

			User user = tokenService.getUser(token);

			return videoService.getAllVideo(userId, user);
		}

		return videoService.getAllVideo(userId);
	}
}
