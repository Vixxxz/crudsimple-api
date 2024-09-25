package com.crud.crudsimple.repositories;

import com.crud.crudsimple.models.Bandeira;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BandeiraRepository extends JpaRepository<Bandeira, Long>
{

}
