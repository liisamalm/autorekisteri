package com.example.demo1.model;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;

import com.example.demo1.model.Auto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.AbstractPersistable;
 
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Omistaja extends AbstractPersistable<Long> {
 
    private String etunimi;
    private String sukunimi;
    
    @ManyToMany
    private List<Auto> autot;
 
 
}