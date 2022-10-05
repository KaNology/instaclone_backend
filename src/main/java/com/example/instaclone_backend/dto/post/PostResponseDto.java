package com.example.instaclone_backend.dto.post;

import java.util.List;

public class PostResponseDto {
	private Integer postId;
	private String title;
	private String description;
	private String[] files;
	private Boolean isPrivate;
	private List<CommentResponseDto> comments;
	
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
	public Boolean getIsPrivate() {
		return isPrivate;
	}
	public void setIsPrivate(Boolean isPrivate) {
		this.isPrivate = isPrivate;
	}
	public PostResponseDto() {
		super();
	}
}
