package com.example.atividade_pratica_fabrica_software.service.exceptions.personagem;

public class MaximoPontosException extends RuntimeException {
    public MaximoPontosException() {
        super("Pontos máximo por persoangem excedido.");
    }
}
