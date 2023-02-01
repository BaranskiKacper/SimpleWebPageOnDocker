package com.example.demo.type.dto;

import com.example.demo.type.entity.Type;
import lombok.*;

import java.util.Collection;
import java.util.List;
import java.util.function.Function;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class GetTypesResponse {


    @Singular
    List<Type> types;

    public static Function<Collection<Type>,GetTypesResponse>
    entityToDtoMapper() {
        return types -> {
            GetTypesResponseBuilder response = GetTypesResponse.builder();
            types.stream()
                    .map(type -> Type.builder()
                            .name(type.getName())
                            .numberOfRooms(type.getNumberOfRooms())
                            .size(type.getSize())
                            .internet_access(type.getInternet_access())
                            .build())
                    .forEach((response::type));
            return response.build();
        };

    }
}