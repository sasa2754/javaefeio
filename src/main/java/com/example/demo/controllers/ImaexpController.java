package com.example.demo.controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.ImaexpData;

@RestController
@RequestMapping("/imaexp")
@CrossOrigin(origins = {"http://localhost:5257/imaexp"})
public class ImaexpController {
    
    @GetMapping
    public ImaexpData imaexp(Integer A, Integer b) {
        Double Re = A * Math.sin(b);
        Double Im = A * Math.cos(b);

        return new ImaexpData(Re,Im);
    }
}
