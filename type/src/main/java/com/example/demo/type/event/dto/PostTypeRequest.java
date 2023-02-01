package com.example.demo.type.event.dto;

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

    public static Function<Type, PostTypeRequest> entityToDtoMapper() {
        return entity-> PostTypeRequest.builder()
                .name(entity.getName())
                .build();
    }
}
