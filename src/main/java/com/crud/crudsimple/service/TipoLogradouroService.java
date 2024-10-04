package com.crud.crudsimple.service;

import com.crud.crudsimple.models.Logradouro;
import com.crud.crudsimple.models.TipoLogradouro;
import com.crud.crudsimple.repositories.TipoLogradouroRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
@AllArgsConstructor
@Service
public class TipoLogradouroService {
    private final TipoLogradouroRepository tipoLogradouroRepository;
    private final TipoLogradouroService tipoLogradouroService;

    @Transactional
    public TipoLogradouro createTipoLogradouro(TipoLogradouro tpLogradouro) {
        tpLogradouro.setId(null);
        return tipoLogradouroRepository.save(tpLogradouro);
    }

    @Transactional
    public Logradouro addLogradouro(Long idTpLogradouro, Logradouro logradouro) {
        TipoLogradouro tpLogradouro = findById(idTpLogradouro);
        tpLogradouro.getLogradouros().add(logradouro);
        tipoLogradouroRepository.save(tpLogradouro);
        return logradouro;
    }

    public TipoLogradouro findById(Long idTipoLogradouro) {
        Optional<TipoLogradouro> tpLogradouro = tipoLogradouroRepository.findById(idTipoLogradouro);
        return tpLogradouro.orElseThrow(() -> new RuntimeException(
                "Não foi possível encontrar o tipo de logradouro.\nID: " + idTipoLogradouro + ", Tipo: " + TipoLogradouro.class.getName()
        ));
    }

    public Optional<TipoLogradouro> verificaExistencia(String tpLogradouro) {
        return tipoLogradouroRepository.findByTpLogradouro(tpLogradouro);
    }

    public TipoLogradouro findOrCreateTipoLogradouro(TipoLogradouro tipoLogradouro) {
        return verificaExistencia(tipoLogradouro.getTpLogradouro())
                .orElseGet(() -> tipoLogradouroService.createTipoLogradouro(tipoLogradouro));
    }
}

//    public void updateTipoLogradouro (Long idTipoLogradouro, TipoLogradouro newTpLogradouro) {
//        TipoLogradouro tpLogradouro = findById(idTipoLogradouro);
//        if(Objects.equals(tpLogradouro.getId(), newTpLogradouro.getId())) {
//            tpLogradouro.setTpLogradouro(newTpLogradouro.getTpLogradouro());
//            tipoLogradouroRepository.save(tpLogradouro);
//        }
//        else{
//            throw new RuntimeException("Os IDs dos tipo de logradouro não correspondem.\nID: " + idTipoLogradouro + ", Novo ID: " + newTpLogradouro.getId() + ", Tipo: " + TipoLogradouro.class.getName());
//        }
//    }

