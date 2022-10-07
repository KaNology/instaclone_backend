package com.example.instaclone_backend.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.example.instaclone_backend.dto.PostDto;
import com.example.instaclone_backend.dto.post.CommentResponseDto;
import com.example.instaclone_backend.dto.post.PostFeedResponseDto;
import com.example.instaclone_backend.dto.post.PostResponseDto;
import com.example.instaclone_backend.dto.post.UserPostResponseDto;
import com.example.instaclone_backend.exception.CustomException;
import com.example.instaclone_backend.model.Comment;
import com.example.instaclone_backend.model.Like;
import com.example.instaclone_backend.model.Post;
import com.example.instaclone_backend.model.User;
import com.example.instaclone_backend.repository.CommentRepo;
import com.example.instaclone_backend.repository.LikeRepo;
import com.example.instaclone_backend.repository.PostRepo;

@Service
public class PostService {
	@Autowired
	PostRepo postRepo;
	@Autowired
	CommentRepo commentRepo;
	@Autowired
	LikeRepo likeRepo;

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

	public List<UserPostResponseDto> getAllUserPost(Integer userId) {
		List<Post> userPosts = postRepo.findByUserId(userId);
		List<UserPostResponseDto> response = new ArrayList<>();

		for (Post post : userPosts) {
			UserPostResponseDto responseDto = new UserPostResponseDto();

			responseDto.setPostId(post.getId());
			responseDto.setTitle(post.getTitle());
			responseDto.setThumbnail(post.getFiles()[0]);
			responseDto.setIsPrivate(post.getIsPrivate());

			response.add(responseDto);
		}
		return response;
	}

	public PostResponseDto getPost(Integer postId, User... user) {
		Optional<Post> optionalPost = postRepo.findById(postId);

		if (optionalPost.isEmpty()) {
			throw new CustomException("Post does not exist");
		}

		Post post = optionalPost.get();
		PostResponseDto response = new PostResponseDto();

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

		List<Like> likes = likeRepo.findByPostId(post.getId());

		response.setPostId(postId);
		response.setTitle(post.getTitle());
		response.setDescription(post.getDescription());
		response.setComments(postComments);
		response.setFiles(post.getFiles());
		response.setNumLikes(likes.size());
		response.setIsLiked(
			Objects.nonNull(likeRepo.findByUserIdAndPostId(user[0].getId(), postId))
		);

		return response;
	}

	public List<PostFeedResponseDto> getAllPublicPost(User...user) {
		// TODO Auto-generated method stub
		List<Post> publicPosts = postRepo.findByIsPrivate(false);
		List<PostFeedResponseDto> response = new ArrayList<>();
		
		for(Post post : publicPosts) {
			PostFeedResponseDto responseDto = new PostFeedResponseDto();
			List<Like> likes = likeRepo.findByPostId(post.getId());
			
			responseDto.setPostId(post.getId());
			responseDto.setTitle(post.getTitle());
			responseDto.setDescription(post.getDescription());
			
			responseDto.setThumbnail(post.getFiles()[0]);
			responseDto.setNumLikes(likes.size());
			responseDto.setIsLiked(Objects.nonNull(likeRepo.findByUserIdAndPostId(user[0].getId(), post.getId())));
			
			responseDto.setUserId(post.getUser().getId());
			responseDto.setUserName(post.getUser().getFirstName() + " " + post.getUser().getLastName());
			responseDto.setUserAvatar(post.getUser().getAvatar());
			responseDto.setCreatedDate(post.getCreatedDate());
			
			response.add(responseDto);
		}
		
		return response;
	}
}
