package com.crud.crudsimple.models;

public class ItemVenda extends Mercadoria {

	private float valor;

	public ItemVenda(int idMercadoria, String categoria, String nome, float valor) {
		super(idMercadoria, categoria, nome);
        this.valor = valor;
	}

	public float getValor() {
		return valor;
	}

	public void setValor(float valor) {
		this.valor = valor;
	}
}
