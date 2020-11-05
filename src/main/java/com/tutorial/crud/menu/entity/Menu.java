package com.tutorial.crud.menu.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotNull;

import com.tutorial.crud.security.entity.Rol;

@Entity
public class Menu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
	@Column(unique = true)
    private String nombre;
    private String icon;
    private String path;
	@NotNull
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "menu_rol", joinColumns = @JoinColumn(name = "menu_id"),
	inverseJoinColumns = @JoinColumn(name = "rol_id"))
	private Set<Rol> roles = new HashSet<>();
    
    
	public Menu() {
		super();
	}


	public Menu(String nombre, String icon, String path) {
		super();
		this.nombre = nombre;
		this.icon = icon;
		this.path = path;
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


	public String getIcon() {
		return icon;
	}


	public void setIcon(String icon) {
		this.icon = icon;
	}


	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public Set<Rol> getRoles() {
		return roles;
	}

	public void setRoles(Set<Rol> roles) {
		this.roles = roles;
	}
}
