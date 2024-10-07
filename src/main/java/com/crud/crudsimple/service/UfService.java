package com.crud.crudsimple.service;

import com.crud.crudsimple.models.Cidade;
import com.crud.crudsimple.models.Pais;
import com.crud.crudsimple.models.Uf;
import com.crud.crudsimple.repositories.UfRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;
import java.util.Optional;
@AllArgsConstructor
@Service
public class UfService {
    private final UfRepository ufRepository;
    private final PaisService paisService;

    @Transactional
    public Uf createUf(Uf uf){
        uf.setIdUf(null);
        return ufRepository.save(uf);
    }

    @Transactional
    public Cidade addCidade(Long idUf, Cidade cidade){
        Uf uf = findById(idUf);
        cidade.setUf(uf);
        uf.getCidades().add(cidade);
        ufRepository.save(uf);
        return uf.getCidades().stream()
                .filter(cidadeExistente -> cidadeExistente.equals(cidade))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Cidade não encontrada"));
    }

    public Uf findById(Long idUf){
        Optional<Uf> uf = ufRepository.findById(idUf);
        return uf.orElseThrow(() -> new RuntimeException(
                "Não foi possível encontrar a UF.\nID: " + idUf + ", UF: " + Uf.class.getName()
        ));
    }

    public Optional<Uf> verificaExistencia(String uf){
        return ufRepository.findByUf(uf);
    }

    public Uf findOrCreateUf(Uf uf, Pais pais) {
        return verificaExistencia(uf.getUf())
                .orElseGet(() -> paisService.addUf(pais.getIdPais(), uf));
    }
}
