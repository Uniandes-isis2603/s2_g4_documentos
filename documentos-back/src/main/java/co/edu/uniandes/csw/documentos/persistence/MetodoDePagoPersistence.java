/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.documentos.persistence;

import java.util.List;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import co.edu.uniandes.csw.documentos.entities.MetodoDePagoEntity;
/**
 *
 * @author g.ospinaa
 */
@Stateless
public class MetodoDePagoPersistence {
    
    private static final Logger LOGGER = Logger.getLogger(MetodoDePagoPersistence.class.getName());

    @PersistenceContext(unitName = "DocumentosPU")
    protected EntityManager em;
    
    public MetodoDePagoEntity create(MetodoDePagoEntity entity)
    {
         LOGGER.info("Creando un metodo de pago nuevo");
        em.persist(entity);
        LOGGER.info("Creando un metodo de pago nuevo");
        return entity;
    }
    
    
    public List<MetodoDePagoEntity> findAll()
    {
         LOGGER.info("Consultando todos los metodos de pago");
        TypedQuery query = em.createQuery("select u from MetodoDePagoEntity u", MetodoDePagoEntity.class);
        return query.getResultList();
    }
    
    public MetodoDePagoEntity find (Long id)
    {
        LOGGER.info("Consultando un metodo de pago");
        return em.find(MetodoDePagoEntity.class, id);
    }
    
    public MetodoDePagoEntity update(MetodoDePagoEntity entity)
    {
        LOGGER.info("Actualizando un metodo de pago");
        return em.merge(entity);
    }
    
    public void delete(Long id)
    {
        LOGGER.info("eliminando un documento");
        MetodoDePagoEntity entity = em.find(MetodoDePagoEntity.class,id);
        em.remove(entity); 
    }
}
