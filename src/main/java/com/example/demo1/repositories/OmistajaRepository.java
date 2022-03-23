package com.example.demo1.repositories;

import com.example.demo1.model.Omistaja;

import org.springframework.data.jpa.repository.JpaRepository;

public interface OmistajaRepository extends JpaRepository<Omistaja, Long> {
    
}

