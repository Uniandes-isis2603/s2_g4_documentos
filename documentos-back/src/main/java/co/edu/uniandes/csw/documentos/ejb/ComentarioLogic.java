/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package co.edu.uniandes.csw.documentos.ejb;

import co.edu.uniandes.csw.documentos.entities.ComentarioEntity;
import co.edu.uniandes.csw.documentos.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.documentos.persistence.ComentarioPersistence;
import java.util.Date;
import java.util.List;
import javax.inject.Inject;

/**
 *
 * @author n.sotelo
 */
public class ComentarioLogic {
    
     /**
     * Inyeccion para conectar la capa de logica con la persistencia.
     */
    @Inject
    private ComentarioPersistence persistencia;
     /**
     *Crea una entidiad en el sistema
     * @param entity la cual sera creada siempre y cuando cumpla con las reglas de negocio
     * @return la entidad nueva que cumple con las reglas del negocio
     * @throws BusinessLogicException si no cumple con una regla de negocio
     */
    public ComentarioEntity createComentario(ComentarioEntity entity) throws BusinessLogicException
    {Date fecha= new Date();
    if(persistencia.find(entity.getId())!=null)
    {
        throw new BusinessLogicException("Ya existe el comentario con ese id");
    }
    if ( entity.getComentario().isEmpty())
    {
        throw new BusinessLogicException("El comentario no puede ser vacio");
    }
    if(entity.getFecha().after(fecha))
    {
        throw new BusinessLogicException("La fecha no es valida");
    }
    
    return persistencia.create(entity);
    }
   /**
     * Retorna toda las entidades del sistema de tipo comentario.
     * @return lista de todas las compras
     * @throws BusinessLogicException  si no existe alguna entiddad en el sistema
     */
    public List<ComentarioEntity> getComentarios() throws BusinessLogicException {
        
        
        List<ComentarioEntity> editorials = persistencia.findAll();
        if(editorials.isEmpty())
        {
            throw new BusinessLogicException("No existen comentarios en el sistema");
        }
        
        return editorials;
    }
    /**
     * Retorna una sola entidad del sitema de tipo comentario.
     * @param id de la entidad que se desea buscar 
     * @return la entidad encontrada
     * @throws BusinessLogicException si no existe la entidad  buscada en el sistema 
     */
    public ComentarioEntity getComentario(Long id) throws BusinessLogicException
    {
        if (persistencia.find(id)==null)
        {
            throw new BusinessLogicException("No existe elemento que coincida con tu busqueda");
        }
        
        ComentarioEntity editorial = persistencia.find(id);
        
        return editorial;
        
    }
      /**
     * Actualiza la informacion de una entidad
     * @param id de la entidad que se desea actualizar
     * @param entity la informacion nueva de la entidad a actualizar
     * @return la entidad con la informacion actualizada
     * @throws BusinessLogicException  si no existe la entidad en el sistema
     */
    public ComentarioEntity updateComentario(Long id, ComentarioEntity entity) throws BusinessLogicException
    {
        Date fecha= new Date();
        if (persistencia.find(id)==null)
        {
            throw new BusinessLogicException("No existe elemento que coincida con tu busqueda");
            
        }
        if (entity.getComentario().isEmpty())
        {
            throw new BusinessLogicException("No se puede actualizar un comentario para que no tenga informacion");
            
        }
        if(entity.getFecha().after(fecha))
        {
            throw new BusinessLogicException("La fecha no es valida");
        }
        ComentarioEntity newEntity = persistencia.update(entity);
        
        return newEntity;
    }
     /**
     * Borra la entidad del sistema
     * @param id del curso que se desea borrar
     * @throws BusinessLogicException  no existe la entidad con ese id
     */
    public void deleteComentario(Long id) throws BusinessLogicException
    {
        if(persistencia.find(id)==null)
        {
            throw new BusinessLogicException("No existe comentario con el id puesto por parametro");
        }
        persistencia.delete(getComentario(id));
        
        
    }
    
}
