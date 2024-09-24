package com.crud.crudsimple.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode


@Entity
@Table(name = "endereco")
public class Endereco {

	public interface CreateEndereco {}
	public interface UpdateEndereco {}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "end_id", unique = true, nullable = false, updatable = false)
	private int idEndereco;

	@Column(name = "end_cep", nullable = false, length = 8)
	@NotNull(groups = {CreateEndereco.class, UpdateEndereco.class})
	@NotEmpty(groups = {CreateEndereco.class, UpdateEndereco.class})
	@Size(groups = {CreateEndereco.class, UpdateEndereco.class}, min = 8, max = 8)
	private String cep;

	@ManyToOne
	@JoinColumn(name = "end_bai_id", nullable = false)
	private Bairro bairro;

	@ManyToOne
	@JoinColumn(name = "end_lgr_id", nullable = false)
	private Logradouro logradouro;
}
