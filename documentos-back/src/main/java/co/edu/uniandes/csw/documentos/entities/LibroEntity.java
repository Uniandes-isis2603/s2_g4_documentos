/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.documentos.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import uk.co.jemos.podam.common.PodamExclude;

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
    
    /**
     * isbn del libro.
     */
    private String isbn;
    
    /**
     * Representa la editorial a la que pertenece el libro.
     */
    @PodamExclude
    @ManyToOne
    private EditorialEntity editorial;

    /**
     * Devuelve la editorial a la que pertenece el libro.
     * @return editorial del documento.
     */
    public EditorialEntity getEditorial() {
        return editorial;
    }

    /**
     * Modifica la editorial a la que pertenece el libro.
     * @param editorial nueva.
     */
    public void setEditorial(EditorialEntity editorial) {
        this.editorial = editorial;
    }

    
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
