package com.example.instaclone_backend.service;

import java.io.IOException;
import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.example.instaclone_backend.model.Video;
import com.example.instaclone_backend.model.Post;
import com.example.instaclone_backend.model.User;
import com.example.instaclone_backend.repository.VideoRepo;

@Service
public class VideoService {
	@Autowired
	VideoRepo vidRepo;

	public void createVideo(MultipartFile video, Post post, User user) {
		Video newVideo = new Video();
		String fileName = StringUtils.cleanPath(video.getOriginalFilename());

		if (fileName.contains("..")) {
			System.out.println("not a a valid file");
		}

		try {
			newVideo.setVideo(Base64.getEncoder().encodeToString(video.getBytes()));
		} catch (IOException e) {
			e.printStackTrace();
		}
		newVideo.setUser(user);
		newVideo.setPost(post);
		vidRepo.save(newVideo);
	}
	
}
