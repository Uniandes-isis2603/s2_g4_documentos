/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.documentos.persistence;


import co.edu.uniandes.csw.documentos.entities.ComentarioEntity;
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
public class ComentarioPersistence {
     private static final Logger LOGGER=Logger.getLogger(ComentarioEntity.class.getName());
    
    @PersistenceContext(unitName = "DocumentosPU")
    protected  EntityManager entidad;
    /**
     * 
     * @param entidadNueva informacion de la nueva entidad a crear
     * @return la entidad nueva creada
     */
    public ComentarioEntity create(ComentarioEntity entidadNueva)
    {
        LOGGER.info("Creando una entidad de comentario");
        entidad.persist(entidadNueva);
        LOGGER.info("Entidad creada con exito");
        
         return entidadNueva;
    }
    /**
     * 
     * @param idDeLaEntidad que se desea buscar
     * @return entidad de comentario
     */
    public ComentarioEntity find(Long idDeLaEntidad)
    {
        return entidad.find(ComentarioEntity.class, idDeLaEntidad);
    }
    /**
     * 
     * @return lista de todas las entidades 
     */
    public List<ComentarioEntity> findAll()
    {
        LOGGER.info("Buscando todos los comentarios del sistema");
        TypedQuery query= entidad.createQuery("select u from ComentarioEntity u", ComentarioEntity.class);
        return query.getResultList();
    }
    /**
     * 
     * @param id entidad de comentario que se desea borrar 
     */
     public void delete(ComentarioEntity id)
     {
         LOGGER.info("Borrando el curso del sistema");
           ComentarioEntity entity = entidad.find(ComentarioEntity.class, id.getId() );
         entidad.remove(entity);
      
        
     }
     /**
      * 
      * @param infoDeComentario de entidad a actualizar
      * @return el comentario actualizado
      */
     public ComentarioEntity update(ComentarioEntity infoDeComentario)
     {
          LOGGER.log(Level.INFO, "Actualizando comentario con id={0}", infoDeComentario.getId());
        return entidad.merge(infoDeComentario);
     }

   

    
    
}
