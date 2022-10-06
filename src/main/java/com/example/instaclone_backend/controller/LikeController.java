package com.example.instaclone_backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.instaclone_backend.common.ApiResponse;
import com.example.instaclone_backend.model.User;
import com.example.instaclone_backend.service.LikeService;
import com.example.instaclone_backend.service.TokenService;

@RequestMapping("/like")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class LikeController {
	@Autowired
	LikeService likeService;
	@Autowired
	TokenService tokenService;

	@PostMapping("/likePost")
	public ResponseEntity<ApiResponse> likePost(@RequestParam("token") String token,
			@RequestParam("postId") Integer postId) {
		// authenticate the user
		tokenService.authenticate(token);

		User user = tokenService.getUser(token);
		
		return likeService.likePost(user, postId);
	}
}
