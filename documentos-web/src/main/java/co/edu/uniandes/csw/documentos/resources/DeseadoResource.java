/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.documentos.resources;

import co.edu.uniandes.csw.documentos.dtos.DeseadoDetailedDTO;
import co.edu.uniandes.csw.documentos.dtos.DeseadoDetailedDTO;
import co.edu.uniandes.csw.documentos.ejb.DeseadoLogic;
import co.edu.uniandes.csw.documentos.ejb.UsuarioLogic;
import co.edu.uniandes.csw.documentos.entities.DeseadoEntity;
import co.edu.uniandes.csw.documentos.entities.UsuarioEntity;
import co.edu.uniandes.csw.documentos.exceptions.BusinessLogicException;
import java.util.ArrayList;
import java.util.List;
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
 * <pre> Clase que implemente el recurso "Deseado".
 * URL: /api/Deseados
 * </pre>
 * <i> Note que la aplicación (definida en {@link RestConfig}) define la ruta
 * "/api" y este recurso tiene la ruta "Deseados". </i>
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
@Path("usuarios/{usuarioId: \\d+}/deseados")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class DeseadoResource {

    @Inject
    DeseadoLogic DeseadoLogica;

    @Inject
    UsuarioLogic usuarioLogic;

    private List<DeseadoDetailedDTO> listDeseadoEntity2DetailDTO(List<DeseadoEntity> entityList) {
        List<DeseadoDetailedDTO> list = new ArrayList<>();
        for (DeseadoEntity entity : entityList) {
            list.add(new DeseadoDetailedDTO(entity));
        }
        return list;
    }

    /**
     * <h1> POST /api/deseados : Crear un Deseado. </h1>
     *
     * <pre> Cuerpo de peticion: JSON (@link DeseadoDetailedDTO}.
     *
     * Crea un nuevo Deseado con la informacion que se recibe en el cuerpo
     * de la peticion y se regresa un objeto identico con un id auto-generado
     * por la base de datos.
     *
     * Codigos de respuesta:
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Creo el nuevo Deseado.
     * </code>
     * <code style="color: #c7254e; background-color: #f9f2f4;">
     * 412 Precodition Failed: Ya existe el Deseado.
     * </code>
     * </pre>
     *
     * @param Deseado {@link DeseadoDetailedDTO} - el Deseado que se desea
     * guardar.
     * @return JSON {@link DeseadoDetailedDTO} - el Deseado guardado con el id
     * generado.
     */
    @POST
    public DeseadoDetailedDTO createDeseado(@PathParam("usuarioId") Long idUser, DeseadoDetailedDTO Deseado) throws BusinessLogicException {
        try {
            usuarioLogic.getUsuario(idUser);

        } catch (BusinessLogicException ex) {
            throw new BusinessLogicException("el usuario al que le quiere agregar el recurso no existe");

        }

        return new DeseadoDetailedDTO(DeseadoLogica.createDeseado(Deseado.toEntity()));
    }

    /**
     * <h1> GET /api/deseados : Obtener todas los Deseados. </h1>
     *
     * <pre> Busca y devuelve todos los Deseados que existen en la aplicacion.
     *
     * Codigos de respuesta:
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Devuelve todas las Deseados de la aplicacion.</code>
     * </pre>
     *
     * @return JSONArray {@link  DeseadoDetailedDTO} - Las Deseados encontradas
     * en la aplicación de no haber ninguna retornar lista vacia.
     */
    @GET
    public List<DeseadoDetailedDTO> getDeseados() {
        List<DeseadoDetailedDTO> lista = new ArrayList<>();
        lista = listDeseadoEntity2DetailDTO(DeseadoLogica.getDeseados());
        return lista;
    }

    /**
     * <h1> GET /api/Deseados/{id} : Obtener la Deseado por id.</h1>
     *
     * <pre> Busca la Deseado con el id asociado recibido en la URL y lo devuelve.
     *
     * Codigos de respuesta:
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Devuelve la Deseado correspondiente al id.
     * </code>
     * <code style="color: #c7254e; background-color: #f9f2f4;">
     * 404 Not Found No existe una Deseado con el id dado.
     * </code>
     * </pre>
     *
     * @param id Id de la Deseado que se esta buscando. Debe ser una cadena de
     * digitos.
     * @return JSON {@link DeseadoDetailedDTO} - la Deseado buscada.
     */
    @GET
    @Path("{id: \\d+}")
    public DeseadoDetailedDTO getDeseado(@PathParam("usuarioId") Long idUser, @PathParam("id") Long id) throws BusinessLogicException {
        try {
            usuarioLogic.getUsuario(idUser);

        } catch (BusinessLogicException ex) {
            throw new BusinessLogicException("el usuario al que le quiere agregar el recurso no existe");

        }
        return new DeseadoDetailedDTO(DeseadoLogica.getDeseado(id));
    }

    /**
     * <h1> PUT /api/Deseados/{id} : Actualizar la Deseado con el id dado.</h1>
     * <pre> Cuerpo de peticion: JSON .
     *
     * Actualiza la Deseado con el id recibido en la URL con la informacion que se recibe en el cuerpo de la peticion.
     *
     * Codigos de respuesta:
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Actualiza la Deseado con el id dado con la informacion enviada como parametro. Retorna un objeto identico.</code>
     * <code style="color: #c7254e; background-color: #f9f2f4;">
     * 404 Not Found. No existe una Deseado con el id dado.
     * </code>
     * </pre>
     *
     * @param id Id de la Deseado que se desea actualizar.
     * @param Deseado {@link DeseadoDetailedDTO} la Deseado que se desea
     * guardar.
     * @return Deseado {@link DeseadoDetailedDTO} la Deseado guardado.
     */
    @PUT
    @Path("{id: \\d+}")
    public DeseadoDetailedDTO updateDeseado(@PathParam("usuarioId") Long idUser, @PathParam("id") Long id, DeseadoDetailedDTO Deseado) throws BusinessLogicException {
        try {
            usuarioLogic.getUsuario(idUser);

        } catch (BusinessLogicException ex) {
            throw new WebApplicationException("el usuario al que le quiere agregar el recurso no existe");

        }
        Deseado.setId(id);
        return new DeseadoDetailedDTO(DeseadoLogica.updateDeseado(Deseado.toEntity()));
    }

    /**
     * <h1> DELETE /api/Deseados/{id} : Eliminar Deseado por id. </h1>
     *
     * <pre> Elimina Deseado con el id asociado en la URL.
     *
     * Códigos de respuesta:<br>
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Elimina la Deseado correspondiente al id dado.</code>
     * <code style="color: #c7254e; background-color: #f9f2f4;">
     * 404 Not Found. No existe la Deseado con el id dado.
     * </code>
     * </pre>
     *
     * @param id Id de la Deseado que se desea eliminar.
     */
    @DELETE
    @Path("{id: \\d+}")
    public void deleteDeseado(@PathParam("usuarioId") Long idUser, @PathParam("id") Long id) throws BusinessLogicException {
        try {
            usuarioLogic.getUsuario(idUser);

        } catch (BusinessLogicException ex) {
            throw new BusinessLogicException("el usuario al que le quiere agregar el recurso no existe");

        }
        DeseadoLogica.deleteDeseado(id);
    }

}
