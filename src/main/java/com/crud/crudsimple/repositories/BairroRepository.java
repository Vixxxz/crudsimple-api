package com.crud.crudsimple.repositories;

import com.crud.crudsimple.models.Bairro;
import com.crud.crudsimple.models.Cidade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BairroRepository extends JpaRepository<Bairro, Long>
{
    //Faz uma busca que retorna uma lista de Bairros de uma unica Cidade:
    List<Bairro> findByCidade_idCidade(Long id);  //Para especificar por qual parametro que ira ser buscado, colocamos o "_".
                                            // Nesse caso, irá achar a lista de Bairros pelo Id que está dentro de uma Cidade

    List<Bairro> findByCidade_cidade(String cidade);

    Optional<Bairro> findByBairro(String bairro);

}