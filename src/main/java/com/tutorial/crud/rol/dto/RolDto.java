package com.tutorial.crud.rol.dto;

public class RolDto {
	private int id;
	private String rolNombre;
	
	public RolDto() {
		super();
	}

	public RolDto(String rolNombre) {
		super();
		this.rolNombre = rolNombre;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getRolNombre() {
		return rolNombre;
	}

	public void setRolNombre(String rolNombre) {
		this.rolNombre = rolNombre;
	}
	
}
