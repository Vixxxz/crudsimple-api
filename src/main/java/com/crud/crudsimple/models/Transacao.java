package com.crud.crudsimple.models;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class Transacao {

	private int idTransacao;
	private LocalDate data;
	private LocalTime hora;
	private float valorTotal;
	private boolean status;
	private String detalhes;
	private ArrayList<ItemVenda> itens;

	public Transacao(int idTransacao, LocalDate data, LocalTime hora, float valorTotal, boolean status, String detalhes, ArrayList<ItemVenda> itens) {
		this.idTransacao = idTransacao;
		this.data = data;
		this.hora = hora;
		this.valorTotal = valorTotal;
		this.status = status;
		this.detalhes = detalhes;
		this.itens = itens;
	}

	public int getIdTransacao() {
		return idTransacao;
	}

	public void setIdTransacao(int idTransacao) {
		this.idTransacao = idTransacao;
	}

	public LocalDate getData() {
		return data;
	}

	public void setData(LocalDate data) {
		this.data = data;
	}

	public LocalTime getHora() {
		return hora;
	}

	public void setHora(LocalTime hora) {
		this.hora = hora;
	}

	public float getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(float valorTotal) {
		this.valorTotal = valorTotal;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public String getDetalhes() {
		return detalhes;
	}

	public void setDetalhes(String detalhes) {
		this.detalhes = detalhes;
	}

}
