package com.example.atividade_pratica_fabrica_software.service;

import com.example.atividade_pratica_fabrica_software.domain.ItemMagico;
import com.example.atividade_pratica_fabrica_software.domain.Personagem;
import com.example.atividade_pratica_fabrica_software.infra.entity.PersonagemEntity;
import com.example.atividade_pratica_fabrica_software.infra.repository.PersonagemRepository;
import com.example.atividade_pratica_fabrica_software.mapper.PersonagemMapper;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Setter
@Getter
public class PersonagemService {

    private final PersonagemRepository repository;

    public Personagem cadastrar(Personagem personagem) {
        this.validaPontosForcaDefesa(personagem);
        this.validaAmuleto(personagem.getItemMagicos());

        PersonagemEntity personagemEntity = repository.save(PersonagemMapper.paraEntity(personagem));

        return PersonagemMapper.paraDomain(personagemEntity);
    }

    public List<Personagem> listar() {
        List<PersonagemEntity> personagemEntities = repository.findAll();
        List<Personagem> resultado = personagemEntities.stream()
                .map(PersonagemMapper::paraDomain)
                .toList();

        return resultado.stream()
                .map(this::ajustaFocaDefesa)
                .toList();
    }

    public Personagem buscarPorId(Long id) {
        Optional<PersonagemEntity> personagemEntity = repository.findById(id);

        if(personagemEntity.isEmpty()) {
            throw new RuntimeException("Personagem não encontrado com id: " + id);
        }

        Personagem resultado = PersonagemMapper.paraDomain(personagemEntity.get());

        return this.ajustaFocaDefesa(resultado);
    }

    public Personagem atualizarNome(String novoNome, Long id) {
        Personagem personagem = this.buscarPorId(id);

        personagem.setNome(novoNome);

        PersonagemEntity personagemEntity = repository.save(PersonagemMapper.paraEntity(personagem));

        return PersonagemMapper.paraDomain(personagemEntity);
    }

    public void deletar(Long id) {
        this.buscarPorId(id);
        repository.deleteById(id);
    }

    private void validaPontosForcaDefesa(Personagem personagem) {
        Personagem personagemAjustado = this.ajustaFocaDefesa(personagem);
        int soma = personagemAjustado.getForca() + personagemAjustado.getDefesa();

        if(soma > 10) {
            throw new RuntimeException("Pontos máximo por persoangem excedido.");
        }
    }
    private void validaAmuleto(List<ItemMagico> itensMagicosPersonagem) {
        List<ItemMagico> itensOrdenados = itensMagicosPersonagem.stream().sorted(item -> item.getTipo().getCodigo()).toList();
    }

    private Personagem ajustaFocaDefesa(Personagem personagem) {
        Integer forcaPersonagem = personagem.getForca();
        Integer defesaPersonagem = personagem.getDefesa();
        Integer forcaTotalItem = 0;
        Integer defesaTotalItem = 0;

        List<ItemMagico> itensMagicos = personagem.getItemMagicos();

        for (ItemMagico itensMagico : itensMagicos) {
            forcaTotalItem += itensMagico.getForca();
            defesaTotalItem += itensMagico.getDefesa();
        }

        personagem.setForca(forcaPersonagem + forcaTotalItem);
        personagem.setDefesa(defesaPersonagem + defesaTotalItem);

        return personagem;
    }
}
