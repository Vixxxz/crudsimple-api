package com.crud.crudsimple.repositories;

import com.crud.crudsimple.models.CartaoCredito;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartaoCreditoRepository  extends JpaRepository<CartaoCredito,Long> {
}
