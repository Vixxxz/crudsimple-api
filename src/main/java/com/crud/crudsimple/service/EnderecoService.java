package com.crud.crudsimple.service;

import com.crud.crudsimple.models.*;
import com.crud.crudsimple.repositories.EnderecoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@AllArgsConstructor

@Service
public class EnderecoService {
    private final EnderecoRepository enderecoRepository;

    @Transactional
    public Endereco createEndereco(Endereco endereco) {
        endereco.setIdEndereco(null);
        return enderecoRepository.save(endereco);
    }

    @Transactional
    public Endereco atualizaEndereco(Endereco endereco, Pais pais, Uf uf, Cidade cidade, Bairro bairro, Logradouro logradouro, ClienteEndereco enderecoVinculado) {
        endereco.getBairro().getCidade().getUf().setPais(pais);
        endereco.getBairro().getCidade().setUf(uf);
        endereco.getBairro().setCidade(cidade);
        endereco.setBairro(bairro);
        endereco.setLogradouro(logradouro);
        endereco.getEnderecos().add(enderecoVinculado); // Associa o endereço ao cliente

        // Salva o endereço, seja novo ou existente
        return enderecoRepository.save(endereco);
    }

    public Endereco findById(Long idEndereco){
        Optional<Endereco> endereco = enderecoRepository.findById(idEndereco);
        return endereco.orElseThrow(() -> new RuntimeException(
                "Não foi possível encontrar o endereço.\nID: " + idEndereco + ", Tipo: " + Endereco.class.getName()
        ));
    }

    public Endereco findByCepAndLogradouroAndBairro(String cep, String logradouro, String bairro) {
        Optional<Endereco> endereco = enderecoRepository.findByCepAndLogradouro_logradouroAndBairro_bairro(cep, logradouro, bairro);
        return endereco.orElseThrow(() -> new RuntimeException(
                "Não foi possível encontrar o endereço.\nCEP: " + cep + ", Logradouro: " + logradouro + ", Bairro: " + bairro + ", Tipo: " + Endereco.class.getName()
        ));
    }

//    @Transactional
//    public void vinculaClienteEndereco(ClienteEndereco endereco){
//        Endereco newEndereco = findById(endereco.getEndereco().getIdEndereco());
//        newEndereco.getEnderecos().add(endereco);
//        enderecoRepository.save(newEndereco);
//    }
//
//    @Transactional
//    public Endereco updateEndereco(Long idEndereco, Endereco newEndereco){
//        Endereco endereco = findById(idEndereco);
//        if(Objects.equals(endereco.getIdEndereco(), newEndereco.getIdEndereco())){
//            endereco.setCep(newEndereco.getCep());
//            endereco.setLogradouro(logradouroService.updateLogradouro(endereco.getLogradouro().getIdLogradouro(), newEndereco.getLogradouro()));
//            endereco.setBairro(newEndereco.getBairro());
//            return enderecoRepository.save(endereco);
//        }
//        else{
//            throw new RuntimeException("Os IDs dos endereços não correspondem.\nID: " + idEndereco + ", Novo ID: " + newEndereco.getIdEndereco() + ", Tipo: " + Endereco.class.getName());
//        }
//    }
}
