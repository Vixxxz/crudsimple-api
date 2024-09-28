package com.crud.crudsimple.repositories;

import com.crud.crudsimple.models.ClienteEndereco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteEnderecoRepository extends JpaRepository<ClienteEndereco, Long>, JpaSpecificationExecutor<ClienteEndereco> {
}
