package ar.com.ada.api.aladas.entities;

import java.util.*;

import javax.persistence.*;

@Entity
@Table(name = "pasaje")
public class Pasaje {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pasaje_id")
    private Integer pasajeId;

    @OneToOne
    @JoinColumn(name = "reserva_id", referencedColumnName = "reserva_id")
    private Reserva reserva;

    @Column(name = "fecha_emision")
    private Date fechaEmision;

    @Column(name = "info_pago")
    private String infoPago;

    public Integer getPasajeId() {
        return pasajeId;
    }

    public void setPasajeId(Integer pasajeId) {
        this.pasajeId = pasajeId;
    }

    public Reserva getReserva() {
        return reserva;
    }

    public void setReserva(Reserva reserva) {
        this.reserva = reserva;
        //reserva.setPasaje(this);relacion bidireccional
    }

    public Date getFechaEmision() {
        return fechaEmision;
    }

    public void setFechaEmision(Date fechaEmision) {
        this.fechaEmision = fechaEmision;
    }

    public String getInfoPago() {
        return infoPago;
    }

    public void setInfoPago(String infoPago) {
        this.infoPago = infoPago;
    }

    
}
