package com.example.demo.type.service;


import com.example.demo.type.entity.Type;
import com.example.demo.type.event.repository.TypeEventRepository;
import com.example.demo.type.repository.TypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class TypeService {
    TypeRepository typeRepository;
    TypeEventRepository typeEventRepository;

    @Autowired
    public TypeService ( TypeRepository typeRepository, TypeEventRepository typeEventRepository) {
        this.typeRepository = typeRepository;
        this.typeEventRepository = typeEventRepository;
    }

    public Optional<Type> find(String name) {  return typeRepository.findById(name);}
    public List<Type> findAll() { return typeRepository.findAll();}
    @Transactional
    public void create (Type type) {
         typeRepository.save(type);
         typeEventRepository.create(type);
    }

    @Transactional
    public void delete (Type type) {
        typeRepository.delete(type);
        typeEventRepository.delete(type);
    }

    @Transactional
    public void update(Type type) { typeRepository.save(type);}
}
