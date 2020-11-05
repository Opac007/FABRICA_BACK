package com.tutorial.crud.security.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tutorial.crud.entity.Producto;
import com.tutorial.crud.security.entity.Usuario;
import com.tutorial.crud.security.repository.UsuarioRepository;

@Service
@Transactional
public class UsuarioService {

	@Autowired
	UsuarioRepository usuarioRepository;

    public Optional<Usuario> getOne(int id){
        return usuarioRepository.findById(id);
    }
	
	public Optional<Usuario> getByNombreUsuario(String nombreUsuario) {
		return usuarioRepository.findByNombreUsuario(nombreUsuario);
	}
	
	public Optional<Usuario> getByEmail(String email) {
		return usuarioRepository.findByEmail(email);
	}
	
	public boolean existsByNombreUsuario(String nombreUsuario) {
		return usuarioRepository.existsByNombreUsuario(nombreUsuario);
	}
	
	public boolean existsByEmail(String email) {
		return usuarioRepository.existsByEmail(email);
	}
	
	public void save(Usuario usuario) {
		usuarioRepository.save(usuario);
	}
}
