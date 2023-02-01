package com.example.demo.type.dto;

import com.example.demo.type.entity.Type;
import lombok.*;

import java.util.function.BiFunction;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class PutTypeRequest {
    int size;
    int numberOfRooms;
    Boolean internet_access;
    public static BiFunction<Type,PutTypeRequest,Type> dtoToEntityUpdater() {
        return (type, request) -> {
            type.setSize(request.getSize());
            type.setNumberOfRooms(request.getNumberOfRooms());
            type.setInternet_access(request.getInternet_access());
            return type;
        };
    }
}
