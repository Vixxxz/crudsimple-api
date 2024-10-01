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
@Table(name = "uf")
public class Uf {

	public interface CreateUf {}
	public interface UpdateUf {}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "uf_id", unique = true, nullable = false, updatable = false)
	private Long idUf;

	@Column(name = "uf_nome", nullable = false, length = 3)
	@NotNull(groups = {CreateUf.class, UpdateUf.class})
	@NotEmpty(groups = {CreateUf.class, UpdateUf.class})
	@Size(groups = {CreateUf.class, UpdateUf.class}, min = 2, max = 3)
	private String uf;

	@ManyToOne
	@JoinColumn(name = "uf_pai_id", nullable = false)
	private Pais pais;

	@OneToMany(mappedBy = "uf", cascade = CascadeType.ALL)
	private List<Cidade> cidades = new ArrayList<Cidade>();
}
