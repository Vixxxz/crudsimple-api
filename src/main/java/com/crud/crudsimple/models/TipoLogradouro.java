package com.crud.crudsimple.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@EqualsAndHashCode

@Entity
@Table(name = "tp_logradouro")
public class TipoLogradouro
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tpl_id", unique = true, nullable = false, updatable = false)
    private Long id;

    @Column(name = "tpl_nome", nullable = false, length = 10)
    @NotNull
    @NotEmpty
    @Size(min = 2, max = 10)
    private String tpLogradouro;

    @OneToMany(mappedBy = "tpLogradouro", cascade = CascadeType.ALL)
    private List<Logradouro> logradouros = new ArrayList<>();
}
