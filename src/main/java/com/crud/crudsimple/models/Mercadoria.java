package com.crud.crudsimple.models;

public abstract class Mercadoria {

	private int idMercadoria;
	private String categoria;
	private String nome;

	public Mercadoria(int idMercadoria, String categoria, String nome) {
		this.idMercadoria = idMercadoria;
		this.categoria = categoria;
		this.nome = nome;
	}

	public int getIdMercadoria() {
		return idMercadoria;
	}

	public void setIdMercadoria(int idMercadoria) {
		this.idMercadoria = idMercadoria;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
}
