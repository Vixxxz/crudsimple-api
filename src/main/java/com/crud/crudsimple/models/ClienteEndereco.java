package com.crud.crudsimple.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

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
	public interface DeleteClienteEndereco {}


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "cle_id", unique = true, nullable = false, updatable = false)
	private Long idCliEnd;

	@Column(name = "cle_numero", nullable = false, length = 5)
	@NotNull(groups = {CreateClienteEndereco.class, UpdateClienteEndereco.class, DeleteClienteEndereco.class})
	@NotEmpty(groups = {CreateClienteEndereco.class, UpdateClienteEndereco.class, DeleteClienteEndereco.class})
	@Size(groups = {CreateClienteEndereco.class, UpdateClienteEndereco.class, DeleteClienteEndereco.class}, min = 1, max = 5)
	private String numero;

	@Column(name = "cle_tp_residencia", nullable = false, length = 30)
	@NotNull(groups = {CreateClienteEndereco.class, UpdateClienteEndereco.class, DeleteClienteEndereco.class})
	@NotEmpty(groups = {CreateClienteEndereco.class, UpdateClienteEndereco.class, DeleteClienteEndereco.class})
	@Size(groups = {CreateClienteEndereco.class, UpdateClienteEndereco.class, DeleteClienteEndereco.class}, min = 1, max = 10)
	private String tipoResidencia;

	@Column(name = "cle_tp_endereco", nullable = false, length = 15)
	@NotNull(groups = {CreateClienteEndereco.class, UpdateClienteEndereco.class, DeleteClienteEndereco.class})
	@NotEmpty(groups = {CreateClienteEndereco.class, UpdateClienteEndereco.class, DeleteClienteEndereco.class})
	@Size(groups = {CreateClienteEndereco.class, UpdateClienteEndereco.class, DeleteClienteEndereco.class}, min = 1, max = 15)
	private String tipoEndereco;

	@Column(name = "cle_observacoes", length = 255)
	@Size(groups = {CreateClienteEndereco.class, UpdateClienteEndereco.class, DeleteClienteEndereco.class}, min = 0, max = 255)
	private String observacoes;

	@ManyToOne
	@JoinColumn(name = "cle_end_id", nullable = false)
	@NotNull(groups = {CreateClienteEndereco.class, UpdateClienteEndereco.class, DeleteClienteEndereco.class})
	@NotEmpty(groups = {CreateClienteEndereco.class, UpdateClienteEndereco.class, DeleteClienteEndereco.class})
	private Endereco endereco;

	@ManyToOne
	@JoinColumn(name = "cle_cli_id", nullable = false)
	@NotNull(groups = {CreateClienteEndereco.class, UpdateClienteEndereco.class, DeleteClienteEndereco.class})
	@NotEmpty(groups = {CreateClienteEndereco.class, UpdateClienteEndereco.class, DeleteClienteEndereco.class})
	private Cliente cliente;
}
