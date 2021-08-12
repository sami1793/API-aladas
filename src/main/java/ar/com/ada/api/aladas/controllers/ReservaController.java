package ar.com.ada.api.aladas.controllers;

import org.springframework.beans.factory.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import ar.com.ada.api.aladas.models.request.InfoReservaNueva;
import ar.com.ada.api.aladas.models.response.GenericResponse;
import ar.com.ada.api.aladas.services.ReservaServices;

@RestController
public class ReservaController {
    @Autowired
    ReservaServices services;

    @PostMapping("/api/reservas")
    public ResponseEntity<GenericResponse> generarReserva(@RequestBody InfoReservaNueva infoReserva){
        GenericResponse respuesta= new GenericResponse();

        Integer numeroReserva = services.generarReserva(infoReserva.vueloId);

        respuesta.Id=numeroReserva;
        respuesta.isOk=true;
        respuesta.message="Reserva creada con éxito";

        return ResponseEntity.ok(respuesta);
    }
}
