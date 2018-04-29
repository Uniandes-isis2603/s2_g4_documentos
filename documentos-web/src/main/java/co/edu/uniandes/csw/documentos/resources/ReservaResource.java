/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.documentos.resources;

import co.edu.uniandes.csw.documentos.dtos.ReservaDetailedDTO;
import co.edu.uniandes.csw.documentos.dtos.UsuarioDetailedDTO;
import co.edu.uniandes.csw.documentos.ejb.ReservaLogic;
import co.edu.uniandes.csw.documentos.ejb.UsuarioLogic;
import co.edu.uniandes.csw.documentos.entities.ReservaEntity;
import co.edu.uniandes.csw.documentos.entities.UsuarioEntity;
import co.edu.uniandes.csw.documentos.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.documentos.persistence.UsuarioPersistence;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;

/**
 * <pre> Clase que implemente el recurso "Reserva".
 * URL: /api/reservas
 * </pre>
 * <i> Note que la aplicación (definida en {@link RestConfig}) define la ruta
 * "/api" y este recurso tiene la ruta "reservas". </i>
 *
 * <h2> Anotaciones </h2>
 * <pre>
 * Path: indica la direccion despues de "api" para acceder al recurso
 * Produces/Consumes: indica que los servicios definidos en este recurso reciben y devuelven objetos en formato JSON
 * RequestScoped: Inicia una transaccion desde el llamado de cada metodo (servicio).
 * </pre>
 *
 * @author federico
 */
@Path("usuarios/{usuarioId: \\d+}/reservas")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class ReservaResource {

    @Inject
    ReservaLogic reservaLogica;

    @Inject
    UsuarioLogic usuarioLogic;
    
        @Inject
    UsuarioPersistence usuarioP;

    private List<ReservaDetailedDTO> listReservaEntity2DetailDTO(List<ReservaEntity> entityList) {
        List<ReservaDetailedDTO> list = new ArrayList<>();
        for (ReservaEntity entity : entityList) {
            list.add(new ReservaDetailedDTO(entity));
        }
        return list;
    }

    /**
     * <h1> POST /api/reservas : Crear un reserva. </h1>
     *
     * <pre> Cuerpo de peticion: JSON (@link ReservaDetailedDTO}.
     *
     * Crea una nueva reserva con la informacion que se recibe en el cuerpo
     * de la peticion y se regresa un objeto identico con un id auto-generado
     * por la base de datos.
     *
     * Codigos de respuesta:
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Creo el nuevo reserva.
     * </code>
     * <code style="color: #c7254e; background-color: #f9f2f4;">
     * 412 Precodition Failed: Ya existe la reserva.
     * </code>
     * </pre>
     *
     * @param idUser
     * @param reserva {@link ReservaDetailedDTO} - la reserva que se desea
     * guardar.
     * @return JSON {@link ReservaDetailedDTO} - la reserva guardado con el id
     * generado.
     */
    @POST
    public ReservaDetailedDTO createReserva(@PathParam("usuarioId") Long idUser, ReservaDetailedDTO reserva) throws BusinessLogicException {
        UsuarioEntity usuario = null;
        try {
            usuario = usuarioLogic.getUsuario(idUser);

        } catch (BusinessLogicException ex) {
            throw new BusinessLogicException("el usuario al que le quiere agregar el recurso no existe");

        }
        reserva.setUsuario(new UsuarioDetailedDTO(usuario));
        usuario.getReservas().add(reserva.toEntity());
        usuarioLogic.updateUsuario(usuario.getId(), usuario);
        return new ReservaDetailedDTO(reservaLogica.createReserva(reserva.toEntity()));

    }

    /**
     * <h1> GET /api/reservas : Obtener todas las reservas. </h1>
     *
     * <pre> Busca y devuelve todos los reservas que existen en la aplicacion.
     *
     * Codigos de respuesta:
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Devuelve todas las reservas de la aplicacion.</code>
     * </pre>
     *
     * @return JSONArray {@link  ReservaDetailedDTO} - Las reservas encontradas
     * en la aplicación de no haber ninguna retornar lista vacia.
     */
    @GET
    public List<ReservaDetailedDTO> getReservas(@PathParam("usuarioId") Long idUser) throws BusinessLogicException {
        List<ReservaDetailedDTO> lista = new ArrayList<>();
        lista = listReservaEntity2DetailDTO(usuarioLogic.getUsuario(idUser).getReservas());
        return lista;
    }

    /**
     * <h1> GET /api/Reservas/{id} : Obtener la reserva por id.</h1>
     *
     * <pre> Busca la reserva con el id asociado recibido en la URL y lo devuelve.
     *
     * Codigos de respuesta:
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Devuelve la reserva correspondiente al id.
     * </code>
     * <code style="color: #c7254e; background-color: #f9f2f4;">
     * 404 Not Found No existe una reserva con el id dado.
     * </code>
     * </pre>
     *
     * @param id Id de la reserva que se esta buscando. Debe ser una cadena de
     * digitos.
     * @return JSON {@link ReservaDetailedDTO} - la reserva buscada.
     */
    @GET
    @Path("{id: \\d+}")
    public ReservaDetailedDTO getReserva(@PathParam("usuarioId") Long idUser, @PathParam("id") Long id) throws BusinessLogicException {
        try {
            usuarioLogic.getUsuario(idUser);

        } catch (BusinessLogicException ex) {
            throw new BusinessLogicException("el usuario al que le quiere agregar el recurso no existe");

        }

        return new ReservaDetailedDTO(reservaLogica.getReserva(id));
    }

    /**
     * <h1> PUT /api/reservas/{id} : Actualizar la reserva con el id dado.</h1>
     * <pre> Cuerpo de peticion: JSON .
     *
     * Actualiza la reserva con el id recibido en la URL con la informacion que se recibe en el cuerpo de la peticion.
     *
     * Codigos de respuesta:
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Actualiza la reserva con el id dado con la informacion enviada como parametro. Retorna un objeto identico.</code>
     * <code style="color: #c7254e; background-color: #f9f2f4;">
     * 404 Not Found. No existe una reserva con el id dado.
     * </code>
     * </pre>
     *
     * @param id Id de la reserva que se desea actualizar.
     * @param reserva {@link ReservaDetailedDTO} la reserva que se desea
     * guardar.
     * @return reserva {@link ReservaDetailedDTO} la reserva guardado.
     */
    @PUT
    @Path("{id: \\d+}")
    public ReservaDetailedDTO updateReserva(@PathParam("usuarioId") Long idUser, @PathParam("id") Long id, ReservaDetailedDTO reserva) throws BusinessLogicException {
        try {
            usuarioLogic.getUsuario(idUser);

        } catch (BusinessLogicException ex) {
            throw new BusinessLogicException("el usuario al que le quiere agregar el recurso no existe");

        }
        reserva.setId(id);
        return new ReservaDetailedDTO(reservaLogica.updateReserva(reserva.toEntity()));

    }

    /**
     * <h1> DELETE /api/reservas/{id} : Eliminar reserva por id. </h1>
     *
     * <pre> Elimina reserva con el id asociado en la URL.
     *
     * Códigos de respuesta:<br>
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Elimina la reserva correspondiente al id dado.</code>
     * <code style="color: #c7254e; background-color: #f9f2f4;">
     * 404 Not Found. No existe la reserva con el id dado.
     * </code>
     * </pre>
     *
     * @param idUser
     * @param id Id de la reserva que se desea eliminar.
     * @throws co.edu.uniandes.csw.documentos.exceptions.BusinessLogicException
     */
    @DELETE
    @Path("{id: \\d+}")
    public void deleteReserva(@PathParam("usuarioId") Long idUser, @PathParam("id") Long id) throws BusinessLogicException {
        try {
            usuarioLogic.getUsuario(idUser);

        } catch (BusinessLogicException ex) {
            throw new BusinessLogicException("el usuario no existe");
        }
        reservaLogica.deleteReserva(id);

    }

}
