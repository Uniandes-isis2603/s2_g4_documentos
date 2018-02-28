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
 * @author n.sotelo
 */
@Entity
public class CompraEntity implements Serializable
        
{
     @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
     private Long id;
     
     private Double costo;
     private String fecha;
     private String tipoDeCompra;
     

     
/**
 * 
 * @return el id de la compra 
 */
    public Long getId() {
        return id;
    }
    /**
     * 
     * @param id nuevo de la compra 
     */

    public void setId(Long id) {
        this.id = id;
    }
    /**
     * 
     * @return costo total de la compra 
     */

    public Double getCosto() {
        return costo;
    }
    
    /**
     * 
     * @param costo total de la compra 
     */
    public void setCosto(Double costo) {
        this.costo = costo;
    }
    /**
     * 
     * @return fecha en que se efectuo la compra
     */
    public String getFecha() {
        return fecha;
    }
    /**
     * 
     * @param fecha en que se efectuo la compra
     */
    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
    /**
     * 
     * @return tipo de compra 
     */

    public String getTipoDeCompra() {
        return tipoDeCompra;
    }
    
        
     /**
     * 
     * @param tipoDeCompra  nuevo costo de la compra. 
     */ 
    public void setTipoDeCompra(String tipoDeCompra) {
        this.tipoDeCompra = tipoDeCompra;
    }
    
}
