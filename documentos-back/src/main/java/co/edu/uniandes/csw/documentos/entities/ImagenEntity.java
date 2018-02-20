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

/**
 *
 * @author Camilojaravila
 */
@Entity
public class ImagenEntity implements Serializable{
    
    /**
    * Id que identifica el Autor
    */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    /**
     * Nombre de la imagen
     */
    private String nombre;
    
    /**
     * Ruta de la imagen
     */
    private String img;
    
     /**
      * Retorna el Id de la imagen
     * @return el id de la imagen 
     */
    public Long getId() {
        return id;
    }

    /**
     * Asigna una nueva Id a la Imagen
     * @param id El nuevo id de la imagen
     */    
    public void setId(Long id) {
        this.id = id;
    }

     /**
      * Retorna el nombre de la imagen
     * @return el nombre de la imagen 
     */
    public String getNombre() {
        return nombre;
    }
    
    /**
     * Asigna el nuevo nombre a la imagen
     * @param nombre El nuevo nombre de la imagen
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    /**
     * Retorna la ruta de la imagen
     * @return La ruta de la Imagen 
     */
    public String getImg() {
        return img;
    }

     /**
      * Asigna una nueva ruta a la imagen
     * @param img La nueva ruta de la imagen
     */
    public void setImg(String img) {
        this.img = img;
    }

}
