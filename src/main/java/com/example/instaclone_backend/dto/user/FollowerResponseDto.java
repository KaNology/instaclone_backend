package com.example.instaclone_backend.dto.user;

public class FollowerResponseDto {
	private Integer userId;
	private String avatar;
	private String username;
	private Integer photos;
	private Integer videos;
	private Boolean isFollowed;
	public FollowerResponseDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Boolean getIsFollowed() {
		return isFollowed;
	}
	public void setIsFollowed(Boolean isFollowed) {
		this.isFollowed = isFollowed;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getAvatar() {
		return avatar;
	}
	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public Integer getPhotos() {
		return photos;
	}
	public void setPhotos(Integer photos) {
		this.photos = photos;
	}
	public Integer getVideos() {
		return videos;
	}
	public void setVideos(Integer videos) {
		this.videos = videos;
	}
}
