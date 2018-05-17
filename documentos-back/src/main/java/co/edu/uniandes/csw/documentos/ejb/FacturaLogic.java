/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.documentos.ejb;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;
import co.edu.uniandes.csw.documentos.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.documentos.entities.FacturaEntity;
import co.edu.uniandes.csw.documentos.persistence.FacturaPersistence;
import java.util.ArrayList;

/**
 *
 * @author g.ospinaa
 */
@Stateless
public class FacturaLogic {

    @Inject
    private FacturaPersistence persistencia;

    public FacturaEntity createFactura(FacturaEntity entity) throws BusinessLogicException {
        if (persistencia.find(entity.getId()) != null) {
            throw new BusinessLogicException("Ya existe la factura con ese id");
        }

        if (entity.getUsuario() == null) {
            throw new BusinessLogicException("Necesita un usuario la factura");

        }
        
        if((entity.getMetodoDePagoPayP() == null && entity.getMetodoDePagoTDC() == null))
        {
            throw new BusinessLogicException("Necesita un metodo de Pago");
        }
          
        return persistencia.create(entity);
    }
    
    public List<FacturaEntity> getFacturas() throws BusinessLogicException {
        
        
        List<FacturaEntity> fac = persistencia.findAll();
        if(fac.isEmpty())
        {
            throw new BusinessLogicException("No existen facturas en el sistema");
        }
        
        return fac;
    }
    
    public FacturaEntity getFactura(Long id) throws BusinessLogicException
    {
        if (persistencia.find(id)==null)
        {
            throw new BusinessLogicException("No existe elemento que coincida con tu busqueda");
        }
        
        FacturaEntity fac = persistencia.find(id);
        
        return fac;
        
    }

    public List<FacturaEntity> getFacturasUsuario(Long id) throws BusinessLogicException 
    {
        List<FacturaEntity> fac = persistencia.findAll();
        List<FacturaEntity> respu = new ArrayList<>();
        if(fac.isEmpty())
        {
            throw new BusinessLogicException("No existen facturas en el sistema");
        }
        for(FacturaEntity factura: fac)
        {
            if(factura.getUsuario().getId().equals(id))
            {
                respu.add(factura);
            }
        }
        
        return respu;
    }
  
    
}
