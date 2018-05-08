/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.documentos.persistence;

import co.edu.uniandes.csw.documentos.entities.CarritoEntity;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author nicolassotelo
 */
@Stateless
public class CarritoPersistence {
    
    
       private  final Logger LOGGER=Logger.getLogger(CarritoEntity.class.getName());
    
    @PersistenceContext(unitName = "DocumentosPU")
    protected  EntityManager entidad;
    /**
     * 
     * @param entidadNueva informacion de la nueva entidad a crear
     * @return la entidad nueva creada
     */
    public CarritoEntity create(CarritoEntity entidadNueva)
    {
        LOGGER.info("Creando una entidad de carrito");
        
        entidad.persist(entidadNueva);
        
        LOGGER.info("Entidad creada con exito");
        
         return entidadNueva;
    }
    /**
     * 
     * @param idDeLaEntidad que se desea buscar
     * @return entidad de compra
     */
    public CarritoEntity find(Long idDeLaEntidad)
    { 
        return entidad.find(CarritoEntity.class, idDeLaEntidad);
    }
    
    /**
     * 
     * @param infoDeCompra
     * @return 
     */
     public CarritoEntity update(CarritoEntity infoDeCompra)
     {
          LOGGER.log(Level.INFO, "Actualizando el carrito con id={0}", infoDeCompra.getId());
          
        return entidad.merge(infoDeCompra);
     }
     
         
     

    
}
