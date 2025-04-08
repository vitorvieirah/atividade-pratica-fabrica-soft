package com.example.atividade_pratica_fabrica_software.mapper;

import com.example.atividade_pratica_fabrica_software.controller.dto.PersonagemDto;
import com.example.atividade_pratica_fabrica_software.domain.Personagem;
import com.example.atividade_pratica_fabrica_software.infra.entity.PersonagemEntity;

public class PersonagemMapper {

    public static Personagem paraDomain(PersonagemEntity entity) {
        return Personagem.builder()
                .id(entity.getId())
                .nome(entity.getNome())
                .nomeAventureiro(entity.getNomeAventureiro())
                .classe(entity.getClasse())
                .level(entity.getLevel())
                .itemMagicos(entity.getItemMagicos()
                        .stream()
                        .map(ItemMapper::paraDomain)
                        .toList()
                )
                .forca(entity.getForca())
                .defesa(entity.getDefesa())
                .build();
    }

    public static PersonagemEntity paraEntity(Personagem domain) {
        return PersonagemEntity.builder()
                .id(domain.getId())
                .nome(domain.getNome())
                .nomeAventureiro(domain.getNomeAventureiro())
                .classe(domain.getClasse())
                .level(domain.getLevel())
                .itemMagicos(domain.getItemMagicos()
                        .stream()
                        .map(ItemMapper::paraEntity)
                        .toList()
                )
                .forca(domain.getForca())
                .defesa(domain.getDefesa())
                .build();
    }

    public static Personagem paraDomainDeDto(PersonagemDto dto) {
        return Personagem.builder()
                .id(dto.getId())
                .nome(dto.getNome())
                .nomeAventureiro(dto.getNomeAventureiro())
                .classe(dto.getClasse())
                .level(dto.getLevel())
                .itemMagicos(dto.getItemMagicos()
                        .stream()
                        .map(ItemMapper::paraDomainDeDto)
                        .toList()
                )
                .forca(dto.getForca())
                .defesa(dto.getDefesa())
                .build();
    }

    public static PersonagemDto paraDto(Personagem domain) {
        return PersonagemDto.builder()
                .id(domain.getId())
                .nome(domain.getNome())
                .nomeAventureiro(domain.getNomeAventureiro())
                .classe(domain.getClasse())
                .level(domain.getLevel())
                .itemMagicos(domain.getItemMagicos()
                        .stream()
                        .map(ItemMapper::paraDto)
                        .toList()
                )
                .forca(domain.getForca())
                .defesa(domain.getDefesa())
                .build();
    }
}
