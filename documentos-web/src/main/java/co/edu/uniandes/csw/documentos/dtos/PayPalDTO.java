/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.documentos.dtos;

import co.edu.uniandes.csw.documentos.entities.PayPalEntity;

/**
 * <h1>PayPalDTO Objeto que representa una cuenta del servicio de pago PayPal.</h1>
 * 
 * <i>Los DTO contienen las represnetaciones de los JSON que se transfieren entre el cliente y el
 * servidor.</i>
 * 
 * Al serializarse como JSON esta clase implementa el siguiente modelo: <br>
 * <pre>
 *   {
 *      "correoElectronico": string,
 *      "usuario: string,
 *   }
 * </pre>
 * Por ejemplo una cuennta PayPal se representa asi:<br>
 * 
 * <pre>
 * 
 *   {
 *      "correoElectronico": "g.ospinaa@uniandes.edu.co",
 *      "usuario: "g.ospinaa",
 *   }
 *
 * </pre>
 * @author g.ospinaa
 */
public class PayPalDTO {
    
    private Long Id;
    private String correoElectronico;
    private String usuario;
    private Long idusuario;
    
    public PayPalDTO()
    {
        
    }
    
    public PayPalDTO(PayPalEntity entity)
    {
        if(entity != null)
        {
            this.Id = entity.getId();
            this.correoElectronico = entity.getCorreoElectronico();
            this.usuario = entity.getUsuario();
            this.idusuario = entity.getIdusuario();
        }
    }
    
    public PayPalEntity toEntity()
    {
        PayPalEntity entity = new PayPalEntity();
        entity.setId(this.Id);
        entity.setCorreoElectronico(this.correoElectronico);
        entity.setUsuario(this.usuario);
        entity.setIdusuario(this.getIdusuario());
        
        return entity;
        }

    /**
     * @return the correoElectronico
     */
    public String getCorreoElectronico() {
        return correoElectronico;
    }

    /**
     * @param correoElectronico the correoElectronico to set
     */
    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    /**
     * @return the usuario
     */
    public String getUsuario() {
        return usuario;
    }

    /**
     * @param usuario the usuario to set
     */
    public void setUsuario(String usuario) {
        this.usuario = usuario;
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

    /**
     * @return the idusuario
     */
    public Long getIdusuario() {
        return idusuario;
    }

    /**
     * @param idusuario the idusuario to set
     */
    public void setIdusuario(Long idusuario) {
        this.idusuario = idusuario;
    }
    
}
