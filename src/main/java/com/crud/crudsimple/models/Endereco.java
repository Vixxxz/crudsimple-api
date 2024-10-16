package com.crud.crudsimple.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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
@EqualsAndHashCode(of = {"cep", "bairro", "logradouro"})


@Entity
@Table(name = "endereco")
public class Endereco {

	public interface CreateEndereco {}
	public interface UpdateEndereco {}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "end_id", unique = true, nullable = false, updatable = false)
	private Long idEndereco;

	@Column(name = "end_cep", nullable = false, length = 8)
	@NotNull(groups = {CreateEndereco.class, UpdateEndereco.class})
	@NotEmpty(groups = {CreateEndereco.class, UpdateEndereco.class})
	@Size(groups = {CreateEndereco.class, UpdateEndereco.class}, min = 8, max = 8)
	private String cep;

	@OneToMany(mappedBy = "endereco")
	private List<ClienteEndereco> enderecos = new ArrayList<ClienteEndereco>();

	@ManyToOne
	@JoinColumn(name = "end_bai_id", nullable = false)
	@NotNull(groups = {CreateEndereco.class, UpdateEndereco.class})
	private Bairro bairro;

	@ManyToOne
	@JoinColumn(name = "end_lgr_id", nullable = false)
	@NotNull(groups = {CreateEndereco.class, UpdateEndereco.class})
	private Logradouro logradouro;
}
