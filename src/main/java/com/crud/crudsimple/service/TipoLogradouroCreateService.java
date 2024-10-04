package com.crud.crudsimple.service;

import com.crud.crudsimple.models.TipoLogradouro;
import com.crud.crudsimple.repositories.TipoLogradouroRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@AllArgsConstructor
@Service
public class TipoLogradouroCreateService {
    private final TipoLogradouroRepository tipoLogradouroRepository;

    @Transactional
    public TipoLogradouro createTipoLogradouro(TipoLogradouro tpLogradouro) {
        tpLogradouro.setId(null);
        return tipoLogradouroRepository.save(tpLogradouro);
    }
}
