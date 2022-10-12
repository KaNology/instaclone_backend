package com.example.instaclone_backend.dto.post;

import java.util.List;

public class PostResponseDto {
	private Integer postId;
	private String title;
	private String description;
	private String[] files;
	private String[] fileType;
	private List<CommentResponseDto> comments;
	private Boolean isLiked;
	private Integer numLikes;
	
	private Integer userId;
	private String userName;
	private String userAvatar;
	
	public String[] getFileType() {
		return fileType;
	}
	public void setFileType(String[] fileType) {
		this.fileType = fileType;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserAvatar() {
		return userAvatar;
	}
	public void setUserAvatar(String userAvatar) {
		this.userAvatar = userAvatar;
	}
	public Boolean getIsLiked() {
		return isLiked;
	}
	public void setIsLiked(Boolean isLiked) {
		this.isLiked = isLiked;
	}
	public Integer getNumLikes() {
		return numLikes;
	}
	public void setNumLikes(Integer numLikes) {
		this.numLikes = numLikes;
	}
	public Integer getPostId() {
		return postId;
	}
	public void setPostId(Integer postId) {
		this.postId = postId;
	}
	public List<CommentResponseDto> getComments() {
		return comments;
	}
	public void setComments(List<CommentResponseDto> comments) {
		this.comments = comments;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String[] getFiles() {
		return files;
	}
	public void setFiles(String[] files) {
		this.files = files;
	}
	public PostResponseDto() {
		super();
	}
}
