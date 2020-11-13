package com.tutorial.crud.estilo.dto;

public class EstiloDto {
	private int id;
	private String description;
	private String valor;
	
	public EstiloDto() {
		super();
	}

	public EstiloDto(String description, String valor) {
		super();
		this.description = description;
		this.valor = valor;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}
	
}
