package com.example.atividade_pratica_fabrica_software.service.exceptions.personagem;

public class AmuletoNaoEncontradoException extends RuntimeException {
    public AmuletoNaoEncontradoException() {
        super("Personagem não possui amuleto.");
    }
}
