package com.example.demo.type.repository;



import com.example.demo.type.entity.Type;
import org.springframework.data.jpa.repository.JpaRepository;

@org.springframework.stereotype.Repository
public interface TypeRepository extends JpaRepository<Type,String> {


}