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
@EqualsAndHashCode(of = {"bairro", "cidade"})

@Entity
@Table(name = "bairro")
public class Bairro {

	public interface CreateBairro {}
	public interface UpdateBairro {}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "bai_id", unique = true, nullable = false, updatable = false)
	private Long idBairro;

	@Column(name = "bai_nome", nullable = false, length = 50)
	@NotNull (groups = {CreateBairro.class, UpdateBairro.class})
	@NotEmpty (groups = {CreateBairro.class, UpdateBairro.class})
	@Size(groups = {CreateBairro.class, UpdateBairro.class}, min = 5, max = 50)
	private String bairro;

	@JsonManagedReference
	@ManyToOne
	@JoinColumn(name = "bai_cid_id", nullable = false)
	@NotNull (groups = {CreateBairro.class, UpdateBairro.class})
	private Cidade cidade;

	@JsonBackReference
	@OneToMany(mappedBy = "bairro", cascade = CascadeType.ALL)
	private List<Endereco> enderecos = new ArrayList<Endereco>();
}
