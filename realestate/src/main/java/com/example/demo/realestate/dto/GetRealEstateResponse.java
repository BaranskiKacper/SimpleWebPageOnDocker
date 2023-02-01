package com.example.demo.realestate.dto;

import com.example.demo.realestate.entity.RealEstate;

import lombok.*;

import java.util.function.Function;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class GetRealEstateResponse {

    Long id;
    String name;

    String city;
    int age;
    String type;

    public static Function<RealEstate, GetRealEstateResponse> entityToDtoMapper() {
        return realEstate -> GetRealEstateResponse.builder()
                .id(realEstate.getId())
                .name(realEstate.getName())
                .city(realEstate.getCity())
                .age(realEstate.getAge())
                .type(realEstate.getType().getName())
                .build();
    }
}
