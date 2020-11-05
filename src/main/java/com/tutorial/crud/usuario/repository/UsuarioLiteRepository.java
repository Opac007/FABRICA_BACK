package com.tutorial.crud.usuario.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tutorial.crud.usuario.entity.UsuarioLite;

@Repository
public interface UsuarioLiteRepository extends JpaRepository<UsuarioLite, Integer>{
	Optional<UsuarioLite> findByNombreUsuario(String nombreUsuario);

}
