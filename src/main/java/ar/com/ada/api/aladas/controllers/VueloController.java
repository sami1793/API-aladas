package ar.com.ada.api.aladas.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import ar.com.ada.api.aladas.entities.Vuelo;
import ar.com.ada.api.aladas.models.response.GenericResponse;
import ar.com.ada.api.aladas.services.VueloService;
import ar.com.ada.api.aladas.services.VueloService.ValidacionVueloDataEnum;

@RestController
public class VueloController {
    //version simple
    /*@Autowired
    VueloServices service;*/

    //version compleja
    private VueloService service;

    public VueloController(VueloService service){
    this.service=service;
    }

    @PostMapping("/api/vuelos")
    public ResponseEntity<GenericResponse> postCrearVuelo(@RequestBody Vuelo vuelo){

        GenericResponse respuesta = new GenericResponse();

        ValidacionVueloDataEnum resultadoValidacion= service.validar(vuelo);

        if(resultadoValidacion==ValidacionVueloDataEnum.OK){
            service.crear(vuelo);
            respuesta.isOk=true;
            respuesta.message="Vuelo creado correctamente";
            respuesta.Id=vuelo.getVueloId();

            return ResponseEntity.ok(respuesta);
        }
        else{
            respuesta.isOk=false;
            respuesta.message="Error("+resultadoValidacion.toString()+")";
            
            return ResponseEntity.badRequest().body(respuesta);
        }
        

    }
}
