package com.example.demo.type.dto;

import com.example.demo.type.entity.Type;
import lombok.*;

import java.util.function.BiFunction;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@EqualsAndHashCode
public class PutTypeRequest {
    String name;


    public static BiFunction<Type,PutTypeRequest,Type> dtoToEntityUpdater() {
        return (type, request) -> {
            type.setName(request.getName());
            return type;
        };
    }
}
