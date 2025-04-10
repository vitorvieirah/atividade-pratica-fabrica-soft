package com.example.atividade_pratica_fabrica_software.service;

import com.example.atividade_pratica_fabrica_software.domain.ItemMagico;
import com.example.atividade_pratica_fabrica_software.domain.Personagem;
import com.example.atividade_pratica_fabrica_software.infra.entity.ItemMagicoEntity;
import com.example.atividade_pratica_fabrica_software.infra.repository.ItemMagicoRepository;
import com.example.atividade_pratica_fabrica_software.mapper.ItemMapper;
import com.example.atividade_pratica_fabrica_software.service.exceptions.item.DefesaForcaException;
import com.example.atividade_pratica_fabrica_software.service.exceptions.item.ItemNaoEncontradoException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ItemMagicoService {

    private final ItemMagicoRepository repository;

    public ItemMagico cadastrar(ItemMagico itemMagico) {

        if(itemMagico.getTipo().getCodigo().equals(0)) {
            itemMagico.setDefesa(0);
        }

        if(itemMagico.getTipo().getCodigo().equals(1)) {
            itemMagico.setForca(0);
        }

        if(itemMagico.getDefesa().equals(0) && itemMagico.getForca().equals(0)) {
            throw new DefesaForcaException("Não pode ter um item com 0 defesa e 0 força !");
        }

        if(itemMagico.getForca() + itemMagico.getDefesa() > 10) {
            throw new DefesaForcaException("Defesa e força de itens, juntos não podem ultrapassar 10 pontos.");
        }

        ItemMagicoEntity itemMagicoEntity = repository.save(ItemMapper.paraEntity(itemMagico));

        return ItemMapper.paraDomain(itemMagicoEntity);
    }

    public List<ItemMagico> listar() {
        List<ItemMagicoEntity> itemMagicoEntities = repository.findAll();

        return itemMagicoEntities.stream()
                .map(ItemMapper::paraDomain)
                .toList();
    }

    public ItemMagico buscarPorId(Long id) {
        Optional<ItemMagicoEntity> itemMagicoEntity = repository.findById(id);

        if(itemMagicoEntity.isEmpty()) {
            throw new ItemNaoEncontradoException(id);
        }

        return ItemMapper.paraDomain(itemMagicoEntity.get());
    }
}
