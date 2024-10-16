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
@EqualsAndHashCode

@Entity
@Table(name = "pais")
public class Pais {

	public interface CreatePais {}
	public interface UpdatePais {}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "pai_id", unique = true, nullable = false, updatable = false)
	private Long idPais;

	@Column(name = "pai_nome", nullable = false, length = 20)
	@NotNull(groups = {CreatePais.class, UpdatePais.class})
	@NotEmpty(groups = {CreatePais.class, UpdatePais.class})
	@Size(groups = {CreatePais.class, UpdatePais.class}, min = 2, max = 20)
	private String pais;

	@OneToMany(mappedBy = "pais", cascade = CascadeType.ALL)
	private List<Uf> ufs = new ArrayList<Uf>();
}
