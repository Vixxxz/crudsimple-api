package com.crud.crudsimple.repositories;

import com.crud.crudsimple.models.Mercadoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MercadoriaRepository extends JpaRepository<Mercadoria, Long> {
}
