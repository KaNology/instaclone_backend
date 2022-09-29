package com.example.instaclone_backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.instaclone_backend.dto.PostDto;
import com.example.instaclone_backend.model.Post;
import com.example.instaclone_backend.model.User;
import com.example.instaclone_backend.repository.PostRepo;

@Service
public class PostService {
	@Autowired
	PostRepo postRepo;

	public Post createPost(PostDto postDto, User user) {
		Post newPost = new Post();
		newPost.setDescription(postDto.getDescription());
		newPost.setUser(user);
		newPost.setIsPrivate(postDto.getIsPrivate());
		postRepo.save(newPost);
		return newPost;
	}
}
