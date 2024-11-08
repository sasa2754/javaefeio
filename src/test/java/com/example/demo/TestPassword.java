package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.example.demo.impl.CreateUserImpl;

public class TestPassword {
    @Test
    public void TestJwt() {
        var regiterImpl = new CreateUserImpl();

        var senha = "banana123";

        assertFalse(regiterImpl.checkPass(senha));
        assertTrue(regiterImpl.checkPass("AKDAgmr007@s"));
    }
}
