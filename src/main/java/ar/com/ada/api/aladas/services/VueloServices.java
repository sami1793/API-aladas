package ar.com.ada.api.aladas.services;

import java.math.BigDecimal;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.com.ada.api.aladas.entities.Aeropuerto;
import ar.com.ada.api.aladas.entities.Vuelo;
import ar.com.ada.api.aladas.repos.VueloRepository;

@Service
public class VueloServices {
    
    @Autowired
    private VueloRepository repo;

    @Autowired
    private AeropuertoServices aeroservices;

    public void crear(Vuelo vuelo){
        repo.save(vuelo);
    }

    //estado vuelo no hace falta ya va a estar en creado
    public Vuelo crear(Date fecha, Integer capacidad, String aeropuertoOrigenIATA, 
    String aeropuertoDestinoIATA, BigDecimal precio, String codigoMoneda ){

        Vuelo vuelo = new Vuelo();

        vuelo.setFecha(fecha);
        vuelo.setCapacidad(capacidad);

        Aeropuerto aeropuertoOrigen = aeroservices.buscarPorCodigoIATA(aeropuertoOrigenIATA);
        Aeropuerto aeropuertoDestino = aeroservices.buscarPorCodigoIATA(aeropuertoDestinoIATA);

        vuelo.setAeropuertoOrigen(aeropuertoOrigen.getAeropuertoId());
        vuelo.setAeropuertoDestino(aeropuertoDestino.getAeropuertoId());


        vuelo.setPrecio(precio);
        vuelo.setCodigoMoneda(codigoMoneda);

        return repo.save(vuelo);//puedo poner asi porque devuelve un Vuelo
    }
}
