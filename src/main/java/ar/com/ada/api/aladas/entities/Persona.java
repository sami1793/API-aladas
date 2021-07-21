package ar.com.ada.api.aladas.entities;

import java.util.Date;

import javax.persistence.*;

import ar.com.ada.api.aladas.entities.Pais.PaisEnum;
import ar.com.ada.api.aladas.entities.Pais.TipoDocuEnum;

@MappedSuperclass
public class Persona {
    
    private String nombre;

    @Column(name="tipo_documento_id")//es un Enum, lo cambio getter y setter
    private Integer tipoDocumentoId;

    private String documento;

    @Temporal(TemporalType.DATE)
    @Column(name="fecha_nacimiento")
    private Date fechaNacimiento;

    private Integer paisId;//pais es un Enum, lo cambio getter y setter

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public TipoDocuEnum getTipoDocumentoId() {
        return TipoDocuEnum.parse(this.tipoDocumentoId);//debe devolver un enum, uso parse ()
    }

    public void setTipoDocumentoId(TipoDocuEnum tipoDocumentoId) {
        this.tipoDocumentoId = tipoDocumentoId.getValue();//debe ser un entero, los transformo con getValue()
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public PaisEnum getPaisId() {
        return PaisEnum.parse(this.paisId);//aqui transformo a Enum
    }

    public void setPaisId(PaisEnum paisId) {//envez de recibir integer recibo Enum
        this.paisId = paisId.getValue();//getValue si pones Arg te devuelve 32
    }


    
}
