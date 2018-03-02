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

import java.io.Serializable;

import javax.persistence.Temporal;

/**
 *
 * @author n.sotelo
 */
@Entity
public class ComentarioEntity implements Serializable
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    
    
    private Long id;

    @Temporal(javax.persistence.TemporalType.TIMESTAMP)

    

    private Date fecha;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    


    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    
}
