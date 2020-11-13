package com.tutorial.crud.rol.controller;

import java.util.List;

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
import com.tutorial.crud.security.entity.Rol;
import com.tutorial.crud.security.enums.RolNombre;
import com.tutorial.crud.rol.dto.RolDto;
import com.tutorial.crud.rol.service.RolService;


@RestController
@RequestMapping("/rol")
@CrossOrigin
public class RolController {
	
	@Autowired
	RolService rolService;

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/lista")
    public ResponseEntity<List<Rol>> list(){
        List<Rol> list = rolService.list();
        return new ResponseEntity(list, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/detailRol/{id}")
    public ResponseEntity<Rol> getById(@PathVariable("id") int id){
        if(!rolService.existsById(id))
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        Rol rol = rolService.getOne(id).get();
        return new ResponseEntity(rol, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/createRol")
    public ResponseEntity<?> create(@RequestBody RolDto rolDto){
        if(StringUtils.isBlank(rolDto.getRolNombre()))
            return new ResponseEntity(new Mensaje("el nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        if(rolService.existsByRolNombre(RolNombre.valueOf(rolDto.getRolNombre())))
            return new ResponseEntity(new Mensaje("ese nombre ya existe"), HttpStatus.BAD_REQUEST);

        Rol rol = new Rol(RolNombre.valueOf(rolDto.getRolNombre()));
		
        rolService.save(rol);
        return new ResponseEntity(new Mensaje("rol creado"), HttpStatus.OK);
    }


    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/updateRol/{id}")
    public ResponseEntity<?> update(@PathVariable("id")int id, @RequestBody RolDto rolDto){
        if(!rolService.existsById(id))
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        if(rolService.existsByRolNombre(RolNombre.valueOf(rolDto.getRolNombre())) && rolService.getByRolNombre(rolDto.getRolNombre()).get().getId() != id)
            return new ResponseEntity(new Mensaje("ese nombre ya existe"), HttpStatus.BAD_REQUEST);
        if(StringUtils.isBlank(rolDto.getRolNombre()))
            return new ResponseEntity(new Mensaje("el nombre es obligatorio"), HttpStatus.BAD_REQUEST);

        Rol rol = rolService.getOne(id).get();
        rol.setRolNombre(RolNombre.valueOf(rolDto.getRolNombre()));
        rolService.save(rol);
        return new ResponseEntity(new Mensaje("rol actualizado"), HttpStatus.OK);
    }


    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/deleteRol/{id}")
    public ResponseEntity<?> delete(@PathVariable("id")int id){
        if(!rolService.existsById(id))
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        rolService.delete(id);
        return new ResponseEntity(new Mensaje("rol eliminado"), HttpStatus.OK);
    }

}
