package com.crud.crudsimple.models;

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
@Table(name = "logradouro")
public class Logradouro {

	public interface CreateLogradouro {}
	public interface UpdateLogradouro {}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "lgr_id", unique = true, nullable = false)
	private int idLogradouro;

	@Column(name = "lgr_nome", nullable = false, length = 100)
	@NotNull(groups = {CreateLogradouro.class, UpdateLogradouro.class})
	@NotEmpty(groups = {CreateLogradouro.class, UpdateLogradouro.class})
	@Size(groups = {CreateLogradouro.class, UpdateLogradouro.class}, min = 2, max = 100)
	private String logradouro;

	@ManyToOne
	@JoinColumn(name = "tp_lgr_id", nullable = false)
	private TipoLogradouro tpLogradouro;

	@OneToMany(mappedBy = "logradouro")
	private List<Endereco> endereco = new ArrayList<Endereco>();
}
