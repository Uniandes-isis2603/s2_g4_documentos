/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.documentos.dtos;

import co.edu.uniandes.csw.documentos.entities.FacturaEntity;

/**
 *
 * @author g.ospinaa
 */
public class FacturaDTO 
{
    
    private Long id;
    
    private String fecha;
    
    
    
    
    public FacturaDTO()
    {
        //Constructor por defecto.
    }
    
    public FacturaDTO(FacturaEntity entity)
    {
        if(entity != null)
        {
            this.id = entity.getId();
            this.fecha = entity.getFecha();            
        }
    }
    
    public FacturaEntity toEntity()
    {
        FacturaEntity respu = new FacturaEntity();
        
        respu.setId(this.id);
        respu.setFecha(this.fecha);
        
        return respu;
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

    /**
     * @return the fecha
     */
    public String getFecha() {
        return fecha;
    }

    /**
     * @param fecha the fecha to set
     */
    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

}
