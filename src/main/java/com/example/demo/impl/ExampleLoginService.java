package com.example.demo.impl;

import com.example.demo.services.LoginService;

public class ExampleLoginService implements LoginService {

    @Override
    public Integer login(String username, String password) {
        if (!username.equals("don"))
            return -1;
        
        if (!password.equals("ferrari"))
            return -1;
        
            return 1;
   }
    
}
