package com.crud.crudsimple.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode

@Entity
@Table(name = "cliente_endereco")
public class ClienteEndereco {

	public interface CreateClienteEndereco {}
	public interface UpdateClienteEndereco {}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "cle_id", unique = true, nullable = false, updatable = false)
	private Long idCliEnd;

	@Column(name = "cle_numero", nullable = false, length = 5)
	@NotNull(groups = {CreateClienteEndereco.class, UpdateClienteEndereco.class})
	@NotEmpty(groups = {CreateClienteEndereco.class, UpdateClienteEndereco.class})
	@Size(groups = {CreateClienteEndereco.class, UpdateClienteEndereco.class}, min = 1, max = 5)
	private String numero;

	@Column(name = "cle_tp_residencia", nullable = false, length = 30)
	@NotNull(groups = {CreateClienteEndereco.class, UpdateClienteEndereco.class})
	@NotEmpty(groups = {CreateClienteEndereco.class, UpdateClienteEndereco.class})
	@Size(groups = {CreateClienteEndereco.class, UpdateClienteEndereco.class}, min = 1, max = 10)
	private String tipoResidencia;

	@Column(name = "cle_tp_endereco", nullable = false, length = 15)
	@NotNull(groups = {CreateClienteEndereco.class, UpdateClienteEndereco.class})
	@NotEmpty(groups = {CreateClienteEndereco.class, UpdateClienteEndereco.class})
	@Size(groups = {CreateClienteEndereco.class, UpdateClienteEndereco.class}, min = 1, max = 15)
	private String tipoEndereco;

	@Column(name = "cle_observacoes", length = 255)
	@Size(groups = {CreateClienteEndereco.class, UpdateClienteEndereco.class}, min = 0, max = 255)
	private String observacoes;

	@JsonManagedReference
	@ManyToOne
	@JoinColumn(name = "cle_end_id", nullable = false)
	@NotNull(groups = {CreateClienteEndereco.class, UpdateClienteEndereco.class})
	private Endereco endereco;

	@JsonBackReference //não insere no json a referencia do cliente
	@ManyToOne
	@JoinColumn(name = "cle_cli_id", nullable = false)
	@NotNull(groups = {CreateClienteEndereco.class, UpdateClienteEndereco.class})
	private Cliente cliente;
}
