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
public class MetodoDePagoDetailDTO extends MetodoDePagoDTO{
    
    /**
     * Constructor por defecto.
     */
    public MetodoDePagoDetailDTO()
    {
        
    }
    
    @Override
    public MetodoDePagoEntity toEntity()
    {
        MetodoDePagoEntity metodoE = super.toEntity();
        return metodoE;
    }
    
}
