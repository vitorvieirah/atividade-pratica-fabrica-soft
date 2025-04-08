package com.example.atividade_pratica_fabrica_software.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Classe {
    GUERREIRO(0, "Guerreiro"),
    MAGO(1, "Mago"),
    ARQUEIRO(2, "Arqueiro"),
    LADINO(3, "Ladino"),
    BARDO(4, "Bardo");
    private final Integer codigo;
    private final String descricao;
}
