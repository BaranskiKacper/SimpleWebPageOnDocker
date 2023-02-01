package com.example.demo.file.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import com.example.demo.file.entity.File;
import com.example.demo.file.service.FileService;

import javax.annotation.PostConstruct;

@Component
public class InitializedData {

    private final FileService fileService;
    private final Environment environment;

    @Autowired
    public InitializedData(FileService fileService, Environment environment) {
        this.fileService = fileService;
        this.environment = environment;
    }

    @PostConstruct
    private synchronized void init() {
        String storage = environment.getProperty("file.storage.path");

        File domek = File.builder()
                .title("Dom")
                .author("Kacper")
                .filePath(storage + "/Dom.jpg")
                .build();

        fileService.create(domek);
    }
}
