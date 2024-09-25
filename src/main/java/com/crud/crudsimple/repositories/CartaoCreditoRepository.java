package com.crud.crudsimple.repositories;

import com.crud.crudsimple.models.CartaoCredito;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CartaoCreditoRepository  extends JpaRepository<CartaoCredito,Long> {

    List<CartaoCredito> findByBandeira_idBandeira(Long bandeira);
    List<CartaoCredito> findByCliente_idCliente(Long cliente);

    Optional<CartaoCredito> findByNumCartao(String numCartao);

    List<CartaoCredito> findByPreferencial(Boolean preferencial);
}
