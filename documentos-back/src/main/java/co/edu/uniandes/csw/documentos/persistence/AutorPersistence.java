/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.documentos.persistence;

import co.edu.uniandes.csw.documentos.entities.AutorEntity;
import java.util.List;
import java.util.logging.Level;
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
public class AutorPersistence{
    private static final Logger LOGGER=Logger.getLogger(AutorEntity.class.getName());
    
    @PersistenceContext(unitName = "DocumentosPU")
    protected  EntityManager em;
    
    /**
     * 
     * @param entity. Objeto de tipo autor que se creara en la base de datos
     * @return null si no existe ningun area de conocimiento con el nombre del argumento. Si
     * existe alguna devuelve la primera.
     */
    public AutorEntity create(AutorEntity entity){
        LOGGER.info("Creando un autor nuevo");
        em.persist(entity);
        LOGGER.info("Creando un autor nuevo");
        return entity;
    }
    
    /**
     * 
     * @param id Id del autor
     * @return Autor o null en caso de no encontrarlo
     */
    public AutorEntity find(Long id){
        return em.find(AutorEntity.class, id);
    }
    
    /**
     * 
     * @return Una lista con todos los Autores en el sistema
     */
    public List<AutorEntity> findAll() {
        LOGGER.info("Consultando todos los autores");
        TypedQuery query = em.createQuery("select u from AutorEntity u", AutorEntity.class);
        return query.getResultList();
    }
    
    /**
     * 
     * @param entity Autor que se va a actualizar
     * @return El Autor ya actualizado
     */
    public AutorEntity update(AutorEntity entity){
        Long id = entity.getId();
        LOGGER.log(Level.INFO, "Actualizando el autor con id {0}", id);
        return em.merge(entity);
    }
    
    /**
     * 
     * @param id Id del autor
     */
    public void delete(Long id){
        LOGGER.log(Level.INFO, "Actualizando el autor con id {0}", id);
        AutorEntity entity = find(id);
        em.remove(entity);
    }
}
