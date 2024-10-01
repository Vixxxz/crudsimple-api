package com.crud.crudsimple.service;

import com.crud.crudsimple.models.Cliente;
import com.crud.crudsimple.models.ClienteEndereco;
import com.crud.crudsimple.queryFilters.ClienteQueryFilter;
import com.crud.crudsimple.repositories.ClienteRepository;
import org.springframework.stereotype.Service;
import lombok.AllArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
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
        cliente = clienteRepository.save(cliente);
        return cliente;
    }

    @Transactional
    public Cliente updateCliente(Long id, Cliente cliente) {
        Cliente newCliente = findById(id);
        if (Objects.equals(id, cliente.getIdCliente())) {
            newCliente.setNome(cliente.getNome());
            newCliente.setGenero(cliente.getGenero());
            newCliente.setTipoTelefone(cliente.getTipoTelefone());
            newCliente.setTelefone(cliente.getTelefone());
            newCliente.setEmail(cliente.getEmail());
            newCliente.setDataNascimento(cliente.getDataNascimento());
        }
        return clienteRepository.save(newCliente);
    }

    @Transactional
    public void updateSenha (Long id, String senhaAntiga, String novaSenha){
        Cliente cliente = findById(id);
        if (Objects.equals(senhaAntiga, cliente.getSenha())){
            cliente.setSenha(novaSenha);
            clienteRepository.save(cliente);
        } else {
            throw new RuntimeException("Senha antiga inválida.");
        }
    }

    @Transactional
    public void updateEndereco (Long idCliente, Long idEndereco, ClienteEndereco endereco){
        Cliente cliente = findById(idCliente);
        //todo: implementar o ClienteEderecoService para poder buscar um endereco pelo id do cliente aqui
//        ClienteEndereco newEndereco = clienteEnderecoService.findById(idEndereco);
//        if (Objects.equals(idEndereco, endereco.getIdCliEnd())){
//            newEndereco.setNumero(endereco.getNumero());
//            newEndereco.setTipoEndereco(endereco.getTipoEndereco());
//            newEndereco.setTipoResidencia(endereco.getTipoResidencia());
//            newEndereco.setObservacoes(endereco.getObservacoes());
//            newEndereco.setEndereco(endereco.getEndereco());
//            // Substitui o endereço atualizado na lista de endereços do cliente
//            cliente.getEnderecos().removeIf(e -> Objects.equals(e.getIdCliEnd(), idEndereco));
//            cliente.getEnderecos().add(newEndereco);
//            clienteRepository.save(cliente);
//        }
    }
}

