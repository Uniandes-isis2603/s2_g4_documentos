/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.documentos.resources;

import co.edu.uniandes.csw.documentos.dtos.FotocopiaDetailDTO;
import co.edu.uniandes.csw.documentos.ejb.FotocopiaLogic;
import co.edu.uniandes.csw.documentos.entities.FotocopiaEntity;
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
 * <pre> Clase que implementa el recurso "Fotocopia".
 * URL: /api/fotocopias
 * </pre>
 * <i> Note que la aplicacion (definida en {@link RestConfig}) define la ruta "/api" y
 * este recurso tiene la ruta "fotocopias".</i>
 * 
 * <h2> Anotaciones </h2>
 * <pre>
 * Path: indica la direccion despues de "api" para acceder al recurso
 * Produces/Consumes: indica que los servicios definidos en este recurso reciben y devuelven objetos en formato JSON
 * RequestScoped: Inicia una transaccion desde el llamado de cada metodo (servicio).
 * </pre> 
 * @author Ernesto Viana
 */
@Path("fotocopias")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class FotocopiaResource {
    
    @Inject
    FotocopiaLogic fotocopiaLogic;
    /**
     * <h1> POST /api/fotocopias : Crear una fotocopia. </h1>
     * 
     * <pre> Cuerpo de peticion: JSON {@link FotocopiaDetailDTO}.
     * 
     * Crea una nueva fotocopia con la informacon que se recibe en el cuerpo
     * de la peticion y se regresa un objeto identico con un id autogenerado
     * por la base de datos.
     * 
     * Codigos de respuesta:
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Creo el nuevo libro .
     * </code>
     * <code style="color: #c7254e; background-color: #f9f2f4;">
     * 412 Precodition Failed: Ya existe la fotocopia.
     * </code>
     * </pre>
     * @throws BusinessLogicException desde la logica.
     * @param fotocopia {@link FotocopiaDetailDTO} - La fotocopia que se desea guardar.
     * @return JSON {@link FotocopiaDetailDTO} - La fotocopia guardada en el id generado.
     */
    @POST
    public FotocopiaDetailDTO createFotocopia(FotocopiaDetailDTO fotocopia) throws BusinessLogicException{
        return new FotocopiaDetailDTO(fotocopiaLogic.createFotocopia(fotocopia.toEntity()));
    }
    
    /**
     * <h1> GET /api/fotocopias : Obtener todas las fotocopias. </h1>
     * 
     * <pre> Busca y devuelve todas las fotocopias que existen en la aplicacion.
     * 
     * Codigos de respuesta:
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Devuelve todas las fotocopias de la aplicacion.</code>
     * </pre>
     * @return  JSONArray {@link  FotocopiaDetailDTO} - Las fotocopias encontradas
     * en la aplicacion, de no haber ninguna retornar lista vacia.
     */
    @GET
    public List<FotocopiaDetailDTO> getFotocopias() {
        return listFotocopiaEntity2DetailDTO(fotocopiaLogic.getFotocopias());
    }
    
    /**
     * <h1> GET /api/fotocopias/{id} : Obtener la fotocopia por id.</h1>
     * 
     * <pre> Busca la fotocopia con el id asociado recibido en la URL y la devuelve.
     * 
     * Codigos de respuesta:
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Devuelve la fotocopia correspondiente al id.
     * </code>
     * <code style="color: #c7254e; background-color: #f9f2f4;">
     * 404 Not Found No existe una fotocopia con el id dado.
     * </code>
     * </pre>
     * @param id Id de la fotocopia que se esta buscandoñ Debe ser una cadena de digitos.
     * @return JSON {@link FotocopiaDetailDTO} - La fotocopia buscada.
     */
    @GET
    @Path("{id: \\d+}")
    public FotocopiaDetailDTO getFotocopia(@PathParam("id") Long id) {
        FotocopiaEntity entity = fotocopiaLogic.getFotocopia(id);
        return new FotocopiaDetailDTO(entity);
    }
    
    /**
     * <h1> GET /api/fotocopias/{profesor} : Obtener la fotocopia por el nombre del profesor.</h1>
     * 
     * <pre> Busca la focotocopia con el profesor recibido en la URL y la devuelve
     * 
     * Codigos de respuesta:
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Devuelve la fotocopia correspondiente al nombre del profesor.
     * </code>
     * <code style="color: #c7254e; background-color: #f9f2f4;">
     * 404 Not Found No existe un libro con el el profesor dado.
     * </code>
     * </pre>
     * @param profesor nombre del profesor que puso esa fotocopia. Debe ser letras de abecedario y/o digitos.
     * @return JSON {@link FotocopiaDetailDTO} - La fotocopia con el profesor buscado.
     */
    @GET
    @Path("{profesor}")
    public List<FotocopiaDetailDTO> getFotocopiasByProfesor(@PathParam("profesor") String profesor) {
        List<FotocopiaEntity> profesores = fotocopiaLogic.getFotocopiaByProfesor(profesor);
        return listFotocopiaEntity2DetailDTO(profesores);
    }
    
    /**
     * <h1> PUT /api/fotocopias/{id} : Actualizar fotocopia con el id dado. </h1>
     * <pre> Cuerpo de peticion: JSON {@link FotocopiaDetailDTO}.
     * 
     * Actualiza la fotocopia con el id recibido en la URL con la informacion que se recibeen el cuerpo de la peticion.
     * 
     * Codigos de respuesta:
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Actualiza la fotocopia con el id dado con la informacion enviada como parametro. Retorna un objeto identico.</code>
     * <code style="color: #c7254e; background-color: #f9f2f4;">
     * 404 Not Found. No existe una fotocopia con el id dado.
     * </code>
     * </pre>
     * @throws BusinessLogicException atrapada desde la logica.
     * @param id Id de la fotocopia que se desea actualizar.
     * @param fotocopia {@link FotocopiaDetailDTO} La fotocopia que se desea guardar.
     * @return JSON {@link FotocopiaDetailDTO} La fotocopia guardada.
     */
    @PUT
    @Path("{id: \\d+}")
    public FotocopiaDetailDTO updateFotocopia(@PathParam("id") Long id,FotocopiaDetailDTO fotocopia) throws BusinessLogicException{
        fotocopia.setId(id); 
        return new FotocopiaDetailDTO(fotocopiaLogic.updateFotocopia(id, fotocopia.toEntity()));
    }
    
    /**
     * <h1> DELETE /api/fotocopias/{id} : Eliminar fotocopia por id. </h1>
     * 
     * <pre> Elimina la fotocopia con el id asociado en la URL.
     * 
     * Códigos de respuesta:<br>
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Elimina la fotocopia correspondiente al id dado.</code>
     * <code style="color: #c7254e; background-color: #f9f2f4;">
     * 404 Not Found. No existe una fotocopia con el id dado.
     * </code>
     * </pre>
     * @param id EL codigo de identificacion de la fotocopia a eliminar
     */
    @DELETE
    @Path("{id: \\d+}")
    public void deleteFotocopia(@PathParam("id") Long id) {
        fotocopiaLogic.deleteFotocopia(id);
    }
    
    private List<FotocopiaDetailDTO> listFotocopiaEntity2DetailDTO(List<FotocopiaEntity> entityList) {
        List<FotocopiaDetailDTO> list = new ArrayList<>();
        for(FotocopiaEntity entity : entityList) {
            list.add(new FotocopiaDetailDTO(entity));
        }
        return list;
    }
}
