package com.crud.crudsimple.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode

@Entity
@Table(name = "transacao")
public class Transacao {

	public interface CreateTransacao {}
	public interface UpdateTransacao {}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "tra_id", nullable = false, unique = true, updatable = false)
	private int idTransacao;

	@Column(name = "tra_dt_transacao", nullable = false)
	@Temporal(TemporalType.DATE)
	@NotNull (groups = {CreateTransacao.class, UpdateTransacao.class})
	@NotEmpty(groups = {CreateTransacao.class, UpdateTransacao.class})
	private LocalDate data;

	@Column(name = "tra_hora_transacao", nullable = false)
	@Temporal(TemporalType.TIME)
	@NotNull(groups = {CreateTransacao.class, UpdateTransacao.class})
	@NotEmpty(groups = {CreateTransacao.class, UpdateTransacao.class})
	private LocalTime hora;

	@Column(name = "tra_valor_total", nullable = false, precision = 9, scale = 2)
	@NotNull (groups = {CreateTransacao.class, UpdateTransacao.class})
	@NotEmpty(groups = {CreateTransacao.class, UpdateTransacao.class})
	@DecimalMax(groups = {CreateTransacao.class, UpdateTransacao.class}, value = "99999999.99")
	@DecimalMin(groups = {CreateTransacao.class, UpdateTransacao.class}, value = "00.01")
	private float valorTotal;

	@Column(name = "tra_qtd_total", nullable = false)
	@NotNull (groups = {CreateTransacao.class, UpdateTransacao.class})
	@NotEmpty(groups = {CreateTransacao.class, UpdateTransacao.class})
	private Integer qtdTotal;

	@Column(name = "tra_status", nullable = false, length = 30)
	@NotNull (groups = {CreateTransacao.class, UpdateTransacao.class})
	@NotEmpty(groups = {CreateTransacao.class, UpdateTransacao.class})
	@Size(groups = {CreateTransacao.class, UpdateTransacao.class}, min = 1, max = 30)
	private String status;

	@Column(name = "tra_detalhes")
	@Size(groups = {CreateTransacao.class, UpdateTransacao.class}, min = 1, max = 255)
	private String detalhes;

	@ManyToOne
	@JoinColumn(name = "tra_cli_id", nullable = false, updatable = false)
	@NotNull(groups = {CartaoCredito.CreateCartaoCredito.class, CartaoCredito.UpdateCartaoCredito.class})
	@NotEmpty(groups = {CartaoCredito.CreateCartaoCredito.class, CartaoCredito.UpdateCartaoCredito.class})
	private Cliente cliente;

	@OneToMany(mappedBy = "transacao")
    private List<MercadoriaTransacao> mercadoriaVenda = new ArrayList<>();

	@OneToMany(mappedBy = "transacao")
    private List<Movimentacao> movimentacos = new ArrayList<>();
}
