package com.crud.crudsimple.service;

import com.crud.crudsimple.models.Pais;
import com.crud.crudsimple.repositories.PaisRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@AllArgsConstructor
@Service
public class PaisCreateService {
    private final PaisRepository paisRepository;

    @Transactional
    public Pais createPais(Pais pais) {
        pais.setIdPais(null);
        return paisRepository.save(pais);
    }

}
