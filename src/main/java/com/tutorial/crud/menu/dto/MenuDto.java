package com.tutorial.crud.menu.dto;

public class MenuDto {
	private int id;
	private String nombre;
	private String icon;
	private String path;
	
	public MenuDto() {
		super();
	}

	public MenuDto(String nombre, String icon, String path) {
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
	
}
