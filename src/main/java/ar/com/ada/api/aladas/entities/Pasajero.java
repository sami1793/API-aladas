package ar.com.ada.api.aladas.entities;

import java.util.*;

import javax.annotation.Generated;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="pasajero")
public class Pasajero extends Persona{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="pasajero_id")
    private Integer pasajeroId;

    public Integer getPasajeroId() {
        return pasajeroId;
    }

    public void setPasajeroId(Integer pasajeroId) {
        this.pasajeroId = pasajeroId;
    }

    
    
}
