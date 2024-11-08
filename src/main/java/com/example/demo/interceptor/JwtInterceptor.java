package com.example.demo.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.example.demo.JwtExample;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JwtInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        var authorization = request.getHeader("Authorization");
        
        if (authorization == null || !authorization.startsWith("Bearer")) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("Unauthorized!");

            return false;
        }

        authorization = authorization
                            .replace("Bearer", "")
                            .trim();

        try {
            var decoded = JwtExample.validateToken(authorization);

            request.setAttribute("username", decoded.getSubject());
            request.setAttribute("id", decoded.getClaim("id"));
            
            return true;
        } catch (JWTVerificationException e) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("Unauthorized!");

            return false;
        }
    }

}