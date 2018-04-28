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
      /**
     * Inyeccion para conectar la capa de logica con la persistencia.
     */
    @Inject
    private CompraPersistence persistencia;
    /**
     *Crea una entidiad en el sistema
     * @param entity la cual sera creada siempre y cuando cumpla con las reglas de negocio
     * @return la entidad nueva que cumple con las reglas del negocio
     * @throws BusinessLogicException si no cumple con una regla de negocio
     */
    public CompraEntity createCompra(CompraEntity entity) throws BusinessLogicException
    { Date fecha= new Date();
    if(persistencia.find(entity.getId())!=null)
    {
        throw new BusinessLogicException("Ya existe la compra con ese id");
    }
    if (entity.getCosto()==0 ||entity.getCosto()==null )
    {
        throw new BusinessLogicException("El costo de la compra no puede ser 0 ni vacio");
    }
   
   
    return persistencia.create(entity);
    }
     /**
     * Retorna toda las entidades del sistema de tipo compra.
     * @return lista de todas las compras
     * @throws BusinessLogicException  si no existe alguna entiddad en el sistema
     */
    public List<CompraEntity> getCompras() throws BusinessLogicException {
        
        
        List<CompraEntity> editorials = persistencia.findAll();
        if(editorials.isEmpty())
        {
            throw new BusinessLogicException("No existen compras en el sistema");
        }
        
        return editorials;
    }
    /**
     * Retorna una sola entidad del sitema de tipo compra
     * @param id de la entidad que se desea buscar 
     * @return la entidad encontrada
     * @throws BusinessLogicException si no existe la entidad  buscada en el sistema 
     */
    public CompraEntity getCompra(Long id) throws BusinessLogicException
    {
        if (persistencia.find(id)==null)
        {
            throw new BusinessLogicException("No existe elemento que coincida con tu busqueda");
        }
        
        CompraEntity editorial = persistencia.find(id);
        
        return editorial;
        
    }
    /**
     * Actualiza la informacion de una entidad
     * @param id de la entidad que se desea actualizar
     * @param entity la informacion nueva de la entidad a actualizar
     * @return la entidad con la informacion actualizada
     * @throws BusinessLogicException  si no existe la entidad en el sistema
     */
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
     /**
     * Borra la entidad del sistema
     * @param id del curso que se desea borrar
     * @throws BusinessLogicException  no existe la entidad con ese id
     */
    public void deleteCompra(Long id) throws BusinessLogicException
    {
        if(persistencia.find(id)==null)
        {
            throw new BusinessLogicException("No existe compra con el id puesto por parametro");
        }
        persistencia.delete(getCompra(id));
        
        
    }
    
    
}
