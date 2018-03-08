/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.documentos.ejb;

import co.edu.uniandes.csw.documentos.entities.EditorialEntity;
import co.edu.uniandes.csw.documentos.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.documentos.persistence.EditorialPersistence;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author n.sotelo
 */
@Stateless
public class EditorialLogic {
      @Inject
    private EditorialPersistence persistencia;
    
    
    public EditorialEntity createEditorial(EditorialEntity entity) throws BusinessLogicException
    {
        if(persistencia.findByName(entity.getNombre())!=null)
        {
            throw new BusinessLogicException("Ya existe la editorial con ese nombre");
        }
        if(entity.getNombre().isEmpty())
        {
            throw new BusinessLogicException("El nombre no puede ser vacio");
        }
        
        return persistencia.create(entity);
    }
    
      public List<EditorialEntity> getEditorials() throws BusinessLogicException {

        
        List<EditorialEntity> editorials = persistencia.findAll();
        if(editorials.isEmpty())
        {
            throw new BusinessLogicException("No existen editoriales en el sistema");
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
             if(entity.getNombre().isEmpty())
        {
            throw new BusinessLogicException("El nombre no puede ser vacio");
        }
        EditorialEntity newEntity = persistencia.update(entity);
       
        return newEntity;
        }
         public void deleteEditorial(Long id) throws BusinessLogicException
         {
            if(persistencia.find(id)==null)
            {
                throw new BusinessLogicException("No existe editorial con el id puesto por parametro");
            }
            persistencia.delete(getEditorial(id));
           
        }
    
}
