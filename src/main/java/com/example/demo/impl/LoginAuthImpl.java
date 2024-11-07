package com.example.demo.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import com.example.demo.JwtExample;
import com.example.demo.dto.ResponseAuthDto;
import com.example.demo.model.User;
import com.example.demo.repositories.UserRepository;
import com.example.demo.services.LoginAuthService;


public class LoginAuthImpl implements LoginAuthService {
    
    @Autowired
    private UserRepository repo;

    @Autowired
    private JwtExample jwtExample;

    @Override
    public ResponseEntity<Object> login(String username, String email, String password) {
        if (email == null && username == null)
            return new ResponseEntity<>("Email ou Username são obrigatórios", HttpStatus.BAD_REQUEST);
        
        Optional<User> userOptional = null;

        if (email != null) {
            userOptional = repo.findByEmailOrUsername(email, null);
        }
        
        else if (username != null) {
            userOptional = repo.findByEmailOrUsername(null, username);
            System.out.println(userOptional);
        }

        if (userOptional.isEmpty())
            return new ResponseEntity<>("Usuário não encontrado", HttpStatus.NOT_FOUND);
        
        User user = userOptional.get();

        // var passEncode = encode(password);

        // if (!user.getPassword().equals(passEncode))
        var encoder = new BCryptPasswordEncoder(10);

        if (!encoder.matches(password, user.getPassword())) {
            System.out.println(password);
            System.out.println(user.getPassword());
            return new ResponseEntity<>("Senha incorreta", HttpStatus.UNAUTHORIZED);
        }

        String token = jwtExample.generateToken(user.getId(), username);

        // return ResponseEntity.ok().body("Bearner " + token);
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(new ResponseAuthDto("Usuário logado com sucesso!", token));
    }

    // public String encode(String password) {
    //     var encoder = new BCryptPasswordEncoder(10);
    //     return encoder.encode(password);
    // }
}
