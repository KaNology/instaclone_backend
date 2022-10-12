package com.example.instaclone_backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.instaclone_backend.dto.PhotoResponseDto;
import com.example.instaclone_backend.model.User;
import com.example.instaclone_backend.service.PhotoService;
import com.example.instaclone_backend.service.TokenService;

@RequestMapping("/photo")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class PhotoController {
	@Autowired
	PhotoService photoService;
	@Autowired
	TokenService tokenService;

	@GetMapping("/list/{userId}")
	public List<PhotoResponseDto> getAllPhoto(@PathVariable("userId") Integer userId,
			@RequestParam(name = "token", required = false) String token) {
		if (token != null) {
			// authenticate the user
			tokenService.authenticate(token);

			User user = tokenService.getUser(token);

			return photoService.getAllPhoto(userId, user);
		}

		return photoService.getAllPhoto(userId);
	}
}
