/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.documentos.dtos;

import co.edu.uniandes.csw.documentos.entities.CarritoEntity;

/**
 *
 * @author nicolassotelo
 */
public class CarritoDTO 
{
    private long id;
    
    public CarritoDTO()
    {}
    public CarritoDTO(CarritoEntity entidad)
    {
      this.id= entidad.getId();
    }
    
     public CarritoEntity toEntity()
        {
            CarritoEntity rta= new CarritoEntity();
            rta.setId(this.id);
            return rta;
        }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
    
    
    
}
