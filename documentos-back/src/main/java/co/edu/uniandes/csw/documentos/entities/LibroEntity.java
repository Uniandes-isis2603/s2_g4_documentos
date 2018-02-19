/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.documentos.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.Temporal;

/**
 *
 * @author Ernesto Viana
 */
@Entity
public class LibroEntity extends DocumentoEntity implements Serializable{
    
    /**
     * Representa la fecha de publicacion del libro.
     */
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fechaPublicacion;
    
    private String isbn;

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    /**
     * 
     * @return fecha de publicación.
     */
    public Date getFechaPublicacion() {
        return fechaPublicacion;
    }

    /**
     * 
     * @param fechaPublicacion nueva fecha de publicacion.
     */
    public void setFechaPublicacion(Date fechaPublicacion) {
        this.fechaPublicacion = fechaPublicacion;
    }
    
}
