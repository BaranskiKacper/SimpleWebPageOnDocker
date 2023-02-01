package com.example.demo.realestate.controller;

import com.example.demo.realestate.dto.GetRealEstateResponse;
import com.example.demo.realestate.dto.GetRealEstatesResponse;
import com.example.demo.realestate.dto.PostRealEstateRequest;
import com.example.demo.realestate.dto.PutRealEstateRequest;
import com.example.demo.realestate.entity.RealEstate;
import com.example.demo.realestate.repository.RealEstateRepository;
import com.example.demo.realestate.service.RealEstateService;
import com.example.demo.type.entity.Type;
import com.example.demo.type.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Optional;

@RestController
@RequestMapping("api/types/{name}/real_estates")
public class TypeRealEstateController {

    RealEstateService realEstateService;
    TypeService typeService;
    private final RealEstateRepository realEstateRepository;

    @Autowired
    public TypeRealEstateController(RealEstateService realEstateService, TypeService typeService,
                                    RealEstateRepository realEstateRepository) {
        this.typeService = typeService;
        this.realEstateService = realEstateService;
        this.realEstateRepository = realEstateRepository;
    }

    @GetMapping
    public ResponseEntity<GetRealEstatesResponse> getRealEstates(@PathVariable("name") String name) {
        Optional<Type> type = typeService.find(name);
        return type.map(value -> ResponseEntity.ok(GetRealEstatesResponse.entityToDtoMapper()
                        .apply(realEstateService.findAll(value))))
                .orElseGet(() -> ResponseEntity
                        .notFound()
                        .build());
    }
    @GetMapping("{id}")
    public ResponseEntity<GetRealEstateResponse> getRealEstate(@PathVariable("name") String name,
                                                               @PathVariable("id") Long id) {
        return realEstateService.find(name, id)
                .map(value -> ResponseEntity
                        .ok(GetRealEstateResponse.entityToDtoMapper().apply(value)))
                .orElseGet( () -> ResponseEntity
                        .notFound()
                        .build());
    }

    @PostMapping
    public ResponseEntity<Void> postRealEstate(@PathVariable("name") String name, @RequestBody PostRealEstateRequest request,
                                               UriComponentsBuilder builder) {
        Optional<Type> type = typeService.find(name);
        if (type.isPresent()) {
            RealEstate realEstate = PostRealEstateRequest
                    .dtoToEntityMapper(type::get)
                    .apply(request);
            realEstate = realEstateService.create(realEstate);
            return ResponseEntity
                    .created(builder.pathSegment("api","types","{name}","real_estates","{id}")
                    .buildAndExpand(type.get().getName(),realEstate.getId())
                    .toUri())
                    .build();

        } else {
            return ResponseEntity
                    .notFound()
                    .build();
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteRealEstate(@PathVariable("name") String name,
                                                 @PathVariable("id") long id) {
        Optional<RealEstate> realEstate = realEstateService.find(name, id);
        if (realEstate.isPresent()) {
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
    public ResponseEntity<Void> putRealEstate(@PathVariable("name") String name,
                                              @RequestBody PutRealEstateRequest request,
                                              @PathVariable("id") long id) {
        Optional<RealEstate> realEstate = realEstateService.find(name, id);
        if (realEstate.isPresent()) {
            PutRealEstateRequest.dtoToEntityUpdater().apply(realEstate.get(),request);
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
