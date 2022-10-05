package com.example.instaclone_backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.instaclone_backend.model.Comment;

@Repository
public interface CommentRepo extends JpaRepository<Comment, Integer>{
	List<Comment> findByPostId(Integer postId);
}
