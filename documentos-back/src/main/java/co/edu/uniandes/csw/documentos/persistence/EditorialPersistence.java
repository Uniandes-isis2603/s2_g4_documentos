/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.documentos.persistence;

import co.edu.uniandes.csw.documentos.entities.EditorialEntity
        ;
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
public class EditorialPersistence {
      private static final Logger LOGGER=Logger.getLogger(EditorialEntity.class.getName());
    
    @PersistenceContext(unitName = "DocumentosPU")
    protected  EntityManager entidad;
    /**
     * 
     * @param entidadNueva informacion de la nueva entidad a crear
     * @return la entidad nueva creada
     */
    public EditorialEntity create(EditorialEntity entidadNueva)
    {
        LOGGER.info("Creando una entidad de curso");
        entidad.persist(entidadNueva);
        LOGGER.info("Entidad creada con exito");
        
         return entidadNueva;
    }
    /**
     * 
     * @param idDeLaEntidad que se desea buscar
     * @return entidad de curso
     */
    public EditorialEntity find(Long idDeLaEntidad)
    {
        return entidad.find(EditorialEntity.class, idDeLaEntidad);
    }
    /**
     * 
     * @return lista de todas las entidades 
     */
    public List<EditorialEntity> findAll()
    {
        LOGGER.info("Buscando todas los cursos del sistema");
        TypedQuery query= entidad.createQuery("select u from EditorialEntity u", EditorialEntity.class);
        return query.getResultList();
    }
    /**
     * 
     * @param id entidad de la editorials que se desea borrar 
     */
     public void delete(EditorialEntity id)
     {
         LOGGER.info("Borrando el curso del sistema");
           EditorialEntity entity = entidad.find(EditorialEntity.class, id.getId());
         entidad.remove(entity);
      
        
     }
     /**
      * 
      * @param infoDeEditorial a ctualizar 
      * @return 
      */
     public EditorialEntity update(EditorialEntity infoDeEditorial)
     {
          LOGGER.log(Level.INFO, "Actualizando el curso con id={0}", infoDeEditorial.getId());
        return entidad.merge(infoDeEditorial);
     }
    
    
}
