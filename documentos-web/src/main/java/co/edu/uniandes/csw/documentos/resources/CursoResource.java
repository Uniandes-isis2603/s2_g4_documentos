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
 * <pre> Clase que implementa el recurso "Curso".
 * URL: /api/cursos
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
 */
@Path("cursos")
@Produces ("application/json")
@Consumes("application/json")
@RequestScoped
public class CursoResource {
    
    
 


    
     /**
     * <h1> POST /api/cursos : crear una nueva Curso. </h1>
     * <pre> Cuerpo de peticion : JSON {CursoDetailedDTO}
     * 
     * Crea un nuevo curso el cual se recibe en el  cuerpo 
     * de la petición y se regresa un objeto identico la cual es represntado por un atributo unico el nombre.
     * 
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Creó un nuevo curso .
     * </code>
     * <code style="color: #c7254e; background-color: #f9f2f4;">
     * 412 Precodition Failed: Ya existe el curso .
     * </code>
     * </pre>
     * 
     * @param Curso {@link CursoDetailDTO} - El curso nuevo para crear.
     * @return JSON {@link CursoDetailDTO}  -El curso guardado.
     * @throws BusinessLogicException
     */
    @POST
    public CursoDetailedDTO createCurso(CursoDetailedDTO Curso ) throws BusinessLogicException
    {
        return Curso;
    }
    
     /**
     * <h1> GET /api/cursos : Encuentra todos los cursos de la tienda</h1>
     * <pre> Busca y devuelve todOs las cursos de la aplicacion
     * Codigos de respuesta:
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Devuelve los cursos de la aplicacion.
     * </code>
     * </pre>
     * 
     * @return JSONArray {@link AutorDetailDTO} - Los cursos  encontradas dentro de la aplicación. Si no hay ninguno retorna una lista vacía.
     */
    @GET
    public List<CursoDetailedDTO> getCursos()
    {
        return new ArrayList<>();
    }
    
    /**
     * <h1> GET /api/cursos/{id} : encuentra un curso , el cual esta identificada por un id unico </h1>
     * 
     * <pre> Encuentra una curso identificado por un ID unico recibido en la URL y lo devuelve.
     * * Codigos de respuesta:
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Devuelve el curso correspondiente al id.
     * </code> 
     * <code style="color: #c7254e; background-color: #f9f2f4;">
     * 404 Not Found No existe un curso con el id dado.
     * </code>
     * </pre>
     * @param id Identificador de el que se busca,
     * @return JSON {@lnk CursoDeitailedDTO} - El curso
     */
    @GET
    @Path("{id: \\d+}")
    public CursoDetailedDTO getCurso(@PathParam("id") Long id)
    {
      return null;
    }
    
     /**
     * <h1> PUT /api/cursos/{id} : Actualiza un Curso </h1>
     * <pre> cuerpo de peticion : JSON.
     * 
     * Actuliza el curso identificado con el identificador(Nombre) , con la
     * informacion en el cuerpo de peticion.
     * 
     * Codigos de respuesta:
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Actualiza el Curso con el id dado con la información enviada como parámetro. Retorna un objeto identico.</code> 
     * <code style="color: #c7254e; background-color: #f9f2f4;">
     * 404 Not Found. No existe un curso con el id dado.
     * </code> 
     * 
     * </pre>
     * @param id Identificador del curso que se desea actualizar.
     * @param Curso La tarjetaDeCredito que se desea actualizar.
     * @return JSON - El curso guardado
     * @throws BusinessLogicException.
     */
    @PUT
    @Path("{id: \\d+}")
    public CursoDetailedDTO updateCurso (@PathParam("id)") Long id, CursoDetailedDTO Curso) throws BusinessLogicException
    {
        return Curso;
    }
    
    /**
     * <h1> DELETE /api/cursos/{id} : elimina un curso de la aplicacion </h1>
     * <pre> Borra el curso identificado con un id unico
     * pasado por parametro.
     * 
     * Códigos de respuesta:<br>
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Elimina el curso correspondiente al id dado.</code>
     * <code style="color: #c7254e; background-color: #f9f2f4;">
     * 404 Not Found. No existe un curso con el id dado.
     * </code>
     * </pre>
     * @param id Identificador unico del curso que se desea borrar.
     */
    @DELETE
    @Path("{id: \\d+}")
    public void deleteCurso (@PathParam("id") Long id)
    {
        // Void
    }
}


    

