/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.documentos.dtos;

import co.edu.uniandes.csw.documentos.entities.TarjetaDeCreditoEntity;

/**
 * <h1>TarjetaDeCreditoDTO Objeto que representa una tarjeta de credito para compras</h1>
 * <i>. Los DTO contienen las represnetaciones de los JSON que se 
 * transfieren entre el cliente y elservidor.</i>
 * 
 * Al serializarse como JSON esta clase implementa el siguiente modelo:
 * <pre>
 *   {
 *      "tipoDeTarjeta": string,
 *      "nroDeTarjeta: string,
 *      "nombreEnLaTarjeta": string,
 *      "numeroDeSeguridad": integer
 * 
 *   }
 * </pre>
 * Por ejemplo una TarjetaDeCredito se representa asi:<br>
 * 
 * <pre>
 * 
 *   {
 *      "tipoDeTarjeta": "Visa",
 *      "nroDeTarjeta: "433467500798",
 *      "nombreEnLaTarjeta": "Gregorio Ospina",
 *      "numeroDeSeguridad": 243
 *   }
 *
 * </pre>
 * @author g.ospinaa
 */
public class TarjetaDeCreditoDTO {
    
    private Long Id;
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

    
    public TarjetaDeCreditoDTO(){
        
    }
    
    public TarjetaDeCreditoDTO(TarjetaDeCreditoEntity entity)
    {
        if(entity != null)
        {
            this.Id = entity.getId();
            this.nombreEnLaTarjeta = entity.getNombreEnLaTarjeta();
            this.nroDeLaTarjeta = entity.getNroDeLaTarjeta();
            this.numeroDeSeguridad = entity.getNumeroDeSeguridad();
            this.tipoDeTarjeta = entity.getTipoDeTarjeta();
        }
    }
    
    public TarjetaDeCreditoEntity toEntity()
    {
        TarjetaDeCreditoEntity entity = new TarjetaDeCreditoEntity();
        entity.setId(this.getId());
        entity.setNombreEnLaTarjeta(this.getNombreEnLaTarjeta());
        entity.setNroDeLaTarjeta(this.getNroDeLaTarjeta());
        entity.setNumeroDeSeguridad(this.getNumeroDeSeguridad());
        entity.setTipoDeTarjeta(this.getTipoDeTarjeta());
        
        return entity;
    }
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
     * @return the Id
     */
    public Long getId() {
        return Id;
    }

    /**
     * @param Id the Id to set
     */
    public void setId(Long Id) {
        this.Id = Id;
    }
    
    
}
