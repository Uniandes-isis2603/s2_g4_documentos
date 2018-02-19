/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.documentos.persistence;

import co.edu.uniandes.csw.documentos.entities.DocumentoEntity;
import java.util.List;
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
public class DocumentoPersistence {
    
    private static final Logger LOGGER = Logger.getLogger(DocumentoPersistence.class.getName());

    @PersistenceContext(unitName = "DocumentosPU")
    protected EntityManager em;
    
    /**
     * 
     * @param entity objeto documento que se creará en la base de datos.
     * @return devuelve la entidad creada con un id dado por la base de datos.
     */
    public DocumentoEntity create(DocumentoEntity entity) {
        LOGGER.info("Creando un documento nuevo");
        em.persist(entity);
        LOGGER.info("Creando un documento nuevo");
        return entity;
    }
    
    /**
     * Busca todos los documentos.
     * @return  todas los documentos.
     */
    public List<DocumentoEntity> findAll() {
        LOGGER.info("Consultando todos los documentos");
        TypedQuery query = em.createQuery("select u from DocumentoEntity u", DocumentoEntity.class);
        return query.getResultList();
    }
    
    /**
     * 
     * @param id del documento que se esta buscando.
     * @return documento que se encontro.
     */
    public DocumentoEntity find(Long id) {
        LOGGER.info("Consultando un documento");
        return em.find(DocumentoEntity.class, id);
    }
    
    /**
     * 
     * @param entity entidad que va a remplazar.
     * @return la entidad actualizada.
     */
    public DocumentoEntity update(DocumentoEntity entity) {
        LOGGER.info("Actualizando un documento");
        return em.merge(entity);
    }
    
    /**
     * 
     * @param id del documento que se va a eliminar.
     */
    public void delete(Long id) {
        LOGGER.info("eliminando un documento");
        DocumentoEntity entity = em.find(DocumentoEntity.class,id);
        em.remove(entity);
    }
}
