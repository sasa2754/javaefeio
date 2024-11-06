package com.example.demo.services;

public interface ChangeService {
    Boolean change(String username, String password, String newPassword, String repeatPassword);
}
