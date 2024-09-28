package com.crud.crudsimple.queryFilters;

import com.crud.crudsimple.models.Cliente;
import com.crud.crudsimple.specifications.ClienteSpecification;
import lombok.Data;
import org.springframework.data.jpa.domain.Specification;

@Data
public class ClienteQueryFilter
{
    private String nome;

    public Specification<Cliente> toSpecification(){
        return ClienteSpecification.nomeContains(nome);
    }
}
