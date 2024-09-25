package com.crud.crudsimple.repositories;

import com.crud.crudsimple.models.Cidade;
import com.crud.crudsimple.models.Uf;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CidadeRepository extends JpaRepository<Cidade, Long> {
    List<Cidade> findByUf_idUf(Long id);

    List<Cidade> findByUf_uf(String nome);

    Optional<Cidade> findByCidade(String cidade);
}
