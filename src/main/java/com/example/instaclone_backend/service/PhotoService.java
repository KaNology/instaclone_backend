package com.example.instaclone_backend.service;

import java.io.IOException;
import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.instaclone_backend.model.Photo;
import com.example.instaclone_backend.model.Post;
import com.example.instaclone_backend.model.User;
import com.example.instaclone_backend.repository.PhotoRepo;

@Service
public class PhotoService {
	@Autowired
	PhotoRepo photoRepo;

	public void createPhoto(MultipartFile photo, Post post, User user) {
		Photo newPhoto = new Photo();
		
		try {
			newPhoto.setPhoto(Base64.getEncoder().encodeToString(photo.getBytes()));
		} catch (IOException e) {
			e.printStackTrace();
		}
		newPhoto.setPost(post);
		newPhoto.setUser(user);
		photoRepo.save(newPhoto);
	}
}
