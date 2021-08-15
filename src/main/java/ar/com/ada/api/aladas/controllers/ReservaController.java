package ar.com.ada.api.aladas.controllers;

import org.springframework.beans.factory.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import ar.com.ada.api.aladas.entities.Usuario;
import ar.com.ada.api.aladas.entities.Usuario.TipoUsuarioEnum;
import ar.com.ada.api.aladas.models.request.InfoReservaNueva;
import ar.com.ada.api.aladas.models.request.InfoReservaNuevaV2;
import ar.com.ada.api.aladas.models.response.GenericResponse;
import ar.com.ada.api.aladas.services.ReservaServices;
import ar.com.ada.api.aladas.services.UsuarioService;

@RestController
public class ReservaController {
    @Autowired
    ReservaServices services;

    @Autowired
    UsuarioService usuarioService;

    @PostMapping("/api/reservas")
    public ResponseEntity<GenericResponse> generarReserva(@RequestBody InfoReservaNueva infoReserva){
        GenericResponse respuesta= new GenericResponse();

        //obtengo a quien esta autenticado del otro lado
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        //de lo que esta autenticado obtengo su username
        String username = authentication.getName();

        Usuario usuario= usuarioService.buscarPorUsername(username);

        Integer numeroReserva = services.generarReserva(infoReserva.vueloId, usuario.getPasajero().getPasajeroId());

        respuesta.Id=numeroReserva;
        respuesta.isOk=true;
        respuesta.message="Reserva creada con éxito";

        return ResponseEntity.ok(respuesta);
    }

    @PostMapping("/api/reservasV2")
    public ResponseEntity<GenericResponse> generarReservaV2(@RequestBody InfoReservaNuevaV2 infoReservaV2){
        GenericResponse respuesta = new GenericResponse();
        //obtengo a quien esta autenticado del otro lado
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        //de lo que esta autenticado obtengo su username
        String username = authentication.getName();

        Usuario usuario= usuarioService.buscarPorUsername(username);

        Integer numeroReserva;

        if(usuario.getTipoUsuarioId()== TipoUsuarioEnum.PASAJERO){

            numeroReserva = services.generarReserva(infoReservaV2.vueloId, usuario.getPasajero().getPasajeroId());
        }
        else
            numeroReserva= services.generarReserva(infoReservaV2.vueloId, infoReservaV2.pasajeroId);

        respuesta.Id=numeroReserva;
        respuesta.isOk=true;
        respuesta.message="Reserva creada con éxito";

        return ResponseEntity.ok(respuesta);


    }
}
