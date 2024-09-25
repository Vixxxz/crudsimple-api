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
@Table(name = "bandeira")
public class Bandeira {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ban_id", unique = true, nullable = false, updatable = false)
	private Long idBandeira;

	@Column(name = "ban_nome", nullable = false, length = 20)
	@NotNull
	@NotEmpty
	@Size(min = 2, max = 50)
	private String bandeira;

	@OneToMany(mappedBy = "bandeira")
	private List<CartaoCredito> cartoes = new ArrayList<CartaoCredito>();
}
