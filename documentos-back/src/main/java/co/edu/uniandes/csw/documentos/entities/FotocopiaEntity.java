/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.documentos.entities;

import java.io.Serializable;
import javax.persistence.Entity;

/**
 *
 * @author ernes
 */
@Entity
public class FotocopiaEntity extends DocumentoEntity implements Serializable{
    
    /**
     * Representa el profesor que pudo haber dejado las fotocopias.
     */
    private String profesor;
    
    /**
     * Representa el numero de paginas que tiene las fotocopias.
     */
    private Integer nroPaginas;
    
    /**
     * Representa el capitulo si es parte de un libro.
     */
    private String capitulo;

    /**
     * 
     * @return profesor
     */
    public String getProfesor() {
        return profesor;
    }

    /**
     * 
     * @param profesor nuevo Profesor.
     */
    public void setProfesor(String profesor) {
        this.profesor = profesor;
    }

    /**
     * 
     * @return numero de paginas.
     */
    public Integer getNroPaginas() {
        return nroPaginas;
    }

    /**
     * 
     * @param nroPaginas nuevo numero de paginas.
     */
    public void setNroPaginas(Integer nroPaginas) {
        this.nroPaginas = nroPaginas;
    }

    /**
     * 
     * @return capitulo.
     */
    public String getCapitulo() {
        return capitulo;
    }

    /**
     * 
     * @param capitulo nuevo.
     */
    public void setCapitulo(String capitulo) {
        this.capitulo = capitulo;
    }
    
}
