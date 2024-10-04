package com.crud.crudsimple.repositories;

import com.crud.crudsimple.models.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, Long> {
    List<Endereco> findByCep(String cep);
    List<Endereco> findByBairro_bairro(String bairro);
    List<Endereco> findByBairro_idBairro(Long idBairro);
    List<Endereco> findByLogradouro_logradouro(String logradouro);
    List<Endereco> findByLogradouro_idLogradouro(Long idLogradouro);

    Optional<Endereco> findByCepAndLogradouro_logradouroAndBairro_bairro(String cep, String logradouro, String bairro);
}
