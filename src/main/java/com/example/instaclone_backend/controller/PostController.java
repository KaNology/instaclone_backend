package com.example.instaclone_backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.instaclone_backend.common.ApiResponse;
import com.example.instaclone_backend.dto.PostDto;
import com.example.instaclone_backend.model.Post;
import com.example.instaclone_backend.model.User;
import com.example.instaclone_backend.service.PhotoService;
import com.example.instaclone_backend.service.PostService;
import com.example.instaclone_backend.service.TokenService;
import com.example.instaclone_backend.service.VideoService;

@RequestMapping("/post")
@RestController
public class PostController {
	@Autowired
	PostService postService;
	@Autowired
	PhotoService photoService;
	@Autowired
	VideoService videoService;
	@Autowired
	TokenService tokenService;

	@PostMapping("/create")
	public ResponseEntity<ApiResponse> createPost(@RequestBody PostDto postDto, @RequestParam("token") String token) {
		// authenticate the user
		tokenService.authenticate(token);

		User user = tokenService.getUser(token);

		// create the post
		Post newPost = postService.createPost(postDto, user);

		// add the photos into the post
		for (MultipartFile photo : postDto.getPhotos()) {
			photoService.createPhoto(photo, newPost, user);
		}

		// add the videos into the post
		for (MultipartFile video : postDto.getVideos()) {
			videoService.createVideo(video, newPost, user);
		}

		return new ResponseEntity<>(new ApiResponse(true, "post created"), HttpStatus.CREATED);
	}
}
