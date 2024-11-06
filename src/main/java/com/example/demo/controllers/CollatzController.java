package com.example.demo.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.CollatzData;
import com.example.demo.dto.ErrorResponse;

@RestController
@CrossOrigin(origins = {"http://localhost:5257/collatz"})
@RequestMapping("/collatz")
public class CollatzController {
    
    @GetMapping("/{current}/{step}")
    public ResponseEntity<Object> collatz(@PathVariable Integer current, @PathVariable Integer step) {

        if(current < 0 || step < 0)
            return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new ErrorResponse("Valores negativos não são permitidos.", 400));
        
        for (int i = 0; i < step; i++) {
            if (current % 2 == 0) {
                current = current / 2;
            } else {
                current = 3 * current + 1;
            }
        }
                

        return ResponseEntity
            .status(HttpStatus.OK)
            .body(new CollatzData(current));
    }
}
