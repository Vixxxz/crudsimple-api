package com.crud.crudsimple.models;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode

@Entity
@Table(name = "mercadoria_transacao")
public class MercadoriaTransacao
{
    public interface CreateMercadoriaTransacao{}
    public interface UpdateMercadoriaTransacao{}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "mtr_id", nullable = false, unique = true, updatable = false)
    private Long idMercadoriaTransacao;

    @ManyToOne
    @JoinColumn(name = "mtr_tra_id", nullable = false)
    @NotNull(groups = {CreateMercadoriaTransacao.class, UpdateMercadoriaTransacao.class})
    private Transacao transacao;

    @ManyToOne
    @JoinColumn(name = "mtr_mer_id", nullable = false)
    @NotNull(groups = {CreateMercadoriaTransacao.class, UpdateMercadoriaTransacao.class})
    private Mercadoria mercadoria;

    @Column(name = "mtr_quantidade", nullable = false)
    @NotNull(groups = {CreateMercadoriaTransacao.class, UpdateMercadoriaTransacao.class})
    private Integer quantidade;
}
