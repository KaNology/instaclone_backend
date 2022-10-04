package com.example.instaclone_backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.instaclone_backend.model.Video;

@Repository
public interface VideoRepo extends JpaRepository<Video, Integer> {
	List<Video> findByPostId(Integer postId);
}
