package com.crud.crudsimple.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@EqualsAndHashCode

@Entity
@Table(name = "cartao_credito")
public class CartaoCredito {

	public interface CreateCartaoCredito {}
	public interface UpdateCartaoCredito {}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "car_id", unique = true, nullable = false, updatable = false)
	private int id;

	@Column(name = "car_num", unique = true, nullable = false, length = 16)
	@NotNull(groups = {CreateCartaoCredito.class, UpdateCartaoCredito.class})
	@NotEmpty(groups = {CreateCartaoCredito.class, UpdateCartaoCredito.class})
	@Size(groups = {CreateCartaoCredito.class, UpdateCartaoCredito.class}, min = 13, max = 16)
	private String numCartao;

	@Column(name = "car_nome_impresso", nullable = false, length = 100)
	@NotNull(groups = {CreateCartaoCredito.class, UpdateCartaoCredito.class})
	@NotEmpty(groups = {CreateCartaoCredito.class, UpdateCartaoCredito.class})
	@Size(groups = {CreateCartaoCredito.class, UpdateCartaoCredito.class}, min = 2, max = 100)
	private String nomeImpresso;

	@Column(name = "car_num_seguranca", nullable = false, length = 3)
	@NotNull(groups = {CreateCartaoCredito.class, UpdateCartaoCredito.class})
	@NotEmpty(groups = {CreateCartaoCredito.class, UpdateCartaoCredito.class})
	@Size(groups = {CreateCartaoCredito.class, UpdateCartaoCredito.class}, min = 3, max = 3)
	private String numSeguranca;

	@Column(name = "car_preferencial", nullable = false)
	@NotNull(groups = {CreateCartaoCredito.class, UpdateCartaoCredito.class})
	@NotEmpty(groups = {CreateCartaoCredito.class, UpdateCartaoCredito.class})
	private boolean preferencial;

	@ManyToOne
	@JoinColumn(name = "car_ban_id", nullable = false)
	private Bandeira bandeira;
}
