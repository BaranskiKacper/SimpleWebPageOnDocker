package com.example.demo.realestate.dto;

import com.example.demo.realestate.entity.RealEstate;
import com.example.demo.type.entity.Type;
import lombok.*;

import java.util.function.Function;
import java.util.function.Supplier;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class PostRealEstateRequest {

    String name;
    String city;
    int age;
    String type;

    public static Function<PostRealEstateRequest, RealEstate> dtoToEntityMapper(
            Supplier<Type> typeFunction) {
        return request -> RealEstate.builder()
                .name(request.getName())
                .city(request.getCity())
                .age(request.getAge())
                .type(typeFunction.get())
                .build();
    }
}
