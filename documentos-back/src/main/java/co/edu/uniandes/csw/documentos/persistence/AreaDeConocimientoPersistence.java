/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.documentos.persistence;

import co.edu.uniandes.csw.documentos.entities.AreaDeConocimientoEntity;
import java.util.List;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author Camilojaravila
 */
@Stateless
public class AreaDeConocimientoPersistence {
    
    private static final Logger LOGGER=Logger.getLogger(AreaDeConocimientoEntity.class.getName());
    
    @PersistenceContext(unitName = "DocumentosPU")
    protected  EntityManager em;
    
    /**
     * 
     * @param entity. Objeto de tipo area de conocimiento que se creara en la base de datos
     * @return null si no existe ningun area de conocimiento con el nombre del argumento. Si
     * existe alguna devuelve la primera.
     */
    public AreaDeConocimientoEntity create(AreaDeConocimientoEntity entity){
        LOGGER.info("Creando un area de conocimiento nueva");
        em.persist(entity);
        LOGGER.info("Creando un area de conocimiento nueva");
        return entity;
    }
    
    /**
     * 
     * @param id Id del area de conocimiento
     * @return Area de Conocimiento o null en caso de no encontrarlo
     */
    public AreaDeConocimientoEntity find(Long id){
        return em.find(AreaDeConocimientoEntity.class, id);
    }
    
    /**
     * 
     * @return Una lista con todas las Areas de Conocimiento en el sistema
     */
    public List<AreaDeConocimientoEntity> findAll() {
        LOGGER.info("Consultando todas las areas de conocimiento");
        TypedQuery query = em.createQuery("select u from AreaDeConocimientoEntity u", AreaDeConocimientoEntity.class);
        return query.getResultList();
    }
    
    /**
     * 
     * @param entity Area de Conocimiento a actualizar
     * @return El Area de Conocimiento ya actualizado
     */
    public AreaDeConocimientoEntity update(AreaDeConocimientoEntity entity){
        return em.merge(entity);
    }
    /**
     * 
     * @param entity Area de Conocimiento que se va a borrar 
     */
    public void delete(AreaDeConocimientoEntity entity){
        em.remove(entity);
    }
}
