/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.documentos.ejb;

import co.edu.uniandes.csw.documentos.entities.CompraEntity;
import co.edu.uniandes.csw.documentos.entities.CompraEntity;
import co.edu.uniandes.csw.documentos.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.documentos.persistence.CompraPersistence;
import co.edu.uniandes.csw.documentos.persistence.CompraPersistence;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author n.sotelo
 */
@Stateless
public class CompraLogic {
    
  @Inject
    private CompraPersistence persistencia;
    public CompraEntity createCompra(CompraEntity entity) throws BusinessLogicException
    { Date fecha= new Date();
        if(persistencia.find(entity.getId())!=null)
        {
            throw new BusinessLogicException("Ya existe la compra con ese id");
        } 
        if (entity.getCosto()==0)
        {
            throw new BusinessLogicException("El costo de la compra no puede ser 0");
        }
        if(entity.getFecha().after(fecha))
        {
           throw new BusinessLogicException("La fecha no es valida. fecha = " + entity.getFecha());   
        }
        return persistencia.create(entity);
    }
    
      public List<CompraEntity> getCompras() throws BusinessLogicException {

        
        List<CompraEntity> editorials = persistencia.findAll();
        if(editorials.isEmpty())
        {
            throw new BusinessLogicException("No existen compras en el sistema");
        }
        
        return editorials;
    }
        public CompraEntity getCompra(Long id) throws BusinessLogicException
        {
        if (persistencia.find(id)==null)
        {
            throw new BusinessLogicException("No existe elemento que coincida con tu busqueda");
        }
        
        CompraEntity editorial = persistencia.find(id);
       
        return editorial;
        
    }
        public CompraEntity updateCompra(Long id, CompraEntity entity) throws BusinessLogicException
        {
            Date fecha= new Date();
            if (persistencia.find(id)==null)
        {
            throw new BusinessLogicException("No existe elemento que coincida con tu busqueda");
        }
             if (entity.getCosto()==0)
        {
            throw new BusinessLogicException("El costo de la compra no puede ser 0");
        }
        if(entity.getFecha().after(fecha))
        {
           throw new BusinessLogicException("La fecha no es valida");   
        }
        CompraEntity newEntity = persistencia.update(entity);
       
        return newEntity;
        }
         public void deleteCompra(Long id) throws BusinessLogicException
         {
            if(persistencia.find(id)==null)
            {
                throw new BusinessLogicException("No existe compra con el id puesto por parametro");
            }
            persistencia.delete(getCompra(id));
           
   
}

    
}
