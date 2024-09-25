package com.crud.crudsimple.repositories;

import com.crud.crudsimple.models.Uf;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UfRepository extends JpaRepository<Uf, Long> {
    List<Uf> findByPais_idPais(Long id);

    List<Uf> findByPais_pais(String pais);

    Optional<Uf> findByUf(String uf);
}
