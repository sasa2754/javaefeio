package com.example.demo.controllers;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.JwtExample;
import com.example.demo.dto.LoginAuthDto;
import com.example.demo.dto.ResponseAuthDto;
import com.example.demo.services.LoginAuthService;


@RestController
@RequestMapping("/loginToken")
public class LoginAuthController {

    @Autowired
    LoginAuthService loginAuth;

    @PostMapping("/login")
    public ResponseEntity<Object> Login(@RequestBody LoginAuthDto data, @RequestHeader(value = "Authorization", required = false) String authorizationHeader) {
        var logar = loginAuth.login(data.username(), data.email(), data.password());

        // System.out.println("\n\n\n\n\n\n" + logar);

        if (authorizationHeader != null) {
            System.out.println(authorizationHeader);
        }

        return logar;
    }

    // Agora tem que pegar o token que o front manda de volta pela header e validar ele, como? NÃO SEI AAAAAAAAAAAAAAAAAAAAAAAAAAAAA

}
