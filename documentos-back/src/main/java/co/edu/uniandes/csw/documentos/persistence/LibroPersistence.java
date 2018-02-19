/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.documentos.persistence;

import co.edu.uniandes.csw.documentos.entities.LibroEntity;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author Ernesto Viana.
 */
@Stateless
public class LibroPersistence {
    
    private static final Logger LOGGER = Logger.getLogger(LibroPersistence.class.getName());
    
    @PersistenceContext(unitName = "DocumentosPU")
    protected EntityManager em;
    
    /**
     * 
     * @param entity objeto libro que se creara en la base de datos.
     * @return devuelve la entidad creada con un id dado por la base de datos.
     */
    public LibroEntity create(LibroEntity entity) {
        LOGGER.info("Creando un libro nuevo");
        em.persist(entity);
        LOGGER.info("Creando un libro nuevo");
        return entity;
    }
    
    /**
     * Busca todos los libros con el nombre que se envia de argumento.
     * @param nombre nombre del libro que se esta buscando
     * @return los libros con el nombre que se esta buscando.
     */
    public List<LibroEntity> findByName(String nombre) {
        LOGGER.log(Level.INFO, "Consultando libros por nombre", nombre);
        
        TypedQuery query = em.createQuery("Select e From LibroEntity e where e.nombre = :nombre", LibroEntity.class);
        query = query.setParameter("nombre", nombre);
        return query.getResultList();
    }
    
    /**
     * Busca todos los libros.
     * @return todos los libros.
     */
    public List<LibroEntity> findAll() {
        
        LOGGER.info("Consultando todos los libros");
        TypedQuery query = em.createQuery("select u from LibroEntity u", LibroEntity.class);
        return query.getResultList();
        
    }
    
    /**
     * 
     * @param id del libro que se esta buscando.
     * @return documento que se encontro.
     */
    public LibroEntity find(Long id) {
        LOGGER.info("Consultando un libro");
        return em.find(LibroEntity.class, id);
    }
    
    /**
     * 
     * @param entity entidad que va a remplazar.
     * @return la entidad actualizada.
     */
    public LibroEntity update(LibroEntity entity) {
        LOGGER.info("Actualizando un libro");
        return em.merge(entity);
    }
    
    /**
     * 
     * @param entity entidad que se va a elimnar.
     */
    public void delete(Long id) {
        LOGGER.info("Eliminando un libro");
        LibroEntity entity = em.find(LibroEntity.class, id);
        em.remove(entity);
    }
}
