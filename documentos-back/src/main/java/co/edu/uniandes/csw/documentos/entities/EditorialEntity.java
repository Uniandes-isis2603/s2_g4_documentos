/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.documentos.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.List;
import javax.persistence.OneToMany;
import uk.co.jemos.podam.common.PodamExclude;


/**
 *
 * @author n.sotelo
 */
@Entity
public class EditorialEntity implements Serializable{
    
    @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
    
      private Long id;
    
    private String nombre;
    @PodamExclude
    @OneToMany(mappedBy = "editorial")
    private List<LibroEntity> libros;
/**
 * 
 * @return lista de libros a lod cuales pertnece una editorial 
 */
    public List<LibroEntity> getLibros() {
        return libros;
    }
/**
 * 
 * @param libros  que se añadiran a una editorial
 */
    public void setLibros(List<LibroEntity> libros) {
        this.libros = libros;
    }

    

   
    
   
/**
 * 
 * @return de la entidad 
 */
    public Long getId() {
        return id;
    }
    /**
     * 
     * @param id nuevo para la editorial 
     */

    public void setId(Long id) {
        this.id = id;
    }
/**
 * 
 * @return nombre de la editorial 
 */
    public String getNombre() {
        return nombre;
    }
/**
 * 
 * @param nombre nuevo para la editorial 
 */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
  
}
