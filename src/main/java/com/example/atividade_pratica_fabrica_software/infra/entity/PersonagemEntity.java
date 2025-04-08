package com.example.atividade_pratica_fabrica_software.infra.entity;


import com.example.atividade_pratica_fabrica_software.domain.Classe;
import com.example.atividade_pratica_fabrica_software.domain.ItemMagico;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity(name = "Personagem")
@Table(name = "personagens")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class PersonagemEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;

    @Column(name = "nome_aventureiro")
    private String nomeAventureiro;

    @Enumerated(EnumType.ORDINAL)
    private Classe classe;
    private Integer level;
    @ManyToMany
    private List<ItemMagicoEntity> itemMagicos;
    private Integer forca;
    private Integer defesa;
}
