package com.crud.crudsimple.repositories;

import com.crud.crudsimple.models.TipoLogradouro;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TipoLogradouroRepository extends JpaRepository<TipoLogradouro, Long> {

    Optional<TipoLogradouro> findByTpLogradouro(String tpLogradouro);

}
