package com.example.demo1;


import org.springframework.web.bind.annotation.GetMapping;

public class Demo1ApplicationController {

    
    @GetMapping("/")
    public String handleDefault() {
        return "index";
    }
}

    
    

