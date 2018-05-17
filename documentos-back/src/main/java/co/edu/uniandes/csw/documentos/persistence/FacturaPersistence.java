/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.documentos.persistence;

import co.edu.uniandes.csw.documentos.entities.FacturaEntity;
import java.util.List;
import java.util.logging.Level;
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
public class FacturaPersistence {
    private static final Logger LOGGER = Logger.getLogger(FacturaEntity.class.getName());
    
    @PersistenceContext(unitName = "DocumentosPU")
    protected EntityManager em;
    
    public FacturaEntity create(FacturaEntity entity){
        LOGGER.info("Creando una factura nuevo");
        em.persist(entity);
        LOGGER.info("Creando una factura nuevo");
        return entity;
    }
    
    public FacturaEntity find(Long id){
        return em.find(FacturaEntity.class, id);
    }
    
    public List<FacturaEntity> findAll() {
        LOGGER.info("Consultando todos los facturas");
        TypedQuery query = em.createQuery("select u from FacturaEntity u", FacturaEntity.class);
        return query.getResultList();
    }

}
