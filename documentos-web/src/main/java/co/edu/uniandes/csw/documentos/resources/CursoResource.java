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
@Path("Curso")
@Produces ("application/json")
@Consumes("application/json")
@RequestScoped
public class CursoResource {
    
    
 


    
     /**
     * <h1> POST /api/Curso : crear una nueva Curso. </h1>
     * <pre> Cuerpo de peticion : JSON {CursoDetailedDTO}
     * 
     * Crea una nueva Curso que se recibe en el el cuerpo 
     * de la petición y se regresa un objeto identico con un id auto-generado 
     * por la base de datos.
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Creó una nueva Curso .
     * </code>
     * <code style="color: #c7254e; background-color: #f9f2f4;">
     * 412 Precodition Failed: Ya existe la Curso .
     * </code>
     * </pre>
     * 
     * @param Curso {@link CursoDetailDTO} - La nueva Curso
     * @return JSON {@link CursoDetailDTO}  -La Curso guardada con el atributo id autogenerado.
     * @throws BusinessLogicException
     */
    @POST
    public CursoDetailedDTO createCurso(CursoDetailedDTO Curso ) throws BusinessLogicException
    {
        return Curso;
    }
    
     /**
     * <h1> GET /api/Cursos : encuentra todas los Cursos de la tienda</h1>
     * <pre> Busca y devuelve todas las Cursoes de la aplicacion
     * Codigos de respuesta:
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Devuelve las Cursoes de la aplicacion.
     * </code> 
     *
     * </pre>
     * 
     * @return JSONArray {@link AutorDetailDTO} - Las Cursoes  encontradas en la aplicación. Si no hay ninguna retorna una lista vacía.
     */
    @GET
    public List<CursoDetailedDTO> getCursos()
    {
        return new ArrayList<>();
    }
    
    /**
     * <h1> GET /api/Curso/{id} : encuentra una Curso , la cual esta identificada por un id </h1>
     * 
     * <pre> Encuentra una Curso identificada por un ID unico recibido en la URL y la devuelve.
     * * Codigos de respuesta:
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Devuelve la Curso correspondiente al id.
     * </code> 
     * <code style="color: #c7254e; background-color: #f9f2f4;">
     * 404 Not Found No existe una Curso con el id dado.
     * </code>
     * </pre>
     * @param id Identificador de la eidtorial que se busca,
     * @return JSON {@lnk CursoDeitailedDTO} - La Curso
     */
    @GET
    @Path("{id: \\d+}")
    public CursoDetailedDTO getCurso(@PathParam("id") Long id)
    {
      return null;
    }
    
     /**
     * <h1> PUT /api/Curso/{id} : actualiza una Curso </h1>
     * <pre> cuerpo de peticion : JSON.
     * 
     * Actuliza la tarjetaDeCredito identificada con el identificador, con la
     * informacion en el cuerpo de peticion.
     * 
     * Codigos de respuesta:
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Actualiza la Curso con el id dado con la información enviada como parámetro. Retorna un objeto identico.</code> 
     * <code style="color: #c7254e; background-color: #f9f2f4;">
     * 404 Not Found. No existe una TDC con el id dado.
     * </code> 
     * 
     * </pre>
     * @param id IDentificador de la Curso que se desea actualizar representado
     * como una cadena de digitos.
     * @param Curso La tarjetaDeCredito que se desea actualizar.
     * @return JSON - La Curso guardada
     * @throws BusinessLogicException.
     */
    @PUT
    @Path("{id: \\d+}")
    public CursoDetailedDTO updateCurso (@PathParam("id)") Long id, CursoDetailedDTO Curso) throws BusinessLogicException
    {
        return Curso;
    }
    
    /**
     * <h1> DELETE /api/Curso/{id} : elimina una eidtorial </h1>
     * <pre> Borra la Curso identificado con un id unico
     * pasado por parametro.
     * 
     * Códigos de respuesta:<br>
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Elimina la Curso correspondiente al id dado.</code>
     * <code style="color: #c7254e; background-color: #f9f2f4;">
     * 404 Not Found. No existe una Curso con el id dado.
     * </code>
     * </pre>
     * @param id Identificador unico de la Curso que se desea borrar.
     */
    @DELETE
    @Path("{id: \\d+}")
    public void deleteCurso (@PathParam("id") Long id)
    {
        // Void
    }
}


    

