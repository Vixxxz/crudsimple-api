package com.crud.crudsimple.service;

import com.crud.crudsimple.models.Pais;
import com.crud.crudsimple.models.Uf;
import com.crud.crudsimple.repositories.PaisRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
@AllArgsConstructor
@Service
public class PaisService {
    private final PaisRepository paisRepository;
    private final PaisService paisService;

    @Transactional
    public Pais createPais(Pais pais) {
        pais.setIdPais(null);
        return paisRepository.save(pais);
    }

    @Transactional
    public Uf addUf(Long idPais, Uf uf){
        Pais pais = findById(idPais);
        pais.getUfs().add(uf);
        paisRepository.save(pais);
        return uf;
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
                .orElseGet(() -> paisService.createPais(pais)); //Auto invoca a propria classe, pois o spring não consegue implementar o @Transactional em metodos autoinvocados
    }
}
