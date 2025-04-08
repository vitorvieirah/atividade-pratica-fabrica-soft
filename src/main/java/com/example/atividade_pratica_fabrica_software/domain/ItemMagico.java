package com.example.atividade_pratica_fabrica_software.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@Builder
public class ItemMagico {

    private Long id;
    private String nome;
    private TipoItem tipo;
    private Integer forca;
    private Integer defesa;
}
