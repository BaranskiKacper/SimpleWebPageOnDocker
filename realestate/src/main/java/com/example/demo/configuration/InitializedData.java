package com.example.demo.configuration;

import com.example.demo.realestate.entity.RealEstate;

import com.example.demo.realestate.service.RealEstateService;

import com.example.demo.type.entity.Type;
import com.example.demo.type.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class InitializedData {
    private final RealEstateService realEstateService;
    public final TypeService typeService;

    @Autowired
    public InitializedData(RealEstateService realEstateService, TypeService typeService) {
        this.realEstateService = realEstateService;
        this.typeService = typeService;
    }

    @PostConstruct
    private synchronized void init() {


        Type flat = Type.builder()
                .name("flat")
                .build();

        Type boat = Type.builder()
                .name("boat")
                .build();

        Type house = Type.builder()
                .name("house")
                .build();

        typeService.create(flat);
        typeService.create(boat);
        typeService.create(house);

        RealEstate kawalerka = RealEstate.builder()
                .name("kawalerka")
                .age(5)
                .city("Gdansk")
                .type(flat)
                .build();
        RealEstate houseBoat = RealEstate.builder()
                .name("house boat")
                .age(3)
                .city("Gdynia")
                .type(boat)
                .build();
        RealEstate pentHouse = RealEstate.builder()
                .name("pent house")
                .age(10)
                .city("Sopot")
                .type(house)
                .build();
        RealEstate mieszkanieDwupokojowe = RealEstate.builder()
                .name("mieszkanie dwupokojowe")
                .age(11)
                .city("Gdynia")
                .type(flat)
                .build();
        RealEstate luksusowyJacht = RealEstate.builder()
                .name("lukssowy jacht")
                .age(1)
                .city("Sardynia")
                .type(boat)
                .build();
        RealEstate blizniak = RealEstate.builder()
                .name("blizniak")
                .age(8)
                .city("Wejherowo")
                .type(house)
                .build();


        realEstateService.create(kawalerka);
        realEstateService.create(houseBoat);
        realEstateService.create(pentHouse);
        realEstateService.create(mieszkanieDwupokojowe);
        realEstateService.create(luksusowyJacht);
        realEstateService.create(blizniak);

    }
}
