package com.crud.crudsimple.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode

@Entity
@Table(name = "movimentacao")
public class Movimentacao
{
    public interface CreateMovimentacao{}
    public interface UpdateMovimentacao{}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "mov_id", unique = true, nullable = false, updatable = false)
    private Long idMovimentacao;

    @Column(name = "mov_tp_movimentacao", nullable = false, length = 100)
    @NotNull(groups = {CreateMovimentacao.class, UpdateMovimentacao.class})
    @NotEmpty(groups = {CreateMovimentacao.class, UpdateMovimentacao.class})
    @Size(groups = {CreateMovimentacao.class, UpdateMoviment}, min = 1, max = 100)
    private String tpMovimentacao;

    @Column(name = "mov_dt_movimentacao", nullable = false)
    @NotNull(groups = {CreateMovimentacao.class, UpdateMovimentacao.class})
    @NotEmpty(groups = {CreateMovimentacao.class, UpdateMovimentacao.class})
    @Temporal(TemporalType.DATE)
    private LocalDate dataMovimentacao;

    @Column(name = "mov_registro", nullable = false)
    @NotNull(groups = {CreateMovimentacao.class, UpdateMovimentacao.class})
    @NotEmpty(groups = {CreateMovimentacao.class, UpdateMovimentacao.class})
    private Integer registroMovimentacao;

    @Column(name = "mov_saldo", nullable = false)
    @NotNull(groups = {CreateMovimentacao.class, UpdateMovimentacao.class})
    @NotEmpty(groups = {CreateMovimentacao.class, UpdateMovimentacao.class})
    private Integer saldo;

    @OneToMany
    @JoinColumn(name = "mov_mer_id", nullable = false)
    private List<Mercadoria> mercadorias = new ArrayList<Mercadoria>();
}
