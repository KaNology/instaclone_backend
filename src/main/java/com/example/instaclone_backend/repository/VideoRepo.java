package com.example.instaclone_backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.instaclone_backend.model.Video;

@Repository
public interface VideoRepo extends JpaRepository<Video, Integer> {

}
