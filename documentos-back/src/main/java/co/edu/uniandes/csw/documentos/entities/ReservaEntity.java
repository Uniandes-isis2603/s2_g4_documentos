/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.documentos.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.*;

/**
 *
 * @author f.marroquin10
 */
@Entity
public class ReservaEntity  implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fecha;
    private double costo;

    /**
     *
     * @return id de la reserva.
     */
    public Long getId() {
        return id;
    }

    /**
     *
     * @return fecha de cuando se hace la reserva.
     */
    public Date getFecha() {
        return fecha;
    }

   

    /**
     *
     * @return costo de la reserva.
     */
    public double getCosto() {
        return costo;
    }

    /**
     *
     * @param pId nuevo id.
     */
    public void setId(Long pId) {
        this.id = pId;
    }

    /**
     *
     * @param pFecha se actualiza la fecha de la reserva.
     */
    public void setFecha(Date pFecha) {
        this.fecha = pFecha;

    }

   

    /**
     *
     * @param pCosto valor del costo de la reserva.
     */
    public void setCosto(double pCosto) {
        this.costo = pCosto;

    }
    
}
