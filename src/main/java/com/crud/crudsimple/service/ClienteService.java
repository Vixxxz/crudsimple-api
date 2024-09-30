package com.crud.crudsimple.service;

import com.crud.crudsimple.models.Cliente;
import com.crud.crudsimple.queryFilters.ClienteQueryFilter;
import com.crud.crudsimple.repositories.ClienteRepository;
import org.springframework.stereotype.Service;
import lombok.AllArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor

@Service
public class ClienteService {
    private final ClienteRepository clienteRepository;
    private final ClienteQueryFilter clienteQueryFilter;

    public Cliente findById(Long id) {
        Optional<Cliente> cliente = clienteRepository.findById(id);
        return cliente.orElseThrow(() -> new RuntimeException   (
                                                                    "Não foi possível encontrar o cliente.\nID: " + id + ", Tipo: " + Cliente.class.getName()
                                                                ));
    }

    public List<Cliente> findAll() {
        return clienteRepository.findAll(clienteQueryFilter.toSpecification());
    }

    @Transactional
    public Cliente CreateCliente(Cliente cliente) {
        cliente.setIdCliente(null);
        return clienteRepository.save(cliente);
    }


}

