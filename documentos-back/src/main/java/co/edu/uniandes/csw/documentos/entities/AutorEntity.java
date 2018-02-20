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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Camilojaravila
 */
@Entity
public class AutorEntity implements Serializable{
    
    /**
     * Id que identifica el Autor
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    /**
     * Nombre del Autor
    */
    private String nombre;
    
    /**
     * Fecha de Nacimiento del Autor
     */
    @Temporal(TemporalType.DATE)
    private Date fechaNacimiento;

    /**
     * Retorna el Id del Autor
     * @return id. El Id del Autor
     */
    public Long getId() {
        return id;
    }

    /**
     * Asigna un nuevo id al Autor
     * @param id Nuevo id del Autor
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Retorna el nombre del autor
     * @return nombre. El nombre del autor
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Asigna el nuevo nombre al autor
     * @param nombre Nuevo nombre del autor 
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Retorna la fecha de nacimiento del autor
     * @return fechaNacimiento. La fecha de nacimiento del autor
     */
    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    /**
     * Asigna la nueva fecha de nacimiento al autor
     * @param fechaNacimiento La nueva fecha de nacimiento del autor
     */
    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }
    
    
}
