package com.tutorial.crud.estilo.controller;

import com.tutorial.crud.dto.Mensaje;
import com.tutorial.crud.estilo.entity.Estilo;
import com.tutorial.crud.estilo.service.EstiloService;
import com.tutorial.crud.estilo.dto.EstiloDto;
import com.tutorial.crud.security.entity.Rol;
import com.tutorial.crud.security.enums.RolNombre;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/estilo")
@CrossOrigin
public class EstiloController {

    @Autowired
    EstiloService estiloService;

    @GetMapping("/lista")
    public ResponseEntity<List<Estilo>> list(){
        List<Estilo> list = estiloService.list();
        return new ResponseEntity(list, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/detailEstilo/{id}")
    public ResponseEntity<Estilo> getById(@PathVariable("id") int id){
        if(!estiloService.existsById(id))
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        Estilo estilo = estiloService.getOne(id).get();
        return new ResponseEntity(estilo, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/createEstilo")
    public ResponseEntity<?> create(@RequestBody EstiloDto estiloDto){
        if(StringUtils.isBlank(estiloDto.getDescription()))
            return new ResponseEntity(new Mensaje("la descripcion es obligatorio"), HttpStatus.BAD_REQUEST);
        if(StringUtils.isBlank(estiloDto.getValor()))
            return new ResponseEntity(new Mensaje("el valor es obligatorio"), HttpStatus.BAD_REQUEST);
        if(estiloService.existsByDescription(estiloDto.getDescription()))
            return new ResponseEntity(new Mensaje("esa descripcion ya existe"), HttpStatus.BAD_REQUEST);

        Estilo estilo = new Estilo(estiloDto.getDescription(), estiloDto.getValor());
		
        estiloService.save(estilo);
        return new ResponseEntity(new Mensaje("estilo creado"), HttpStatus.OK);
    }


    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/updateEstilo/{id}")
    public ResponseEntity<?> update(@PathVariable("id")int id, @RequestBody EstiloDto estiloDto){
        if(!estiloService.existsById(id))
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        if(estiloService.existsByDescription(estiloDto.getDescription()) && estiloService.getByDescription(estiloDto.getDescription()).get().getId() != id)
            return new ResponseEntity(new Mensaje("esa descripcion ya existe"), HttpStatus.BAD_REQUEST);
        if(StringUtils.isBlank(estiloDto.getDescription()))
            return new ResponseEntity(new Mensaje("la descripcion es obligatorio"), HttpStatus.BAD_REQUEST);
        if(StringUtils.isBlank(estiloDto.getValor()))
            return new ResponseEntity(new Mensaje("el valor es obligatorio"), HttpStatus.BAD_REQUEST);

        Estilo estilo = estiloService.getOne(id).get();
        estilo.setDescription(estiloDto.getDescription());
        estilo.setValor(estiloDto.getValor());
        estiloService.save(estilo);
        return new ResponseEntity(new Mensaje("estilo actualizado"), HttpStatus.OK);
    }


    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/deleteEstilo/{id}")
    public ResponseEntity<?> delete(@PathVariable("id")int id){
        if(!estiloService.existsById(id))
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        estiloService.delete(id);
        return new ResponseEntity(new Mensaje("estilo eliminado"), HttpStatus.OK);
    }

}
