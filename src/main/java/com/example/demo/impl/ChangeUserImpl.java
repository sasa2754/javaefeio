package com.example.demo.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.model.User;
import com.example.demo.repositories.UserRepository;
import com.example.demo.services.ChangeService;

public class ChangeUserImpl implements ChangeService {

    @Autowired
    private UserRepository repo;

    @Override
    public Boolean change(String username, String password, String newPassword, String repeatPassword) {
        if (!newPassword.equals(repeatPassword))
            return false;
        
        User user = repo.findByUsername(username)
            .stream().findFirst()
            .orElseThrow(() -> new RuntimeException("User not found"));

        if (!user.getPassword().equals(password))
            return false;

        user.setPassword(newPassword);
        repo.save(user);

        return true;
    }
    
}
