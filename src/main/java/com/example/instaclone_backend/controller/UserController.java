package com.example.instaclone_backend.controller;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.instaclone_backend.common.ApiResponse;
import com.example.instaclone_backend.dto.ResponseDto;
import com.example.instaclone_backend.dto.user.EditUserProfileDto;
import com.example.instaclone_backend.dto.user.FollowerResponseDto;
import com.example.instaclone_backend.dto.user.SigninDto;
import com.example.instaclone_backend.dto.user.SigninResponseDto;
import com.example.instaclone_backend.dto.user.SignupDto;
import com.example.instaclone_backend.dto.user.UserProfileDto;
import com.example.instaclone_backend.exception.CustomException;
import com.example.instaclone_backend.model.User;
import com.example.instaclone_backend.service.TokenService;
import com.example.instaclone_backend.service.UserService;

@RequestMapping("/user")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class UserController {
	@Autowired
	UserService userService;
	@Autowired
	TokenService tokenService;

	@PostMapping("/signup")
	public ResponseDto signup(@RequestBody SignupDto signupDto) {
		return userService.signup(signupDto);
	}

	@PostMapping("/signin")
	public SigninResponseDto signin(@RequestBody SigninDto signinDto) {
		return userService.signin(signinDto);
	}

	@GetMapping("/")
	public ResponseEntity<Integer> getUserIdByToken(@RequestParam("token") String token) {
		tokenService.authenticate(token);

		return new ResponseEntity<>(tokenService.getUser(token).getId(), HttpStatus.OK);
	}

	@GetMapping("/profile/{userId}")
	public UserProfileDto getUserProfile(@PathVariable("userId") Integer userId) {
		return userService.getUserProfile(userId);
	}

//	https://stackoverflow.com/questions/60874967/java-spring-content-type-multipart-form-databoundary-charset-utf-8-not-supp
	@PostMapping(path = "/profile/{userId}", consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
	public ResponseEntity<ApiResponse> editUserProfile(@ModelAttribute EditUserProfileDto editUserDto,
			@PathVariable("userId") Integer userId, @RequestParam("token") String token) {
		// authenticate the user
		tokenService.authenticate(token);

		User user = tokenService.getUser(token);

		if (user.getId() != userId) {
			throw new CustomException("not the same user");
		}

		return userService.editUserProfile(editUserDto, userId);
	}

	@Transactional
	@PostMapping("/follow/{userId}")
	public void followUser(@PathVariable("userId") Integer userId, @RequestParam("token") String token) {
		// authenticate the user
		tokenService.authenticate(token);

		User user = tokenService.getUser(token);

		if (user.getId() == userId) {
			throw new CustomException("cannot follow yourself");
		}

		userService.followUser(userId, user);
	}

	@Transactional
	@GetMapping("/isFollowed/{userId}")
	public Boolean isFollowed(@PathVariable("userId") Integer userId, @RequestParam("token") String token) {
		// authenticate the user
		tokenService.authenticate(token);

		User currentUser = tokenService.getUser(token);

		return userService.isFollowed(userId, currentUser);
	}

	@GetMapping("/follower/{userId}")
	public List<FollowerResponseDto> getFollowers(@PathVariable("userId") Integer userId,
			@RequestParam(name = "token", required = false) String token) {
		if (token != null) {
			// authenticate the user
			tokenService.authenticate(token);

			User currentUser = tokenService.getUser(token);

			return userService.getFollowers(userId, currentUser);
		}

		return userService.getFollowers(userId);
	}

	@GetMapping("/following/{userId}")
	public List<FollowerResponseDto> getFollowings(@PathVariable("userId") Integer userId,
			@RequestParam(name = "token", required = false) String token) {
		if (token != null) {
			// authenticate the user
			tokenService.authenticate(token);

			User currentUser = tokenService.getUser(token);

			return userService.getFollowings(userId, currentUser);
		}

		return userService.getFollowings(userId);
	}
}
