package com.crud.crudsimple.models;

public class ItemEstoque extends Mercadoria {

	private int quantidade;

	public ItemEstoque(int idMercadoria, String categoria, String nome, int quantidade) {
		super(idMercadoria, categoria, nome);
		this.quantidade = quantidade;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}
}
