package com.example.demo1;

import java.util.ArrayList;

import com.example.demo1.model.Auto;
import com.example.demo1.repositories.AutoRepository;
import com.example.demo1.repositories.OmistajaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
 
@Controller
public class AutoController {

    @Autowired
    private AutoRepository autoRepository;

    @Autowired OmistajaRepository omistajaRepository;

//Get-pyynnön käsittely, kaikkien autojen listaus / linkki autot sivulle pääsivulta ja varmaan miltä vaan
    @GetMapping("/autot")
    public String list(Model model) {
        model.addAttribute("autot", autoRepository.findAll());
        return "autot";
    }

//Get-pyynnön käsittely, paluu pääsivulle kaikilta sivuilta    
    @GetMapping("/index")
    public String paluu(){
    return "redirect:/";
    }

 
    @PostMapping("/autot")
    public String addAuto(@RequestParam Integer valmistenumero, @RequestParam String rekisterinumero, @RequestParam String merkki, @RequestParam String malli, @RequestParam Integer valmistusvuosi) {


        Auto a = new Auto(valmistenumero, rekisterinumero, merkki, malli, valmistusvuosi, new ArrayList<>());
        autoRepository.save(a);
 
        return "redirect:/autot";
    }
 
}    
    
