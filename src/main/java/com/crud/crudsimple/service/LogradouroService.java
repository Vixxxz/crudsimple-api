package com.crud.crudsimple.service;

import com.crud.crudsimple.models.Endereco;
import com.crud.crudsimple.models.Logradouro;
import com.crud.crudsimple.models.TipoLogradouro;
import com.crud.crudsimple.repositories.LogradouroRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@AllArgsConstructor

@Service
public class LogradouroService {
    private final LogradouroRepository logradouroRepository;
    private final TipoLogradouroService tipoLogradouroService;

    // todo: Implementar métodos para busca de logradouros

    @Transactional
    public Logradouro createLogradouro(Logradouro logradouro) {
        logradouro.setIdLogradouro(null);
        return logradouroRepository.save(logradouro);
    }

    @Transactional
    public Endereco addEndereco(Long idLogradouro, Endereco endereco) {
        Logradouro logradouro = findById(idLogradouro);
        endereco.setLogradouro(logradouro);
        logradouro.getEndereco().add(endereco);
        logradouroRepository.save(logradouro);
        return logradouro.getEndereco().stream()
                .filter(enderecoExistente -> enderecoExistente.equals(endereco))
                .findFirst()
                .orElseThrow(()->new RuntimeException("Endereço não encontrado"));
    }

    public Logradouro findById(Long idLogradouro) {
        Optional<Logradouro> logradouro = logradouroRepository.findById(idLogradouro);
        return logradouro.orElseThrow(() -> new RuntimeException("Não foi possível encontrar o logradouro.\nID: " + idLogradouro + ", Tipo: " + Logradouro.class.getName()));
    }

    public Optional<Logradouro> verificaExistencia(String logradouro, TipoLogradouro tpLogradouro) {
        return logradouroRepository.findByLogradouroAndTpLogradouro(logradouro, tpLogradouro);
    }

    public Logradouro findOrCreateLogradouro(Logradouro logradouro, TipoLogradouro tipoLogradouro) {
        return verificaExistencia(logradouro.getLogradouro(), tipoLogradouro)
                .orElseGet(() -> tipoLogradouroService.addLogradouro(tipoLogradouro.getId(), logradouro));
    }

//    @Transactional
//    public Logradouro updateLogradouro(Long idLogradouro, Logradouro newLogradouro){
//        Logradouro logradouro = findById(idLogradouro);
//        if (Objects.equals(logradouro.getIdLogradouro(), newLogradouro.getIdLogradouro())){
//            logradouro.setLogradouro(newLogradouro.getLogradouro());
//            //apenas muda qual o tipo de logradouro, não atualiza nada na tabela tipo logradouro. Se mudar o tipo de logradouro na tabela referente a ele, todos os logradouros associados tambem vao mudar o tipo de logradouro
//            logradouro.setTpLogradouro(newLogradouro.getTpLogradouro());
//            return logradouroRepository.save(logradouro);
//        }
//        else{
//            throw new RuntimeException("Os IDs dos logradouros não correspondem.\nID: " + idLogradouro + ", Novo ID: " + newLogradouro.getIdLogradouro() + ", Tipo: " + Logradouro.class.getName());
//        }
//    }
}
