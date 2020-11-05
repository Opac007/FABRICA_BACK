package com.tutorial.crud.menu.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tutorial.crud.entity.Producto;
import com.tutorial.crud.menu.entity.Menu;
import com.tutorial.crud.menu.repository.MenuRepository;
import com.tutorial.crud.security.entity.Rol;


@Service
@Transactional
public class MenuService {

    @Autowired
    MenuRepository menuRepository;

    public List<Menu> list(){
        return menuRepository.findAll();
    }

    public Optional<Menu> getOne(int id){
        return menuRepository.findById(id);
    }
	
	public List<Menu> getByRoles(Set<Rol> roles) {
		return menuRepository.findDistinctByRolesIn(roles);
	}
	
	public List<Menu> getByRol(Rol roles) {
		return menuRepository.findByRoles(roles);
	}

    public Optional<Menu> getByNombre(String nombre){
        return menuRepository.findByNombre(nombre);
    }

    public boolean existsById(int id){
        return menuRepository.existsById(id);
    }

    public boolean existsByNombre(String nombre){
        return menuRepository.existsByNombre(nombre);
    }

    public void  save(Menu menu){
        menuRepository.save(menu);
    }

    public void delete(int id){
        menuRepository.deleteById(id);
    }
	
}
