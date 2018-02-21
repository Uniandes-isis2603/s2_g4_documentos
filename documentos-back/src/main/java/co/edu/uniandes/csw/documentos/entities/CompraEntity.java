/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.documentos.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import uk.co.jemos.podam.common.PodamExclude;


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
     
     @PodamExclude
     @OneToMany
     private List<DocumentoEntity>libros;
     @PodamExclude
     @OneToOne
     private PayPalEntity PayPalMetodo;
     @PodamExclude
     @OneToOne
     private TarjetaDeCreditoEntity TarjetaDeCredito;
/**
 * 
 * @return libros con los cuales se va a realizar una compra. 
 */
    public List<DocumentoEntity> getLibros() {
        return libros;
    }
/**
 * 
 * @param libros  nuevos para efectuar una compra.
 */
    public void setLibros(List<DocumentoEntity> libros) {
        this.libros = libros;
    }
/**
 * 
 * @return metodo paypal de pago
 */
    public PayPalEntity getPayPalMetodo() {
        return PayPalMetodo;
    }
/**
 * 
 * @param PayPalMetodo nuevo metodo pay pal para efectuar un pago.
 */
    public void setPayPalMetodo(PayPalEntity PayPalMetodo) {
        this.PayPalMetodo = PayPalMetodo;
    }
/**
 * 
 * @return  metodo de pago con una tarjeta de credito
 */
    public TarjetaDeCreditoEntity getTarjetaDeCredito() {
        return TarjetaDeCredito;
    }
    /**
     * 
     * @param TarjetaDeCredito nuevo metodo de pago que va ser una tarjeta de credito.
     */
    public void setTarjetaDeCredito(TarjetaDeCreditoEntity TarjetaDeCredito) {
        this.TarjetaDeCredito = TarjetaDeCredito;
    }
     
     
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
