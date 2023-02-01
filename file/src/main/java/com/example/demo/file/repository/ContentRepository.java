package com.example.demo.file.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

@Component
public class ContentRepository {
    private final File storage;

    @Autowired
    public ContentRepository(Environment environment) {
        this.storage = new File(environment.getProperty("file.storage.path"));
    }

    public String save(String title, MultipartFile content) throws IOException {
        byte[] bytes = content.getBytes();

        String location = storage.getPath() + "/" + title + ".jpg";
        try (FileOutputStream fos = new FileOutputStream(location)) {
            fos.write(bytes);
        }

        return location;
    }
}
