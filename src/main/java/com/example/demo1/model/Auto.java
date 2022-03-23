package com.example.demo1.model;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;

import com.example.demo1.model.Omistaja;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.AbstractPersistable;
 
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Auto extends AbstractPersistable<Long> {
 
    private Integer valmistenumero;
    private String rekisterinumero;
    private String merkki;
    private String malli;
    private Integer valmistusvuosi;

    @ManyToMany(mappedBy="autot")
    private List<Omistaja> omistajat = new ArrayList<>();
 
}
