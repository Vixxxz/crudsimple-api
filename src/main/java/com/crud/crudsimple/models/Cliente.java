package com.crud.crudsimple.models;



import com.crud.implementacaov01.Domain.Entity.CartaoCredito.CartaoCredito;
import com.crud.implementacaov01.Domain.Entity.Endereco.Endereco;
import com.crud.implementacaov01.Domain.Entity.Transacao.Transacao;

import java.time.LocalDate;
import java.util.ArrayList;

public class Cliente {

	private int idCliente, ranking;

	private String	nome, genero,
					cpf, tipoTelefone,
					telefone, email, senha;

	private LocalDate dataNascimento;

	private ArrayList<Endereco> enderecos;

	private ArrayList<CartaoCredito> cartoes;

	private ArrayList<Transacao> transacoes;

	public Cliente (	int idCliente, int ranking, String nome,
					   	String genero, String cpf, String tipoTelefone,
					   	String telefone, String email, String senha,
					   	LocalDate dataNascimento, ArrayList<Endereco> enderecos,
					   	ArrayList<CartaoCredito> cartoes, ArrayList<Transacao> transacoes	) {
		this.idCliente = idCliente;
		this.ranking = ranking;
		this.nome = nome;
		this.genero = genero;
		this.cpf = cpf;
		this.tipoTelefone = tipoTelefone;
		this.telefone = telefone;
		this.email = email;
		this.senha = senha;
		this.dataNascimento = dataNascimento;
		this.enderecos = enderecos;
		this.cartoes = cartoes;
		this.transacoes = transacoes;
	}

	public int getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}

	public int getRanking() {
		return ranking;
	}

	public void setRanking(int ranking) {
		this.ranking = ranking;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getTipoTelefone() {
		return tipoTelefone;
	}

	public void setTipoTelefone(String tipoTelefone) {
		this.tipoTelefone = tipoTelefone;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public LocalDate getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
}
