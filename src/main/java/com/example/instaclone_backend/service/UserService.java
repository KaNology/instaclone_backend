package com.example.instaclone_backend.service;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Objects;
import java.util.Optional;

import javax.xml.bind.DatatypeConverter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.example.instaclone_backend.common.ApiResponse;
import com.example.instaclone_backend.dto.ResponseDto;
import com.example.instaclone_backend.dto.user.EditUserProfileDto;
import com.example.instaclone_backend.dto.user.SigninDto;
import com.example.instaclone_backend.dto.user.SigninResponseDto;
import com.example.instaclone_backend.dto.user.SignupDto;
import com.example.instaclone_backend.dto.user.UserProfileDto;
import com.example.instaclone_backend.exception.AuthenticationFailException;
import com.example.instaclone_backend.exception.CustomException;
import com.example.instaclone_backend.exception.UserEmailAlreadyExistException;
import com.example.instaclone_backend.model.AuthenticationToken;
import com.example.instaclone_backend.model.User;
import com.example.instaclone_backend.repository.UserRepo;

@Service
public class UserService {
	@Autowired
	UserRepo userRepo;
	@Autowired
	TokenService tokenService;

	@Transactional
	public ResponseDto signup(SignupDto signupDto) {
		if (Objects.nonNull(userRepo.findByEmail(signupDto.getEmail()))) {
			throw new UserEmailAlreadyExistException("user email already exists");
		}

		String password = signupDto.getPassword();
		try {
			password = hashPassword(password);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			throw new CustomException(e.getMessage());
		}

		// default user should be "normal user"
		User user = new User(signupDto.getFirstName(), signupDto.getLastName(), signupDto.getEmail(), password,
				"normal");

		userRepo.save(user);

		final AuthenticationToken authToken = new AuthenticationToken(user);
		tokenService.saveConfirmationToken(authToken);

		ResponseDto responseDto = new ResponseDto("success", "user successfully created");
		return responseDto;
	}

	private String hashPassword(String password) throws NoSuchAlgorithmException {
		MessageDigest md = MessageDigest.getInstance("MD5");
		md.update(password.getBytes());
		byte[] digest = md.digest();
		String hash = DatatypeConverter.printHexBinary(digest).toUpperCase();
		return hash;
	}

	public SigninResponseDto signin(SigninDto signinDto) {
		User user = userRepo.findByEmail(signinDto.getEmail());
		if (Objects.isNull(user)) {
			throw new AuthenticationFailException("wrong email/password");
		}

		try {
			if (!user.getPassword().equals(hashPassword(signinDto.getPassword()))) {
				throw new AuthenticationFailException("wrong password");
			}
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}

		AuthenticationToken token = tokenService.getToken(user);

		if (Objects.isNull(token)) {
			throw new CustomException("token is not present");
		}

		// return response
		return new SigninResponseDto("success", token.getToken());
	}

	public UserProfileDto getUserProfile(Integer userId) {
		Optional<User> optionalUser = userRepo.findById(userId);

		if (optionalUser.isEmpty()) {
			throw new CustomException("user is not present");
		}

		User user = optionalUser.get();
		UserProfileDto response = new UserProfileDto(user.getAvatar(), user.getFirstName(), user.getLastName(),
				user.getEmail(), user.getPhotos().size(), user.getVideos().size(), user.getPosts().size());

		return response;
	}

	public ResponseEntity<ApiResponse> editUserProfile(EditUserProfileDto editUserDto, Integer userId) {
		Optional<User> optionalUser = userRepo.findById(userId);
		MultipartFile avatar = editUserDto.getAvatar();

		if (optionalUser.isEmpty()) {
			throw new CustomException("user is not present");
		}

		User user = optionalUser.get();

		// Check if the old password is correct
		try {
			if (!user.getPassword().equals(hashPassword(editUserDto.getOldPassword()))) {
				throw new AuthenticationFailException("wrong password");
			}
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}

		String fileName = StringUtils.cleanPath(avatar.getOriginalFilename());
		if (fileName.contains("..")) {
			throw new CustomException("not a valid file");
		}

		try {
			user.setAvatar(Base64.getEncoder().encodeToString(avatar.getBytes()));
		} catch (IOException e) {
			e.printStackTrace();
		}

		user.setFirstName(editUserDto.getFirstName());
		user.setLastName(editUserDto.getLastName());
		user.setEmail(editUserDto.getEmail());

		try {
			user.setPassword(hashPassword(editUserDto.getNewPassword()));
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}

		userRepo.save(user);
		return new ResponseEntity<>(new ApiResponse(true, "user edited"), HttpStatus.CREATED);
	}

	public void followUser(Integer toFollowId, User user) {
		Optional<User> optionalToFollow = userRepo.findById(toFollowId);

		if (optionalToFollow.isEmpty()) {
			throw new CustomException("user does not exist");
		}

		User toFollow = optionalToFollow.get();
		
		if(user.getFollowing().contains(toFollow)) {
			unfollow(user, toFollow);
		}
		else {
			follow(user, toFollow);
		}
	}

	public void follow(User user, User toFollow) {
		user.getFollowing().add(toFollow);
		toFollow.getFollowers().add(user);
	}

	public void unfollow(User user, User toFollow) {
		user.getFollowing().remove(toFollow);
		toFollow.getFollowers().remove(user);
	}

	public Boolean isFollowed(Integer userId, User currentUser) {
		Optional<User> optionalToFollow = userRepo.findById(userId);

		if (optionalToFollow.isEmpty()) {
			throw new CustomException("user does not exist");
		}

		User toFollow = optionalToFollow.get();
		
		return currentUser.getFollowing().contains(toFollow);
	}
}
