package com.example.atividade_pratica_fabrica_software.service.exceptions.item;

public class ItemNaoEncontradoException extends RuntimeException {
    public ItemNaoEncontradoException(Long id) {
        super("Item não encontrado pelo id: " + id);
    }
}
