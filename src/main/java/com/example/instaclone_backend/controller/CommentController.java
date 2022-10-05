package com.example.instaclone_backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.instaclone_backend.common.ApiResponse;
import com.example.instaclone_backend.model.User;
import com.example.instaclone_backend.service.CommentService;
import com.example.instaclone_backend.service.TokenService;

@RequestMapping("/comment")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class CommentController {
	@Autowired
	CommentService commentService;
	@Autowired
	TokenService tokenService;

	@PostMapping(path = "/create", consumes = "text/plain")
	public ResponseEntity<ApiResponse> createComment(@RequestBody String content, @RequestParam("token") String token,
			@RequestParam("postId") Integer postId) {
		// authenticate the user
		tokenService.authenticate(token);

		User user = tokenService.getUser(token);

		return commentService.createComment(content, user, postId);
	}
}
