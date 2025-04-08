package com.example.atividade_pratica_fabrica_software.infra.entity;

import com.example.atividade_pratica_fabrica_software.domain.TipoItem;
import jakarta.persistence.*;
import lombok.*;

@Entity(name = "ItemMagico")
@Table(name = "itens_magicos")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ItemMagicoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;

    @Enumerated(EnumType.ORDINAL)
    private TipoItem tipo;
    private Integer forca;
    private Integer defesa;
}
