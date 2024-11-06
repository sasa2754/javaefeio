package com.example.demo.services;

public interface UserBcryptService {
    String create(String username, String email, String password);
    String encode(String password);
}
