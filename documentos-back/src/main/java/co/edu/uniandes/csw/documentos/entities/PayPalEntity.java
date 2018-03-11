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
public class PayPalEntity extends MetodoDePagoEntity implements Serializable{

    @PodamExclude
    @ManyToOne
    private UsuarioEntity UEntity;
    
    private String usuario;
    private String correoElectronico;
    
    
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
     * @return the UEntity
     */
    public UsuarioEntity getUEntity() {
        return UEntity;
    }

    /**
     * @param UEntity the UEntity to set
     */
    public void setUEntity(UsuarioEntity UEntity) {
        this.UEntity = UEntity;
    }
    

}
