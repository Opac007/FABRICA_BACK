package com.tutorial.crud.rol.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tutorial.crud.rol.repository.RolRepository;
import com.tutorial.crud.security.entity.Rol;
import com.tutorial.crud.security.enums.RolNombre;


@Service
@Transactional
public class RolService {

    @Autowired
    RolRepository rolRepository;

    public List<Rol> list(){
        return rolRepository.findAll();
    }

    public Optional<Rol> getOne(int id){
        return rolRepository.findById(id);
    }

    public Optional<Rol> getByRolNombre(String rolNombre){
        return rolRepository.findByRolNombre(rolNombre);
    }

	public Optional<Rol> getByRolNombre(RolNombre rolNombre) {
		return rolRepository.findByRolNombre(rolNombre);
	}

    public boolean existsById(int id){
        return rolRepository.existsById(id);
    }

    public boolean existsByRolNombre(RolNombre rolNombre){
        return rolRepository.existsByRolNombre(rolNombre);
    }

    public void  save(Rol rol){
        rolRepository.save(rol);
    }

    public void delete(int id){
        rolRepository.deleteById(id);
    }
	
}
