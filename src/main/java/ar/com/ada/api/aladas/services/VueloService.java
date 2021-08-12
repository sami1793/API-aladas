package ar.com.ada.api.aladas.services;

import java.math.BigDecimal;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.com.ada.api.aladas.entities.Aeropuerto;
import ar.com.ada.api.aladas.entities.Vuelo;
import ar.com.ada.api.aladas.entities.Reserva.EstadoReservaEnum;
import ar.com.ada.api.aladas.entities.Vuelo.EstadoVueloEnum;
import ar.com.ada.api.aladas.repos.VueloRepository;

@Service
public class VueloService {
    
    @Autowired
    private VueloRepository repo;

    @Autowired
    private AeropuertoService aeroservices;

    public void crear(Vuelo vuelo){
        
        vuelo.setEstadoVueloId(EstadoVueloEnum.CREADO);
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

    public ValidacionVueloDataEnum validar(Vuelo vuelo){

        if(!validarPrecio(vuelo))//si se cumple hay un error en el precio
            return ValidacionVueloDataEnum.ERROR_PRECIO;

        if(!validarAeropuertoOrigenDiffDestino(vuelo))
            return ValidacionVueloDataEnum.ERROR_AEROPUERTOS_IGUALES;

        if(!validarAeropuertoOrigen(vuelo))//si NO existe
            return ValidacionVueloDataEnum.ERROR_AEROPUERTO_ORIGEN;

        if(!validarAeropuertoDestino(vuelo))
            return ValidacionVueloDataEnum.ERROR_AEROPUERTO_DESTINO;

        
        return ValidacionVueloDataEnum.OK;
    }

    public boolean validarPrecio(Vuelo vuelo){
        if(vuelo.getPrecio()==null)
            return false;
        if(vuelo.getPrecio().doubleValue()>0)
            return true;
        return false;
    }

    public boolean validarAeropuertoOrigenDiffDestino(Vuelo vuelo){

        //return vuelo.getAeropuertoOrigen()!=vuelo.getAeropuertoDestino();//verifico que sean diferentes
        //return vuelo.getAeropuertoOrigen().equals(vuelo.getAeropuertoDestino());// porque como integer es objeto, se compara con equals
        return vuelo.getAeropuertoOrigen().intValue()!=vuelo.getAeropuertoDestino().intValue();
    }

    public boolean validarAeropuertoOrigen(Vuelo vuelo){
        return aeroservices.existeAeropuertoId(vuelo.getAeropuertoOrigen());
    }

    public boolean validarAeropuertoDestino(Vuelo vuelo){
        return aeroservices.existeAeropuertoId(vuelo.getAeropuertoDestino());
    }

    public enum ValidacionVueloDataEnum{
        OK, ERROR_PRECIO, ERROR_AEROPUERTO_ORIGEN, ERROR_AEROPUERTO_DESTINO, ERROR_FECHA,
        ERROR_AEROPUERTOS_IGUALES,ERROR_MONEDA, ERROR_CAPACIDAD_MINIMA, ERROR_CAPACIDAD_MAXIMA, 
        ERROR_GENERAL,
    }

    public Vuelo buscar(Integer id) {
        return repo.findByVueloId(id);
    }

    public void actualizar(Vuelo vuelo) {
        repo.save(vuelo);
    }

    public List<Vuelo> traerVuelosAbiertos() {
        return repo.findByEstadoVueloId(EstadoVueloEnum.ABIERTO.getValue());
    }


}
