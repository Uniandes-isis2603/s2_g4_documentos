/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.documentos.dtos;

import co.edu.uniandes.csw.documentos.entities.PayPalEntity;

/**
 *
 * @author g.ospinaa
 */
public class PayPalDetailDTO extends PayPalDTO {
    
    private UsuarioDTO uEntity;
    
    public PayPalDetailDTO()
    {
     super();   
    }
    
    public PayPalDetailDTO(PayPalEntity entity)
    {
        super(entity);
    }
    
    @Override
    public PayPalEntity toEntity()
    {
        PayPalEntity entity = super.toEntity();
        
        return entity;
    }
}
