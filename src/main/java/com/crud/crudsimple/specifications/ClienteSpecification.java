package com.crud.crudsimple.specifications;

import com.crud.crudsimple.models.Cliente;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.ObjectUtils;

public class ClienteSpecification {
    public static Specification<Cliente> nomeContains(String nome) {
        return (root, query, builder) -> {
            if (ObjectUtils.isEmpty(nome)) {
                return null;
            }
            return builder.like(builder.lower(root.get("nome")), "%" + nome.toLowerCase() + "%");
        };
    }
}
