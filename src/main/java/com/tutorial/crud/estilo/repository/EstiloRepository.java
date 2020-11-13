package com.tutorial.crud.estilo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tutorial.crud.estilo.entity.Estilo;

public interface EstiloRepository extends JpaRepository<Estilo, Integer> {
	Optional<Estilo> findByDescription(String description);
	Optional<Estilo> findByValor(String valor);
	boolean existsByDescription(String description);
	boolean existsByValor(String valor);
}
