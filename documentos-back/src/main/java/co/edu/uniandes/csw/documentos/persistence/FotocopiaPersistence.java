/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.documentos.persistence;

import co.edu.uniandes.csw.documentos.entities.FotocopiaEntity;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author Ernesto Viana
 */
@Stateless
public class FotocopiaPersistence {
    
    /**
     * Logger que se va a utilizar para loguear las operaciones.
     */
    private static final Logger LOGGER = Logger.getLogger(FotocopiaPersistence.class.getName());
    
    /**
     * Contexto de persistencia en el cual se van a realizar las operaciones.
     */
    @PersistenceContext(unitName = "DocumentosPU")
    protected EntityManager em;
    
    /**
     * 
     * @param entity objeto fotocopia que se creara en la base de datos.
     * @return devuelve la entidad cereada con un id dado por la base de datos.
     */
    public FotocopiaEntity create(FotocopiaEntity entity) {
        LOGGER.info("Creando una fotocopia nueva");
        em.persist(entity);
        LOGGER.info("Creando una fotocopia nueva");
        return entity;
    }
    
    /**
     * Busca todas las fotocopias con el nombre del profesor que se envia de argumento.
     * @param profesor nombre del profesor que se esta buscando.
     * @return las fotocopias con el profesor que se esta buscando.
     */
    public List<FotocopiaEntity> findByProfesor(String profesor) {
        LOGGER.log(Level.INFO, "Consultando fotocopias por el nombre del profesor", profesor);
        
        TypedQuery query = em.createQuery("Select e From FotocopiaEntity e where e.profesor = :profesor", FotocopiaEntity.class);
        query = query.setParameter("profesor", profesor);
        return query.getResultList();
    }
    
    /**
     * Busca todas las fotocopias.
     * @return todas las fotocopias.
     */
    public List<FotocopiaEntity> findAll() {
        LOGGER.info("Consultando todas las fotocopias");
        TypedQuery query = em.createQuery("select u from FotocopiaEntity u", FotocopiaEntity.class);
        return query.getResultList();
    }
    
    /**
     * 
     * @param id de la fotocopia que se esta buscando.
     * @return fotocopia que se encontro.
     */
    public FotocopiaEntity find(Long id) {
        LOGGER.info("Consultando una fotocopia");
        return em.find(FotocopiaEntity.class, id);
    }
    
    /**
     * 
     * @param entity entidad que va a remplazar.
     * @return la entidad actualizada.
     */
    public FotocopiaEntity update(FotocopiaEntity entity){
        LOGGER.info("Actualizando un libro");
        return em.merge(entity);
    }
    
    /**
     * 
     * @param id de la  entidad que se va a eliminar.
     */
    public void delete(Long id) {
        LOGGER.info("Eliminando una fotocopia");
        FotocopiaEntity entity = em.find(FotocopiaEntity.class, id);
        em.remove(entity);
    }
}
