package com.tutorial.crud.usuario.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.tutorial.crud.usuario.entity.UsuarioLite;
import com.tutorial.crud.usuario.repository.UsuarioLiteRepository;

@Service
@Transactional
public class UsuarioLiteService {

	@Autowired
	UsuarioLiteRepository usuarioLiteRepository;
	
	public Optional<UsuarioLite> getByNombreUsuario(String nombreUsuario) {
		return usuarioLiteRepository.findByNombreUsuario(nombreUsuario);
	}
}
