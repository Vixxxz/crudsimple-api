package com.crud.crudsimple.service;

import com.crud.crudsimple.models.Cliente;
import com.crud.crudsimple.models.ClienteEndereco;
import com.crud.crudsimple.models.Endereco;
import com.crud.crudsimple.repositories.ClienteEnderecoRepository;
import com.crud.crudsimple.repositories.EnderecoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@AllArgsConstructor

@Service
public class ClienteEnderecoService {
    private final ClienteEnderecoRepository clienteEnderecoRepository;
    private final EnderecoService enderecoService;

    public ClienteEndereco findById(Long id) {
        Optional<ClienteEndereco> endereco = clienteEnderecoRepository.findById(id);
        return endereco.orElseThrow(() -> new RuntimeException(
                "Não foi possível encontrar o endereço.\nID: " + id + ", Tipo: " + ClienteEndereco.class.getName()
        ));
    }

    public List<ClienteEndereco> findByClienteId(Long idCliente) {
        return new ArrayList<ClienteEndereco>(clienteEnderecoRepository.findByCliente_idCliente(idCliente));
    }

    public List<ClienteEndereco> findByClienteNome(String nome) {
        return new ArrayList<ClienteEndereco>(clienteEnderecoRepository.findByCliente_nome(nome));
    }

    public List<ClienteEndereco> findByEnderecoCep(String cep) {
        return new ArrayList<ClienteEndereco>(clienteEnderecoRepository.findByEndereco_cep(cep));
    }

    public List<ClienteEndereco> findByEnderecoId(Long idEndereco) {
        return new ArrayList<ClienteEndereco>(clienteEnderecoRepository.findByEndereco_idEndereco(idEndereco));
    }
}
