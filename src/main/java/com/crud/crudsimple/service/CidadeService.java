package com.crud.crudsimple.service;

import com.crud.crudsimple.models.Bairro;
import com.crud.crudsimple.models.Cidade;
import com.crud.crudsimple.models.Uf;
import com.crud.crudsimple.repositories.CidadeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;
import java.util.Optional;

@AllArgsConstructor

@Service
public class CidadeService {
    private final CidadeRepository cidadeRepository;
    private final UfService ufService;

    @Transactional
    public Cidade createCidade(Cidade cidade) {
        cidade.setIdCidade(null);
        return cidadeRepository.save(cidade);
    }

    @Transactional
    public Bairro addBairro(Long idCidade, Bairro bairro) {
        Cidade cidade = findById(idCidade);
        bairro.setCidade(cidade);
        cidade.getBairros().add(bairro);
        cidadeRepository.save(cidade);
        return cidade.getBairros().stream()
                .filter(bairroExistente -> bairroExistente.equals(bairro))
                .findFirst()
                .orElseThrow(()->new RuntimeException("Não foi possível encontrar o bairro."));
    }

    public Cidade findById(Long idCidade) {
        Optional<Cidade> cidade = cidadeRepository.findById(idCidade);
        return cidade.orElseThrow(() -> new RuntimeException("Cidade não encontrada.\nTD: " + idCidade + ", TIPO: " + Cidade.class.getName()));
    }

    public Optional<Cidade> verificaExistencia(String cidade){
        return cidadeRepository.findByCidade(cidade);
    }

    public Cidade findOrCreateCidade(Cidade cidade, Uf uf) {
        return verificaExistencia(cidade.getCidade())
                .orElseGet(() -> ufService.addCidade(uf.getIdUf(), cidade));
    }
}
