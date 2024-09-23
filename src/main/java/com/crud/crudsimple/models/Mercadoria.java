package com.crud.crudsimple.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE) //todas as classes da hierarquia (neste caso, Mercadoria, ItemVenda, e ItemEstoque) serão armazenadas em uma única tabela no banco de dados.
@DiscriminatorColumn(name = "mer_tp_produto", discriminatorType = DiscriminatorType.STRING)	//Define uma coluna que será usada para distinguir entre as diferentes subclasses na tabela única.
																							//A coluna tipo_produto armazenará um valor que identifica a classe da instância
																								//por exemplo, "VENDA" para ItemVenda e "ESTOQUE" para ItemEstoque.
																							//O discriminatorType = DiscriminatorType.STRING indica que o valor armazenado será uma string.
@Table(name = "mercadoria")
public abstract class Mercadoria {

	interface CreateMercadoria {}
	interface UpdateMercadoria {}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "mer_id", unique = true, nullable = false)
	private int idMercadoria;

	@Column(name = "mer_categoria", nullable = false, length = 50)
	@NotNull(groups = {CreateMercadoria.class, UpdateMercadoria.class})
	@NotEmpty(groups = {CreateMercadoria.class, UpdateMercadoria.class})
	@Size(groups = {CreateMercadoria.class, UpdateMercadoria.class}, min = 2, max = 50)
	private String categoria;

	@Column(name = "mer_nome", nullable = false, length = 100)
	@NotNull(groups = {CreateMercadoria.class, UpdateMercadoria.class})
	@NotEmpty(groups = {CreateMercadoria.class, UpdateMercadoria.class})
	@Size(groups = {CreateMercadoria.class, UpdateMercadoria.class}, min = 2, max = 100)
	private String nome;

}
