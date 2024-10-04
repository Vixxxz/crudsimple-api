package com.crud.crudsimple.controllers;

import com.crud.crudsimple.models.Cliente;
import com.crud.crudsimple.queryFilters.ClienteQueryFilter;
import com.crud.crudsimple.service.ClienteService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@AllArgsConstructor


@RestController
@RequestMapping("/cliente")
public class ClienteController {
    private final ClienteService clienteService;

    @PostMapping
    @Validated(Cliente.CreateCliente.class)
    public ResponseEntity<Void> createCliente(@Valid @RequestBody Cliente cliente){
        clienteService.createCliente(cliente);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(cliente.getIdCliente()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @GetMapping
    public ResponseEntity<List<Cliente>> findAll(ClienteQueryFilter clienteQueryFilter){
        List<Cliente> clientes = clienteService.findAll(clienteQueryFilter);
        return ResponseEntity.ok().body(clientes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cliente> findById(@PathVariable Long id){
        Cliente cliente = clienteService.findById(id);
        return ResponseEntity.ok().body(cliente);
    }
}
