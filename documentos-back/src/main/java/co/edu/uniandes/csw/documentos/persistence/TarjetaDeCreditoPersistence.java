/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.documentos.persistence;

import co.edu.uniandes.csw.documentos.entities.TarjetaDeCreditoEntity;
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
public class TarjetaDeCreditoPersistence {
 
    private static final Logger LOGGER = Logger.getLogger(CityPersistence.class.getName());

    @PersistenceContext(unitName = "DocumentosPU")
    protected EntityManager em;
    
    /**
     * 
     * @param entity objeto TarjetaDeCredito que se creara en base de datos.
     * @return entidad creada con un id dado por base de datos.
     * 
     */
    public TarjetaDeCreditoEntity create(TarjetaDeCreditoEntity entity)
    {
        LOGGER.info("Creando una nueva tarjeta de credito");
        em.persist(entity);
        LOGGER.info("Creando una nueva tarjeta de credito)");
        return entity;
    }
    
    /**
     * Busca si hay alguna tarjeta con el numero enviado por argumento.
     * 
     * @param numero Numero de la tarjeta que se busca.
     * @return null si no encuentra la tarjeta, de lo contrario,
     * devuelve la primera que encuentre.
     */
    public TarjetaDeCreditoEntity findByNumber(String numero)
    {
        LOGGER.log(Level.INFO, "Consultando TDC por numero", numero);
        
        TypedQuery query = em.createQuery("Select e from PayPalEntity e where e.numero = :numero", TarjetaDeCreditoEntity.class);
        query = query.setParameter("numero", numero);
        List<TarjetaDeCreditoEntity> sameNumero = query.getResultList();
        if(sameNumero.isEmpty())
        {
            return null;
        }
        else
        {
            return sameNumero.get(0);
        }
    }
    
    public List<TarjetaDeCreditoEntity> findAll()
    {
        LOGGER.info("Consultando todas las tarjetas del usuario");
        TypedQuery query = em.createQuery("Select u from TarjetaDeCreditoEntity u", TarjetaDeCreditoEntity.class);
        return query.getResultList();
    }
    
    public TarjetaDeCreditoEntity find(Long Id)
    {
        return em.find(TarjetaDeCreditoEntity.class, Id);
    }
    
    public TarjetaDeCreditoEntity update(TarjetaDeCreditoEntity entity)
    {
        return em.merge(entity);
    }
    
    public void delete(Long Id)
    {
        LOGGER.log(Level.INFO, "Borrando cuenta PayPal con id={0}", Id);
        TarjetaDeCreditoEntity entity = em.find(TarjetaDeCreditoEntity.class, Id);
        em.remove(entity);
    }
}
