package com.example.demo.type.controller;

import com.example.demo.type.dto.PostTypeRequest;
import com.example.demo.type.dto.PutTypeRequest;
import com.example.demo.type.entity.Type;
import com.example.demo.type.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Optional;

@RestController
@RequestMapping("api/types")
public class TypeController {

    TypeService typeService;

    @Autowired
    public TypeController(TypeService typeService) {
        this.typeService=typeService;
    }

    @DeleteMapping("{name}")
    public ResponseEntity<Void> deleteType(@PathVariable("name") String name) {
        Optional<Type> type = typeService.find(name);
        if (type.isPresent()) {
            typeService.delete(type.get());
            return ResponseEntity
                    .accepted()
                    .build();
        } else {
            return ResponseEntity
                    .notFound()
                    .build();
        }
    }

    @PostMapping("")
    public ResponseEntity<Void> postType(@RequestBody PostTypeRequest request, UriComponentsBuilder builder) {
        Type type = PostTypeRequest.dtoToEntityMapper().apply(request);
        typeService.create(type);
        return ResponseEntity
                .created(builder
                        .pathSegment("api","types","{name}")
                        .buildAndExpand(type.getName()).toUri())
                .build();
    }

    @PutMapping("{name}")
    public ResponseEntity<Void> putType(@RequestBody PutTypeRequest request, @PathVariable("{name}") String name) {
        Optional<Type> type = typeService.find(name);
        if (type.isPresent()) {
            PutTypeRequest.dtoToEntityUpdater().apply(type.get(), request);
            typeService.update(type.get());
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
