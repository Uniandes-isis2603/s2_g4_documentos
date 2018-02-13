/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.documentos.resources;

import co.edu.uniandes.csw.documentos.dtos.ComentarioDTO;
import co.edu.uniandes.csw.documentos.exceptions.BusinessLogicException;
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
 *   <pre> Clase que implementa el recurso "Comentario".
 * URL: /api/comentarios
 * </pre>
 * <i> Note que la aplicación (definida en {@link RestConfig}) define la ruta "/api" y
 * este recurso tiene la ruta "libros". </i>
 * 
 * <h2> Anotaciones </h2>
 * <pre> 
 * Path: indica la direccion despues de "api" para acceder al recurso
 * Produces/Consumes: indica que los servicios definidos en este recurso reciben y devuelven objetos en formato JSON
 * RequestScoped: Inicia una transaccion desde el llamado de cada metodo (servicio).
 * </pre>
 *
 * @author n.sotelo
 */@Path("comentarios")
@Produces ("application/json")
@Consumes("application/json")
@RequestScoped
public class ComentarioResource {    
     /**
     * <h1> POST /api/comentarios : crear un nuevo comentario. </h1>
     * <pre> Cuerpo de peticion : JSON {ComentarioDTO}
     * 
     * Crea un nuevo comentario que se recibe en el el cuerpo 
     * de la petición y se regresa un objeto identico con un id auto-generado 
     * por la base de datos.
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Creó un nuevo comentario .
     * </code>
     * <code style="color: #c7254e; background-color: #f9f2f4;">
     * 412 Precodition Failed: Ya existe el comentario .
     * </code>
     * </pre>
     * 
     * @param Comentario {@link ComentarioDetailDTO} - El nuevo Comentario
     * @return JSON {@link ComentarioDetailDTO}  -El Comentario guardado 
     * @throws BusinessLogicException
     */
    @POST
    public ComentarioDTO createComentario(ComentarioDTO Comentario ) throws BusinessLogicException
    {
        return Comentario;
    }
    
     /**
     * <h1> GET /api/comentarios : encuentra todos los Comentarios de la tienda</h1>
     * <pre> Busca y devuelve todas los comentarios de la aplicacion
     * Codigos de respuesta:
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Devuelve los comentarios de la aplicacion.
     * </code> 
     *
     * </pre>
     * 
     * @return JSONArray {@link AutorDetailDTO} - Los comentarios  encontrados dentro de la aplicación. Si no existen se retorna una lista vacía.
     */
    @GET
    public List<ComentarioDTO> getComentarios()
    {
        return new ArrayList<>();
    }
    
    /**
     * <h1> GET /api/comentarios/{id} : encuentra una comentario , el cual esta identificada por un id </h1>
     * 
     * <pre> Encuentra un comentario identificada por un ID unico .
     * * Codigos de respuesta:
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Devuelve el Comentario correspondiente al id.
     * </code> 
     * <code style="color: #c7254e; background-color: #f9f2f4;">
     * 404 Not Found No existe un Comentario con el id dado.
     * </code>
     * </pre>
     * @param id Identificador del comentario que se busca,
     * @return JSON {@lnk ComentarioDeitailedDTO} - El Comentario
     */
    @GET
    @Path("{id: \\d+}")
    public ComentarioDTO getComentario(@PathParam("id") Long id)
    {
      return null;
    }
    
     /**
     * <h1> PUT /api/comentarios/{id} : actualiza un comentario </h1>
     * <pre> cuerpo de peticion : JSON.
     * 
     * Actuliza la informacion de uncomentario identificado con el id, con la
     * informacion en el cuerpo de peticion
     * 
     * Codigos de respuesta:
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Actualiza el comentario con el id dado con la información enviada como parámetro.</code> 
     * <code style="color: #c7254e; background-color: #f9f2f4;">
     * 404 Not Found. No existe un comentario con el id dado.
     * </code> 
     * 
     * </pre>
     * @param id Identificador del comentario que se desea actualizar.
     * @param Comentario El comentario que se desea actualizar.
     * @return JSON - El Comentario guardada
     * @throws BusinessLogicException.
     */
    @PUT
    @Path("{id: \\d+}")
    public ComentarioDTO updateComentario (@PathParam("id)") Long id, ComentarioDTO Comentario) throws BusinessLogicException
    {
        return Comentario;
    }
    
    /**
     * <h1> DELETE /api/comentarios/{id} : elimina un comentario </h1>
     * <pre> Borra la Comentario identificado con un id unico
     * pasado por parametro.
     * 
     * Códigos de respuesta:<br>
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 se elimino el comentario correspondiente al id dado.</code>
     * <code style="color: #c7254e; background-color: #f9f2f4;">
     * 404 Not Found. No existe una Comentario con el id dado.
     * </code>
     * </pre>
     * @param id Identificador unico de la Comentario que se desea borrar.
     */
    @DELETE
    @Path("{id: \\d+}")
    public void deleteComentario (@PathParam("id") Long id)
    {
        // Void
    }
}


    


    

