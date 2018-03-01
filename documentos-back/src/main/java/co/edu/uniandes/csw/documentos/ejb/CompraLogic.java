/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.documentos.ejb;

import co.edu.uniandes.csw.documentos.entities.CompraEntity;
import co.edu.uniandes.csw.documentos.entities.EditorialEntity;
import co.edu.uniandes.csw.documentos.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.documentos.persistence.CompraPersistence;
import co.edu.uniandes.csw.documentos.persistence.EditorialPersistence;
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
    private EditorialPersistence persistencia;
    public EditorialEntity createEditorial(EditorialEntity entity) throws BusinessLogicException
    {
        if(persistencia.find(entity.getId())!=null)
        {
            throw new BusinessLogicException("Ya existe la compra con ese id");
        }
        
        return persistencia.create(entity);
    }
    
      public List<EditorialEntity> getEditorials() throws BusinessLogicException {

        
        List<EditorialEntity> editorials = persistencia.findAll();
        if(editorials.size()==0)
        {
            throw new BusinessLogicException("No existen compras en el sistema");
        }
        
        return editorials;
    }
        public EditorialEntity getEditorial(Long id) throws BusinessLogicException
        {
        if (persistencia.find(id)==null)
        {
            throw new BusinessLogicException("No existe elemento que coincida con tu busqueda");
        }
        
        EditorialEntity editorial = persistencia.find(id);
       
        return editorial;
        
    }
        public EditorialEntity updateEditorial(Long id, EditorialEntity entity) throws BusinessLogicException
        {
            if (persistencia.find(id)==null)
        {
            throw new BusinessLogicException("No existe elemento que coincida con tu busqueda");
        }
        EditorialEntity newEntity = persistencia.update(entity);
       
        return newEntity;
        }
         public void deleteEditorial(Long id) throws BusinessLogicException
         {
            if(persistencia.find(id)==null)
            {
                throw new BusinessLogicException("No existe compra con el id puesto por parametro");
            }
            persistencia.delete(getEditorial(id));
           
   
}
}
