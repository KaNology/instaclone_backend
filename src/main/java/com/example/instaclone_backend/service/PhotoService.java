package com.example.instaclone_backend.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.instaclone_backend.model.File;
import com.example.instaclone_backend.model.Photo;
import com.example.instaclone_backend.model.User;
import com.example.instaclone_backend.repository.PhotoRepo;

@Service
public class PhotoService {
	@Autowired
	PhotoRepo photoRepo;

	public void createPhoto(File newFile, User user) {
		Photo newPhoto = new Photo();
		
		newPhoto.setFile(newFile);
		newPhoto.setUser(user);
		
		photoRepo.save(newPhoto);
	}
}
