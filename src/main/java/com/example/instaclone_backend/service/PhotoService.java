package com.example.instaclone_backend.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.instaclone_backend.dto.PhotoResponseDto;
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

	public List<PhotoResponseDto> getAllPhoto(Integer userId, User... user) {
		List<Photo> photos = photoRepo.findByUserId(userId);
		List<PhotoResponseDto> response = new ArrayList<>();

		// if guest user or not the same user
		if (Objects.isNull(user) || user[0].getId() != userId) {
			// return public photos only
			for (Photo photo : photos) {
				if (!photo.getFile().getPost().getIsPrivate()) {
					PhotoResponseDto photoResponse = new PhotoResponseDto();
					photoResponse.setPhoto(photo.getFile().getFile());
					photoResponse.setPostId(photo.getFile().getPost().getId());
					photoResponse.setUserId(userId);

					response.add(photoResponse);
				}
			}

			return response;
		}

		for (Photo photo : photos) {
			PhotoResponseDto photoResponse = new PhotoResponseDto();
			photoResponse.setPhoto(photo.getFile().getFile());
			photoResponse.setPostId(photo.getFile().getPost().getId());
			photoResponse.setUserId(userId);

			response.add(photoResponse);

		}

		return response;
	}
}
