package com.crud.crudsimple.service;

import com.crud.crudsimple.models.Bairro;
import com.crud.crudsimple.models.Cidade;
import com.crud.crudsimple.models.Endereco;
import com.crud.crudsimple.repositories.BairroRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.Optional;

@AllArgsConstructor
@Service
public class BairroService {
    private final BairroRepository bairroRepository;
    private final CidadeService cidadeService;

    @Transactional
    public Bairro createBairro(Bairro bairro){
        bairro.setIdBairro(null);
        return bairroRepository.save(bairro);
    }

    @Transactional
    public Endereco addEndereco(Long idBairro, Endereco endereco) {
        Bairro bairro = findById(idBairro);
        endereco.setBairro(bairro);
        bairro.getEnderecos().add(endereco);
        bairroRepository.save(bairro);
        return bairro.getEnderecos().stream()
                .filter(enderecoExistente -> enderecoExistente.equals(endereco))
                .findFirst()
                .orElseThrow(()->new RuntimeException("Endereço não encontrado"));
    }

    public Bairro findById(Long idBairro) {
        Optional<Bairro> bairro = bairroRepository.findById(idBairro);
        return bairro.orElseThrow(() -> new RuntimeException(
                "Não foi possível encontrar o bairro.\nID: " + idBairro + ", Tipo: " + Bairro.class.getName()
        ));
    }

    public Optional<Bairro> verificaExistencia(String bairro){
        return bairroRepository.findByBairro(bairro);
    }

    public Bairro findOrCreateBairro(Bairro bairro, Cidade cidade) {
        return verificaExistencia(bairro.getBairro())
                .orElseGet(() -> cidadeService.addBairro(cidade.getIdCidade(), bairro));
    }
}
