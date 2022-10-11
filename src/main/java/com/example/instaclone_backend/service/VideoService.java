package com.example.instaclone_backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.instaclone_backend.model.Video;
import com.example.instaclone_backend.model.File;
import com.example.instaclone_backend.model.User;
import com.example.instaclone_backend.repository.VideoRepo;

@Service
public class VideoService {
	@Autowired
	VideoRepo vidRepo;

	public void createVideo(File newFile, User user) {
		Video newVideo = new Video();
		
		newVideo.setFile(newFile);
		newVideo.setUser(user);
		
		vidRepo.save(newVideo);
	}
	
}
