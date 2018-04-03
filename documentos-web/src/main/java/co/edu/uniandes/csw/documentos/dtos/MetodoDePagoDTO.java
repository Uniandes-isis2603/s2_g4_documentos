/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.documentos.dtos;

import co.edu.uniandes.csw.documentos.entities.MetodoDePagoEntity;

/**
 *
 * @author g.ospinaa
 */
public class MetodoDePagoDTO {
    
    
    private Long id;
    
    
    public MetodoDePagoDTO(){
}
    
    public MetodoDePagoEntity toEntity()
    {
        MetodoDePagoEntity entity = new MetodoDePagoEntity();
        entity.setId(this.id);
        
        return entity;
    }
    public MetodoDePagoDTO(MetodoDePagoEntity entidad)
    {
        if (entidad!=null)
        {
            this.id= entidad.getId();
        }
    }

    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }
    
    
}
