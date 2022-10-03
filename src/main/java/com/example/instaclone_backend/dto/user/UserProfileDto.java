package com.example.instaclone_backend.dto.user;

import java.util.List;

import com.example.instaclone_backend.model.Photo;
import com.example.instaclone_backend.model.Post;
import com.example.instaclone_backend.model.Video;

public class UserProfileDto {
	private String avatar;
	private String firstName;
	private String lastName;
	private String email;
	private List<Photo> photos;
	private List<Video> videos;
	private List<Post> posts;
	
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

	public List<Post> getPosts() {
		return posts;
	}

	public void setPosts(List<Post> posts) {
		this.posts = posts;
	}

	public UserProfileDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public UserProfileDto(String firstName, String lastName, List<Photo> photos, List<Video> videos) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.photos = photos;
		this.videos = videos;
	}

	public UserProfileDto(String avatar, String firstName, String lastName, String email,
			List<Photo> photos, List<Video> videos, List<Post> posts) {
		super();
		this.avatar = avatar;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.photos = photos;
		this.videos = videos;
		this.posts = posts;
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
	public List<Photo> getPhotos() {
		return photos;
	}
	public void setPhotos(List<Photo> photos) {
		this.photos = photos;
	}
	public List<Video> getVideos() {
		return videos;
	}
	public void setVideos(List<Video> videos) {
		this.videos = videos;
	}
}
