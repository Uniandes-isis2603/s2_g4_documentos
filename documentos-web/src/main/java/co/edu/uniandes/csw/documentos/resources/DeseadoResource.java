/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.documentos.resources;


import co.edu.uniandes.csw.documentos.dtos.ReservaDetailedDTO;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

/**
 * <pre> Clase que implemente el recurso "Deseado".
 * URL: /api/deseados
 * </pre>
 * <i> Note que la aplicación (definida en {@link RestConfig}) define la ruta "/api" y
 * este recurso tiene la ruta "deseados". </i>
 * 
 * <h2> Anotaciones </h2>
 * <pre> 
 * Path: indica la direccion despues de "api" para acceder al recurso
 * Produces/Consumes: indica que los servicios definidos en este recurso reciben y devuelven objetos en formato JSON
 * RequestScoped: Inicia una transaccion desde el llamado de cada metodo (servicio).
 * </pre>
 * @author federico
 */
@Path("deseados")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class DeseadoResource {
    
        
    /**
     * <h1> POST /api/deseados : Crear un objeto deseado. </h1>
     * 
     * <pre> Cuerpo de peticion: JSON (@link ReservaDetailedDTO}.
     * 
     * Crea una nueva reserva con la informacion que se recibe en el cuerpo
     * de la peticion y se regresa un objeto identico con un id auto-generado
     * por la base de datos.
     * 
     * Codigos de respuesta:
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Creo el nuevo usuario.
     * </code>
     * <code style="color: #c7254e; background-color: #f9f2f4;">
     * 412 Precodition Failed: Ya existe el usuario.
     * </code>
     * </pre>
     * @param reserva {@link ReservaDetailedDTO} - la reserva que se desea guardar.
     * @return JSON {@link ReservaDetailedDTO} - la reserva guardado con el id generado.
     */
    @POST
    public ReservaDetailedDTO createReserva(ReservaDetailedDTO reserva) {
        return reserva;
    }
    
    /**
     * <h1> GET /api/reservas : Obtener todas las reservas. </h1>
     * 
     * <pre> Busca y devuelve todos los Usuarios que existen en la aplicacion.
     * 
     * Codigos de respuesta:
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Devuelve todas las reservas de la aplicacion.</code>
     * </pre>
     * @return JSONArray {@link  ReservaDetailedDTO} - Las reservas encontradas en la aplicación
     * de no haber ninguna retornar lista vacia.
     */
    @GET
    public List<ReservaDetailedDTO> getReservas() {
        return new ArrayList<>();
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
     * @param id Id de la reserva que se esta buscando. Debe ser una cadena de digitos.
     * @return JSON {@link ReservaDetailedDTO} - la  reserva buscada.
     */
    @GET
    @Path("{id: \\d+}")
    public ReservaDetailedDTO getReserva(@PathParam("id") Long id) {
        return null;
    }
    
   
    
    /**
     * <h1> PUT /api/reservas/{id} : Actualizar la reserva con el id dado.</h1>
     * <pre> Cuerpo de peticion: JSON {@link UsuarioDetailedDTO}.
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
     * @param id Id de la reserva que se desea actualizar.
     * @param reserva {@link ReservaDetailedDTO} la  reserva que se desea guardar.
     * @return reserva {@link ReservaDetailedDTO} la reserva guardado.
     */
    @PUT
    @Path("{id: \\d+}")
    public ReservaDetailedDTO updateReserva(@PathParam("id") Long id, ReservaDetailedDTO reserva) {
        return reserva;
    }
    
    /**
     * <h1> DELETE /api/Usuarios/{id} : Eliminar reserva por id. </h1>
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
     * @param id Id de la  reserva que se desea eliminar.
     */
    @DELETE
    @Path("{id: \\d+}")
    public void deleteReserva(@PathParam("id") Long id){
        
    }
    
    
    
    
}
