package com.example.demo.services;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import com.example.demo.JwtExample;
import com.example.demo.impl.ChangeUserImpl;
import com.example.demo.impl.CreateUserImpl;
import com.example.demo.impl.ExampleLoginService;
import com.example.demo.impl.LoginAuthImpl;
import com.example.demo.impl.UserBcryptImpl;

@Configuration
public class DependencyConfiguration {
   
    @Bean
    @Scope("singleton")
    // @Scope("prototype")
    // @Scope("request")
    // @Scope("session")
    public LoginService loginService() {
        return new ExampleLoginService();
    }

    @Bean
    @Scope("prototype")
    public CreateUserImpl createUser() {
        return new CreateUserImpl();
    }

    @Bean
    @Scope("prototype")
    public ChangeUserImpl changeUser() {
        return new ChangeUserImpl();
    }

    @Bean
    @Scope("prototype")
    public UserBcryptImpl userBcrypt() {
        return new UserBcryptImpl();
    }

    @Bean
    @Scope("singleton")
    public LoginAuthImpl loginAuth() {
        return new LoginAuthImpl();
    }

    @Bean
    @Scope("singleton")
    public JwtExample jwtExample() {
        return new JwtExample();
    }

    
}
