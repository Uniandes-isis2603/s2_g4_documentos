/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.documentos.persistence;

import co.edu.uniandes.csw.documentos.entities.CursoEntity;
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
public class CursoPersistence {
     private  final Logger LOGGER=Logger.getLogger(CursoEntity.class.getName());
    
    @PersistenceContext(unitName = "DocumentosPU")
    protected  EntityManager entidad;
    /**
     * 
     * @param entidadNueva informacion de la nueva entidad a crear
     * @return la entidad nueva creada
     */
    public CursoEntity create(CursoEntity entidadNueva)
    {
        LOGGER.info("Creando una entidad de curso");
        entidad.persist(entidadNueva);
        LOGGER.info("Entidad creada con exito");
        
         return entidadNueva;
    }
    public CursoEntity findByName(String nombre)
    {
        TypedQuery query = entidad.createQuery("Select e From CursoEntity e where e.nombre = :nombre", CursoEntity.class);
        
        query = query.setParameter("nombre", nombre);
        // Se invoca el query se obtiene la lista resultado
        List<CursoEntity> sameName = query.getResultList();
       CursoEntity result = null; 
         if (sameName == null ) {
            result = null;
        } else if (sameName.isEmpty()) {
             result = null;
        } else {
            result =  sameName.get(0);
        }
       
        
        return result;
    }
    
    /**
     * 
     * @param idDeLaEntidad que se desea buscar
     * @return entidad de curso
     */
    public CursoEntity find(Long idDeLaEntidad)
    {
        return entidad.find(CursoEntity.class, idDeLaEntidad);
    }
    /**
     * 
     * @return lista de todas las entidades 
     */
    public List<CursoEntity> findAll()
    {
        LOGGER.info("Buscando todas los cursos del sistema");
        TypedQuery query= entidad.createQuery("select u from CursoEntity u", CursoEntity.class);
        return query.getResultList();
    }
    /**
     * 
     * @param id entidad de curso que se desea borrar 
     */
     public void delete(CursoEntity id)
     {
         
           CursoEntity entity = entidad.find(CursoEntity.class, id.getId());
         entidad.remove(entity);
      
        
     }
     /**
      * 
      * @param infoDeCurso a ctualizar 
      * @return 
      */
     public CursoEntity update(CursoEntity infoDeCurso)
     {
          LOGGER.log(Level.INFO, "Actualizando el curso con id={0}", infoDeCurso.getId());
        return entidad.merge(infoDeCurso);
     }
    
}
