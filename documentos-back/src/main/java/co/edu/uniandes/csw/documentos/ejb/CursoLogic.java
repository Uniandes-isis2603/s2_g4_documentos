/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.documentos.ejb;

import co.edu.uniandes.csw.documentos.entities.CursoEntity;
import co.edu.uniandes.csw.documentos.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.documentos.persistence.CursoPersistence;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;



/**
 *
 * @author n.sotelo
 */
@Stateless
public class CursoLogic 
{
    
    
    @Inject
    private CursoPersistence persistencia;
    
    
    public CursoEntity createCurso(CursoEntity entity) throws BusinessLogicException
    {
        if(persistencia.findByName(entity.getNombre())!=null)
        {
            throw new BusinessLogicException("Ya existe el curso con ese nombre");
        }
        
        return persistencia.create(entity);
    }
    
      public List<CursoEntity> getCursos() throws BusinessLogicException {

        
        List<CursoEntity> editorials = persistencia.findAll();
        if(editorials.size()==0)
        {
            throw new BusinessLogicException("No existen cursos en el sistema");
        }
        
        return editorials;
    }
        public CursoEntity getCurso(Long id) 
        {
        
        
        CursoEntity editorial = persistencia.find(id);
       
        return editorial;
        
    }
        public CursoEntity updateCurso(Long id, CursoEntity entity) 
        {
        CursoEntity newEntity = persistencia.update(entity);
       
        return newEntity;
        }
         public void deleteCurso(Long id) throws BusinessLogicException
         {
            if(persistencia.find(id)==null)
            {
                throw new BusinessLogicException("No existe curso con el id puesto por parametro");
            }
            persistencia.delete(getCurso(id));
           
        }
    }

