package com.example.instaclone_backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.instaclone_backend.dto.ResponseDto;
import com.example.instaclone_backend.dto.user.SigninDto;
import com.example.instaclone_backend.dto.user.SigninResponseDto;
import com.example.instaclone_backend.dto.user.SignupDto;
import com.example.instaclone_backend.service.UserService;

@RequestMapping("/user")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class UserController {
	@Autowired
	UserService userService;

	@PostMapping("/signup")
	public ResponseDto signup(@RequestBody SignupDto signupDto) {
		return userService.signup(signupDto);
	}
	
	@PostMapping("/signin")
	public SigninResponseDto signin(@RequestBody SigninDto signinDto) {
		return userService.signin(signinDto);
	}
}
