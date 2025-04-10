package com.example.atividade_pratica_fabrica_software.service.exceptions.personagem;

public class AmuletoQuantidadeException extends RuntimeException {
    public AmuletoQuantidadeException() {
        super("Personagem pode ter apenas 1 item do tipo Amuleto");
    }
}
