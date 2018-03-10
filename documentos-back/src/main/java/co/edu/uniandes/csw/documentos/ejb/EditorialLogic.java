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
      /**
     * Inyeccion para conectar la capa de logica con la persistencia.
     */
    @Inject
    private EditorialPersistence persistencia;
    
     /**
     *Crea una entidiad en el sistema
     * @param entity la cual sera creada siempre y cuando cumpla con las reglas de negocio
     * @return la entidad nueva que cumple con las reglas del negocio
     * @throws BusinessLogicException si no cumple con una regla de negocio
     */
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
     /**
     * Retorna toda las entidades del sistema de tipo editorial.
     * @return lista de todas las compras
     * @throws BusinessLogicException  si no existe alguna entiddad en el sistema
     */
    public List<EditorialEntity> getEditorials() throws BusinessLogicException {
        
        
        List<EditorialEntity> editorials = persistencia.findAll();
        if(editorials.isEmpty())
        {
            throw new BusinessLogicException("No existen editoriales en el sistema");
        }
        
        return editorials;
    }
    /**
     * Retorna una sola entidad del sitema de tipo editorial.
     * @param id de la entidad que se desea buscar 
     * @return la entidad encontrada
     * @throws BusinessLogicException si no existe la entidad  buscada en el sistema 
     */
    public EditorialEntity getEditorial(Long id) throws BusinessLogicException
    {
        if (persistencia.find(id)==null)
        {
            throw new BusinessLogicException("No existe elemento que coincida con tu busqueda");
        }
        
        EditorialEntity editorial = persistencia.find(id);
        
        return editorial;
        
    }
     /**
     * Actualiza la informacion de una entidad
     * @param id de la entidad que se desea actualizar
     * @param entity la informacion nueva de la entidad a actualizar
     * @return la entidad con la informacion actualizada
     * @throws BusinessLogicException  si no existe la entidad en el sistema
     */
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
    /**
     * Borra la entidad del sistema
     * @param id del curso que se desea borrar
     * @throws BusinessLogicException  no existe la entidad con ese id
     */
    public void deleteEditorial(Long id) throws BusinessLogicException
    {
        if(persistencia.find(id)==null)
        {
            throw new BusinessLogicException("No existe editorial con el id puesto por parametro");
        }
        persistencia.delete(getEditorial(id));
        
    }
    
}
