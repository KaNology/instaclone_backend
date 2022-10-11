package com.example.instaclone_backend.service;

import java.io.IOException;
import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.instaclone_backend.model.File;
import com.example.instaclone_backend.model.Post;
import com.example.instaclone_backend.repository.FileRepo;

@Service
public class FileService {
	@Autowired
	FileRepo fileRepo;

	public File createFile(MultipartFile file, String fileType, Post newPost) {
		File newFile = new File();
		
		try {
			newFile.setFile(Base64.getEncoder().encodeToString(file.getBytes()));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		newFile.setFileType(fileType);
		newFile.setPost(newPost);
		
		fileRepo.save(newFile);
		
		return newFile;
	}
}
