/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.documentos.persistence;

import co.edu.uniandes.csw.documentos.entities.UsuarioEntity;
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
public class UsuarioPersistence {

    private static final Logger LOGGER = Logger.getLogger(UsuarioEntity.class.getName());

    @PersistenceContext(unitName = "DocumentosPU")
    EntityManager em;

    /**
     * 
     * @param userEntity entidad que se va a crear.
     * @return  la entidad creada.
     */
    public UsuarioEntity create(UsuarioEntity userEntity) {

        System.out.println("asdasd");
        LOGGER.info("se va a crear una entidad de usuario");
        em.persist(userEntity);
        LOGGER.info("se creo la entidad");

        return userEntity;
    }

    /**
     * 
     * @param userEntity entidad que se va a actualizar.
     * @return  la entidad actualizada.
     */
    public UsuarioEntity update(UsuarioEntity userEntity) {
        return em.merge(userEntity);

    }

    /**
     * 
     * @param id id de la entidad que se quiere encontrar.
     * @return la entidad con el id dado por parametro.
     */
    public UsuarioEntity find(Long id) {
        return em.find(UsuarioEntity.class, id);
    }

    /**
     * 
     * @return todas las entidades en el sistema.
     */
    public List<UsuarioEntity> findAll() {
        LOGGER.info("Buscando todas los usuarios del sistema");
        TypedQuery query = em.createQuery("select u from UsuarioEntity u", UsuarioEntity.class);
        return query.getResultList();
    }

    /**


    /**
     * 
     * @param id id de la entidad que se va a eliminar
     */
    public void delete(Long id) {
        LOGGER.info("se eliminará user buscandolo por su id");
        UsuarioEntity user = em.find(UsuarioEntity.class, id);
        LOGGER.info("se encontró user");
        em.remove(user);
        LOGGER.info("se eliminó user");
    }

}
