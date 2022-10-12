package com.example.instaclone_backend.dto;

public class PhotoResponseDto {
	private String photo;
	private Integer userId;
	private Integer postId;
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Integer getPostId() {
		return postId;
	}
	public void setPostId(Integer postId) {
		this.postId = postId;
	}
	public PhotoResponseDto() {
		super();
		// TODO Auto-generated constructor stub
	}
}
