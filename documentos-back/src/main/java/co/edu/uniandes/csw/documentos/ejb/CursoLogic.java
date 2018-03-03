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
        if(entity.getNombre().length()>=50)
        {
            throw new BusinessLogicException("Nombre no valido");
        }
        if(entity.getNombre().isEmpty()|| entity.getCodigo().isEmpty()||entity.getDepartamento().isEmpty())
        {
             throw new BusinessLogicException("Ninguno de los atributos de nombre,codigo o departamento puede ser vacio");
        }
        if(entity.getCodigo().length()>6)
        {
            throw new BusinessLogicException("Formato no valido");
        }
        
        return persistencia.create(entity);
    }
    
      public List<CursoEntity> getCursos() throws BusinessLogicException {

        
        List<CursoEntity> editorials = persistencia.findAll();
        if(editorials.isEmpty())
        {
            throw new BusinessLogicException("No existen cursos en el sistema");
        }
        
        return editorials;
    }
        public CursoEntity getCurso(Long id) throws BusinessLogicException
        {
        if (persistencia.find(id)==null)
        {
            throw new BusinessLogicException("No existe elemento que coincida con tu busqueda");
        }
        
        CursoEntity editorial = persistencia.find(id);
       
        return editorial;
        
    }
        public CursoEntity updateCurso(Long id, CursoEntity entity) throws BusinessLogicException
        {
            if (persistencia.find(id)==null)
        {
            throw new BusinessLogicException("No existe elemento que coincida con tu busqueda");
        }
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

