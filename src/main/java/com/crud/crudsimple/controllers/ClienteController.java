package com.crud.crudsimple.controllers; // Pacote onde a classe está localizada

import com.crud.crudsimple.models.Cliente; // Importa a classe Cliente
import com.crud.crudsimple.queryFilters.ClienteQueryFilter; // Importa a classe ClienteQueryFilter
import com.crud.crudsimple.service.ClienteService; // Importa a classe ClienteService
import jakarta.validation.Valid; // Importa a anotação para validação
import lombok.AllArgsConstructor; // Importa a anotação para gerar um construtor com todos os parâmetros
import org.springframework.http.ResponseEntity; // Importa a classe ResponseEntity para construir respostas HTTP
import org.springframework.validation.annotation.Validated; // Importa a anotação para validação em controllers
import org.springframework.web.bind.annotation.*; // Importa as anotações para trabalhar com controllers REST
import org.springframework.web.servlet.support.ServletUriComponentsBuilder; // Importa a classe para construir URIs

import java.net.URI; // Importa a classe URI
import java.util.List; // Importa a classe List

@AllArgsConstructor // Gera um construtor com todos os parâmetros da classe

@RestController // Indica que a classe é um controller REST
@RequestMapping("/cliente") // Mapeia a URL base para esta classe
@Validated // Habilita validações na classe
public class ClienteController {
    private final ClienteService clienteService; // Declaração do serviço que manipula clientes

    @PostMapping(consumes = "application/json") // Mapeia requisições POST com tipo de conteúdo JSON
    @Validated(Cliente.CreateCliente.class) // Aplica validações específicas para a criação de clientes
    public ResponseEntity<Void> createCliente(@Valid @RequestBody Cliente cliente){ // Metodo para criar um cliente
        clienteService.createCliente(cliente); // Chama o serviço para criar um cliente
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest() // Constrói a URI do novo recurso criado
                .path("/{id}").buildAndExpand(cliente.getIdCliente()).toUri(); // Adiciona o ID do cliente à URI
        return ResponseEntity.created(uri).build(); // Retorna uma resposta com status 201 (Criado) e a URI do cliente
    }

    @GetMapping // Mapeia requisições GET para buscar todos os clientes
    public ResponseEntity<List<Cliente>> findAll(ClienteQueryFilter clienteQueryFilter){ // Metodo para encontrar todos os clientes
        List<Cliente> clientes = clienteService.findAll(clienteQueryFilter); // Chama o serviço para encontrar todos os clientes com filtros
        return ResponseEntity.ok().body(clientes); // Retorna a lista de clientes com status 200 (OK)
    }

    @GetMapping("/{id}") // Mapeia requisições GET para buscar um cliente específico pelo ID
    public ResponseEntity<Cliente> findById(@PathVariable Long id){ // Metodo para encontrar um cliente pelo ID
        Cliente cliente = clienteService.findById(id); // Chama o serviço para encontrar o cliente pelo ID
        return ResponseEntity.ok().body(cliente); // Retorna o cliente encontrado com status 200 (OK)
    }
}
