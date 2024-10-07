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

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@EqualsAndHashCode (of = {"logradouro", "tpLogradouro"})

@Entity
@Table(name = "logradouro")
public class Logradouro {

	public interface CreateLogradouro {}
	public interface UpdateLogradouro {}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "lgr_id", unique = true, nullable = false, updatable = false)
	private Long idLogradouro;

	@Column(name = "lgr_nome", nullable = false, length = 100)
	@NotNull(groups = {CreateLogradouro.class, UpdateLogradouro.class})
	@NotEmpty(groups = {CreateLogradouro.class, UpdateLogradouro.class})
	@Size(groups = {CreateLogradouro.class, UpdateLogradouro.class}, min = 2, max = 100)
	private String logradouro;

	@JsonManagedReference
	@ManyToOne
	@JoinColumn(name = "tp_lgr_id", nullable = false)
	@NotNull(groups = {CreateLogradouro.class, UpdateLogradouro.class})
	private TipoLogradouro tpLogradouro;

	@JsonBackReference
	@OneToMany(mappedBy = "logradouro", cascade = CascadeType.ALL)
	private List<Endereco> endereco = new ArrayList<Endereco>();
}
