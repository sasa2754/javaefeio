package com.example.demo.services;

import org.springframework.http.ResponseEntity;

public interface LoginAuthService {
    ResponseEntity<Object> login(String username, String email, String password);
}
