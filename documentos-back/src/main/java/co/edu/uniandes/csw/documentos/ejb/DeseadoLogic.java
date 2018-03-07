/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.documentos.ejb;

import co.edu.uniandes.csw.documentos.entities.DeseadoEntity;
import co.edu.uniandes.csw.documentos.persistence.DeseadoPersistence;
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
public class DeseadoLogic {

    private static final Logger LOGGER = Logger.getLogger(DeseadoLogic.class.getName());

    @Inject
    private DeseadoPersistence persistence;

    //@Inject
    //private DocumentoLogic DocumentoLogic;
    /**
     * Se encarga de crear una Deseado en la base de datos.
     *
     * @param entity Objeto de DeseadoEntity con los datos nuevos
     * @return Objeto de DeseadoEntity con los datos nuevos y su ID.
     */
    public DeseadoEntity createDeseado(DeseadoEntity entity) {
        LOGGER.log(Level.INFO, "Inicia proceso de crear un deseado ");

        DeseadoEntity buscado = persistence.find(entity.getId());
        if (buscado != null) {
            LOGGER.log(Level.INFO, "el Deseado con el id {0} ya existe ", entity.getId());
        } else if (entity.getNombre() == null || entity.getNombre().equals("") || entity.getNombre().length() > 15) {
            LOGGER.log(Level.INFO, "El nombre de la Deseado no puede ser nulo, vacio o tener mas de 15 caracteres");
        } else if (entity.getCantidad() < 0) {
            LOGGER.log(Level.INFO, "el Deseado no puede tener una cantidad menor a cero");

        } else {
            return persistence.create(entity);
        }

        return null;
    }

    /**
     * Obtiene la lista de los registros de Deseadoes de un Documento dado.
     *
     * @return Colección de objetos de DeseadoEntity.
     */
    public List<DeseadoEntity> getDeseadoes() {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar todas las Deseadoes");
        return persistence.findAll();
    }

    /**
     * Obtiene los datos de una instancia de un deseado a partir de su ID.
     *
     * @param id Identificador de la instancia a consultar
     * @return Instancia de DeseadoEntity con los datos de la Deseado
     * consultada.
     */
    public DeseadoEntity getDeseado(Long id) {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar una Deseado con id = {0}", id);
        return persistence.find(id);
    }

    /**
     * Actualiza la información de una instancia de Deseado.
     *
     * @param entity Instancia de DeseadoEntity con los nuevos datos.
     * @return Instancia de DeseadoEntity con los datos actualizados.
     */
    public DeseadoEntity updateDeseado(DeseadoEntity entity) {
        LOGGER.log(Level.INFO, "Inicia proceso de actualizar una Deseado ");

        DeseadoEntity buscado = persistence.find(entity.getId());
        if (buscado == null) {
            LOGGER.log(Level.INFO, "La Deseado con el id {0} no existe ", entity.getId());
        } else if (entity.getNombre() == null || entity.getNombre().equals("") || entity.getNombre().length() > 15) {
            LOGGER.log(Level.INFO, "El nombre de la Deseado no puede ser nulo, vacio o tener mas de 15 caracteres");
        }else if(entity.getCantidad()<0){
                        LOGGER.log(Level.INFO, "El deseado no puede tener una cantidad negativa de documentos");

        }
        else {
            return persistence.update(entity);
        }

        return null;
    }

    /**
     * Elimina una instancia de Deseado de la base de datos.
     *
     * @param id Identificador de la instancia a eliminar.
     */
    public void deleteDeseado(Long id) {
        LOGGER.log(Level.INFO, "Inicia proceso de borrar un deseado ");

        DeseadoEntity buscado = persistence.find(id);
        if (buscado == null) {
            LOGGER.log(Level.INFO, "el deseado con el id {0} no existe ", id);
        } else {
            persistence.delete(id);
        }
    }

}
