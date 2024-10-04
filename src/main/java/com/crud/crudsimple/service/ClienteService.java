package com.crud.crudsimple.service;

import com.crud.crudsimple.models.*;
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
    private final EnderecoService enderecoService;
    private final PaisService paisService;
    private final UfService ufService;
    private final CidadeService cidadeService;
    private final BairroService bairroService;
    private final LogradouroService logradouroService;
    private final TipoLogradouroService tpLogradouroService;

    @Transactional
    public Cliente createCliente(Cliente cliente) {
        List<ClienteEndereco> enderecosVinculados = cliente.getEnderecos();

        enderecosVinculados.forEach(enderecoVinculado -> {
            Endereco endereco = enderecoVinculado.getEndereco();

            // Verificar ou criar as entidades relacionadas
            Pais pais = paisService.findOrCreatePais(endereco.getBairro().getCidade().getUf().getPais());
            Uf uf = ufService.findOrCreateUf(endereco.getBairro().getCidade().getUf(), pais);
            Cidade cidade = cidadeService.findOrCreateCidade(endereco.getBairro().getCidade(), uf);
            Bairro bairro = bairroService.findOrCreateBairro(endereco.getBairro(), cidade);
            TipoLogradouro tipoLogradouro = tpLogradouroService.findOrCreateTipoLogradouro(endereco.getLogradouro().getTpLogradouro());
            Logradouro logradouro = logradouroService.findOrCreateLogradouro(endereco.getLogradouro(), tipoLogradouro);

            // Atualizar as referências no Endereço
            endereco = enderecoService.atualizaEndereco(endereco, pais, uf, cidade, bairro, logradouro);
            //Atualiza a tebela ClienteEndereco com o novo endereco
            enderecoVinculado.setEndereco(endereco);
        });

        return clienteRepository.save(cliente);
    }

    public Cliente findById(Long id) {
        Optional<Cliente> cliente = clienteRepository.findById(id);
        return cliente.orElseThrow(() -> new RuntimeException   (
                                                                    "Não foi possível encontrar o cliente.\nID: " + id + ", Tipo: " + Cliente.class.getName()
                                                                ));
    }

    public List<Cliente> findAll(ClienteQueryFilter clienteFilter) {
        return clienteRepository.findAll(clienteFilter.toSpecification());
    }

//    @Transactional
//    public Cliente vinculaEndereco(ClienteEndereco cliEndereco) {
//        Cliente cliente = findById(cliEndereco.getCliente().getIdCliente());
//        cliente.getEnderecos().removeIf(e -> Objects.equals(e.getIdCliEnd(), cliEndereco.getIdCliEnd()));
//        cliente.getEnderecos().add(cliEndereco);
//        return clienteRepository.save(cliente);
//    }
//
//    @Transactional
//    public void vinculaCartao(CartaoCredito cartao) {
//        Cliente cliente = findById(cartao.getCliente().getIdCliente());
//        boolean cartaoJaExistente = cliente.getCartoes().stream()
//                .anyMatch(cartaoExistente -> Objects.equals(cartaoExistente.getIdCartaoCredito(), cartao.getIdCartaoCredito()));
//
//        if (cartaoJaExistente) {
//            throw new RuntimeException("Este cartão já existe para o cliente.\nID: " + cartao.getIdCartaoCredito() + ", Tipo: " + CartaoCredito.class.getName());
//        }
//        cliente.getCartoes().add(cartao);
//        clienteRepository.save(cliente);
//    }
//
//    @Transactional
//    public void vinculaTransacao(Transacao transacao){
//        Cliente cliente = findById(transacao.getCliente().getIdCliente());
//        boolean transacaoJaExistente = cliente.getTransacoes().stream()
//                .anyMatch(transacaoExistente -> Objects.equals(transacaoExistente.getIdTransacao(), transacao.getIdTransacao()));
//        if (transacaoJaExistente) {
//            throw new RuntimeException("Esta transação já existe para o cliente.\nID: " + transacao.getIdTransacao() + ", Tipo: " + Transacao.class.getName());
//        }
//        cliente.getTransacoes().add(transacao);
//        clienteRepository.save(cliente);
//    }
//
//    @Transactional
//    public void vinculaLog(Log log){
//        Cliente cliente = findById(log.getCliente().getIdCliente());
//        boolean logJaExistente = cliente.getLogs().stream()
//                .anyMatch(logExistente -> Objects.equals(logExistente.getIdLog(), logExistente.getIdLog()));
//        if (logJaExistente) {
//            throw new RuntimeException("Este log já está vinculado com o cliente.\nID: " + log.getIdLog() + ", Tipo: " + Log.class.getName());
//        }
//        cliente.getLogs().add(log);
//        clienteRepository.save(cliente);
//    }
//
//    @Transactional
//    public Cliente updateCliente(Long idCliente, Cliente updatedCliente) {
//        Cliente cliente = findById(idCliente);
//        if (Objects.equals(idCliente, updatedCliente.getIdCliente())) {
//            cliente.setNome(updatedCliente.getNome());
//            cliente.setGenero(updatedCliente.getGenero());
//            cliente.setTipoTelefone(updatedCliente.getTipoTelefone());
//            cliente.setTelefone(updatedCliente.getTelefone());
//            cliente.setEmail(updatedCliente.getEmail());
//            cliente.setDataNascimento(updatedCliente.getDataNascimento());
//        }
//        return clienteRepository.save(cliente);
//    }
//
//    @Transactional
//    public void updateSenha (Long id, String senhaAntiga, String novaSenha){
//        Cliente cliente = findById(id);
//        if (Objects.equals(senhaAntiga, cliente.getSenha())){
//            cliente.setSenha(novaSenha);
//            clienteRepository.save(cliente);
//        } else {
//            throw new RuntimeException("Senha antiga inválida.");
//        }
//    }
//
//    //começa dando update no ClienteEndereco
//    @Transactional
//    public Cliente updateEndereco (Long idCliente, Long idClienteEndereco, ClienteEndereco newEndereco){
//        //verifica se o cliente existe
//        findById(newEndereco.getCliente().getIdCliente());
//        //verifica se o cliente corresponde
//        if (Objects.equals(newEndereco.getCliente().getIdCliente(), idCliente)){
//            return vinculaEndereco(clienteEnderecoService.updateClienteEndereco(idClienteEndereco, newEndereco));
//        }
//        else{
//            throw new RuntimeException(
//                    "Não foi possível atualizar o endereço. Cliente não corresponde com o do cliente do endereço novo ou o cliente não existe.\nID Cliente: " +
//                            idCliente + ", ID cliente no endereço: " + newEndereco.getCliente().getIdCliente() + ", Tipo: " + ClienteEndereco.class.getName()
//            );
//        }
//    }
//
//    public void deleteEndereco(Long idCliente, Long idEndereco)
//    {
//        Cliente cliente = findById(idCliente);
//        boolean deletado;
//        deletado = cliente.getEnderecos().removeIf(e -> Objects.equals(e.getIdCliEnd(), idEndereco));
//        if (!deletado) {
//            throw new RuntimeException("Não foi possível excluir o endereço. Endereço não encontrado.");
//        }
//    }
//
//    public void deleteCartao(Long idCliente, Long idCartao){
//        Cliente cliente = findById(idCliente);
//        boolean deletado;
//        deletado = cliente.getCartoes().removeIf(e -> Objects.equals(e.getIdCartaoCredito(), idCartao));
//        if (!deletado) {
//            throw new RuntimeException("Não foi possível excluir o cartão. Cartão não encontrado.");
//        }
//    }
//
//    public void deleteCliente (Long idCliente){
//        Cliente cliente = findById(idCliente);
//        clienteRepository.delete(cliente);
//    }
}

