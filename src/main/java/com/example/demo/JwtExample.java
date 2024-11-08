package com.example.demo;

import java.util.Date;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;

public class JwtExample {
    // Header: contém o tipo do token (JWT) e o algoritmo de assinatura (HS256 no nosso caso).
    // Payload: contém as “claims”, ou seja, as informações do usuário ou outros dados.
    // Signature: assina o token, garantindo a autenticidade e integridade.


    private static final String SECRET_KEY = "minha-chave-secreta-super-segura-muito-segura-aaaa"; //  Chave usada para gerar e validar a assinatura do token, geralmente é mais forte que essa, mas né, n estamos afim

    // Cria um token com um algoritmo HMAC256
    public String generateToken(Long id, String username) {
        Algorithm algorithm = Algorithm.HMAC256(SECRET_KEY); // Define o algoritmo e a chave secreta

        return JWT.create()
                .withSubject(username) // Define o identificador, nesse caso o username
                .withClaim("id", id)
                .withIssuedAt(new Date()) // Define a data que o token foi criado
                .withExpiresAt(new Date(System.currentTimeMillis() + 86400000)) // Define a data de expiração do tokenpara 24 horas após a criação dele
                .sign(algorithm); // Assina o token e retorna o JWT completo
    }

    // Valida o token verificando a assinatura e a validade do token JWT, se estiver correto, o token será decodificado e suas claims serão retornadas, se ele for inválido, ele retorna null
    public static DecodedJWT validateToken(String token) throws JWTVerificationException {
        Algorithm algorithm = Algorithm.HMAC256(SECRET_KEY); // Configura o verificador com o mesmo algoritmo e chave usados antes

        JWTVerifier verifier = JWT.require(algorithm) 
                .build();

        return verifier.verify(token); // Verifica o token, se certificando de que ele foi gerado com a mesma chave e que ainda está válido.
        
    }
}
