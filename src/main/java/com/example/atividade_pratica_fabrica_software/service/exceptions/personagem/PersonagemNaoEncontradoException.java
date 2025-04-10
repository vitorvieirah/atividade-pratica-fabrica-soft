package com.example.atividade_pratica_fabrica_software.service.exceptions.personagem;

public class PersonagemNaoEncontradoException extends RuntimeException {
    public PersonagemNaoEncontradoException(Long id) {
        super("Personagem não encontrado com id: " + id);
    }
}
