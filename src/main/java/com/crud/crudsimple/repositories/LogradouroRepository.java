package com.crud.crudsimple.repositories;

import com.crud.crudsimple.models.Logradouro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LogradouroRepository extends JpaRepository<Logradouro, Long> {
}
