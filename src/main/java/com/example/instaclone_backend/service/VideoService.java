package com.example.instaclone_backend.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.instaclone_backend.model.Video;
import com.example.instaclone_backend.dto.VideoResponseDto;
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

	public List<VideoResponseDto> getAllVideo(Integer userId, User... user) {
		List<Video> videos = vidRepo.findByUserId(userId);
		List<VideoResponseDto> response = new ArrayList<>();

		// if guest user or not the same user
		if (Objects.isNull(user) || user[0].getId() != userId) {
			// return public photos only
			for (Video video : videos) {
				if (!video.getFile().getPost().getIsPrivate()) {
					VideoResponseDto videoResponse = new VideoResponseDto();
					videoResponse.setVideo(video.getFile().getFile());
					videoResponse.setPostId(video.getFile().getPost().getId());
					videoResponse.setUserId(userId);
					response.add(videoResponse);
				}
			}

			return response;
		}

		for (Video video : videos) {
			VideoResponseDto videoResponse = new VideoResponseDto();
			videoResponse.setVideo(video.getFile().getFile());
			videoResponse.setPostId(video.getFile().getPost().getId());
			videoResponse.setUserId(userId);
			response.add(videoResponse);
		}

		return response;
	}

}
