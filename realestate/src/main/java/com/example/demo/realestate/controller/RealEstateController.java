package com.example.demo.realestate.controller;

import com.example.demo.realestate.dto.GetRealEstateResponse;
import com.example.demo.realestate.dto.GetRealEstatesResponse;
import com.example.demo.realestate.dto.PostRealEstateRequest;
import com.example.demo.realestate.dto.PutRealEstateRequest;
import com.example.demo.realestate.entity.RealEstate;
import com.example.demo.realestate.service.RealEstateService;

import com.example.demo.type.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Optional;

@RestController
@RequestMapping("api/real_estates")
public class RealEstateController {

    RealEstateService realEstateService;
    TypeService typeService;

    @Autowired
    public RealEstateController(RealEstateService realEstateService, TypeService typeService) {
        this.realEstateService = realEstateService;
        this.typeService = typeService;
    }

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<GetRealEstatesResponse> getRealEstates() {
        return ResponseEntity
                .ok(GetRealEstatesResponse.entityToDtoMapper().apply(realEstateService.findAll()));
    }
    @GetMapping("{id}")
    public ResponseEntity<GetRealEstateResponse> getRealEstate(@PathVariable("id") long id) {
        return realEstateService.find(id)
                .map(value -> ResponseEntity
                        .ok(GetRealEstateResponse.entityToDtoMapper().apply(value)))
                .orElseGet(() -> ResponseEntity
                        .notFound()
                        .build());
    }

    @PostMapping
    public ResponseEntity<Void> postRealEstate(@RequestBody PostRealEstateRequest request, UriComponentsBuilder builder) {

        RealEstate realEstate = PostRealEstateRequest
                .dtoToEntityMapper(()->null)
                .apply(request);
        realEstate = realEstateService.create(realEstate);
        return ResponseEntity
                .created(builder
                        .pathSegment("api","real_estates","{id}")
                        .buildAndExpand(realEstate.getId()).toUri())
                .build();
    }
    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteRealEstate(@PathVariable("id") long id) {
        Optional<RealEstate> realEstate =realEstateService.find(id);
        if(realEstate.isPresent()) {
            realEstateService.delete(realEstate.get().getId());
            return ResponseEntity
                    .accepted()
                    .build();
        } else {
            return ResponseEntity
                    .notFound()
                    .build();
        }
    }

    @PutMapping("{id}")
    public ResponseEntity<Void> putRealEstate(@RequestBody PutRealEstateRequest request, @PathVariable("id") long id) {
        Optional<RealEstate> realEstate = realEstateService.find(id);
        if(realEstate.isPresent()) {
            PutRealEstateRequest.dtoToEntityUpdater()
                    .apply(realEstate.get(),request);
            realEstateService.update(realEstate.get());
            return ResponseEntity
                    .accepted()
                    .build();
        } else {
            return ResponseEntity
                    .notFound()
                    .build();
        }
    }
}
