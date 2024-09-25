package com.crud.crudsimple.repositories;

import com.crud.crudsimple.models.Log;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface LogRepository extends JpaRepository<Log, Long> {
    List<Log> findByAno(LocalDate ano);
    List<Log> findByMes(LocalDate mes);
    List<Log> findByDia(LocalDate dia);
    List<Log> findByCliente_idCliente(Long id);
    List<Log> findByOperacao(String operacao);
    List<Log> findByDiaBetween(LocalDate startDate, LocalDate endDate);
}
