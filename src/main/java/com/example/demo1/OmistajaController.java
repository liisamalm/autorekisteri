package com.example.demo1;

import javax.transaction.Transactional;

import com.example.demo1.model.Auto;
import com.example.demo1.model.Omistaja;
import com.example.demo1.repositories.AutoRepository;
import com.example.demo1.repositories.OmistajaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class OmistajaController {
    @Autowired
    private AutoRepository autoRepository;

    @Autowired
    private OmistajaRepository omistajaRepository;
    
    //Get-pyynnön käsittely, mennään sivulle (autot linkistä lisää autolle omistaja) kaikkien omistajien listaus
    @GetMapping("/omistajat")
    public String listOmistajat(Model model) {
        model.addAttribute("omistajat", omistajaRepository.findAll(PageRequest.of(0, 10, Sort.Direction.ASC, "sukunimi")));
        return "omistajat";
    }
//Get-pyynnön käsittely, yksittäisen omistajan näyttäminen (klikataan omistajan nimeä 'omistajat' sivulla ja aukeaa lista autojen tiedoista 'omistajat/id sivulla')  
    @GetMapping("/omistajat/{id}")
    public String viewOmistaja(Model model, @PathVariable Long id) {
 
        model.addAttribute("omistaja", omistajaRepository.getById(id));
        model.addAttribute("autot", autoRepository.findAll());
 
        return "omistaja";
    }
    
    
    @GetMapping("/lista")
    public String viewlistataas(Model model) {
        model.addAttribute("omistaja", omistajaRepository.findAll());
        model.addAttribute("autot", autoRepository.findAll(PageRequest.of(0, 6, Sort.by("malli"))));
 
        return "lista";
    }

//post-pyynnön käsittely, yksittäisen omistajan lisääminen
    @PostMapping("/omistajat")
    public String addOmistaja(@RequestParam String etunimi,
            @RequestParam String sukunimi) {
        if (etunimi.isEmpty() || sukunimi.isEmpty()) {
            return "redirect:/omistajat";
        }
 
        Omistaja o = new Omistaja();
        o.setEtunimi(etunimi);
        o.setSukunimi(sukunimi);
 
        omistajaRepository.save(o);
 
        return "redirect:/omistajat";
    }
    //post-pyynnön käsittely, Auton lisääminen omistajalle
    @PostMapping("/omistajat/{omistajaId}/autot/{autoId}")
    @Transactional
    public String addOmistajalleAuto(@PathVariable Long omistajaId, @PathVariable Long autoId) {

        if (omistajaId == null || autoId == null) {
            return "redirect:/";
        }

        Omistaja omistaja = omistajaRepository.getById(omistajaId);
        Auto auto = autoRepository.getById(autoId);
 
        if (!omistaja.getAutot().contains(auto)) {
            omistaja.getAutot().add(auto);
        }
 
        return "redirect:/omistajat/" + omistajaId;
    }

}
