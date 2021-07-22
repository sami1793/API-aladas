package ar.com.ada.api.aladas.entities;

import java.util.*;

import javax.persistence.*;

@Entity
@Table(name="pasajero")
public class Pasajero extends Persona{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="pasajero_id")
    private Integer pasajeroId;

    @OneToMany(mappedBy = "pasajero", cascade = CascadeType.ALL, fetch = FetchType.LAZY )//VER!!
    private List<Reserva> reservas = new ArrayList<>();

    public Integer getPasajeroId() {
        return pasajeroId;
    }

    public void setPasajeroId(Integer pasajeroId) {
        this.pasajeroId = pasajeroId;
    }

    //relacion bidireccional con Reserva
    public void agregarReserva(Reserva reserva){
        this.reservas.add(reserva);
        //otra forma de hacer la relacion bidireccional
        reserva.setPasajero(this);//sino llamo a este método nunca se va a hacer la relac bidireccional
    }

    
    
}
