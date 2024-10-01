package com.crud.crudsimple.service;

import com.crud.crudsimple.models.*;
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
    public void addEndereco(Long idCliente, ClienteEndereco endereco) {
        Cliente cliente = findById(idCliente);
        endereco.setIdCliEnd(null);
        cliente.getEnderecos().add(endereco);
        clienteRepository.save(cliente);
    }

    @Transactional
    public void addCartao(Long idCliente, CartaoCredito cartao) {
        Cliente cliente = findById(idCliente);
        cartao.setCliente(cliente);
        cartao.setIdCartaoCredito(null);
        cliente.getCartoes().add(cartao);
        clienteRepository.save(cliente);
    }

    @Transactional
    public void addTransactao(Long idCliente, Transacao transacao){
        Cliente cliente = findById(idCliente);
        transacao.setCliente(cliente);
        transacao.setIdTransacao(null);
        cliente.getTransacoes().add(transacao);
        clienteRepository.save(cliente);
    }

    @Transactional
    public void addLog(Long idCliente, Log log){
        Cliente cliente = findById(idCliente);
        log.setCliente(cliente);
        log.setIdLog(null);
        cliente.getLogs().add(log);
        clienteRepository.save(cliente);
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

    public void deleteEndereco(Long idCliente, Long idEndereco)
    {
        Cliente cliente = findById(idCliente);
        boolean deletado;
        deletado = cliente.getEnderecos().removeIf(e -> Objects.equals(e.getIdCliEnd(), idEndereco));
        if (!deletado) {
            throw new RuntimeException("Não foi possível excluir o endereço. Endereço não encontrado.");
        }
    }

    public void deleteCartao(Long idCliente, Long idCartao){
        Cliente cliente = findById(idCliente);
        boolean deletado;
        deletado = cliente.getCartoes().removeIf(e -> Objects.equals(e.getIdCartaoCredito(), idCartao));
        if (!deletado) {
            throw new RuntimeException("Não foi possível excluir o cartão. Cartão não encontrado.");
        }
    }

    public void deleteCliente (Long idCliente){
        Cliente cliente = findById(idCliente);
        clienteRepository.delete(cliente);
    }
}

