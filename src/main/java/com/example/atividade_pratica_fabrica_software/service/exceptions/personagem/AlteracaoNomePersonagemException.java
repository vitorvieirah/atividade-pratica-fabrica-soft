package com.example.atividade_pratica_fabrica_software.service.exceptions.personagem;

public class AlteracaoNomePersonagemException extends RuntimeException {
    public AlteracaoNomePersonagemException() {
        super("Só pode alterar nome de personagens do tipo guerreiro");
    }
}
