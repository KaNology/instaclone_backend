package com.example.instaclone_backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.instaclone_backend.model.User;

@Repository
public interface UserRepo extends JpaRepository<User, Integer> {
	User findByEmail(String email);
}
