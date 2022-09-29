package com.example.instaclone_backend.dto;

import org.springframework.web.multipart.MultipartFile;

public class PostDto {
	private MultipartFile[] photos;
	private MultipartFile[] videos;
	private String description;
	private Boolean isPrivate;
	
	public Boolean getIsPrivate() {
		return isPrivate;
	}
	public void setIsPrivate(Boolean isPrivate) {
		this.isPrivate = isPrivate;
	}
	public MultipartFile[] getPhotos() {
		return photos;
	}
	public void setPhotos(MultipartFile[] photos) {
		this.photos = photos;
	}
	public MultipartFile[] getVideos() {
		return videos;
	}
	public void setVideos(MultipartFile[] videos) {
		this.videos = videos;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
}
