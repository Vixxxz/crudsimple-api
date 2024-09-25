package com.crud.crudsimple.repositories;

import com.crud.crudsimple.models.MercadoriaTransacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MercadoriaTransacaoRepository extends JpaRepository<MercadoriaTransacao, Long> {
}
