package com.crud.crudsimple.repositories;

import com.crud.crudsimple.models.Logradouro;
import com.crud.crudsimple.models.TipoLogradouro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.List;

@Repository
public interface LogradouroRepository extends JpaRepository<Logradouro, Long> {
    List<Logradouro> findByTpLogradouro_id(Long id);
    List<Logradouro> findByTpLogradouro_tpLogradouro(String tpLogradouro);
    List<Logradouro> findByLogradouro(String logradouro);

    Optional<Logradouro> findByLogradouroAndTpLogradouro(String logradouro, TipoLogradouro tpLogradouro);
}
