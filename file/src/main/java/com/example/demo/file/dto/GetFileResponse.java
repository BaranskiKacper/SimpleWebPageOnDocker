package com.example.demo.file.dto;

import com.example.demo.file.entity.File;
import lombok.*;

import java.util.function.Function;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class GetFileResponse {
    private Long id;
    private String title;
    private String author;

    public static Function<File, GetFileResponse> entityToDtoMapper() {
        return file -> GetFileResponse.builder()
                        .id(file.getId())
                        .title(file.getTitle())
                        .author(file.getAuthor())
                        .build();
    }
}
