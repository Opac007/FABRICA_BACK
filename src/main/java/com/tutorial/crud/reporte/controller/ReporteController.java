package com.tutorial.crud.reporte.controller;

import com.tutorial.crud.dto.Mensaje;
import com.tutorial.crud.entity.Producto;
import com.tutorial.crud.service.ProductoService;
import com.tutorial.crud.util.CommonUtils;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/report")
@CrossOrigin
public class ReporteController {

    @Autowired
    ProductoService productoService;

    @GetMapping("/generateAllProduct")
    public ResponseEntity<?> generateAllProduct(){
    	
    	List<Producto> listProducto = productoService.list();
        
        List<String[]> list = new ArrayList<>();
        
        String[] headerCsv = {"NOMBRE PRODUCTO", "PRECIO PRODUCTO"};
        list.add(headerCsv);
        for (Producto regProd: listProducto) {
        	list.add(new String[]{regProd.getNombre(), String.valueOf(regProd.getPrecio())});
        }
        
        Path path = null;
        try {
            path = CommonUtils.fileOutCustomPath("csv/listaMateriales.csv");
        	//path = CommonUtils.fileOutAllPath();
        } catch (Exception ex) {
        	return new ResponseEntity(new Mensaje("error en el sistema"), HttpStatus.BAD_REQUEST);
        }
        
        CommonUtils.csvWriterAll(list, path);
        
        return new ResponseEntity(new Mensaje("El reporte fue creado con exito"), HttpStatus.OK);
    }
    
}
