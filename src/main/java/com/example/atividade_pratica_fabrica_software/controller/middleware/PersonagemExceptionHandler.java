package com.example.atividade_pratica_fabrica_software.controller.middleware;

import com.example.atividade_pratica_fabrica_software.service.exceptions.personagem.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class PersonagemExceptionHandler {

    @ExceptionHandler(AlteracaoNomePersonagemException.class)
    public ResponseEntity alteracaoNoemPersonagemExceptionHandler(AlteracaoNomePersonagemException exception) {
        return ResponseEntity.badRequest().body(exception.getMessage());
    }

    @ExceptionHandler(AmuletoNaoEncontradoException.class)
    public ResponseEntity amuletoNaoEncontradoExceptionHandler() {
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(AmuletoQuantidadeException.class)
    public ResponseEntity amuletoQuantidadeExceptionHandler(AmuletoQuantidadeException exception) {
        return ResponseEntity.badRequest().body(exception.getMessage());
    }

    @ExceptionHandler(MaximoPontosException.class)
    public ResponseEntity maximoPontosExceptionHandler(MaximoPontosException exception) {
        return ResponseEntity.badRequest().body(exception.getMessage());
    }

    @ExceptionHandler(PersonagemNaoEncontradoException.class)
    public ResponseEntity personagemNaoEncontradoExceptionHandler() {
        return ResponseEntity.notFound().build();
    }

}
