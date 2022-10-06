package com.example.instaclone_backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.instaclone_backend.model.Like;

@Repository
public interface LikeRepo extends JpaRepository<Like, Integer> {
	Like findByUserIdAndPostId(Integer userId, Integer postId);
	
	List<Like> findByPostId(Integer postId);
}
