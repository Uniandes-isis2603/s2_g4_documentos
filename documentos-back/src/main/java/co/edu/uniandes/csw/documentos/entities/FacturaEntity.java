/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.documentos.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import uk.co.jemos.podam.common.PodamExclude;


/**
 *
 * @author g.ospinaa
 */
@Entity
public class FacturaEntity {
    
    
    @Id
    @GeneratedValue
    private Long id;
    
    private String fecha;
    
    private Double costo;
    
    /**
     * representa el metodo de pago usado para realizar un pago con paypal
     */
    @OneToOne
    @JoinColumn(name="METODODEPAGOPAYPAL_ID")
    @PodamExclude
    private PayPalEntity metodoDePagoPayP;
    /**
     * representa el metodo de pago usado para realizar un pago con tarjeta de credito
     */
    @OneToOne
    @JoinColumn(name="METODODEPAGOTDC_ID")
    @PodamExclude
    private TarjetaDeCreditoEntity metodoDePagoTDC;    
    
    @ManyToOne
    @PodamExclude
    private UsuarioEntity usuario;
        
    @OneToMany
    @PodamExclude
    private List<DocumentoEntity> documentos;

    /**
     *
     * @return metodo de pago con paypal
     */
    public PayPalEntity getMetodoDePagoPayP() {
        return metodoDePagoPayP;
    }
    /**
     *
     * @param metodoDePagoPayPal nuevo metodo de pago
     */
    public void setMetodoDePagoPayP(PayPalEntity metodoDePagoPayPal) {
        this.metodoDePagoPayP = metodoDePagoPayPal;
    }
    /**
     *
     * @return metodo de pago con tarjeta de credito
     */
    public TarjetaDeCreditoEntity getMetodoDePagoTDC() {
        return metodoDePagoTDC;
    }
    /**
     *
     * @param metodoDePagoTDC nuevo metodo de pago.
     */
    public void setMetodoDePagoTDC(TarjetaDeCreditoEntity metodoDePagoTDC) {
        this.metodoDePagoTDC = metodoDePagoTDC;
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
     * @return the usuario
     */
    public UsuarioEntity getUsuario() {
        return usuario;
    }

    /**
     * @param usuario the usuario to set
     */
    public void setUsuario(UsuarioEntity usuario) {
        this.usuario = usuario;
    }    

    /**
     * @return the costo
     */
    public Double getCosto() {
        return costo;
    }

    /**
     * @param costo the costo to set
     */
    public void setCosto(Double costo) {
        this.costo = costo;
    }

    /**
     * @return the documentos
     */
    public List<DocumentoEntity> getDocumentos() {
        return documentos;
    }

    /**
     * @param documentos the documentos to set
     */
    public void setDocumentos(List<DocumentoEntity> documentos) {
        this.documentos = documentos;
    }
}
