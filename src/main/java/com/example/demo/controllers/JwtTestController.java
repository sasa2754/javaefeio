package com.example.demo.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClient;

import jakarta.servlet.http.HttpServletRequest;

@RestController
public class JwtTestController {
    @GetMapping("/ola")
    public static ResponseEntity<String> bomdia(@RequestAttribute("username") String username) {
        return ResponseEntity.ok("Bom dia " + username + "!");
    }
}
