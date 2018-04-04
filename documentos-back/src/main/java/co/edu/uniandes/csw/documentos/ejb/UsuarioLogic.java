package co.edu.uniandes.csw.documentos.ejb;

import co.edu.uniandes.csw.documentos.entities.UsuarioEntity;
import co.edu.uniandes.csw.documentos.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.documentos.persistence.UsuarioPersistence;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author f.marroquin10
 */
@Stateless
public class UsuarioLogic {

    @Inject
    private UsuarioPersistence persistence; // Variable para acceder a la persistencia de la aplicación. Es una inyección de dependencias.

    private static final Logger LOGGER = Logger.getLogger(UsuarioLogic.class.getName());

    /**
     * Se encarga de crear un Usuario en la base de datos.
     *
     * @param entity Objeto de UsuarioEntity con los datos nuevos
     * @return Objeto de UsuarioEntity con los datos nuevos y su ID.
     * @throws co.edu.uniandes.csw.documentos.exceptions.BusinessLogicException
     */
    public UsuarioEntity createUsuario(UsuarioEntity entity) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de crear un Usuario ");

        UsuarioEntity buscado = persistence.find(entity.getId());
        if (buscado != null) {
            LOGGER.log(Level.INFO, "El Usuario con el id {0} ya existe ", entity.getId());
            throw new BusinessLogicException("El Usuario con el id ya existe");

        } else if (entity.getNombreUsuario() == null) {
            LOGGER.log(Level.INFO, "El usuario tiene atributos nulos");
            throw new BusinessLogicException("El usuario tiene el nombre de usuario nulo");
        } else if (null == entity.getId()) {
            LOGGER.log(Level.INFO, "El usuario tiene atributos nulos");
            throw new BusinessLogicException("El usuario tiene el id nulo");
        } else if (0 == entity.getEdad()) {
            LOGGER.log(Level.INFO, "El usuario tiene atributos nulos");
            throw new BusinessLogicException("El usuario tiene la edad igual a cero");
        } else if (entity.getCorreo() == null) {

            LOGGER.log(Level.INFO, "El usuario tiene atributos nulos");
            throw new BusinessLogicException("El usuario tiene el correo nulo");
        } else if (!entity.getNombre().matches("([A-Z]|[a-z]|\\s)+")) {
            LOGGER.log(Level.INFO, "El nombre del Usuario no puede contener caracteres especiales");
            throw new BusinessLogicException("El nombre del Usuario no puede contener caracteres especiales");

        } else if (entity.getNombreUsuario().length() < 7 || entity.getNombreUsuario().length() > 15) {
            LOGGER.log(Level.INFO, "El nombre del Usuario no puede tener menos de 7 caracteres o mas de 15");
            throw new BusinessLogicException("El nombre del Usuario no puede tener menos de 7 caracteres o mas de 15");

        } else if (entity.getEdad() < 17) {
            LOGGER.log(Level.INFO, "La edad del Usuario no puede ser menor a 17");
            throw new BusinessLogicException("La edad del Usuario no puede ser menor a 17");

        } else if (entity.getGenero() < 0 || entity.getGenero() > 1) {
            LOGGER.log(Level.INFO, "el genero debe ser entre 1 y 0");
            throw new BusinessLogicException("el genero debe ser entre 1 y 0");

        } else if (!entity.getCorreo().contains("@")) {
            LOGGER.log(Level.INFO, "el correo no es valido");
            throw new BusinessLogicException("el correo no es valido");

        } else if (entity.getNombre() == null) {
            LOGGER.log(Level.INFO, "El usuario tiene atributos nulos");
            throw new BusinessLogicException("El usuario tiene ael nombre nulo");

        } else {
            return persistence.create(entity);
        }

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
    public UsuarioEntity getUsuario(Long id) throws BusinessLogicException {

        LOGGER.log(Level.INFO, "Inicia proceso de consultar un Usuario con id = {0}", id);
        if (id == null) {
            LOGGER.log(Level.INFO, "el id no puede ser nulo");
            throw new BusinessLogicException("el id no puede ser nulo");

        }
        UsuarioEntity usuario = persistence.find(id);
        if (usuario == null) {
            throw new BusinessLogicException("el usuario no existe");
        }

        return persistence.find(id);

    }

    /**
     * Obtiene los datos de una instancia de Usuario a partir de su ID.
     *
     * @param nombre nombre de usuario de la instancia a consultar
     * @return Instancia de UsuarioEntity con los datos del Usuario consultado.
     */
    public UsuarioEntity getUsuarioByName(String nombre) throws BusinessLogicException {

        LOGGER.log(Level.INFO, "Inicia proceso de consultar un Usuario con nombre= ", nombre);
        if (nombre == null || nombre == "") {
            LOGGER.log(Level.INFO, "el nombre no puede ser nulo o vacio");
            throw new BusinessLogicException("el nombre no puede ser nulo o vacio");

        }
        List<UsuarioEntity> users = getUsuarios();
        UsuarioEntity us = null;
        for (UsuarioEntity usuario : users) {
            if (usuario.getNombreUsuario().equals(nombre)) {
                us = usuario;
                break;
            }
        }
        if (us == null) {
            LOGGER.log(Level.INFO, "no se encontro usuario con el nombre dado");
            throw new BusinessLogicException("no se encontro usuario con el nombre dado");
        }

        return us;
    }

    /**
     * Actualiza la información de una instancia de Usuario.
     *
     * @param id
     * @param entity Instancia de UsuarioEntity con los nuevos datos.
     * @return Instancia de AuthorEntity con los datos actualizados.
     * @throws co.edu.uniandes.csw.documentos.exceptions.BusinessLogicException
     */
    public UsuarioEntity updateUsuario(Long id, UsuarioEntity entity) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de actualizar un Usuario ");

        UsuarioEntity buscado = persistence.find(id);
        if (buscado == null) {
            LOGGER.log(Level.INFO, "El Usuario con el id {0} no existe ", entity.getId());
            throw new BusinessLogicException("El Usuario con el id noexiste");

        } else if (entity.getNombre() == null || entity.getNombreUsuario() == null || null == entity.getId() || 0 == entity.getEdad() || entity.getCorreo() == null) {
            LOGGER.log(Level.INFO, "El usuario tiene atributos nulos");
            throw new BusinessLogicException("El usuario tiene atributos nulos");

        } else if (!entity.getNombre().matches("([A-Z]|[a-z]|\\s)+")) {
            LOGGER.log(Level.INFO, "El nombre del Usuario no puede contener caracteres especiales");
            throw new BusinessLogicException("El nombre del Usuario no puede contener caracteres especiales");

        } else if (entity.getNombreUsuario().length() < 7 || entity.getNombreUsuario().length() > 15) {
            LOGGER.log(Level.INFO, "El nombre del Usuario no puede tener menos de 7 caracteres o mas de 15");
            throw new BusinessLogicException("El nombre del Usuario no puede tener menos de 7 caracteres o mas de 15");

        } else if (entity.getEdad() < 17) {
            LOGGER.log(Level.INFO, "La edad del Usuario no puede ser menor a 17");
            throw new BusinessLogicException("La edad del Usuario no puede ser menor a 17");

        } else if (entity.getGenero() < 0 || entity.getGenero() > 1) {
            LOGGER.log(Level.INFO, "el genero debe ser entre 1 y 0");
            throw new BusinessLogicException("el genero debe ser entre 1 y 0");

        } else if (!entity.getCorreo().contains("@")) {
            LOGGER.log(Level.INFO, "el correo no es valido");
            throw new BusinessLogicException("el correo no es valido");

        } else {
            return persistence.update(entity);
        }
    }

    /**
     * Elimina una instancia de Usuario de la base de datos.
     *
     * @param id Identificador de la instancia a eliminar.
     * @throws co.edu.uniandes.csw.documentos.exceptions.BusinessLogicException
     */
    public void deleteUsuario(Long id) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de borrar un Usuario ");

           if (id == null) {
            throw new BusinessLogicException("el usuario con el id dado no existe " + id);
        }

        UsuarioEntity buscado = persistence.find(id);
        if (buscado == null) {
            LOGGER.log(Level.INFO, "el usuario con el id {0} no existe ", id);
            throw new BusinessLogicException("el usuario con el id dado no existe " + id);

        } else {
            persistence.delete(id);
        }
        persistence.delete(id);

    }

}
