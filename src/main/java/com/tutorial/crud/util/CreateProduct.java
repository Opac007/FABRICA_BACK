package com.tutorial.crud.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.tutorial.crud.entity.Producto;
import com.tutorial.crud.service.ProductoService;

@Component
public class CreateProduct implements CommandLineRunner{

	@Autowired
	ProductoService productoService;
	
	@Override
	public void run(String... args) throws Exception {
		Producto producto = new Producto("producto1", 100);
		//productoService.save(producto);
	}

}
