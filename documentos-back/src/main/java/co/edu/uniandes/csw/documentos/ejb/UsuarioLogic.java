/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.documentos.ejb;

import co.edu.uniandes.csw.documentos.entities.UsuarioEntity;
import co.edu.uniandes.csw.documentos.entities.UsuarioEntity;
import co.edu.uniandes.csw.documentos.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.documentos.persistence.UsuarioPersistence;
import co.edu.uniandes.csw.documentos.persistence.UsuarioPersistence;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;

/**
 *
 * @author f.marroquin10
 */

public class UsuarioLogic {

    @Inject
    private UsuarioPersistence persistence; // Variable para acceder a la persistencia de la aplicación. Es una inyección de dependencias.

    private static final Logger LOGGER = Logger.getLogger(UsuarioLogic.class.getName());

    /**
     * Se encarga de crear un Usuario en la base de datos.
     *
     * @param entity Objeto de UsuarioEntity con los datos nuevos
     * @return Objeto de UsuarioEntity con los datos nuevos y su ID.
     */
    public UsuarioEntity createUsuario(UsuarioEntity entity) {
        LOGGER.log(Level.INFO, "Inicia proceso de crear un Usuario ");

        UsuarioEntity buscado = persistence.find(entity.getId());
        if (buscado != null) {
            LOGGER.log(Level.INFO, "El Usuario con el id {0} ya existe ", entity.getId());
        } else if(entity.getNombre()==null||entity.getUserName()==null||null==entity.getId()||0==entity.getEdad()||entity.getCorreo()==null)
        { LOGGER.log(Level.INFO, "El usuario tiene atributos nulos");
        }else if (!entity.getNombre().matches("([A-Z]|[a-z]|\\s)+")) {
            LOGGER.log(Level.INFO, "El nombre del Usuario no puede contener caracteres especiales");
        } else if (entity.getUserName().length() < 7 || entity.getUserName().length() > 15) {
            LOGGER.log(Level.INFO, "El nombre del Usuario no puede tener menos de 7 caracteres o mas de 15");

        } else if (entity.getEdad() < 17) {
            LOGGER.log(Level.INFO, "La edad del Usuario no puede ser menor a 17");

        } else {
            return persistence.create(entity);
        }

        return null;
    }

    /**
     * Obtiene la lista de los registros de Usuario.
     *
     * @return Colección de objetos de UsuarioEntity.
     */
    public List<UsuarioEntity> getUsuarios() {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar todos los Usuarioes");
        return persistence.findAll();
    }

    /**
     * Obtiene los datos de una instancia de Usuario a partir de su ID.
     *
     * @param id Identificador de la instancia a consultar
     * @return Instancia de UsuarioEntity con los datos del Usuario consultado.
     */
    public UsuarioEntity getUsuario(Long id) {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar un Usuario con id = {0}", id);
        return persistence.find(id);
    }

    /**
     * Actualiza la información de una instancia de Usuario.
     *
     * @param entity Instancia de UsuarioEntity con los nuevos datos.
     * @return Instancia de AuthorEntity con los datos actualizados.
     */
    public UsuarioEntity updateUsuario(UsuarioEntity entity) {
        LOGGER.log(Level.INFO, "Inicia proceso de actualizar un Usuario ");

        UsuarioEntity buscado = persistence.find(entity.getId());
        if (buscado != null) {
            LOGGER.log(Level.INFO, "El Usuario con el id {0} no existe ", entity.getId());
        } else if (!entity.getNombre().matches("([A-Z]|[a-z]|\\s)+")) {
            LOGGER.log(Level.INFO, "El nombre del Usuario no puede contener caracteres especiales");
        } else {
            return persistence.update(entity);
        }

        return null;
    }

    /**
     * Elimina una instancia de Usuario de la base de datos.
     *
     * @param id Identificador de la instancia a eliminar.
     */
    public void deleteUsuario(Long id) {
        LOGGER.log(Level.INFO, "Inicia proceso de borrar un Usuario ");

        UsuarioEntity buscado = persistence.find(id);
        if (buscado != null) {
            LOGGER.log(Level.INFO, "El Usuario con el id {0} no existe ", id);
        } else {
            persistence.delete(id);
        }
    }

}
