package com.palestra.camel;

import java.io.Serializable;

/**
 * @author victor
 *
 */
public class Financial implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private long id;
    private String nome;
    private String conta;
    private Integer score;
    
    
    
	public Financial(long id, String nome, String conta, Integer score) {
		this.id = id;
		this.nome = nome;
		this.conta = conta;
		this.score = score;
	}
	
	public Financial(){}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getConta() {
		return conta;
	}

	public void setConta(String conta) {
		this.conta = conta;
	}

	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

	
    

}
