package com.tutorial.crud.estilo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tutorial.crud.estilo.entity.Estilo;
import com.tutorial.crud.estilo.repository.EstiloRepository;
import com.tutorial.crud.security.enums.RolNombre;

@Service
@Transactional
public class EstiloService {

    @Autowired
    EstiloRepository estiloRepository;

	public Optional<Estilo> getByDescription(String description) {
		return estiloRepository.findByDescription(description);
	}

    public List<Estilo> list(){
        return estiloRepository.findAll();
    }

    public boolean existsById(int id){
        return estiloRepository.existsById(id);
    }

    public Optional<Estilo> getOne(int id){
        return estiloRepository.findById(id);
    }

    public void save(Estilo estilo){
        estiloRepository.save(estilo);
    }

    public void delete(int id){
        estiloRepository.deleteById(id);
    }

    public boolean existsByDescription(String description){
        return estiloRepository.existsByDescription(description);
    }

    public boolean existsByValor(String valor){
        return estiloRepository.existsByValor(valor);
    }

}
