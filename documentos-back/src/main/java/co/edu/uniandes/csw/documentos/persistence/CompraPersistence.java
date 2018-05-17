/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.documentos.persistence;

import co.edu.uniandes.csw.documentos.entities.CompraEntity;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author n.sotelo
 */
@Stateless
public class CompraPersistence {
    private  final Logger LOGGER=Logger.getLogger(CompraEntity.class.getName());
    
    @PersistenceContext(unitName = "DocumentosPU")
    protected  EntityManager entidad;
    /**
     * 
     * @param entidadNueva informacion de la nueva entidad a crear
     * @return la entidad nueva creada
     */
    public CompraEntity create(CompraEntity entidadNueva)
    {
        LOGGER.info("Creando una entidad de compra");
        entidad.persist(entidadNueva);
        LOGGER.info("Entidad creada con exito");
        
         return entidadNueva;
    }
    /**
     * 
     * @param idDeLaEntidad que se desea buscar
     * @return entidad de compra
     */
    public CompraEntity find(Long idDeLaEntidad)
    {
        return entidad.find(CompraEntity.class, idDeLaEntidad);
    }
    /**
     * 
     * @return lista de todas las entidades 
     */
    public List<CompraEntity> findAll()
    {
        LOGGER.info("Buscando todas las compras del sistema");
        TypedQuery query= entidad.createQuery("select u from CompraEntity u", CompraEntity.class);
        return query.getResultList();
    }
    /**
     * 
     * @param id entidad de compra que se desea borrar 
     */
     public void delete(CompraEntity id)
     {
         LOGGER.info("Borrando la compra del sistema");
           CompraEntity entity = entidad.find(CompraEntity.class, id.getId());
         entidad.remove(entity);
      
        
     }
     public CompraEntity update(CompraEntity infoDeCompra)
     {
          LOGGER.log(Level.INFO, "Actualizando la compra con id={0}", infoDeCompra.getId());
        return entidad.merge(infoDeCompra);
     }
     
         
     }
     
     
             
    

