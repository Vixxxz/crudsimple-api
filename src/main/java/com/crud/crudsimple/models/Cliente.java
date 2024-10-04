package com.crud.crudsimple.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.validator.constraints.br.CPF;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode

@Entity
@Table(name = "cliente")
public class Cliente {

	public interface CreateCliente{}
	public interface UpdateCliente{}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "cli_id", unique = true, nullable = false, updatable = false)
	private Long idCliente;

	@Column(name = "cli_rank", nullable = false)
	@NotNull (groups = {CreateCliente.class, UpdateCliente.class})
	private Integer ranking = 1;

	@Column(name = "cli_nome", nullable = false, length = 100)
	@NotNull (groups = {CreateCliente.class, UpdateCliente.class})
	@NotEmpty(groups = {CreateCliente.class, UpdateCliente.class})
	@Size(groups = {CreateCliente.class, UpdateCliente.class}, min = 2, max = 100)
	private String	nome;

	@Column(name = "cli_genero", nullable = false, length = 30)
	@NotNull (groups = {CreateCliente.class, UpdateCliente.class})
	@NotEmpty(groups = {CreateCliente.class, UpdateCliente.class})
	@Size(groups = {CreateCliente.class, UpdateCliente.class}, min = 2, max = 30)
	private String genero;

	@Column(name = "cli_cpf", nullable = false, length = 11, unique = true)
	@NotNull (groups = {CreateCliente.class, UpdateCliente.class})
	@NotEmpty(groups = {CreateCliente.class, UpdateCliente.class})
	@Size(groups = {CreateCliente.class, UpdateCliente.class}, min = 11, max = 11)
	@CPF(groups = {CreateCliente.class, UpdateCliente.class})
	private String cpf;

	@Column(name = "cli_tp_telefone", nullable = false, length = 100)
	@NotNull (groups = {CreateCliente.class, UpdateCliente.class})
	@NotEmpty(groups = {CreateCliente.class, UpdateCliente.class})
	@Size(groups = {CreateCliente.class, UpdateCliente.class}, min = 2, max = 100)
	private String tipoTelefone;

	@Column(name = "cli_telefone", nullable = false, length = 14)
	@NotNull (groups = {CreateCliente.class, UpdateCliente.class})
	@NotEmpty(groups = {CreateCliente.class, UpdateCliente.class})
	@Size(groups = {CreateCliente.class, UpdateCliente.class}, min = 14, max = 14)
	private String telefone;

	@Column(name = "cli_email", nullable = false, length = 100, unique = true)
	@NotNull (groups = {CreateCliente.class, UpdateCliente.class})
	@NotEmpty(groups = {CreateCliente.class, UpdateCliente.class})
	@Size(groups = {CreateCliente.class, UpdateCliente.class}, min = 2, max = 100)
	@Email(groups = {CreateCliente.class, UpdateCliente.class})
	private String email;

	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	@Column(name = "cli_senha", nullable = false, length = 255)
	@NotNull (groups = {CreateCliente.class, UpdateCliente.class})
	@NotEmpty(groups = {CreateCliente.class, UpdateCliente.class})
	@Size(groups = {CreateCliente.class, UpdateCliente.class}, min = 8, max = 255)
	private String senha;

	@Column(name = "cli_dt_nascimento", nullable = false)
	@Temporal(TemporalType.DATE)
	@NotNull (groups = {CreateCliente.class, UpdateCliente.class})
	private LocalDate dataNascimento;

	@OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
	@NotNull (groups = {CreateCliente.class, UpdateCliente.class})
	private List<ClienteEndereco> enderecos = new ArrayList<ClienteEndereco>();

	@OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
	private List<CartaoCredito> cartoes = new ArrayList<CartaoCredito>();

	@OneToMany(mappedBy = "cliente", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
	private List<Transacao> transacoes = new ArrayList<Transacao>();

	@OneToMany(mappedBy = "cliente", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
	private List<Log> logs = new ArrayList<Log>();
}
