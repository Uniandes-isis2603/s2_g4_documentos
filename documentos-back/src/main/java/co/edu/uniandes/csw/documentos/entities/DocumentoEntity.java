/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.documentos.entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

/**
 *
 * @author Ernesto Viana
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class DocumentoEntity implements Serializable {
    
    /**
     * Representa el id y llave primaria de la tabla. 
     * Se genera automaticamente.
     * 
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    /**
     * Representa el nombre del documento.
     */
    private String nombre;
    
    /**
     * Representa la califiacion del documento.
     */
    private Double calificacionPromedio;
    
    /**
     * Representa la descripcion del usuario que vende el documento.
     */
    private String descripcion;
    
    /**
     * Representa el precio del documento.
     */
    private Double precio;
    
    /**
     * Representa la direccion de la caratula.
     */
    private String caratula;

    /**
     * 
     * @return el identificador.
     */
    public Long getId() {
        return id;
    }

    /**
     * 
     * @param id id nuevo.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 
     * @return el nombre del documento.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * 
     * @param nombre nombre nuevo.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * 
     * @return la califiacion promedio del documento.
     */
    public Double getCalificacionPromedio() {
        return calificacionPromedio;
    }

    /**
     * 
     * @param calificacionPromedio calificacion nueva.
     */
    public void setCalificacionPromedio(Double calificacionPromedio) {
        this.calificacionPromedio = calificacionPromedio;
    }

    /**
     * 
     * @return la descripcion del documento.
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * 
     * @param descripcion nueva descripcion.
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * 
     * @return El precio del documento.
     */
    public Double getPrecio() {
        return precio;
    }

    /**
     * 
     * @param precio nuevo precio.
     */
    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    /**
     * 
     * @return direccion de la caratula del documento
     */
    public String getCaratula() {
        return caratula;
    }

    /**
     * 
     * @param caratula nueva del documento.
     */
    public void setCaratula(String caratula) {
        this.caratula = caratula;
    }
    
}
