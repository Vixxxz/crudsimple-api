package com.crud.crudsimple.specifications;

import com.crud.crudsimple.models.*;
import jakarta.persistence.criteria.Join;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import java.time.LocalDate;

@Component
public class ClienteSpecification {
    public static Specification<Cliente> findByNome(String nome) {
        return (root, query, builder) -> {
            if (ObjectUtils.isEmpty(nome)) {
                return null;
            }
            return builder.like(builder.lower(root.get("nome")), "%" + nome.toLowerCase() + "%");
        };
    }

    public static Specification<Cliente> findByGenero(String genero) {
        return (root, query, builder) -> {
            if (ObjectUtils.isEmpty(genero)) {
                return null;
            }
            return builder.equal(builder.lower(root.get("genero")), genero.toLowerCase());
        };
    }

    public static Specification<Cliente> findByCpf(String cpf) {
        return (root, query, builder) -> {
            if (ObjectUtils.isEmpty(cpf)) {
                return null;
            }
            return builder.equal(root.get("cpf"), cpf);
        };
    }

    public static Specification<Cliente> findByRanking(Integer ranking) {
        return (root, query, builder) -> {
            if (ObjectUtils.isEmpty(ranking)) {
                return null;
            }
            return builder.equal(root.get("ranking"), ranking);
        };
    }

    public static Specification<Cliente> findByTelefone(String telefone) {
        return (root, query, builder) -> {
            if (ObjectUtils.isEmpty(telefone)) {
                return null;
            }
            return builder.equal(root.get("telefone"), telefone);
        };
    }

    public static Specification<Cliente> findByEmail(String email) {
        return (root, query, builder) -> {
            if (ObjectUtils.isEmpty(email)) {
                return null;
            }
            return builder.equal(builder.lower(root.get("email")), email.toLowerCase());
        };
    }

    //pode ser que de bo por conta do tipo LocalDate:
    public static Specification<Cliente> findByDataNascimento(LocalDate dtNascimento) {
        return (root, query, builder) -> {
            if (ObjectUtils.isEmpty(dtNascimento)) {
                return null;
            }
            return builder.equal(root.get("dataNascimento"), dtNascimento);
        };
    }

    public static Specification<Cliente> findByCep(String cep) {
        return (root, query, builder) -> {
            if (ObjectUtils.isEmpty(cep)) {
                return null;
            }
            Join<Cliente, ClienteEndereco> clienteEnderecoJoin = root.join("enderecos");
            Join<ClienteEndereco, Endereco> enderecoJoin = clienteEnderecoJoin.join("endereco");

            return builder.equal(enderecoJoin.get("cep"), cep);
        };
    }

    public static Specification<Cliente> findByLogradouro(String logradouro) {
        return (root, query, builder) -> {
            if (ObjectUtils.isEmpty(logradouro)) {
                return null;
            }
            Join<Cliente, ClienteEndereco> clienteEnderecoJoin = root.join("enderecos");
            Join<ClienteEndereco, Endereco> enderecoJoin = clienteEnderecoJoin.join("endereco");
            Join<Endereco, Logradouro> logradouroJoin = enderecoJoin.join("logradouro");
            return builder.like(builder.lower(logradouroJoin.get("logradouro")), "%" + logradouro.toLowerCase() + "%");
        };
    }

    public static Specification<Cliente> findByBairro(String bairro) {
        return (root, query, builder) -> {
            if (ObjectUtils.isEmpty(bairro)) {
                return null;
            }
            Join<Cliente, ClienteEndereco> clienteEnderecoJoin = root.join("enderecos");
            Join<ClienteEndereco, Endereco> enderecoJoin = clienteEnderecoJoin.join("endereco");
            Join<Endereco, Bairro> bairroJoin = enderecoJoin.join("bairro");
            return builder.like(builder.lower(bairroJoin.get("bairro")), "%" + bairro.toLowerCase() + "%");
        };
    }

    public static Specification<Cliente> findByCidade(String cidade) {
        return (root, query, builder) -> {
            if (ObjectUtils.isEmpty(cidade)) {
                return null;
            }
            Join<Cliente, ClienteEndereco> clienteEnderecoJoin = root.join("enderecos");
            Join<ClienteEndereco, Endereco> enderecoJoin = clienteEnderecoJoin.join("endereco");
            Join<Endereco, Bairro> bairroJoin = enderecoJoin.join("bairro");
            Join<Bairro, Cidade> cidadeJoin = bairroJoin.join("cidade");
            return builder.equal(builder.lower(cidadeJoin.get("cidade")), "%" + cidade.toLowerCase() + "%");
        };
    }

    public static Specification<Cliente> findByUf(String uf) {
        return (root, query, builder) -> {
            if (ObjectUtils.isEmpty(uf)) {
                return null;
            }
            Join<Cliente, ClienteEndereco> clienteEnderecoJoin = root.join("enderecos");
            Join<ClienteEndereco, Endereco> enderecoJoin = clienteEnderecoJoin.join("endereco");
            Join<Endereco, Bairro> bairroJoin = enderecoJoin.join("bairro");
            Join<Bairro, Cidade> cidadeJoin = bairroJoin.join("cidade");
            Join<Cidade, Uf> ufJoin = cidadeJoin.join("uf");
            return builder.equal(builder.lower(ufJoin.get("uf")), "%" + uf.toLowerCase() + "%");
        };
    }

    public static Specification<Cliente> findByPais(String pais) {
        return (root, query, builder) -> {
            if (ObjectUtils.isEmpty(pais)) {
                return null;
            }
            Join<Cliente, ClienteEndereco> clienteEnderecoJoin = root.join("enderecos");
            Join<ClienteEndereco, Endereco> enderecoJoin = clienteEnderecoJoin.join("endereco");
            Join<Endereco, Bairro> bairroJoin = enderecoJoin.join("bairro");
            Join<Bairro, Cidade> cidadeJoin = bairroJoin.join("cidade");
            Join<Cidade, Uf> ufJoin = cidadeJoin.join("uf");
            Join<Uf, Pais> paisJoin = ufJoin.join("pais");
            return builder.equal(builder.lower(paisJoin.get("pais")), "%" + pais.toLowerCase() + "%");
        };
    }
    
    public static Specification<Cliente> findByBandeira(String bandeira){
        return (root, query, builder) -> {
            if (ObjectUtils.isEmpty(bandeira)) {
                return null;
            }
            Join<Cliente, CartaoCredito> cartaoCreditoJoin = root.join("cartoes");
            Join<CartaoCredito, Bandeira> bandeiraJoin = cartaoCreditoJoin.join("bandeira");
            return builder.equal(bandeiraJoin.get("bandeira"), bandeira);
        };
    }
}
