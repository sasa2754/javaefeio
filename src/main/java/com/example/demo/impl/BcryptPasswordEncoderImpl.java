package com.example.demo.impl;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.example.demo.services.PasswordEncoderService;

public class BcryptPasswordEncoderImpl implements PasswordEncoderService{
    @Override
    public String encode(String password)
    {
        var encoder = new BCryptPasswordEncoder(10);
        return encoder.encode(password);
    }
}
