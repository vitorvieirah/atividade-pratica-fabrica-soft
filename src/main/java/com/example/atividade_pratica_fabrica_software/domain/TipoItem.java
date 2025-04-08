package com.example.atividade_pratica_fabrica_software.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum TipoItem {
    ARMA(0, "Arma"),
    ARMADURA(1, "Armadura"),
    AMULETO(2, "Amuleto");

    private final Integer codigo;
    private final String descricao;
}
