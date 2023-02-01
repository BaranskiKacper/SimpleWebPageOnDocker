package com.example.demo.realestate.repository;


import com.example.demo.realestate.entity.RealEstate;

import com.example.demo.type.entity.Type;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

@org.springframework.stereotype.Repository
public interface RealEstateRepository extends JpaRepository<RealEstate,Long> {

    List<RealEstate> findAllByType(Type type);

    Optional<RealEstate> findByIdAndType(Long id, Type type);

    Optional<RealEstate> findByNameAndType(String realEstateName, Type type);
}
