package com.example.instaclone_backend.dto.user;

import org.springframework.web.multipart.MultipartFile;

public class EditUserProfileDto {
	private MultipartFile avatar;
	private String firstName;
	private String lastName;
	private String email;
	private String oldPassword;
	private String newPassword;
	public MultipartFile getAvatar() {
		return avatar;
	}
	public void setAvatar(MultipartFile avatar) {
		this.avatar = avatar;
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getOldPassword() {
		return oldPassword;
	}
	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}
	public String getNewPassword() {
		return newPassword;
	}
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
}
