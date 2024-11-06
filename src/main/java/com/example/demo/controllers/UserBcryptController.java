package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.UserBcryptDto;
import com.example.demo.services.UserBcryptService;

@RestController
@RequestMapping("/userBcrypt")
public class UserBcryptController {
    
    @Autowired
    UserBcryptService userBcrypt;

    @PostMapping
    public ResponseEntity<String> Login(@RequestBody UserBcryptDto data) {
        var create = userBcrypt.create(data.username(), data.email(), data.password());

        if (!create.isEmpty()) {
            System.out.println("\n\n\n\n\n\n\n\n" +create);
            return ResponseEntity
                .status(HttpStatus.CREATED)
                .body("Usuário criado!");
        }
        
        return ResponseEntity
            .status(HttpStatus.BAD_REQUEST)
            .body("Erro ao criar usuário!");
    }
}
