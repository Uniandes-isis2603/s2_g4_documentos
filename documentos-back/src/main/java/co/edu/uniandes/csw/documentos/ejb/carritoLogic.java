/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.documentos.ejb;

import co.edu.uniandes.csw.documentos.entities.CarritoEntity;
import co.edu.uniandes.csw.documentos.entities.DocumentoEntity;
import co.edu.uniandes.csw.documentos.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.documentos.persistence.CarritoPersistence;
import java.util.ArrayList;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author nicolassotelo
 */
@Stateless
public class carritoLogic 
{
        /**
     * Inyeccion para conectar la capa de logica con la persistencia.
     */
    @Inject
    private CarritoPersistence persistencia;
    /**
     *Crea una entidiad en el sistema
     * @param entity la cual sera creada siempre y cuando cumpla con las reglas de negocio
     * @return la entidad nueva que cumple con las reglas del negocio
     * @throws BusinessLogicException si no cumple con una regla de negocio
     */
    public CarritoEntity createCarrito(CarritoEntity entity) throws BusinessLogicException
    { 
    
        if(persistencia.find(entity.getId())!=null)
    {
        throw new BusinessLogicException("Ya existe el carrito con ese id");
    }
  
    return persistencia.create(entity);
    }
   
    /**
     * Retorna una sola entidad del sitema de tipo compra
     * @param id de la entidad que se desea buscar 
     * @return la entidad encontrada
     * @throws BusinessLogicException si no existe la entidad  buscada en el sistema 
     */
    public CarritoEntity getCarrito(Long id) throws BusinessLogicException
    { 
        System.out.println(id);
        
        CarritoEntity carrito = persistencia.find(id);
        System.out.println("desde logica"+ carrito.getDocumentos().size());
        return carrito;
        
    }
    /**
     * Actualiza la informacion de una entidad
     * @param id de la entidad que se desea actualizar
     * @param entity la informacion nueva de la entidad a actualizar
     * @return la entidad con la informacion actualizada
     * @throws BusinessLogicException  si no existe la entidad en el sistema
     */
    public CarritoEntity updateCarrito(Long id,CarritoEntity entity) throws BusinessLogicException
    {
        
        CarritoEntity newEntity = persistencia.update(entity);
        
        return newEntity;
    }
     /**
     * Borra el contenido del contenido del carrito, es decir los documentos asociados a el dejan de existir.Esto con el fin de mantener un carrito unico por usuario
     * @param id del curso que se desea borrar
     * @throws BusinessLogicException  no existe la entidad con ese id
     */
    public void deleteCarrito(Long id) throws BusinessLogicException
    {
        if(persistencia.find(id)==null)
        {
            throw new BusinessLogicException("No existe carrito con el id puesto por parametro");
        }
        //Lista nueva  a agregar a la entidad
        ArrayList<DocumentoEntity> lista= new ArrayList<>();
        CarritoEntity elCarrito= persistencia.find(id);
        elCarrito.setDocumentos(lista);
        updateCarrito(elCarrito.getId(), elCarrito);
        
    }
    
}
