package com.example.atividade_pratica_fabrica_software.mapper;

import com.example.atividade_pratica_fabrica_software.controller.dto.ItemMagicoDto;
import com.example.atividade_pratica_fabrica_software.domain.ItemMagico;
import com.example.atividade_pratica_fabrica_software.infra.entity.ItemMagicoEntity;

public class ItemMapper {
    public static ItemMagico paraDomain(ItemMagicoEntity entity) {
        return ItemMagico.builder()
                .id(entity.getId())
                .nome(entity.getNome())
                .tipo(entity.getTipo())
                .forca(entity.getForca())
                .defesa(entity.getDefesa())
                .build();
    }

    public static ItemMagicoEntity paraEntity(ItemMagico domain) {
        return ItemMagicoEntity.builder()
                .id(domain.getId())
                .nome(domain.getNome())
                .tipo(domain.getTipo())
                .forca(domain.getForca())
                .defesa(domain.getDefesa())
                .build();
    }

    public static ItemMagico paraDomainDeDto(ItemMagicoDto dto) {
        return ItemMagico.builder()
                .id(dto.getId())
                .nome(dto.getNome())
                .tipo(dto.getTipo())
                .forca(dto.getForca())
                .defesa(dto.getDefesa())
                .build();
    }

    public static ItemMagicoDto paraDto(ItemMagico domain) {
        return ItemMagicoDto.builder()
                .id(domain.getId())
                .nome(domain.getNome())
                .tipo(domain.getTipo())
                .forca(domain.getForca())
                .defesa(domain.getDefesa())
                .build();
    }
}
