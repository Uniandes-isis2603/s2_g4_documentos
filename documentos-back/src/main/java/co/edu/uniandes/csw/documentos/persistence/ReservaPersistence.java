/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.documentos.persistence;

import co.edu.uniandes.csw.documentos.entities.ReservaEntity;
import java.util.List;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author f.marroquin10
 */
@Stateless
public class ReservaPersistence {
    
      private static final Logger LOGGER = Logger.getLogger(ReservaEntity.class.getName());

    @PersistenceContext(unitName = "DocumentosPU")
    EntityManager em;

    /**
     * 
     * @param reservaEntity entidad que se va a crear.
     * @return  la entidad creada.
     */
    public ReservaEntity create(ReservaEntity reservaEntity) {

        LOGGER.info("se va a crear una entidad de usuario");
        em.persist(reservaEntity);
        LOGGER.info("se creo la entidad");

        return reservaEntity;
    }

    /**
     * 
     * @param reservaEntity entidad que se va a actualizar.
     * @return  la entidad actualizada.
     */
    public ReservaEntity update(ReservaEntity reservaEntity) {
        return em.merge(reservaEntity);

    }

    /**
     * 
     * @param id id de la entidad que se quiere encontrar.
     * @return la entidad con el id dado por parametro.
     */
    public ReservaEntity find(Long id) {
        return em.find(ReservaEntity.class, id);
    }

    /**
     * 
     * @return todas las entidades en el sistema.
     */
    public List<ReservaEntity> findAll() {
        LOGGER.info("Buscando todas los usuarios del sistema");
        TypedQuery query = em.createQuery("select u from ReservaEntity u", ReservaEntity.class);
        return query.getResultList();
    }


    /**
     * 
     * @param id id de la entidad que se va a eliminar
     */
    public void delete(Long id) {
        LOGGER.info("se eliminará user buscandolo por su id");
        ReservaEntity reserva = em.find(ReservaEntity.class, id);
        LOGGER.info("se encontró user");
        em.remove(reserva);
        LOGGER.info("se eliminó user");
    }

}
