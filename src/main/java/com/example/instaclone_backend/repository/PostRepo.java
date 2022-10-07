package com.example.instaclone_backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.instaclone_backend.model.Post;

@Repository
public interface PostRepo extends JpaRepository<Post, Integer> {
	List<Post> findByUserId(Integer userId);
	List<Post> findByIsPrivate(Boolean isPrivate);
}
