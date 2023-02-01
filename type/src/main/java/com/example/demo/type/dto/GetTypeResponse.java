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
public class GetTypeResponse {

    String name;
    int numberOfRooms;
    int size;
    Boolean internet_access;

    public static Function<Type,GetTypeResponse> entityToDtoMapper() {
        return type -> GetTypeResponse.builder()
                .name(type.getName())
                .numberOfRooms(type.getNumberOfRooms())
                .size(type.getSize())
                .internet_access(type.getInternet_access())
                .build();
    }
}