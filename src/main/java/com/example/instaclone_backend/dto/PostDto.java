package com.example.instaclone_backend.dto;

import org.springframework.web.multipart.MultipartFile;

public class PostDto {
	private MultipartFile[] files;
	private String title;
	private String description;
	private Boolean isPrivate;
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Boolean getIsPrivate() {
		return isPrivate;
	}
	public void setIsPrivate(Boolean isPrivate) {
		this.isPrivate = isPrivate;
	}
	public MultipartFile[] getFiles() {
		return files;
	}
	public void setFiles(MultipartFile[] files) {
		this.files = files;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
}
