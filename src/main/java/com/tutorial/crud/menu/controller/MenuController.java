package com.tutorial.crud.menu.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tutorial.crud.dto.Mensaje;
import com.tutorial.crud.menu.dto.MenuDto;
import com.tutorial.crud.menu.entity.Menu;
import com.tutorial.crud.menu.service.MenuService;
import com.tutorial.crud.security.entity.Rol;
import com.tutorial.crud.security.entity.Usuario;
import com.tutorial.crud.security.enums.RolNombre;
import com.tutorial.crud.security.service.RolService;
import com.tutorial.crud.security.service.UsuarioService;


@RestController
@RequestMapping("/menu")
@CrossOrigin
public class MenuController {

    @Autowired
    MenuService menuService;
	
	@Autowired
	RolService rolService;

    @Autowired
    UsuarioService usuarioService;

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/lista")
    public ResponseEntity<List<Menu>> list(){
        List<Menu> list = menuService.list();
        return new ResponseEntity(list, HttpStatus.OK);
    }

    @GetMapping("/lista/{nombreUsuario}")
    public ResponseEntity<List<Menu>> listByUsuario(@PathVariable("nombreUsuario") String nombreUsuario){
    	
    	Usuario usuario = usuarioService.getByNombreUsuario(nombreUsuario).get();
    	
    	//List<Menu> resultado = new ArrayList<>();
    	
    	//for (Rol rol :usuario.getRoles()) {
    	//	List<Menu> menuList = menuService.getByRol(rol);
    	//	
    	//	for (Menu menu: menuList) {
    	//		if (!resultado.contains(menu)) {
    	//			resultado.add(menu);
    	//		}
    	//	}
    	//	
    	//}
    	
    	List<Menu> menuList = menuService.getByRoles(usuario.getRoles());
        
        for (int i = 0; i < menuList.size(); i++) {
        	menuList.get(i).setRoles(null);
        }
        
        return new ResponseEntity(menuList, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/detailMenu/{id}")
    public ResponseEntity<Menu> getById(@PathVariable("id") int id){
        if(!menuService.existsById(id))
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        Menu menu = menuService.getOne(id).get();
        return new ResponseEntity(menu, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/createMenu")
    public ResponseEntity<?> create(@RequestBody MenuDto menuDto){
        if(StringUtils.isBlank(menuDto.getNombre()))
            return new ResponseEntity(new Mensaje("el nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        if(menuService.existsByNombre(menuDto.getNombre()))
            return new ResponseEntity(new Mensaje("ese nombre ya existe"), HttpStatus.BAD_REQUEST);
		
        Set<Rol> roles = new HashSet<>();
		roles.add(rolService.getByRolNombre(RolNombre.ROLE_ADMIN).get());

        Menu menu = new Menu(menuDto.getNombre(), menuDto.getIcon(), menuDto.getPath());
		menu.setRoles(roles);
		
        menuService.save(menu);
        return new ResponseEntity(new Mensaje("menu creado"), HttpStatus.OK);
    }


    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/updateMenu/{id}")
    public ResponseEntity<?> update(@PathVariable("id")int id, @RequestBody MenuDto menuDto){
        if(!menuService.existsById(id))
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        if(menuService.existsByNombre(menuDto.getNombre()) && menuService.getByNombre(menuDto.getNombre()).get().getId() != id)
            return new ResponseEntity(new Mensaje("ese nombre ya existe"), HttpStatus.BAD_REQUEST);
        if(StringUtils.isBlank(menuDto.getNombre()))
            return new ResponseEntity(new Mensaje("el nombre es obligatorio"), HttpStatus.BAD_REQUEST);

        Menu menu = menuService.getOne(id).get();
        menu.setNombre(menuDto.getNombre());
        menu.setIcon(menuDto.getIcon());
        menu.setPath(menuDto.getPath());
        menuService.save(menu);
        return new ResponseEntity(new Mensaje("menu actualizado"), HttpStatus.OK);
    }


    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/deleteMenu/{id}")
    public ResponseEntity<?> delete(@PathVariable("id")int id){
        if(!menuService.existsById(id))
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        menuService.delete(id);
        return new ResponseEntity(new Mensaje("menu eliminado"), HttpStatus.OK);
    }

}
