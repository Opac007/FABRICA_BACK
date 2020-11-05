package com.tutorial.crud.estilo.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
//@Table(uniqueConstraints= @UniqueConstraint(columnNames = {"tipo", "description"}) )
@Table(name="estilo")
public class Estilo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    //private String tipo;
    private String description;
    private String valor; 
	
	public Estilo() {
		super();
	}

	public Estilo(String description, String valor) {
		super();
		//this.tipo = tipo;
		this.description = description;
		this.valor = valor;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	//public String getTipo() {
	//	return tipo;
	//}

	//public void setTipo(String tipo) {
	//	this.tipo = tipo;
	//}

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
