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
@Getter
@Setter
@EqualsAndHashCode

@Entity
@Table(name = "cidade")
public class Cidade {

	public interface CreateCidade {}
	public interface UpdateCidade {}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "cid_id", unique = true, nullable = false, updatable = false)
	private int idCidade;

	@Column(name = "cid_nome", nullable = false, length = 100)
	@NotNull (groups = {CreateCidade.class, UpdateCidade.class})
	@NotEmpty (groups = {CreateCidade.class, UpdateCidade.class})
	@Size(groups = {CreateCidade.class, UpdateCidade.class}, min = 3, max = 100)
	private String cidade;

	@ManyToOne
	@JoinColumn(name = "cid_uf_id", nullable = false)
	private Uf uf;

	@OneToMany(mappedBy = "cidade")
	private List<Bairro> bairros = new ArrayList<Bairro>();
}
