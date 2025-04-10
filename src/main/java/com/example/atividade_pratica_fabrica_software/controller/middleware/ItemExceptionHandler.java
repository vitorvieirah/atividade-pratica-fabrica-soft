package com.example.atividade_pratica_fabrica_software.controller.middleware;

import com.example.atividade_pratica_fabrica_software.service.exceptions.item.DefesaForcaException;
import com.example.atividade_pratica_fabrica_software.service.exceptions.item.ItemNaoEncontradoException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ItemExceptionHandler {

    @ExceptionHandler(DefesaForcaException.class)
    public ResponseEntity defesaForcaExceptionHandler(DefesaForcaException exception) {
        return ResponseEntity.badRequest().body(exception.getMessage());
    }

    @ExceptionHandler(ItemNaoEncontradoException.class)
    public ResponseEntity itemNaoEncontradoExceptionHandler() {
        return ResponseEntity.notFound().build();
    }
}
