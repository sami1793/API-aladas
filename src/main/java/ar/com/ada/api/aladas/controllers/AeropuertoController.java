package ar.com.ada.api.aladas.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import ar.com.ada.api.aladas.entities.Aeropuerto;
import ar.com.ada.api.aladas.models.response.GenericResponse;
import ar.com.ada.api.aladas.services.AeropuertoServices;

@RestController
public class AeropuertoController {
    @Autowired
    AeropuertoServices service;

    @PostMapping("/api/aeropuertos")
    public ResponseEntity<GenericResponse> crear(@RequestBody Aeropuerto aeropuerto){

        GenericResponse respuesta= new GenericResponse();

        if(!(service.existeAeropuertoId(aeropuerto.getAeropuertoId()))){

            service.crear(aeropuerto.getAeropuertoId(), aeropuerto.getNombre(), aeropuerto.getCodigoIATA());

            respuesta.isOk= true;
            respuesta.message="Aeropuerto creado correctamente";

            return ResponseEntity.ok(respuesta);
        }
        else{
            respuesta.isOk=false;
            respuesta.message="El id que quiere crear ya existe";

            return ResponseEntity.badRequest().body(respuesta);

        }
            

        
    }
    
}
