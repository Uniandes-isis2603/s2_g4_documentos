/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.documentos.ejb;

import co.edu.uniandes.csw.documentos.entities.ReservaEntity;
import co.edu.uniandes.csw.documentos.persistence.ReservaPersistence;
import java.util.Date;
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
public class ReservaLogic {

    private static final Logger LOGGER = Logger.getLogger(ReservaLogic.class.getName());

    @Inject
    private ReservaPersistence persistence;

    /**
     * Se encarga de crear una Reserva en la base de datos.
     *
     * @param entity Objeto de ReservaEntity con los datos nuevos
     * @return Objeto de ReservaEntity con los datos nuevos y su ID.
     */
    public ReservaEntity createReserva(ReservaEntity entity) {
        LOGGER.log(Level.INFO, "Inicia proceso de crear una Reserva ");

        ReservaEntity buscado = persistence.find(entity.getId());
        if (buscado != null) {
            LOGGER.log(Level.INFO, "La Reserva con el id {0} ya existe ", entity.getId());
        } else if (entity.getId() == null || entity.getFecha() == null ) {
            LOGGER.log(Level.INFO, "los atributos son nulos o invalidos");
        } else if (entity.getCosto() < 0) {
            LOGGER.log(Level.INFO, "La Reserva tiene un valor menor a cero");
        } else if (entity.getFecha().before(new Date())) {
            LOGGER.log(Level.INFO, "la fecha de la reserva es anterior a la fecha actual");

        } else {
            return persistence.create(entity);
        }

        return null;
    }

    /**
     * Obtiene la lista de los registros de Reservaes de un Documento dado.
     *
     * @return Colección de objetos de ReservaEntity.
     */
    public List<ReservaEntity> getReservaes() {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar todas las Reservaes");
        return persistence.findAll();
    }

    /**
     * Obtiene los datos de una instancia de un Reserva a partir de su ID.
     *
     * @param id Identificador de la instancia a consultar
     * @return Instancia de ReservaEntity con los datos de la Reserva
     * consultada.
     */
    public ReservaEntity getReserva(Long id) {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar una Reserva con id = {0}", id);
        return persistence.find(id);
    }

    /**
     * Actualiza la información de una instancia de Reserva.
     *
     * @param entity Instancia de ReservaEntity con los nuevos datos.
     * @return Instancia de ReservaEntity con los datos actualizados.
     */
    public ReservaEntity updateReserva(ReservaEntity entity) {
        LOGGER.log(Level.INFO, "Inicia proceso de actualizar una Reserva ");

        ReservaEntity buscado = persistence.find(entity.getId());
        if (buscado == null) {
            LOGGER.log(Level.INFO, "La Reserva con el id {0} no existe ", entity.getId());
        } else if (entity.getId() == null || entity.getFecha() == null || entity.getCosto() == 0) {
            LOGGER.log(Level.INFO, "los atributos son nulos o invalidos");
        } else if (entity.getCosto() < 0) {
            LOGGER.log(Level.INFO, "La Reserva tiene un valor menor a cero");
        } else if (entity.getFecha().before(new Date())) {
            LOGGER.log(Level.INFO, "la fecha de la reserva es anterior a la fecha actual");

        } else {
            return persistence.update(entity);
        }

        return null;
    }

    /**
     * Elimina una instancia de Reserva de la base de datos.
     *
     * @param id Identificador de la instancia a eliminar.
     */
    public void deleteReserva(Long id) {
        LOGGER.log(Level.INFO, "Inicia proceso de borrar un reserva ");

        ReservaEntity buscado = persistence.find(id);
        if (buscado == null) {
            LOGGER.log(Level.INFO, "la Reserva con el id {0} no existe ", id);
        } else {
            persistence.delete(id);
        }
    }

}
