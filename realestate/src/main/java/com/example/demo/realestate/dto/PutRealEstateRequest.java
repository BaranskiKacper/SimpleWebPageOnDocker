package com.example.demo.realestate.dto;

import com.example.demo.realestate.entity.RealEstate;
import lombok.*;


import java.util.function.BiFunction;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class PutRealEstateRequest {
    private String name;

    private String city;
    private int age;
    String type;


    public static BiFunction<RealEstate, PutRealEstateRequest, RealEstate> dtoToEntityUpdater() {
        return (realEstate, request) -> {
            realEstate.setName(request.getName());
            realEstate.setAge(request.getAge());
            realEstate.setCity(request.getCity());
            return realEstate;
        };
    }
}
