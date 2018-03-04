/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.documentos.ejb;

import co.edu.uniandes.csw.documentos.entities.AutorEntity;
import co.edu.uniandes.csw.documentos.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.documentos.persistence.AutorPersistence;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author Camilojaravila
 */
@Stateless
public class AutorLogic {
    
    private static final Logger LOGGER = Logger.getLogger(AutorLogic.class.getName());

    @Inject
    private AutorPersistence persistence;

    //@Inject
    //private DocumentoLogic DocumentoLogic;
    
    /**
     * Se encarga de crear un Autor en la base de datos.
     *
     * @param entity Objeto de AutorEntity con los datos nuevos
     * @return Objeto de AutorEntity con los datos nuevos y su ID.
     * @throws BusinessLogicException Excepción cuando se incumple una regla de negocio.
     */
    public AutorEntity createAutor(AutorEntity entity) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de crear un autor");
        
        AutorEntity buscado = persistence.find(entity.getId());
        if (buscado != null){
            LOGGER.log(Level.INFO, "El autor con el id {0} ya existe", entity.getId());
            throw new BusinessLogicException("El autor con el id " + entity.getId() + " ya existe");
        }
        else if (!entity.getNombre().matches("([A-Z]|[a-z]|[0-9]|\\s)+")){
            LOGGER.log(Level.INFO, "El nombre del autor no puede contener caracteres especiales");
            throw new BusinessLogicException("El nombre del autor no puede contener caracteres especiales");
        }

        return persistence.create(entity);

    }
    
     /**
     * Obtiene la lista de los registros de Autor.
     *
     * @return Colección de objetos de AutorEntity.
     */
    public List<AutorEntity> getAutores() {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar todos los autores");
        return persistence.findAll();
    }
    
     /**
     * Obtiene los datos de una instancia de Autor a partir de su ID.
     *
     * @param id Identificador de la instancia a consultar
     * @return Instancia de AutorEntity con los datos del Autor consultado.
     */
    public AutorEntity getAutor(Long id) {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar un autor con id = {0}", id);
        return persistence.find(id);
    }

    /**
     * Actualiza la información de una instancia de Autor.
     *
     * @param entity Instancia de AutorEntity con los nuevos datos.
     * @return Instancia de AuthorEntity con los datos actualizados.
     * @throws BusinessLogicException Excepción cuando se incumple una regla de negocio.
     */
    public AutorEntity updateAutor(AutorEntity entity) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de actualizar un autor");
        
        AutorEntity buscado = persistence.find(entity.getId());
        if (buscado == null){
            LOGGER.log(Level.INFO, "El autor con el id {0} no existe", entity.getId());
            throw new BusinessLogicException("El autor con el id " + entity.getId() + " no existe");
        }
        else if (!entity.getNombre().matches("([A-Z]|[a-z]|[0-9]|\\s)+")){
            LOGGER.log(Level.INFO, "El nombre del autor no puede contener caracteres especiales");
            throw new BusinessLogicException("El nombre del autor no puede contener caracteres especiales");
        }
        return persistence.update(entity);
    }

    /**
     * Elimina una instancia de Autor de la base de datos.
     *
     * @param id Identificador de la instancia a eliminar.
     * @throws BusinessLogicException Excepción cuando se incumple una regla de negocio.
     */
    public void deleteAutor(Long id) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de borrar un autor");

        AutorEntity buscado = persistence.find(id);
        if (buscado == null){
            LOGGER.log(Level.INFO, "El autor con el id {0} no existe", id);
            throw new BusinessLogicException("El autor con el id " + id + " no existe");
        }

        persistence.delete(id);
    }

}
