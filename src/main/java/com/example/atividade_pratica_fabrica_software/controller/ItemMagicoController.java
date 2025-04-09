package com.example.atividade_pratica_fabrica_software.controller;

import com.example.atividade_pratica_fabrica_software.controller.dto.ItemMagicoDto;
import com.example.atividade_pratica_fabrica_software.domain.ItemMagico;
import com.example.atividade_pratica_fabrica_software.mapper.ItemMapper;
import com.example.atividade_pratica_fabrica_software.service.ItemMagicoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("itens-magicos")
@RequiredArgsConstructor
public class ItemMagicoController {

    private final ItemMagicoService service;

    @PostMapping
    public ResponseEntity<ItemMagicoDto> cadastrar(@RequestBody ItemMagicoDto novoItem) {
        ItemMagico itemSalvo = service.cadastrar(ItemMapper.paraDomainDeDto(novoItem));
        ItemMagicoDto resultado = ItemMapper.paraDto(itemSalvo);

        return ResponseEntity.created(
                UriComponentsBuilder
                        .newInstance()
                        .path("/itens-magico/{id}")
                        .buildAndExpand(resultado.getId())
                        .toUri()
        ).body(resultado);
    }

    @GetMapping
    public ResponseEntity<List<ItemMagicoDto>> listar() {
        List<ItemMagico> itensMagicos = service.listar();
        List<ItemMagicoDto> resultado = itensMagicos.stream()
                .map(ItemMapper::paraDto)
                .toList();

        return ResponseEntity.ok(resultado);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ItemMagicoDto> buscarPorId(@PathVariable Long id) {
        ItemMagico itemMagico = service.buscarPorId(id);
        ItemMagicoDto resultado = ItemMapper.paraDto(itemMagico);

        return ResponseEntity.ok(resultado);
    }
}
