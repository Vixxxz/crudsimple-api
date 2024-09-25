package com.crud.crudsimple.repositories;

import com.crud.crudsimple.models.Bandeira;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BandeiraRepository extends JpaRepository<Bandeira, Long>
{
    Optional<Bandeira> findByBandeira(String bandeira);
}
