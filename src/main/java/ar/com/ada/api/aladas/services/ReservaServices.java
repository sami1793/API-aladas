package ar.com.ada.api.aladas.services;

import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.com.ada.api.aladas.entities.Pasajero;
import ar.com.ada.api.aladas.entities.Reserva;
import ar.com.ada.api.aladas.entities.Vuelo;
import ar.com.ada.api.aladas.entities.Reserva.EstadoReservaEnum;
import ar.com.ada.api.aladas.repos.ReservaRepository;

@Service
public class ReservaServices {

    @Autowired
    ReservaRepository repo;

    @Autowired
    VueloService vueloServices;

    @Autowired
    PasajeroService pasajeroService;

    public Reserva generarReserva(Integer vueloId, Integer pasajeroId) {
        Reserva reserva = new Reserva();

        Vuelo vuelo = vueloServices.buscar(vueloId);

        
        reserva.setFechaEmision(new Date());

        //Agrego un día como fecha de vencimiento
        Calendar c = Calendar.getInstance();
        c.setTime(reserva.getFechaEmision());
        c.add(Calendar.DATE, 1);

        reserva.setFechaVencimiento(c.getTime());

        reserva.setEstadoReservaId(EstadoReservaEnum.CREADO);

        Pasajero pasajero= pasajeroService.buscarPorId(pasajeroId);
        reserva.setPasajero(pasajero);

        //pongo las relaciones bidireccionales
        //una reserva tiene pasajero y vuelo
        pasajero.agregarReserva(reserva);
        vuelo.agregarReserva(reserva);

        repo.save(reserva);

        return reserva;
    }

    public Reserva buscarPorId(Integer reservaId){
        Reserva reserva=repo.findByReservaId(reservaId);
        return reserva;
    }

    

}
