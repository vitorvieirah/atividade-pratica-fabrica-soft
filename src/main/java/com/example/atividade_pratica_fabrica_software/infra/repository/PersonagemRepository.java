package com.example.atividade_pratica_fabrica_software.infra.repository;

import com.example.atividade_pratica_fabrica_software.infra.entity.PersonagemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonagemRepository extends JpaRepository<PersonagemEntity, Long> {
}
