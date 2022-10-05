package com.example.instaclone_backend.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.instaclone_backend.common.ApiResponse;
import com.example.instaclone_backend.exception.CustomException;
import com.example.instaclone_backend.model.Comment;
import com.example.instaclone_backend.model.Post;
import com.example.instaclone_backend.model.User;
import com.example.instaclone_backend.repository.CommentRepo;
import com.example.instaclone_backend.repository.PostRepo;

@Service
public class CommentService {
	@Autowired
	CommentRepo commentRepo;
	@Autowired
	PostRepo postRepo;

	public ResponseEntity<ApiResponse> createComment(String content, User user, Integer postId) {
		Optional<Post> post = postRepo.findById(postId);

		if (post.isEmpty()) {
			throw new CustomException("Post does not exist");
		}

		Comment newComment = new Comment(content, post.get(), user);

		commentRepo.save(newComment);

		return new ResponseEntity<>(new ApiResponse(true, "comment created"), HttpStatus.CREATED);
	}
}
