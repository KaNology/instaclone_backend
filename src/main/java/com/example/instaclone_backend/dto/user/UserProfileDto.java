package com.example.instaclone_backend.dto.user;

public class UserProfileDto {
	private String avatar;
	private String firstName;
	private String lastName;
	private String email;
	private Integer photos;
	private Integer videos;
	private Integer posts;
	private Integer followers;
	private Integer followings;
	
	public Integer getFollowers() {
		return followers;
	}

	public void setFollowers(Integer followers) {
		this.followers = followers;
	}

	public Integer getFollowings() {
		return followings;
	}

	public void setFollowings(Integer followings) {
		this.followings = followings;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public UserProfileDto(String avatar, String firstName, String lastName, String email, Integer photos,
			Integer videos, Integer posts, Integer followers, Integer followings) {
		super();
		this.avatar = avatar;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.photos = photos;
		this.videos = videos;
		this.posts = posts;
		this.followers = followers;
		this.followings = followings;
	}

	public UserProfileDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
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

	public Integer getPosts() {
		return posts;
	}

	public void setPosts(Integer posts) {
		this.posts = posts;
	}
}
