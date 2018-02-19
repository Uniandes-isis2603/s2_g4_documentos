/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.documentos.persistence;

import co.edu.uniandes.csw.documentos.entities.DeseadoEntity;
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
public class DeseadoPersistence {

    private static final Logger LOGGER = Logger.getLogger(DeseadoEntity.class.getName());

    @PersistenceContext(unitName = "DocumentosPU")
    EntityManager em;

    /**
     * 
     * @param deseadoEntity entidad que se va a crear.
     * @return  la entidad creada.
     */
    public DeseadoEntity create(DeseadoEntity deseadoEntity) {

        LOGGER.info("se va a crear una entidad de usuario");
        em.persist(deseadoEntity);
        LOGGER.info("se creo la entidad");

        return deseadoEntity;
    }

    /**
     * 
     * @param deseadoEntity entidad que se va a actualizar.
     * @return  la entidad actualizada.
     */
    public DeseadoEntity update(DeseadoEntity deseadoEntity) {
        return em.merge(deseadoEntity);

    }

    /**
     * 
     * @param id id de la entidad que se quiere encontrar.
     * @return la entidad con el id dado por parametro.
     */
    public DeseadoEntity find(Long id) {
        return em.find(DeseadoEntity.class, id);
    }

    /**
     * 
     * @return todas las entidades en el sistema.
     */
    public List<DeseadoEntity> findAll() {
        LOGGER.info("Buscando todas los usuarios del sistema");
        TypedQuery query = em.createQuery("select u from UsuarioEntity u", DeseadoEntity.class);
        return query.getResultList();
    }

    /**
     * 
     * @param deseadoEntity entidad que se eliminará.
     */
    public void delete(DeseadoEntity deseadoEntity) {
        
        LOGGER.info("se eliminará userEntity");
        em.remove(deseadoEntity);
        LOGGER.info("se eliminó userEntity");
    }

    /**
     * 
     * @param id id de la entidad que se va a eliminar
     */
    public void deleteId(Long id) {
        LOGGER.info("se eliminará user buscandolo por su id");
        DeseadoEntity deseado = em.find(DeseadoEntity.class, id);
        LOGGER.info("se encontró user");
        em.remove(deseado);
        LOGGER.info("se eliminó user");
    }

    
}
