package com.example.demo.type.event.repository;

import com.example.demo.type.entity.Type;
import com.example.demo.type.event.dto.PostTypeRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

@Repository
public class TypeEventRepository {
    RestTemplate restTemplate;

    @Autowired
    public TypeEventRepository(@Value("http://realestate:8081/api/") String baseUrl) {
        restTemplate = new RestTemplateBuilder().rootUri(baseUrl).build();
    }

    public void delete(Type type) {
        restTemplate.delete("/types/{name}",type.getName());
    }

    public void create(Type type) {
        restTemplate.postForLocation("/types", PostTypeRequest.entityToDtoMapper().apply(type));
    }
}
