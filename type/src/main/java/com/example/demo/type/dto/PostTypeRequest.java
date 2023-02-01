package com.example.demo.type.dto;

import com.example.demo.type.entity.Type;
import lombok.*;

import java.util.function.Function;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class PostTypeRequest {
    String name;
    int numberOfRooms;
    int size;
    Boolean internet_access;
    public static Function<PostTypeRequest,Type> dtoToEntityMapper() {
        return request -> Type.builder()
                .name(request.getName())
                .numberOfRooms(request.getNumberOfRooms())
                .size(request.getSize())
                .internet_access(request.getInternet_access())
                .build();
    }
}