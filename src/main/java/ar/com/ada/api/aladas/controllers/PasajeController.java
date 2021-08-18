package ar.com.ada.api.aladas.controllers;

import java.util.List;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import ar.com.ada.api.aladas.entities.Pasaje;
import ar.com.ada.api.aladas.models.request.InfoPasajeNuevo;
import ar.com.ada.api.aladas.models.response.GenericResponse;
import ar.com.ada.api.aladas.services.PasajeService;

@RestController
public class PasajeController {
    @Autowired
    PasajeService service;

    @PostMapping("api/pasajes")
    public ResponseEntity<GenericResponse> emitirPasaje(@RequestBody InfoPasajeNuevo infoPasaje ){
        
        GenericResponse respuesta = new GenericResponse();

        Pasaje pasaje = service.emitirPasaje(infoPasaje.reservaId);

        respuesta.isOk=true;
        respuesta.message="Pasaje creado correctamente";
        respuesta.Id=pasaje.getPasajeId();

        return ResponseEntity.ok(respuesta);


    }
    @GetMapping("api/pasajes")
    public ResponseEntity<List<Pasaje>> obtenerPasajes(){
        List<Pasaje> pasajes = service.obtenerPasajes();
        return ResponseEntity.ok(pasajes);
    }
}
