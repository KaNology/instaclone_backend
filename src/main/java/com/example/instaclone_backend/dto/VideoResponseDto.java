package com.example.instaclone_backend.dto;

public class VideoResponseDto {
	private String video;
	private Integer userId;
	private Integer postId;
	public String getVideo() {
		return video;
	}
	public void setVideo(String video) {
		this.video = video;
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
	public VideoResponseDto() {
		super();
		// TODO Auto-generated constructor stub
	}
}
