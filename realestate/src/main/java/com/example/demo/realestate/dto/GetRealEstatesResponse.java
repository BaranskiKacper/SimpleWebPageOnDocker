package com.example.demo.realestate.dto;

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
public class GetRealEstatesResponse {
    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @ToString
    @EqualsAndHashCode
    public static class RealEstate {
        Long id;
        String name;
        String city;
        int age;
        String type;

    }

    @Singular
    List<RealEstate> realEstates;

    public static Function<Collection<com.example.demo.realestate.entity.RealEstate>, GetRealEstatesResponse>
    entityToDtoMapper() {
        return realEstates -> {
            GetRealEstatesResponseBuilder response = GetRealEstatesResponse.builder();
            realEstates.stream()
                    .map(realEstate -> RealEstate.builder()
                            .id(realEstate.getId())
                            .name(realEstate.getName())
                            .city(realEstate.getCity())
                            .age(realEstate.getAge())
                            .type(realEstate.getType().getName())
                            .build())
                    .forEach((response::realEstate));
            return response.build();
        };
    }
}
