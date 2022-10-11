package com.example.instaclone_backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.instaclone_backend.model.File;

@Repository
public interface FileRepo extends JpaRepository<File, Integer> {

}
