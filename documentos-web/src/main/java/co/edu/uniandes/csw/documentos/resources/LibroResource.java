/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.documentos.resources;

import co.edu.uniandes.csw.documentos.dtos.LibroDetailDTO;
import co.edu.uniandes.csw.documentos.ejb.LibroLogic;
import co.edu.uniandes.csw.documentos.entities.LibroEntity;
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

/**
 * <pre> Clase que implemente el recurso "Libro".
 * URL: /api/libros
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
 * @author Ernesto Viana
 */
@Path("libros")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class LibroResource {
    
    @Inject
    LibroLogic libroLogic;
    /**
     * <h1> POST /api/libros : Crear un libro. </h1>
     * 
     * <pre> Cuerpo de peticion: JSON (@link LibroDetailDTO}.
     * 
     * Crea un nuevo libro con la informacion que se recibe en el cuerpo
     * de la peticion y se regresa un objeto identico con un id auto-generado
     * por la base de datos.
     * 
     * Codigos de respuesta:
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Creo el nuevo libro .
     * </code>
     * <code style="color: #c7254e; background-color: #f9f2f4;">
     * 412 Precodition Failed: Ya existe el libro.
     * </code>
     * </pre>
     * @throws BusinessLogicException desde la logica.
     * @param libro {@link LibroDetailDTO} - El libro que se desea guardar.
     * @return JSON {@link LibroDetailDTO} - El libro guardado con el id generado.
     */
    @POST
    public LibroDetailDTO createLibro(LibroDetailDTO libro) throws BusinessLogicException{
        return new LibroDetailDTO(libroLogic.createLibro(libro.toEntity()));
    }
    
    /**
     * <h1> GET /api/libros : Obtener todos los libros. </h1>
     * 
     * <pre> Busca y devuelve todos los libros que existen en la aplicacion.
     * 
     * 
     * Codigos de respuesta:
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Devuelve todos los libros de la aplicacion.</code>
     * </pre>
     * @return JSONArray {@link  LibroDetailDTO} - Los libros encontrados en la aplicación
     * de no haber ninguna retornar lista vacia.
     */
    @GET
    public List<LibroDetailDTO> getLibros() {
        return listLibroEntity2DetailDTO(libroLogic.getLibros());
    }
    
    /**
     * <h1> GET /api/libros/{id} : Obtener el libro por id.</h1>
     * 
     * <pre> Busca el libro con el id asociado recibido en la URL y lo devuelve.
     * 
     * Codigos de respuesta:
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Devuelve el libro correspondiente al id.
     * </code>
     * <code style="color: #c7254e; background-color: #f9f2f4;">
     * 404 Not Found No existe un libro con el id dado.
     * </code>
     * </pre>
     * @param id Id del libro que se esta buscando. Debe ser una cadena de digitos.
     * @return JSON {@link LibroDetailDTO} - El libro buscado.
     */
    @GET
    @Path("{id: \\d+}")
    public LibroDetailDTO getLibro(@PathParam("id") Long id) {
        LibroEntity entity = libroLogic.getLibro(id);
        return new LibroDetailDTO(entity);
    }
    
    /**
     * <h1> GET /api/libros/{nombre} : Obtener el libro por el nombre.</h1>
     * 
     * <pre> Busca el libro con el nombre recibido en la URL y lo devuelve.
     * 
     * Codigos de respuesta:
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Devuelve el libro correspondiente al nombre.
     * </code>
     * <code style="color: #c7254e; background-color: #f9f2f4;">
     * 404 Not Found No existe un libro con el nombre dado.
     * </code>
     * </pre>
     * @param nombre nombe del libro que se quiere buscar. Debe ser letras y/o digitos.
     * @return JSON {@link LibroDetailDTO} - El libro con el nombre buscado.
     */
    @GET
    @Path("{nombre}")
    public List<LibroDetailDTO> getLibroByName(@PathParam("nombre") String nombre) {
        List<LibroEntity> nombres = libroLogic.getLibrosByName(nombre);
        return listLibroEntity2DetailDTO(nombres);
    }
    
    /**
     * <h1> PUT /api/libros/{id} : Actualizar libro con el id dado.</h1>
     * <pre> Cuerpo de peticion: JSON {@link LibroDetailDTO}.
     * 
     * Actualiza el libro con el id recibido en la URL con la informacion que se recibe en el cuerpo de la peticion.
     * 
     * Codigos de respuesta:
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Actualiza el libro con el id dado con la informacion enviada como parametro. Retorna un objeto identico.</code>
     * <code style="color: #c7254e; background-color: #f9f2f4;">
     * 404 Not Found. No existe un libro con el id dado.
     * </code>
     * </pre>
     * @throws BusinessLogicException atrapada desde la logica.
     * @param id Id del libro que se desea actualizar.
     * @param libro {@link LibroDetailDTO} El libro que se desea guardar.
     * @return JSON {@link LibroDetailDTO} El libro guardado.
     */
    @PUT
    @Path("{id: \\d+}")
    public LibroDetailDTO updateLibro(@PathParam("id") Long id, LibroDetailDTO libro) throws BusinessLogicException{
       libro.setId(id);
       return new LibroDetailDTO(libroLogic.updateLibro(id, libro.toEntity()));
    }
    
    /**
     * <h1> DELETE /api/libros/{id} : Eliminar libro por id. </h1>
     * 
     * <pre> Elimina el libro con el id asociado en la URL.
     * 
     * Códigos de respuesta:<br>
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Elimina el libro correspondiente al id dado.</code>
     * <code style="color: #c7254e; background-color: #f9f2f4;">
     * 404 Not Found. No existe un libro con el id dado.
     * </code>
     * </pre>
     * 
     * @param id Id del libro que se desea eliminar.
     */
    @DELETE
    @Path("{id: \\d+}")
    public void deleteLibro(@PathParam("id") Long id){
        libroLogic.deleteLibro(id);
    }
    
    private List<LibroDetailDTO> listLibroEntity2DetailDTO(List<LibroEntity> entityList) {
        List<LibroDetailDTO> list = new ArrayList<>();
        for(LibroEntity entity : entityList) {
            list.add(new LibroDetailDTO(entity));
        }
        return list;
    }
}
