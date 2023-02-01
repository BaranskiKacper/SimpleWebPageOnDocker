package com.example.demo.configuration;


import com.example.demo.type.entity.Type;
import com.example.demo.type.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;


@Component
public class InitializedData {
    public final TypeService typeService;

    @Autowired
    public InitializedData(TypeService typeService) {
        this.typeService = typeService;
    }


    @PostConstruct
    private synchronized void init() {


        Type flat = Type.builder()
                .name("flat")
                .internet_access(Boolean.TRUE)
                .numberOfRooms(2)
                .size(40)
                .build();

        Type boat = Type.builder()
                .name("boat")
                .internet_access(Boolean.FALSE)
                .numberOfRooms(4)
                .size(14)
                .build();

        Type house = Type.builder()
                .name("house")
                .internet_access(Boolean.TRUE)
                .numberOfRooms(8)
                .size(250)
                .build();

        typeService.create(flat);
        typeService.create(boat);
        typeService.create(house);



    }
}
