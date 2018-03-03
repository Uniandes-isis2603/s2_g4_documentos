/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.documentos.persistence;

import co.edu.uniandes.csw.documentos.entities.PayPalEntity;
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
public class PayPalPersistence {
    
    private static final Logger LOGGER = Logger.getLogger(PayPalPersistence.class.getName());

    @PersistenceContext(unitName = "DocumentosPU")
    protected EntityManager em;

    /**
     * 
     * @param entity objeto PayPal que se creara en la base de datos.
     * @return devuelve la entidad creada con un Id dado por la base de datos.
     */
    public PayPalEntity create(PayPalEntity entity)
    {
        LOGGER.info("Creando una cuenta PayPal nueva");
        em.persist(entity);
        LOGGER.info("Creando una cuenta PayPal nueva");
        return entity;
    }
    
    /**
     * Busca si hay alguna cuenta PayPal con el nombre que se envia en el argumento.
     * 
     * @param correo: Correo electronico de el usuario asociado a la cuenta.
     * @return null si no existe ninguna cuenta paypal asociada con el correo enviado de param. 
     * De lo contrario, retorna la primera que encuentre.
     */
    public PayPalEntity findByMail(String correo)
    {
        LOGGER.log(Level.INFO, "COnsultando la cuenta por correoElectronico", correo);
        
        TypedQuery query = em.createQuery("Select e From PayPalEntity e Where correoElectronico = ?1", PayPalEntity.class);
        query = query.setParameter("1", correo);
        List<PayPalEntity> sameCorreo = query.getResultList();
        if(sameCorreo.isEmpty()){
            return null;
        }
        else {
            return sameCorreo.get(0);
        }
    }
    
    public List<PayPalEntity> findAll()
    {
        LOGGER.log(Level.INFO, "Consultando todas las cuentas PayPal del usuario");
        TypedQuery query = em.createQuery("Select u from PayPalEntity u", PayPalEntity.class);
        return query.getResultList();
    }        
    
    public PayPalEntity find(Long Id)
    {
        return em.find(PayPalEntity.class, Id);
    }
    
    public PayPalEntity update (PayPalEntity entity)
    {
        return em.merge(entity);
    }
    
    public void delete(Long Id)
    {
        LOGGER.log(Level.INFO, "Borrando cuenta PayPal con id={0}", Id);
        PayPalEntity entity = em.find(PayPalEntity.class, Id);
        em.remove(entity);
    }
    
}
