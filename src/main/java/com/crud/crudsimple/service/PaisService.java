package com.crud.crudsimple.service;

import com.crud.crudsimple.models.Pais;
import com.crud.crudsimple.models.Uf;
import com.crud.crudsimple.repositories.PaisRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
@AllArgsConstructor
@Service
public class PaisService {
    private final PaisRepository paisRepository;
    private final PaisCreateService paisCreateService;

    @Transactional
    public Uf addUf(Long idPais, Uf uf){
        Pais pais = findById(idPais);
        uf.setPais(pais);
        pais.getUfs().add(uf);
        pais = paisRepository.save(pais);
        return pais.getUfs().stream()
                .filter(ufExistente -> ufExistente.equals(uf))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("UF não encontrada após salvar."));
    }

    public Pais findById(Long idPais){
        Optional<Pais> pais = paisRepository.findById(idPais);
        return pais.orElseThrow(() -> new RuntimeException("Não foi possível encontrar o pais.\nID: " + idPais + ", TIPO: " +Pais.class.getName()));
    }


    public Optional<Pais> verificaExistencia(String pais) {
        return paisRepository.findByPais(pais);
    }


    public Pais findOrCreatePais(Pais pais) {
        return verificaExistencia(pais.getPais())
                .orElseGet(() -> paisCreateService.createPais(pais));
    }
}
