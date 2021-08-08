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

    @OneToOne(mappedBy = "pasajero", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Usuario usuario;

    public Integer getPasajeroId() {
        return pasajeroId;
    }

    public void setPasajeroId(Integer pasajeroId) {
        this.pasajeroId = pasajeroId;
    }

    
    
    public List<Reserva> getReservas() {
        return reservas;
    }

    public void setReservas(List<Reserva> reservas) {
        this.reservas = reservas;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
        usuario.setPasajero(this);
    }

    //relacion bidireccional con Reserva
    public void agregarReserva(Reserva reserva){
        this.reservas.add(reserva);
        //otra forma de hacer la relacion bidireccional
        reserva.setPasajero(this);//sino llamo a este método nunca se va a hacer la relac bidireccional
    }

    
    
}
