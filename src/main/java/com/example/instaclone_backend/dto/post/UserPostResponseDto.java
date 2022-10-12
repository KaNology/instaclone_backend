package com.example.instaclone_backend.dto.post;

public class UserPostResponseDto {
	private Integer postId;
	private String title;
	private String thumbnail;
	private String thumbnailType;
	private Boolean isPrivate;
	public UserPostResponseDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getThumbnailType() {
		return thumbnailType;
	}
	public void setThumbnailType(String thumbnailType) {
		this.thumbnailType = thumbnailType;
	}
	public Integer getPostId() {
		return postId;
	}
	public void setPostId(Integer postId) {
		this.postId = postId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getThumbnail() {
		return thumbnail;
	}
	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}
	public Boolean getIsPrivate() {
		return isPrivate;
	}
	public void setIsPrivate(Boolean isPrivate) {
		this.isPrivate = isPrivate;
	}
}
