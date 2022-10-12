package com.example.instaclone_backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.instaclone_backend.common.ApiResponse;
import com.example.instaclone_backend.dto.PostDto;
import com.example.instaclone_backend.dto.post.PostFeedResponseDto;
import com.example.instaclone_backend.dto.post.PostResponseDto;
import com.example.instaclone_backend.dto.post.UserPostResponseDto;
import com.example.instaclone_backend.exception.CustomException;
import com.example.instaclone_backend.model.File;
import com.example.instaclone_backend.model.Post;
import com.example.instaclone_backend.model.User;
import com.example.instaclone_backend.service.FileService;
import com.example.instaclone_backend.service.PhotoService;
import com.example.instaclone_backend.service.PostService;
import com.example.instaclone_backend.service.TokenService;
import com.example.instaclone_backend.service.VideoService;

@RequestMapping("/post")
@CrossOrigin(origins = "*", allowedHeaders = "*")
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
	@Autowired
	FileService fileService;

	@PostMapping(path = "/create")
	public ResponseEntity<ApiResponse> createPost(@ModelAttribute PostDto postDto,
			@RequestParam("token") String token) {
		// authenticate the user
		tokenService.authenticate(token);

		User user = tokenService.getUser(token);

		// create the post
		Post newPost = postService.createPost(postDto, user);

		// add the photos/videos relationship into the post and the user
		for (MultipartFile file : postDto.getFiles()) {
			String fileName = StringUtils.cleanPath(file.getOriginalFilename());
			if (fileName.contains("..")) {
				throw new CustomException("not a valid file");
			}

			if (fileName.contains(".JPG") || fileName.contains(".jpg") || fileName.contains(".jpeg") || fileName.contains(".png")) {
				File newFile = fileService.createFile(file, "image", newPost);
				photoService.createPhoto(newFile, user);
			} else if (fileName.contains(".mp4")) {
				File newFile = fileService.createFile(file, "video", newPost);
				videoService.createVideo(newFile, user);
			}
		}

		return new ResponseEntity<>(new ApiResponse(true, "post created"), HttpStatus.CREATED);
	}

	@GetMapping("/list/{userId}")
	public List<UserPostResponseDto> getAllUserPost(@PathVariable("userId") Integer userId,
			@RequestParam(name = "token", required = false) String token) {
		if (token != null) {
			// authenticate the user
			tokenService.authenticate(token);

			User currentUser = tokenService.getUser(token);

			return postService.getAllUserPost(userId, currentUser);
		}
		
		return postService.getAllUserPost(userId);
	}

	@GetMapping("/{postId}")
	public PostResponseDto getPost(@PathVariable("postId") Integer postId,
			@RequestParam(name = "token", required = false) String token) {
		if (token != null) {
			// authenticate the user
			tokenService.authenticate(token);

			User user = tokenService.getUser(token);

			return postService.getPost(postId, user);
		}

		return postService.getPost(postId);
	}

	@GetMapping("/")
	public List<PostFeedResponseDto> getAllPublicPost(@RequestParam(name = "token", required = false) String token) {

		if (token != null) {
			// authenticate the user
			tokenService.authenticate(token);

			User user = tokenService.getUser(token);

			return postService.getAllPublicPost(user);
		}

		return postService.getAllPublicPost();
	}
}
