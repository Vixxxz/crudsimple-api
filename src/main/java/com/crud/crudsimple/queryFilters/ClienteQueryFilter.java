package com.crud.crudsimple.queryFilters;

import com.crud.crudsimple.models.Cliente;
import com.crud.crudsimple.specifications.ClienteSpecification;
import lombok.Data;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import java.time.LocalDate;

@Data

@Component
public class ClienteQueryFilter
{
    private String nome;
    private String genero;
    private String cpf;
    private Integer ranking;
    private String telefone;
    private String email;
    private LocalDate dataNascimento;
    private String cep;
    private String logradouro;
    private String bairro;
    private String cidade;
    private String uf;
    private String pais;
    private String bandeira;

    public Specification<Cliente> toSpecification() {
        Specification<Cliente> clienteSpec = Specification.where(null);

        clienteSpec = addSpecification(clienteSpec, nome, ClienteSpecification::findByNome);
        clienteSpec = addSpecification(clienteSpec, genero, ClienteSpecification::findByGenero);
        clienteSpec = addSpecification(clienteSpec, cpf, ClienteSpecification::findByCpf);
        clienteSpec = addSpecification(clienteSpec, ranking, ClienteSpecification::findByRanking);
        clienteSpec = addSpecification(clienteSpec, telefone, ClienteSpecification::findByTelefone);
        clienteSpec = addSpecification(clienteSpec, email, ClienteSpecification::findByEmail);
        clienteSpec = addSpecification(clienteSpec, dataNascimento, ClienteSpecification::findByDataNascimento);
        clienteSpec = addSpecification(clienteSpec, cep, ClienteSpecification::findByCep);
        clienteSpec = addSpecification(clienteSpec, logradouro, ClienteSpecification::findByLogradouro);
        clienteSpec = addSpecification(clienteSpec, bairro, ClienteSpecification::findByBairro);
        clienteSpec = addSpecification(clienteSpec, cidade, ClienteSpecification::findByCidade);
        clienteSpec = addSpecification(clienteSpec, uf, ClienteSpecification::findByUf);
        clienteSpec = addSpecification(clienteSpec, pais, ClienteSpecification::findByPais);
        clienteSpec = addSpecification(clienteSpec, bandeira, ClienteSpecification::findByBandeira);

        return clienteSpec;
    }

    //<T> diz que é generico, então pode o que for anotado com isso pode retornar ou receber qualquer tipo
    private <T> Specification<Cliente> addSpecification(Specification<Cliente> spec, T valor,
                                                        SpecificationFunction<T> specFuncao) {
        if (!ObjectUtils.isEmpty(valor)) {
            return spec.and(specFuncao.apply(valor));
        }
        return spec;
    }

    @FunctionalInterface
    private interface SpecificationFunction<T> {
        Specification<Cliente> apply(T value);
    }
}
