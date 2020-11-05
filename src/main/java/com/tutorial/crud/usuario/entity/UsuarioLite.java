package com.tutorial.crud.usuario.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.tutorial.crud.estilo.entity.Estilo;

@Entity
@Table(name="usuario")
public class UsuarioLite {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@NotNull
	private String nombre;
	@NotNull
	@Column(unique = true)
	private String nombreUsuario;
	@NotNull
	@Column(unique = true)
	private String email;
	@NotNull
    @ManyToOne
    @JoinColumn(name="estilo_id", nullable=false)
    private Estilo estilo;
	
	public UsuarioLite() {
	}
	
	public UsuarioLite(@NotNull String nombre, @NotNull String nombreUsuario, @NotNull String email) {
		super();
		this.nombre = nombre;
		this.nombreUsuario = nombreUsuario;
		this.email = email;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getNombreUsuario() {
		return nombreUsuario;
	}

	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Estilo getEstilo() {
		return estilo;
	}

	public void setEstilo(Estilo estilo) {
		this.estilo = estilo;
	}
	
}
