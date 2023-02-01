package com.example.demo.file.service;

import com.example.demo.file.entity.File;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import com.example.demo.file.repository.ContentRepository;
import com.example.demo.file.repository.FileRepository;

import java.io.IOException;
import java.util.List;
import java.util.Optional;


@Service
public class FileService {
    private final FileRepository fileRepository;

    private final ContentRepository contentRepository;


    @Autowired
    public FileService(FileRepository fileRepository, ContentRepository contentRepository) {
        this.fileRepository = fileRepository;
        this.contentRepository = contentRepository;
    }

    public Optional<File> find(Long id) {
        return fileRepository.findById(id);
    }

    public List<File> findAll() {
        return fileRepository.findAll();
    }

    @Transactional
    public Optional<File> create(File file, MultipartFile content) {
        try {
            String filePath = contentRepository.save(file.getTitle(), content);
            file.setFilePath(filePath);
        } catch (IOException e) {
            e.printStackTrace();
            return Optional.empty();
        }
        return Optional.of(fileRepository.save(file));
    }

    @Transactional
    public File create(File file) {
        return fileRepository.save(file);
    }
}
