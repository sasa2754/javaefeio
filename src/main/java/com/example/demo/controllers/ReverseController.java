package com.example.demo.controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.ReverseData;

@RestController
@RequestMapping("/reverse")
@CrossOrigin(origins = {"http://localhost:5257/reverse"})
public class ReverseController {
    
    @GetMapping("/{world}")
    public ReverseData reverso(@PathVariable String world) {
        Integer qtd = world.length();
        var divise = world.split("");
        Boolean isPlaindrome = true;

        String inverted = new StringBuilder(world).reverse().toString();
        System.out.println("\n\n\n\n\n\n\nPalavra normal: " + world + "\nPalavra invertida: " + inverted);

        var diviseInverted = inverted.split("");

        for (int i = 0; i < qtd; i++) {
            if (!divise[i].equals(diviseInverted[i])) {
                isPlaindrome = false;
            }
        }

        return new ReverseData(inverted, isPlaindrome);
    }
}
