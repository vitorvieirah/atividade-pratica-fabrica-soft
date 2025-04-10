package com.example.atividade_pratica_fabrica_software.service;

import com.example.atividade_pratica_fabrica_software.controller.dto.ItemMagicoDto;
import com.example.atividade_pratica_fabrica_software.domain.ItemMagico;
import com.example.atividade_pratica_fabrica_software.domain.Personagem;
import com.example.atividade_pratica_fabrica_software.infra.entity.PersonagemEntity;
import com.example.atividade_pratica_fabrica_software.infra.repository.PersonagemRepository;
import com.example.atividade_pratica_fabrica_software.mapper.PersonagemMapper;
import com.example.atividade_pratica_fabrica_software.service.exceptions.personagem.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Setter
@Getter
public class PersonagemService {

    private final PersonagemRepository repository;
    private final ItemMagicoService itemMagicoService;

    public Personagem cadastrar(Personagem personagem) {
        List<ItemMagico> itensMagico = personagem.getItemMagicos().stream()
                .map(itemMagico -> this.itemMagicoService.buscarPorId(itemMagico.getId()))
                .toList();

        personagem.setItemMagicos(itensMagico);

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
            throw new PersonagemNaoEncontradoException(id);
        }

        Personagem resultado = PersonagemMapper.paraDomain(personagemEntity.get());

        return this.ajustaFocaDefesa(resultado);
    }

    public Personagem atualizarNome(String novoNome, Long id) {
        Personagem personagem = this.buscarPorId(id);

        if(!personagem.getClasse().getCodigo().equals(0)) {
            throw new AlteracaoNomePersonagemException();
        }

        personagem.setNome(novoNome);

        PersonagemEntity personagemEntity = repository.save(PersonagemMapper.paraEntity(personagem));

        return PersonagemMapper.paraDomain(personagemEntity);
    }

    public void deletar(Long id) {
        this.buscarPorId(id);
        repository.deleteById(id);
    }

    public List<ItemMagico> listaItens(Long idPersonagem) {
        Personagem personagem = this.buscarPorId(idPersonagem);
        return personagem.getItemMagicos();
    }

    public Personagem adicionarItem(ItemMagicoDto novoItem, Long id) {
        Personagem personagem = this.buscarPorId(id);
        ItemMagico item = this.itemMagicoService.buscarPorId(novoItem.getId());

        if(item.getTipo().getCodigo().equals(2)) {
            Optional<ItemMagico> itemAmuleto = personagem.getItemMagicos()
                    .stream()
                    .filter(itemFilter -> itemFilter.getTipo().getCodigo().equals(2))
                    .findFirst();

            if(itemAmuleto.isPresent()) {
                throw new AmuletoQuantidadeException();
            }
        }


        personagem.setItemMagicos(new ArrayList<>(personagem.getItemMagicos()));

        personagem.getItemMagicos().add(item);

        this.validaPontosForcaDefesa(personagem);

        PersonagemEntity personagemEntity = repository.save(PersonagemMapper.paraEntity(personagem));

        return PersonagemMapper.paraDomain(personagemEntity);
    }

    public Personagem removerItem(Long idItem, Long idPersonagem) {
        Personagem personagem = this.buscarPorId(idPersonagem);
        ItemMagico item = itemMagicoService.buscarPorId(idItem);

        personagem.setItemMagicos(new ArrayList<>(personagem.getItemMagicos()));

        personagem.getItemMagicos().remove(item);

        PersonagemEntity personagemEntity = repository.save(PersonagemMapper.paraEntity(personagem));

        return PersonagemMapper.paraDomain(personagemEntity);
    }

    public ItemMagico buscarAmuleto(Long id) {
        Personagem personagem = this.buscarPorId(id);

        List<ItemMagico> itens = personagem.getItemMagicos();

        Optional<ItemMagico> itemAmuleto = itens
                .stream()
                .filter(item -> item.getTipo().getCodigo().equals(2))
                .findFirst();

        if(itemAmuleto.isEmpty()) {
            throw new AmuletoNaoEncontradoException();
        }

        return itemAmuleto.get();
    }

    private void validaPontosForcaDefesa(Personagem personagem) {
        Personagem personagemAjustado = this.ajustaFocaDefesa(personagem);
        int soma = personagemAjustado.getForca() + personagemAjustado.getDefesa();

        if(soma > 10) {
            throw new MaximoPontosException();
        }
    }

    private void validaAmuleto(List<ItemMagico> itensMagicosPersonagem) {

        if(itensMagicosPersonagem.size() > 1) {
            List<ItemMagico> itensOrdenados = itensMagicosPersonagem
                    .stream()
                    .sorted((item1, item2) -> Integer.compare(item2.getTipo().getCodigo(), item1.getTipo().getCodigo()))
                    .toList();

            ItemMagico primeiroElemento = itensOrdenados.get(0);
            ItemMagico segundoElemento = itensOrdenados.get(1);

            if (primeiroElemento.getTipo().getCodigo().equals(segundoElemento.getTipo().getCodigo())) {
                throw new AmuletoQuantidadeException();
            }
        }
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
