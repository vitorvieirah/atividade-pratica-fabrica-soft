package com.example.atividade_pratica_fabrica_software.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@Getter
@Setter
@Builder
public class Personagem {

    private Long id;
    private String nome;
    private String nomeAventureiro;
    private Classe classe;
    private Integer level;
    private List<ItemMagico> itemMagicos;
    private Integer forca;
    private Integer defesa;
}
