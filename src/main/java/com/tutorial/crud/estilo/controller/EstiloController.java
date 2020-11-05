package com.tutorial.crud.estilo.controller;

import com.tutorial.crud.estilo.entity.Estilo;
import com.tutorial.crud.estilo.service.EstiloService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

}
