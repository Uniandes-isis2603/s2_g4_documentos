/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.documentos.persistence;
import co.edu.uniandes.csw.documentos.entities.MetodoDePagoEntity;
import java.util.List;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author g.ospinaa
 */
@Stateless
public class MetodoDePagoPersistence {
 
     /**
     * Logger que se va a utilizar para loguear las operaciones.
     */
    private static final Logger LOGGER = Logger.getLogger(MetodoDePagoPersistence.class.getName());

    /**
     * Contexto de persistencia en el que se van a hacer las operaciones de base de datos (esta en el persitence.xml).
     */
    @PersistenceContext(unitName = "DocumentosPU")
    protected EntityManager em;
    
    /**
     * 
     * @param entity objeto metodoDePago que se creará en la base de datos.
     * @return devuelve la entidad creada con un id dado por la base de datos.
     */
    public MetodoDePagoEntity create(MetodoDePagoEntity entity) {
        LOGGER.info("Creando un metodoDePago nuevo");
        em.persist(entity);
        LOGGER.info("Creando un metodoDePago nuevo");
        return entity;
    }
    
    /**
     * Busca todos los metodoDePagos.
     * @return  todas los metodoDePagos.
     */
    public List<MetodoDePagoEntity> findAll() {
        LOGGER.info("Consultando todos los metodoDePagos");
        TypedQuery query = em.createQuery("select u from MetodoDePagoEntity u", MetodoDePagoEntity.class);
        return query.getResultList();
    }
    
    /**
     * 
     * @param id del metodoDePago que se esta buscando.
     * @return metodoDePago que se encontro.
     */
    public MetodoDePagoEntity find(Long id) {
        LOGGER.info("Consultando un metodoDePago");
        return em.find(MetodoDePagoEntity.class, id);
    }
    
    /**
     * 
     * @param entity entidad que va a remplazar.
     * @return la entidad actualizada.
     */
    public MetodoDePagoEntity update(MetodoDePagoEntity entity) {
        LOGGER.info("Actualizando un metodoDePago");
        return em.merge(entity);
    }
    
    /**
     * 
     * @param id del metodoDePago que se va a eliminar.
     */
    public void delete(Long id) {
        LOGGER.info("eliminando un metodoDePago");
        MetodoDePagoEntity entity = em.find(MetodoDePagoEntity.class,id);
        em.remove(entity);
    }
    
}
