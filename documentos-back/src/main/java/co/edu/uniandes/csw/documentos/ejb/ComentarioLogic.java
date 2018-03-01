/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.documentos.ejb;

import co.edu.uniandes.csw.documentos.entities.ComentarioEntity;
import co.edu.uniandes.csw.documentos.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.documentos.persistence.ComentarioPersistence;
import java.util.List;
import javax.inject.Inject;

/**
 *
 * @author n.sotelo
 */
public class ComentarioLogic {
     @Inject
    private ComentarioPersistence persistencia;
    public ComentarioEntity createComentario(ComentarioEntity entity) throws BusinessLogicException
    {
        if(persistencia.find(entity.getId())!=null)
        {
            throw new BusinessLogicException("Ya existe el comentario con ese id");
        }
        
        return persistencia.create(entity);
    }
    
      public List<ComentarioEntity> getComentarios() throws BusinessLogicException {

        
        List<ComentarioEntity> editorials = persistencia.findAll();
        if(editorials.isEmpty())
        {
            throw new BusinessLogicException("No existen comentarios en el sistema");
        }
        
        return editorials;
    }
        public ComentarioEntity getComentario(Long id) throws BusinessLogicException
        {
        if (persistencia.find(id)==null)
        {
            throw new BusinessLogicException("No existe elemento que coincida con tu busqueda");
        }
        
        ComentarioEntity editorial = persistencia.find(id);
       
        return editorial;
        
    }
        public ComentarioEntity updateComentario(Long id, ComentarioEntity entity) throws BusinessLogicException
        {
            if (persistencia.find(id)==null)
        {
            throw new BusinessLogicException("No existe elemento que coincida con tu busqueda");
        }
        ComentarioEntity newEntity = persistencia.update(entity);
       
        return newEntity;
        }
         public void deleteComentario(Long id) throws BusinessLogicException
         {
            if(persistencia.find(id)==null)
            {
                throw new BusinessLogicException("No existe comentario con el id puesto por parametro");
            }
            persistencia.delete(getComentario(id));
           
   
}
    
}
