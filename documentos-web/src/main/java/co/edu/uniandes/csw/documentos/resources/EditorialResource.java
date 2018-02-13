/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.documentos.resources;

import co.edu.uniandes.csw.documentos.dtos.*;
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
 */
@Path("editorial")
@Produces ("application/json")
@Consumes("application/json")
@RequestScoped
public class EditorialResource {
    
     /**
     * <h1> POST /api/editorial : crear una nueva Editorial. </h1>
     * <pre> Cuerpo de peticion : JSON {EditorialDetailedDTO}
     * 
     * Crea una nueva editorial que se recibe en el el cuerpo 
     * de la petición y se regresa un objeto identico con un id auto-generado 
     * por la base de datos.
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Creó una nueva editorial .
     * </code>
     * <code style="color: #c7254e; background-color: #f9f2f4;">
     * 412 Precodition Failed: Ya existe la editorial .
     * </code>
     * </pre>
     * 
     * @param editorial {@link EditorialDetailDTO} - La nueva editorial
     * @return JSON {@link EditorialDetailDTO}  -La editorial guardada con el atributo id autogenerado.
     * @throws BusinessLogicException
     */
    @POST
    public EditorialDetailedDTO createEditorial(EditorialDetailedDTO editorial ) throws BusinessLogicException
    {
        return editorial;
    }
    
     /**
     * <h1> GET /api/editoriales : encuentra todas los editoriales de la tienda</h1>
     * <pre> Busca y devuelve todas las editoriales de la aplicacion
     * Codigos de respuesta:
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Devuelve las editoriales de la aplicacion.
     * </code> 
     *
     * </pre>
     * 
     * @return JSONArray {@link AutorDetailDTO} - Las editoriales  encontradas en la aplicación. Si no hay ninguna retorna una lista vacía.
     */
    @GET
    public List<EditorialDetailedDTO> getEditorial()
    {
        return new ArrayList<>();
    }
    
    /**
     * <h1> GET /api/editorial/{id} : encuentra una editorial , la cual esta identificada por un id </h1>
     * 
     * <pre> Encuentra una editorial identificada por un ID unico recibido en la URL y la devuelve.
     * * Codigos de respuesta:
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Devuelve la editorial correspondiente al id.
     * </code> 
     * <code style="color: #c7254e; background-color: #f9f2f4;">
     * 404 Not Found No existe una editorial con el id dado.
     * </code>
     * </pre>
     * @param id Identificador de la eidtorial que se busca,
     * @return JSON {@lnk EditorialDeitailedDTO} - La editorial
     */
    @GET
    @Path("{id: \\d+}")
    public EditorialDetailedDTO getEditorial(@PathParam("id") Long id)
    {
      return null;
    }
    
     /**
     * <h1> PUT /api/editorial/{id} : actualiza una editorial </h1>
     * <pre> cuerpo de peticion : JSON.
     * 
     * Actuliza la tarjetaDeCredito identificada con el identificador, con la
     * informacion en el cuerpo de peticion.
     * 
     * Codigos de respuesta:
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Actualiza la editorial con el id dado con la información enviada como parámetro. Retorna un objeto identico.</code> 
     * <code style="color: #c7254e; background-color: #f9f2f4;">
     * 404 Not Found. No existe una TDC con el id dado.
     * </code> 
     * 
     * </pre>
     * @param id IDentificador de la editorial que se desea actualizar representado
     * como una cadena de digitos.
     * @param editorial La tarjetaDeCredito que se desea actualizar.
     * @return JSON - La editorial guardada
     * @throws BusinessLogicException.
     */
    @PUT
    @Path("{id: \\d+}")
    public EditorialDetailedDTO updateEditorial (@PathParam("fid)") Long id, EditorialDetailedDTO editorial) throws BusinessLogicException
    {
        return editorial;
    }
    
    /**
     * <h1> DELETE /api/editorial/{id} : elimina una eidtorial </h1>
     * <pre> Borra la editorial identificado con un id unico
     * pasado por parametro.
     * 
     * Códigos de respuesta:<br>
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Elimina la editorial correspondiente al id dado.</code>
     * <code style="color: #c7254e; background-color: #f9f2f4;">
     * 404 Not Found. No existe una editorial con el id dado.
     * </code>
     * </pre>
     * @param id Identificador unico de la editorial que se desea borrar.
     */
    @DELETE
    @Path("{id: \\d+}")
    public void deleteEditorial (@PathParam("id") Long id)
    {
        // Void
    }
}

