package ar.com.ada.api.aladas.entities;

import java.util.Date;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "reserva")
public class Reserva {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reserva_id")
    private Integer reservaId;

    @ManyToOne
    @JsonIgnore//para no tener anidado al buscar vuelos BIEN
    @JoinColumn(name = "vuelo_id", referencedColumnName = "vuelo_id")
    private Vuelo vuelo;//muchas reservas para un mismo vuelo

    @ManyToOne
    @JoinColumn(name = "pasajero_id", referencedColumnName = "pasajero_id")
    @JsonIgnore//sino me va a imprimir todo lo que lleva pasajero y adentro tiene una lista reserv
    private Pasajero pasajero;

    private Integer estadoReservaId;

    @Column(name = "fecha_emision")
    private Date fechaEmision;

    @Column(name = "fecha_vencimiento")
    private Date fechaVencimiento;

    @JsonIgnore
    @OneToOne(mappedBy = "reserva", cascade = CascadeType.ALL, fetch = FetchType.EAGER)//como es solo 1 a 1 no es necesario LAZY
    private Pasaje pasaje;

    public Integer getReservaId() {
        return reservaId;
    }

    public void setReservaId(Integer reservaId) {
        this.reservaId = reservaId;
    }

    public Pasajero getPasajero() {
        return pasajero;
    }

    public void setPasajero(Pasajero pasajero) {
        this.pasajero = pasajero;
    }
        

    public EstadoReservaEnum getEstadoReservaId() {
        return EstadoReservaEnum.parse(estadoReservaId);
    }

    public void setEstadoReservaId(EstadoReservaEnum estadoReservaId) {
        this.estadoReservaId = estadoReservaId.getValue();
    }

    public Date getFechaEmision() {
        return fechaEmision;
    }

    public void setFechaEmision(Date fechaEmision) {
        this.fechaEmision = fechaEmision;
    }

    public Date getFechaVencimiento() {
        return fechaVencimiento;
    }

    public void setFechaVencimiento(Date fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    public Vuelo getVuelo() {
        return vuelo;
    }

    public void setVuelo(Vuelo vuelo) {
        this.vuelo = vuelo;
    }

    
    public Pasaje getPasaje() {
        return pasaje;
    }

    public void setPasaje(Pasaje pasaje) {
        this.pasaje = pasaje;
        pasaje.setReserva(this);//relacion bidireccional
    }

    //realacion bidireccional que hcieron las chicas
    //yo directamente lo hago en el setter
    public void asociarPasaje(Pasaje pasaje){
        this.pasaje = pasaje;
        pasaje.setReserva(this);
    }


    public enum EstadoReservaEnum {
        CREADO(1), TRANSMITIENDO_AL_PG(2), ERROR_AL_CONECTAR_PG(3), PENDIENTE_DE_PAGO(4),
        PAGADO(5), CANCELADO_POR_USUARIO(6), CANCELADO_POR_EMPRESA(7), PAGO_RECHAZADO(8),
        EXPIRADO(9), EMITIDO(10);

        private final int value;

        //constructor ENUM tiene que estar en privado
        private EstadoReservaEnum(Integer value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }

        public static EstadoReservaEnum parse(Integer id) {
            EstadoReservaEnum status = null; // Default
            for (EstadoReservaEnum item : EstadoReservaEnum.values()) {
                if (item.getValue() == id) {
                    status = item;
                    break;
                }
            }
            return status;
        }
    }

    

}
