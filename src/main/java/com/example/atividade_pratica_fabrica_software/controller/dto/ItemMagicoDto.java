package com.example.atividade_pratica_fabrica_software.controller.dto;

import com.example.atividade_pratica_fabrica_software.domain.TipoItem;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@Builder
public class ItemMagicoDto {
    private Long id;
    private String nome;
    private TipoItem tipo;
    private Integer forca;
    private Integer defesa;
}
