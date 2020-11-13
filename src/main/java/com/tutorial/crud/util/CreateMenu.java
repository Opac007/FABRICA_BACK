package com.tutorial.crud.util;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.tutorial.crud.menu.entity.Menu;
import com.tutorial.crud.menu.service.MenuService;
import com.tutorial.crud.security.entity.Rol;
import com.tutorial.crud.security.enums.RolNombre;
import com.tutorial.crud.rol.service.RolService;

@Component
public class CreateMenu implements CommandLineRunner{

	@Autowired
	MenuService menuService;
	
	@Autowired
	RolService rolService;
	
	@Override
	public void run(String... args) throws Exception {
		Set<Rol> roles = new HashSet<>();

		//roles.add(rolService.getByRolNombre(RolNombre.ROLE_USER).get());
		Menu menu1 = new Menu("Feedback", "feedback", "/dashboard"); 
		menu1.setRoles(roles);
		//menuService.save(menu1);
		
		//roles.add(rolService.getByRolNombre(RolNombre.ROLE_ADMIN).get());
		Menu menu2 = new Menu("Producto", "work", "/producto"); 
		menu2.setRoles(roles);
		//menuService.save(menu2);
	}

}
