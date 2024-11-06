package com.example.demo.impl;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.example.demo.model.User;
import com.example.demo.repositories.UserRepository;
import com.example.demo.services.UserBcryptService;

public class UserBcryptImpl implements UserBcryptService {

    @Autowired
    private UserRepository repo;

    @Override
    public String encode(String password) {
        var encoder = new BCryptPasswordEncoder(10);
        return encoder.encode(password);
    }

    @Override
    public String create(String username, String email, String password) {
        var haveEmail = repo.findByEmail(email);
        var haveUsername = repo.findByUsername(username);

        if (!haveEmail.isEmpty() || !haveUsername.isEmpty())
            throw new RuntimeException("Username ou Email vazios!");

        if (!checkPass(password))
            throw new RuntimeException("Senha inválida!");

        if (!checkEmail(email))
            throw new RuntimeException("Email inválido!");

        var encodePass = encode(password);

        User user = new User();
        user.setEmail(email);
        user.setPassword(encodePass);
        user.setUsername(username);

        repo.save(user);

        String frase = "deu boa, vlw flw, tó sua senha ai: ";
        String response = frase + encodePass;

        return response;
    }

    public Boolean checkPass(String pass) {
        int maiusc = 0, minusc = 0, num = 0;

        if (pass.isEmpty())
            return false;
            
        if (pass.length() < 8)
            return false;

        for (int i = 0; i < pass.length(); i++) {
            char c = pass.charAt(i);

            if (Character.isUpperCase(c))
                maiusc++;

            if (Character.isLowerCase(c))
                minusc++;

            if (Character.isDigit(c))
                num++;
        }

        if (maiusc == 0)
            return false;
        
        if (minusc == 0)
            return false;

        if (num == 0)
            return false;

        if(!pass.matches(".*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?].*"))
            return false;

        return true;
    }

    public Boolean checkEmail(String email) {
        Pattern p = Pattern.compile(".+@.+\\.[a-z]+");
	    Matcher m = p.matcher(email);

        return m.matches();
    }
    
}
