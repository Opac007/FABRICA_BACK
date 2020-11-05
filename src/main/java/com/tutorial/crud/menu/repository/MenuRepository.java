package com.tutorial.crud.menu.repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tutorial.crud.menu.entity.Menu;
import com.tutorial.crud.security.entity.Rol;

@Repository
public interface MenuRepository extends JpaRepository<Menu, Integer> {
	List<Menu> findDistinctByRolesIn(Set<Rol> roles);
	List<Menu> findByRoles(Rol rol);
    Optional<Menu> findByNombre(String nombre);
    boolean existsByNombre(String nombre);
}
