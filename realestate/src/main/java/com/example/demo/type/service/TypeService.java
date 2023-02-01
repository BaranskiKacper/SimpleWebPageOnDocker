package com.example.demo.type.service;

import com.example.demo.type.entity.Type;
import com.example.demo.type.repository.TypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class TypeService {
    TypeRepository repository;

    @Autowired
    public TypeService (TypeRepository repository) {
        this.repository = repository;
    }

    public Optional<Type> find(String name) {
        return repository.findById(name);
    }

    public List<Type> findAll() {
        return repository.findAll();
    }

    @Transactional
    public Type create(Type type) {
        return repository.save(type);
    }

    @Transactional
    public void update(Type type) {
        repository.save(type);
    }

    @Transactional
    public void delete(Type type) {
        repository.delete(type);
    }

}
