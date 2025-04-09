package com.example.atividade_pratica_fabrica_software.controller;

import com.example.atividade_pratica_fabrica_software.controller.dto.ItemMagicoDto;
import com.example.atividade_pratica_fabrica_software.controller.dto.PersonagemDto;
import com.example.atividade_pratica_fabrica_software.domain.ItemMagico;
import com.example.atividade_pratica_fabrica_software.domain.Personagem;
import com.example.atividade_pratica_fabrica_software.mapper.ItemMapper;
import com.example.atividade_pratica_fabrica_software.mapper.PersonagemMapper;
import com.example.atividade_pratica_fabrica_software.service.PersonagemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("personagens")
@RequiredArgsConstructor
public class PersonagemController {

    private final PersonagemService service;

    @PostMapping
    public ResponseEntity<PersonagemDto> cadastrar(@RequestBody PersonagemDto novoPersonagem) {
        Personagem personagemSalvo = service.cadastrar(PersonagemMapper.paraDomainDeDto(novoPersonagem));
        PersonagemDto resultado = PersonagemMapper.paraDto(personagemSalvo);
        return ResponseEntity.created(
                UriComponentsBuilder
                        .newInstance()
                        .path("/personagens/{id}")
                        .buildAndExpand(resultado.getId())
                        .toUri()
        ).body(resultado);
    }

    @PutMapping("/adicionar-item/{id}")
    public ResponseEntity<PersonagemDto> adicionarItem(@RequestBody ItemMagicoDto novoItem, @PathVariable Long id) {
        Personagem personagemAlterado = service.adicionarItem(novoItem, id);
        PersonagemDto resultado = PersonagemMapper.paraDto(personagemAlterado);

        return ResponseEntity.ok(resultado);
    }

    @PutMapping("/remover-item/{id}")
    public ResponseEntity<PersonagemDto> removerItem(@RequestBody Long idItem, @PathVariable("id") Long idPersonagem) {
        Personagem personagemAlterado = service.removerItem(idItem, idPersonagem);
        PersonagemDto resultado = PersonagemMapper.paraDto(personagemAlterado);

        return ResponseEntity.ok(resultado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PersonagemDto> atualizarNomeGuerreiro(@RequestBody String novoNome, @PathVariable Long id) {
        Personagem personagemAlterado = service.atualizarNome(novoNome, id);
        PersonagemDto resultado = PersonagemMapper.paraDto(personagemAlterado);

        return ResponseEntity.ok(resultado);
    }

    @GetMapping
    public ResponseEntity<List<PersonagemDto>> listar() {
        List<Personagem> personagens = service.listar();
        List<PersonagemDto> resultado = personagens.stream()
                .map(PersonagemMapper::paraDto)
                .toList();

        return ResponseEntity.ok(resultado);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PersonagemDto> buscarPorId(@PathVariable Long id) {
        Personagem personagem = service.buscarPorId(id);
        PersonagemDto resultado = PersonagemMapper.paraDto(personagem);

        return ResponseEntity.ok(resultado);
    }

    @GetMapping("/itens/{id}")
    public ResponseEntity<List<ItemMagicoDto>> listarPorPersonagem(@PathVariable Long id) {
        List<ItemMagico> itensPersonagem = service.listaItens(id);
        List<ItemMagicoDto> resultado = itensPersonagem.stream()
                .map(ItemMapper::paraDto)
                .toList();

        return ResponseEntity.ok(resultado);
    }

    @GetMapping("/amuleto/{id}")
    public ResponseEntity<ItemMagicoDto> buscarAmuleto(@PathVariable Long id) {
        ItemMagico itemMagico = service.buscarAmuleto(id);
        ItemMagicoDto resultado = ItemMapper.paraDto(itemMagico);

        return ResponseEntity.ok(resultado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }

}
