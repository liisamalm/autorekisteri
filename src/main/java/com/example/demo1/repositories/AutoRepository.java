package com.example.demo1.repositories;

import com.example.demo1.model.Auto;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AutoRepository extends JpaRepository<Auto, Long> {
    
}
 
