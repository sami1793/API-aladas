package ar.com.ada.api.aladas.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.com.ada.api.aladas.entities.Aeropuerto;
import ar.com.ada.api.aladas.repos.AeropuertoRepository;

@Service
public class AeropuertoServices {
    
    @Autowired
    AeropuertoRepository repo;//declarar al repo

    //pongo aeropuertoId porque NO es autoincremental
    public void crear(Integer aeropuertoId, String nombre, String codigoIATA){

        Aeropuerto aeropuerto =  new Aeropuerto();

        aeropuerto.setAeropuertoId(aeropuertoId);
        aeropuerto.setNombre(nombre);
        aeropuerto.setCodigoIATA(codigoIATA);

        repo.save(aeropuerto);
    }

    public List<Aeropuerto> obtenerTodos(){

        return repo.findAll();
    }

}