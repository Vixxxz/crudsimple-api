package com.crud.crudsimple.repositories;

import com.crud.crudsimple.models.ClienteEndereco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClienteEnderecoRepository extends JpaRepository<ClienteEndereco, Long>, JpaSpecificationExecutor<ClienteEndereco> {

    List<ClienteEndereco> findByCliente_idCliente(Long idCliente);
    
    List<ClienteEndereco> findByEndereco_cep(String cep);

    List<ClienteEndereco> findByEndereco_idEndereco(Long idEndereco);

    List<ClienteEndereco> findByCliente_nome(String nome);
}
