package com.tutorial.crud.usuario.controller;

import com.tutorial.crud.dto.Mensaje;
import com.tutorial.crud.estilo.entity.Estilo;
import com.tutorial.crud.estilo.service.EstiloService;
import com.tutorial.crud.security.entity.Usuario;
import com.tutorial.crud.security.service.UsuarioService;
import com.tutorial.crud.usuario.entity.UsuarioLite;
import com.tutorial.crud.usuario.service.UsuarioLiteService;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuario")
@CrossOrigin
public class UsuarioController {

    @Autowired
    UsuarioService usuarioService;

    @Autowired
    UsuarioLiteService usuarioLiteService;

    @Autowired
    EstiloService estiloService;

    @GetMapping("/detailUser/{nombre_usuario}")
    public ResponseEntity<Usuario> getByUserName(@PathVariable("nombre_usuario") String nombreUsuario){
        if (!usuarioService.existsByNombreUsuario(nombreUsuario))
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        UsuarioLite usuario = usuarioLiteService.getByNombreUsuario(nombreUsuario).get();
        return new ResponseEntity(usuario, HttpStatus.OK);
    }

    @PutMapping("/update/{idEstilo}")
    public ResponseEntity<?> update(@PathVariable("idEstilo")int idEstilo, @RequestBody Usuario usuarioDto){
        if(!estiloService.existsById(idEstilo))
            return new ResponseEntity(new Mensaje("no existe el estilo"), HttpStatus.NOT_FOUND);
        if(StringUtils.isBlank(usuarioDto.getNombreUsuario()))
            return new ResponseEntity(new Mensaje("el nombre de usuario es obligatorio"), HttpStatus.BAD_REQUEST);
        if(usuarioService.existsByNombreUsuario(usuarioDto.getNombreUsuario()) && usuarioService.getByNombreUsuario(usuarioDto.getNombreUsuario()).get().getId() != usuarioDto.getId())
            return new ResponseEntity(new Mensaje("ese nombre de usuario ya existe"), HttpStatus.BAD_REQUEST);
		if (usuarioService.existsByEmail(usuarioDto.getEmail()) && usuarioService.getByEmail(usuarioDto.getEmail()).get().getId() != usuarioDto.getId())
			return new ResponseEntity(new Mensaje("el email ya existe"), HttpStatus.BAD_REQUEST);
        
		Estilo estilo = estiloService.getOne(idEstilo).get();
        Usuario usuario = usuarioService.getOne(usuarioDto.getId()).get();
        usuario.setNombre(usuarioDto.getNombre());
        usuario.setNombreUsuario(usuarioDto.getNombreUsuario());
        usuario.setEmail(usuarioDto.getEmail());
        usuario.setEstilo(estilo);
        usuarioService.save(usuario);
        
        return new ResponseEntity(new Mensaje("usuario actualizado"), HttpStatus.OK);
    }

}
