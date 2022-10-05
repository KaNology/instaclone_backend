package com.example.instaclone_backend.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.example.instaclone_backend.dto.PostDto;
import com.example.instaclone_backend.dto.post.CommentResponseDto;
import com.example.instaclone_backend.dto.post.PostResponseDto;
import com.example.instaclone_backend.exception.CustomException;
import com.example.instaclone_backend.model.Comment;
import com.example.instaclone_backend.model.Post;
import com.example.instaclone_backend.model.User;
import com.example.instaclone_backend.repository.CommentRepo;
import com.example.instaclone_backend.repository.PostRepo;

@Service
public class PostService {
	@Autowired
	PostRepo postRepo;
	@Autowired
	CommentRepo commentRepo;

	public Post createPost(PostDto postDto, User user) {
		Post newPost = new Post();
		List<String> files = new ArrayList<String>();
		newPost.setTitle(postDto.getTitle());
		newPost.setDescription(postDto.getDescription());
		newPost.setUser(user);
		newPost.setIsPrivate(postDto.getIsPrivate());

		// add the photos/videos into the post
		for (MultipartFile file : postDto.getFiles()) {
			String fileName = StringUtils.cleanPath(file.getOriginalFilename());
			if (fileName.contains("..")) {
				throw new CustomException("not a valid file");
			}

			try {
				files.add(Base64.getEncoder().encodeToString(file.getBytes()));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		newPost.setFiles(files.toArray(new String[0]));
		postRepo.save(newPost);
		return newPost;
	}

	public List<PostResponseDto> getUserPost(Integer userId) {
		List<Post> userPosts = postRepo.findByUserId(userId);
		List<PostResponseDto> response = new ArrayList<>();

		for (Post post : userPosts) {
			PostResponseDto responseDto = new PostResponseDto();
			List<CommentResponseDto> postComments = new ArrayList<>();
			
			List<Comment> comments = commentRepo.findByPostId(post.getId());
			for (Comment comment : comments) {
				CommentResponseDto commentDto = new CommentResponseDto();
				commentDto.setContent(comment.getContent());
				commentDto.setUserId(comment.getUser().getId());
				commentDto.setUserName(comment.getUser().getFirstName() + " " + comment.getUser().getLastName());
				commentDto.setUserAvatar(comment.getUser().getAvatar());
				postComments.add(commentDto);
			}

			responseDto.setPostId(post.getId());
			responseDto.setTitle(post.getTitle());
			responseDto.setDescription(post.getDescription());
			responseDto.setFiles(post.getFiles());
			responseDto.setIsPrivate(post.getIsPrivate());
			responseDto.setComments(postComments);

			response.add(responseDto);
		}
		return response;
	}
}
