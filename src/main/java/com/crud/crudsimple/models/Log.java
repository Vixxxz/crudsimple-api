package com.crud.crudsimple.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode

@Entity
@Table(name = "log")
public class Log {

	public interface CreateLog {}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "log_id", unique = true, nullable = false, updatable = false)
	private Long idLog;

	@Column(name = "log_dia", nullable = false, length = 2)
	@NotNull(groups = CreateLog.class)
	@NotEmpty(groups = CreateLog.class)
	@Size(groups = CreateLog.class, min = 2, max = 2)
	private LocalDate dia;

	@Column(name = "log_mes", nullable = false, length = 2)
	@NotNull(groups = CreateLog.class)
	@NotEmpty(groups = CreateLog.class)
	@Size(groups = CreateLog.class, min = 2, max = 2)
	private LocalDate mes;

	@Column(name = "log_ano", nullable = false, length = 4)
	@NotNull(groups = CreateLog.class)
	@NotEmpty(groups = CreateLog.class)
	@Size(groups = CreateLog.class, min = 4, max = 4)
	private LocalDate ano;

	@Column(name = "log_hora", nullable = false, length = 8)
	@NotNull(groups = CreateLog.class)
	@NotEmpty(groups = CreateLog.class)
	@Size(groups = CreateLog.class, min = 8, max = 8)
	private LocalTime hora;

	@ManyToOne
	@JoinColumn(name = "log_cli_id", nullable = false)
	private Cliente cliente;

	@Column(name = "log_operacao_realizada", nullable = false, length = 100)
	@NotEmpty(groups = CreateLog.class)
	@NotEmpty(groups = CreateLog.class)
	@Size(groups = CreateLog.class, min = 1, max = 100)
	private String operacao;

	@Column(name = "log_dados_alterados", nullable = false, length = 255)
	@NotEmpty(groups = CreateLog.class)
	@NotEmpty(groups = CreateLog.class)
	@Size(groups = CreateLog.class, min = 1, max = 255)
	private String dadosAlterados;
}
