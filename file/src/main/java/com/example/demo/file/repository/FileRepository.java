package com.example.demo.file.repository;

import com.example.demo.file.entity.File;
import org.springframework.data.jpa.repository.JpaRepository;

@org.springframework.stereotype.Repository
public interface FileRepository extends JpaRepository<File, Long> {
}
