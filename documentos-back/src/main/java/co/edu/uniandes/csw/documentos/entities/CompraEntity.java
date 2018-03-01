/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.documentos.entities;

import co.edu.uniandes.csw.documentos.podam.DateStrategy;
import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import uk.co.jemos.podam.common.PodamStrategyValue;



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
     
     @Temporal(TemporalType.DATE)
     @PodamStrategyValue(DateStrategy.class)
     private Date fecha;
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
    public Date getFecha() {
        return fecha;
    }
    /**
     * 
     * @param fecha en que se efectuo la compra
     */
    public void setFecha(Date fecha) {
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
