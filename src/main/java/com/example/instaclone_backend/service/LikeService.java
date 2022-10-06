package com.example.instaclone_backend.service;

import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.instaclone_backend.common.ApiResponse;
import com.example.instaclone_backend.exception.CustomException;
import com.example.instaclone_backend.model.Like;
import com.example.instaclone_backend.model.Post;
import com.example.instaclone_backend.model.User;
import com.example.instaclone_backend.repository.LikeRepo;
import com.example.instaclone_backend.repository.PostRepo;

@Service
public class LikeService {
	@Autowired
	LikeRepo likeRepo;
	@Autowired
	PostRepo postRepo;

	public ResponseEntity<ApiResponse> likePost(User user, Integer postId) {
		Optional<Post> post = postRepo.findById(postId);

		if (post.isEmpty()) {
			throw new CustomException("Post does not exist");
		}
		
		Like optionalLike = likeRepo.findByUserIdAndPostId(user.getId(), postId);
		
		if (Objects.isNull(optionalLike)) {
			createLike(post.get(), user);
			
			return new ResponseEntity<>(new ApiResponse(true, "post liked"), HttpStatus.CREATED);
		}
		else {
			deleteLike(optionalLike);
			
			return new ResponseEntity<>(new ApiResponse(true, "post disliked"), HttpStatus.CREATED);
		}
	}

	public void createLike(Post post, User user) {
		Like newLike = new Like(post, user);

		likeRepo.save(newLike);
	}
	
	public void deleteLike(Like like) {
		likeRepo.delete(like);
	}
}
