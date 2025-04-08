package com.example.atividade_pratica_fabrica_software.controller.dto;

import com.example.atividade_pratica_fabrica_software.domain.Classe;
import com.example.atividade_pratica_fabrica_software.domain.ItemMagico;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@Getter
@Setter
@Builder
public class PersonagemDto {private Long id;
    private String nome;
    private String nomeAventureiro;
    private Classe classe;
    private Integer level;
    private List<ItemMagicoDto> itemMagicos;
    private Integer forca;
    private Integer defesa;
}
