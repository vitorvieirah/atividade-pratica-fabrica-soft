package com.example.atividade_pratica_fabrica_software.infra.repository;

import com.example.atividade_pratica_fabrica_software.infra.entity.ItemMagicoEntity;
import com.example.atividade_pratica_fabrica_software.infra.entity.PersonagemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonagemRepository extends JpaRepository<PersonagemEntity, Long> {}
