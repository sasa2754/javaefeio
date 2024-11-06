package com.example.demo.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.ErrorResponse;


@RestController
@CrossOrigin(origins = {"http://localhost:5257/curitiba"})
@RequestMapping("/curitiba")
public class CuritibaController {
    
    @GetMapping("/{CPF}/{CEP}")
    public ResponseEntity<Object> curitiba(@PathVariable String CPF, @PathVariable String CEP) {
        // String url = "https://viacep.com.br/ws/";

        // Definindo as variáveis
        Integer totalSoma = 0;
        Integer mult = 10;
        Integer digUm = 0;
        Integer digDois = 0;
        Integer[] cpfInteger = new Integer[11];


        // Verificando se os valores são nulos
        if(CPF == null || CEP == null || CPF.isEmpty() || CEP.isEmpty())
            return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new ErrorResponse("Campos vazios!", 400));
        
        // Tirando o . e o - se tiver e separando numa lista
        String cpfString = CPF.replaceAll("[.-]", "");
        var cpfDivide = cpfString.split("");


        // Esses números passam na verificação, então tem q fazer ela manualmente
        if(cpfString.equals("11111111111") || cpfString.equals("22222222222") || cpfString.equals("33333333333") || cpfString.equals("44444444444") || cpfString.equals("55555555555") || cpfString.equals("66666666666") || cpfString.equals("77777777777") || cpfString.equals("88888888888") || cpfString.equals("99999999999") || cpfString.equals("00000000000") || cpfString.length() != 11)
            return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new ErrorResponse("CPF inválido 1!", 400));

        // Passando pra Integer para poder fazer as operações
        for(int i = 0; i < cpfString.length(); i++) {
            cpfInteger[i] = Integer.parseInt(cpfDivide[i]);
        }

        // Soma dos 9 primeiros números multiplicados pela sequência de 10 a 2
        for(int i = 0; i < 9; i++) {
            totalSoma += cpfInteger[i] * mult;
            mult -= 1;
        }

        // Pegando o valor do primeiro digito de verificação
        digUm = (totalSoma * 10) % 11;
        if (digUm == 10) digUm = 0;
        totalSoma = 0;
        mult = 11;

        // Operação bem parecida para pegar o segundo digito validador, mas agora considerando o primeiro digito também
        for(int i = 0; i < 10; i++) {
            totalSoma += cpfInteger[i] * mult;
            mult -= 1;
        }

        digDois = (totalSoma * 10) % 11;
        if (digDois == 10) digDois = 0;

        if (cpfInteger[9].equals(digUm) && cpfInteger[10].equals(digDois)) 
            return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ErrorResponse("CPF válido", 200));
        

        return ResponseEntity
            .status(HttpStatus.BAD_REQUEST)
            .body(new ErrorResponse("CPF inválido 2!", 400));
        
    }
}
