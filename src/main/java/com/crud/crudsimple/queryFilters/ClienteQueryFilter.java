package com.crud.crudsimple.queryFilters; // Pacote onde a classe está localizada

import com.crud.crudsimple.models.Cliente; // Importa a classe Cliente
import com.crud.crudsimple.specifications.ClienteSpecification; // Importa a classe ClienteSpecification para filtros
import lombok.Data; // Importa a anotação Data para gerar getters, setters, toString, etc.
import org.springframework.data.jpa.domain.Specification; // Importa a classe Specification para especificações JPA
import org.springframework.stereotype.Component; // Importa a anotação Component para registrar a classe como um componente Spring
import org.springframework.util.ObjectUtils; // Importa a classe ObjectUtils para operações utilitárias

import java.time.LocalDate; // Importa a classe LocalDate para trabalhar com datas

@Data // Gera automaticamente métodos getters, setters e toString
@Component // Indica que a classe é um componente Spring gerenciado
public class ClienteQueryFilter { // Classe que representa os filtros de consulta para Cliente
    // Atributos que serão usados como critérios de filtragem
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
    private Long idTransacao;
    private Long idLog;

    // metodo que converte os filtros em uma especificação JPA
    public Specification<Cliente> toSpecification() {
        Specification<Cliente> clienteSpec = Specification.where(null); // Inicializa a especificação como vazia

        // Adiciona as especificações de filtro para cada atributo, se não estiver vazio
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
        clienteSpec = addSpecification(clienteSpec, idTransacao, ClienteSpecification::findByIdTransacao);
        clienteSpec = addSpecification(clienteSpec, idLog, ClienteSpecification::findByIdLog);

        return clienteSpec; // Retorna a especificação construída
    }

    // metodo genérico para adicionar especificações à consulta
    private <T> Specification<Cliente> addSpecification(Specification<Cliente> spec, T valor,
                                                        SpecificationFunction<T> specFuncao) {
        // Verifica se o valor não é nulo ou vazio
        if (!ObjectUtils.isEmpty(valor)) {
            return spec.and(specFuncao.apply(valor)); // Adiciona a especificação
        }
        return spec; // Retorna a especificação sem alterações
    }

    // Interface funcional para aplicar especificações
    @FunctionalInterface
    private interface SpecificationFunction<T> {
        Specification<Cliente> apply(T value); // metodo que aplica a especificação
    }
}
