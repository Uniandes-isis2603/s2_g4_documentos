/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.documentos.persistence;

import co.edu.uniandes.csw.documentos.entities.ImagenEntity;
import java.util.List;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author Camilojaravila
 */
@Stateless
public class ImagenPersistance {
    
    private static final Logger LOGGER=Logger.getLogger(ImagenEntity.class.getName());
    
    @PersistenceContext(unitName = "DocumentosPU")
    protected  EntityManager em;
    
    /**
     * 
     * @param entity. Objeto de tipo imagen que se creara en la base de datos
     * @return null si no existe ningun area de conocimiento con el nombre del argumento. Si
     * existe alguna devuelve la primera.
     */
    public ImagenEntity create(ImagenEntity entity){
        LOGGER.info("Creando una imagen nuevo");
        em.persist(entity);
        LOGGER.info("Creando una imagen nuevo");
        return entity;
    }
    
    /**
     * 
     * @param id Id de la imagen
     * @return Imagen o null en caso de no encontrarlo
     */
    public ImagenEntity find(Long id){
        return em.find(ImagenEntity.class, id);
    }
    
    /**
     * 
     * @return Una lista con todas las imagenes en el sistema
     */
    public List<ImagenEntity> findAll() {
        LOGGER.info("Consultando todas las imageneses");
        TypedQuery query = em.createQuery("select u from ImagenEntity u", ImagenEntity.class);
        return query.getResultList();
    }
    
    /**
     * 
     * @param entity Imagen a actualizar
     * @return La Imagen ya actualizada
     */
    public ImagenEntity update(ImagenEntity entity){
        return em.merge(entity);
    }
    /**
     * 
     * @param entity Imagen que se va a borrar 
     */
    public void delete(ImagenEntity entity){
        em.remove(entity);
    }
}
