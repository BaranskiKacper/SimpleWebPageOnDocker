package com.example.demo.realestate.service;

import com.example.demo.realestate.entity.RealEstate;
import com.example.demo.realestate.repository.RealEstateRepository;
import com.example.demo.type.entity.Type;
import com.example.demo.type.repository.TypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class RealEstateService {

    RealEstateRepository realEstateRepository;
    TypeRepository typeRepository;

    @Autowired
    public RealEstateService (RealEstateRepository realEstateRepository, TypeRepository typeRepository) {
        this.realEstateRepository = realEstateRepository;
        this.typeRepository = typeRepository;
    }
    public Optional<RealEstate> find(Long id) {

        return realEstateRepository.findById(id);
    }


    public List<RealEstate> findAll() {

        return realEstateRepository.findAll();
    }

    public List<RealEstate> findAll(Type type) {

        return realEstateRepository.findAllByType(type);
    }


    public Optional<RealEstate> find(String name, Long id) {
        Optional<Type> type = typeRepository.findById(name);
        if (type.isPresent()) {
            return realEstateRepository.findByIdAndType(id, type.get());
        }
        else {
            return Optional.empty();
        }
    }


    @Transactional
    public RealEstate create(RealEstate realEstate) {
        return realEstateRepository.save(realEstate);
    }
    @Transactional
    public void delete(Long realEstate) {
        realEstateRepository.deleteById(realEstate);
    }

    @Transactional
    public void update(RealEstate realEstate) {
        realEstateRepository.save(realEstate);
    }



}
