package com.example.atividade_pratica_fabrica_software.controller;

import com.example.atividade_pratica_fabrica_software.controller.dto.PersonagemDto;
import com.example.atividade_pratica_fabrica_software.domain.Personagem;
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

    @PutMapping("/{id}")
    public ResponseEntity<PersonagemDto> atualizarNome(@RequestBody String novoNome, @PathVariable Long id) {
        Personagem personagemAlterado = service.atualizarNome(novoNome, id);
        PersonagemDto resultado = PersonagemMapper.paraDto(personagemAlterado);

        return ResponseEntity.ok(resultado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }

}
