package com.example.demo.file.dto;

import lombok.*;

import java.util.Collection;
import java.util.List;
import java.util.function.Function;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class GetFilesResponse {

    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    @ToString
    @EqualsAndHashCode
    public static class File {
        private Long id;

        private String title;

        private String author;
    }

    @Singular
    private List<File> files;

    public static Function<Collection<com.example.demo.file.entity.File>, GetFilesResponse> entityToDtoMapper() {
        return files -> {
            GetFilesResponseBuilder response = GetFilesResponse.builder();
            files.stream()
                    .map(file -> File.builder()
                            .id(file.getId())
                            .title(file.getTitle())
                            .author(file.getAuthor())
                            .build())
                    .forEach(response::file);
            return response.build();
        };
    }
}
