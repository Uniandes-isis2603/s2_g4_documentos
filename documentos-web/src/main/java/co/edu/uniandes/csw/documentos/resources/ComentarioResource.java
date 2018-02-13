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
 *
 * @author n.sotelo
 */@Path("comentario")
@Produces ("application/json")
@Consumes("application/json")
@RequestScoped
public class ComentarioResource {
    
/**
 *
 * @author n.sotelo
 */


    
    
 


    
     /**
     * <h1> POST /api/comentario : crear un nuevO comentario. </h1>
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
     * @param Comentario {@link ComentarioDetailDTO} - La nueva Comentario
     * @return JSON {@link ComentarioDetailDTO}  -La Comentario guardada con el atributo id autogenerado.
     * @throws BusinessLogicException
     */
    @POST
    public ComentarioDTO createComentario(ComentarioDTO Comentario ) throws BusinessLogicException
    {
        return Comentario;
    }
    
     /**
     * <h1> GET /api/comentarios : encuentra todas los Comentarios de la tienda</h1>
     * <pre> Busca y devuelve todas las Comentarios de la aplicacion
     * Codigos de respuesta:
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Devuelve los Comentarios de la aplicacion.
     * </code> 
     *
     * </pre>
     * 
     * @return JSONArray {@link AutorDetailDTO} - Las Comentarios  encontradas en la aplicación. Si no hay ninguna retorna una lista vacía.
     */
    @GET
    public List<ComentarioDTO> getComentarios()
    {
        return new ArrayList<>();
    }
    
    /**
     * <h1> GET /api/comentario/{id} : encuentra una Comentario , la cual esta identificada por un id </h1>
     * 
     * <pre> Encuentra una Comentario identificada por un ID unico recibido en la URL y la devuelve.
     * * Codigos de respuesta:
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Devuelve la Comentario correspondiente al id.
     * </code> 
     * <code style="color: #c7254e; background-color: #f9f2f4;">
     * 404 Not Found No existe una Comentario con el id dado.
     * </code>
     * </pre>
     * @param id Identificador de la eidtorial que se busca,
     * @return JSON {@lnk ComentarioDeitailedDTO} - La Comentario
     */
    @GET
    @Path("{id: \\d+}")
    public ComentarioDTO getComentario(@PathParam("id") Long id)
    {
      return null;
    }
    
     /**
     * <h1> PUT /api/comentario/{id} : actualiza una Comentario </h1>
     * <pre> cuerpo de peticion : JSON.
     * 
     * Actuliza la tarjetaDeCredito identificada con el identificador, con la
     * informacion en el cuerpo de peticion.
     * 
     * Codigos de respuesta:
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Actualiza la Comentario con el id dado con la información enviada como parámetro. Retorna un objeto identico.</code> 
     * <code style="color: #c7254e; background-color: #f9f2f4;">
     * 404 Not Found. No existe un comentario con el id dado.
     * </code> 
     * 
     * </pre>
     * @param id IDentificador de la Comentario que se desea actualizar representado
     * como una cadena de digitos.
     * @param Comentario La tarjetaDeCredito que se desea actualizar.
     * @return JSON - La Comentario guardada
     * @throws BusinessLogicException.
     */
    @PUT
    @Path("{id: \\d+}")
    public ComentarioDTO updateComentario (@PathParam("id)") Long id, ComentarioDTO Comentario) throws BusinessLogicException
    {
        return Comentario;
    }
    
    /**
     * <h1> DELETE /api/comentario/{id} : elimina una eidtorial </h1>
     * <pre> Borra la Comentario identificado con un id unico
     * pasado por parametro.
     * 
     * Códigos de respuesta:<br>
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Elimina la Comentario correspondiente al id dado.</code>
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


    


    

