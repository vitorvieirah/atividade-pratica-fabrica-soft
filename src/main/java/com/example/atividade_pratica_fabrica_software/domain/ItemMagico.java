package com.example.atividade_pratica_fabrica_software.domain;

import lombok.*;

@AllArgsConstructor
@Getter
@Setter
@Builder
@EqualsAndHashCode
public class ItemMagico {

    private Long id;
    private String nome;
    private TipoItem tipo;
    private Integer forca;
    private Integer defesa;
}
