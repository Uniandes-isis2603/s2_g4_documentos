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
    
    /**
     * Inyeccion para conectar la capa de logica con la persistencia.
     */
    @Inject
    private CursoPersistence persistencia;
    
    /**
     *Crea una entidiad en el sistema
     * @param entity la cual sera creada siempre y cuando cumpla con las reglas de negocio
     * @return la entidad nueva que cumple con las reglas del negocio
     * @throws BusinessLogicException si no cumple con una regla de negocio
     */
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
    /**
     * Retorna toda las entidades del sistema de tipo curso
     * @return lista de todos los cursos
     * @throws BusinessLogicException  si no existe alguna entiddad en el sistema
     */
    public List<CursoEntity> getCursos() throws BusinessLogicException {
        
        
        List<CursoEntity> editorials = persistencia.findAll();
        if(editorials.isEmpty())
        {
            throw new BusinessLogicException("No existen cursos en el sistema");
        }
        
        return editorials;
    }
    /**
     * Retorna una sola entidad del sitema de tipo curso
     * @param id de la entidad que se desea buscar 
     * @return la entidad encontrada
     * @throws BusinessLogicException si no existe la entidad  buscada en el sistema 
     */
    public CursoEntity getCurso(Long id) throws BusinessLogicException
    {
        if (persistencia.find(id)==null)
        {
            throw new BusinessLogicException("No existe elemento que coincida con tu busqueda");
        }
        
        CursoEntity editorial = persistencia.find(id);
        
        return editorial;
        
    }
    /**
     * Actualiza la informacion de una entidad
     * @param id de la entidad que se desea actualizar
     * @param entity la informacion nueva de la entidad a actualizar
     * @return la entidad con la informacion actualizada
     * @throws BusinessLogicException  si no existe la entidad en el sistema
     */
    public CursoEntity updateCurso(Long id, CursoEntity entity) throws BusinessLogicException
    {
        if (persistencia.find(id)==null)
        {
            throw new BusinessLogicException("No existe elemento que coincida con tu busqueda");
        }
        CursoEntity newEntity = persistencia.update(entity);
        
        return newEntity;
    }
    /**
     * Borra la entidad del sistema
     * @param id del curso que se desea borrar
     * @throws BusinessLogicException  no existe la entidad con ese id
     */
    public void deleteCurso(Long id) throws BusinessLogicException
    {
        if(persistencia.find(id)==null)
        {
            throw new BusinessLogicException("No existe curso con el id puesto por parametro");
        }
        persistencia.delete(getCurso(id));
        
    }
}

