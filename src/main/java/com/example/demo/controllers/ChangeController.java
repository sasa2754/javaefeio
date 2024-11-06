package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.ChangeDto;
import com.example.demo.services.ChangeService;

@RestController
@RequestMapping("/changepassword")
public class ChangeController {
    
    @Autowired
    ChangeService changeService;

    @PatchMapping
    public ResponseEntity<String> change(@RequestBody ChangeDto data) {
        var changePass = changeService.change(data.username(), data.password(), data.newPassword(), data.repeatPassword());

        if (changePass)
            return ResponseEntity
                .status(HttpStatus.OK)
                .body("Usuário atualizado");

        return ResponseEntity
            .status(HttpStatus.BAD_REQUEST)
            .body("Erro ao atualizar usuário");
    }
}
