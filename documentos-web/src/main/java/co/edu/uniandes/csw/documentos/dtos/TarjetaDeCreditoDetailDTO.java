/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.documentos.dtos;

import co.edu.uniandes.csw.documentos.entities.TarjetaDeCreditoEntity;

/**
 *
 * @author g.ospinaa
 */
public class TarjetaDeCreditoDetailDTO extends TarjetaDeCreditoDTO {
    
    private UsuarioDTO usuario;
    
    public TarjetaDeCreditoDetailDTO()
    {
        super();
    }
    
    public TarjetaDeCreditoDetailDTO(TarjetaDeCreditoEntity entity)
    {
        super(entity);
    }
    
    
    @Override
    public TarjetaDeCreditoEntity toEntity()
    {
        TarjetaDeCreditoEntity entity = super.toEntity();
        
        return entity;
    }

    /**
     * @return the usuario
     */
    public UsuarioDTO getUsuario() {
        return usuario;
    }

    /**
     * @param usuario the usuario to set
     */
    public void setUsuario(UsuarioDTO usuario) {
        this.usuario = usuario;
    }
}
