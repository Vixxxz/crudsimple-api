package com.crud.crudsimple.repositories;

import com.crud.crudsimple.models.Bairro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BairroRepository extends JpaRepository<Bairro, Long>
{

}
