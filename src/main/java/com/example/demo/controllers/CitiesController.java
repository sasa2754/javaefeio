package com.example.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Cities;
import com.example.demo.repositories.CitiesRepository;

@RestController
@RequestMapping("/cities")
public class CitiesController {
    
    @Autowired
    private CitiesRepository repo;

    @GetMapping
    public List<Cities> getAllCities() {
        return repo.findAll();
    }

    @PostMapping
    public ResponseEntity<Cities> createCity(@RequestBody Cities city) {
        Cities savedCity = repo.save(city); 
        return ResponseEntity.status(HttpStatus.CREATED).body(savedCity); 
    }

    @DeleteMapping("/delete/{id}")
    public void deleteCity(@PathVariable Long id) {
        repo.deleteById(id);
    }
    
    @GetMapping("/query/{city}")
    public List<Cities> getByType(@PathVariable String city) {
        return repo.findByCity(city);
    }
}
