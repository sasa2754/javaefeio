package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.CreateUserDto;
import com.example.demo.services.CreateUserService;


@RestController
@RequestMapping("/create")
public class CreateUserController {
    
    @Autowired
    CreateUserService createUser;

    @PostMapping
    public ResponseEntity<String> Login(@RequestBody CreateUserDto data) {
        var create = createUser.create(data.username(), data.email(), data.password());
        
        if (create)
            return ResponseEntity
                .status(HttpStatus.CREATED)
                .body("Usuário criado!");
        
        return ResponseEntity
            .status(HttpStatus.BAD_REQUEST)
            .body("Erro ao criar usuário!");
    }
}
