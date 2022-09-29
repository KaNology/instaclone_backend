package com.example.instaclone_backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.instaclone_backend.model.AuthenticationToken;
import com.example.instaclone_backend.model.User;

@Repository
public interface TokenRepo extends JpaRepository<AuthenticationToken, Integer>{
	AuthenticationToken findByUser(User user);
	AuthenticationToken findByToken(String token);
}
