/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.documentos.entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author g.ospinaa
 */
@Entity
public class TarjetaDeCreditoEntity extends MetodoDePagoEntity implements Serializable{
    
    @PodamExclude
    @ManyToOne
    private UsuarioEntity UEntity;
    /**
     * tipo de tarjeta (ej. Visa, MasterCard, Maestro)
     */
    private String tipoDeTarjeta;
    /**
     * String numerica de 16 digitos.
     */
    private String nroDeLaTarjeta;
    /**
     * NOmbre al que la tarjeta esta registrada.
     */
    private String nombreEnLaTarjeta;
    /**
     * 
     */
    private Integer numeroDeSeguridad;
    
    private Long idUsuario;

    /**
     * @return the tipoDeTarjeta
     */
    public String getTipoDeTarjeta() {
        return tipoDeTarjeta;
    }

    /**
     * @param tipoDeTarjeta the tipoDeTarjeta to set
     */
    public void setTipoDeTarjeta(String tipoDeTarjeta) {
        this.tipoDeTarjeta = tipoDeTarjeta;
    }

    /**
     * @return the nroDeLaTarjeta
     */
    public String getNroDeLaTarjeta() {
        return nroDeLaTarjeta;
    }

    /**
     * @param nroDeLaTarjeta the nroDeLaTarjeta to set
     */
    public void setNroDeLaTarjeta(String nroDeLaTarjeta) {
        this.nroDeLaTarjeta = nroDeLaTarjeta;
    }

    /**
     * @return the nombreEnLaTarjeta
     */
    public String getNombreEnLaTarjeta() {
        return nombreEnLaTarjeta;
    }

    /**
     * @param nombreEnLaTarjeta the nombreEnLaTarjeta to set
     */
    public void setNombreEnLaTarjeta(String nombreEnLaTarjeta) {
        this.nombreEnLaTarjeta = nombreEnLaTarjeta;
    }

    /**
     * @return the numeroDeSeguridad
     */
    public Integer getNumeroDeSeguridad() {
        return numeroDeSeguridad;
    }

    /**
     * @param numeroDeSeguridad the numeroDeSeguridad to set
     */
    public void setNumeroDeSeguridad(Integer numeroDeSeguridad) {
        this.numeroDeSeguridad = numeroDeSeguridad;
    }

    /**
     * @return the UEntity
     */
    public UsuarioEntity getUEntity() {
        return this.UEntity;
    }

    /**
     * @param UEntity the UEntity to set
     */
    public void setUEntity(UsuarioEntity UEntity) {
        this.UEntity = UEntity;
    }

    /**
     * @return the idusuario
     */
    public Long getIdUsuario() {
        return idUsuario;
    }

    /**
     * @param idusuario the idusuario to set
     */
    public void setIdUsuario(Long idusuario) {
        this.idUsuario = idusuario;
    }

  
    
}
