package com.example.instaclone_backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.instaclone_backend.model.Photo;

@Repository
public interface PhotoRepo extends JpaRepository<Photo, Integer> {
	List<Photo> findByPostId(Integer postId);
}
