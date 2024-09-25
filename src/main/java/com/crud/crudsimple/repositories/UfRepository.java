package com.crud.crudsimple.repositories;

import com.crud.crudsimple.models.Uf;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UfRepository extends JpaRepository<Uf, Long> {
}
