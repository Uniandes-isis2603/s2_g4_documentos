/*
MIT License

Copyright (c) 2017 ISIS2603

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
CITYS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
 */
package co.edu.uniandes.csw.documentos.resources;


import co.edu.uniandes.csw.documentos.dtos.ImagenDTO;
import co.edu.uniandes.csw.documentos.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.documentos.mappers.BusinessLogicExceptionMapper;
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
 * <pre>Clase que implementa el recurso "imagenes".
 * URL: /api/imagenes
 * </pre>
 * <i>Note que la aplicación (definida en {@link RestConfig}) define la ruta "/api" y
 * este recurso tiene la ruta "imagenes".</i>
 *
 * <h2>Anotaciones </h2>
 * <pre>
 * Path: indica la dirección después de "api" para acceder al recurso
 * Produces/Consumes: indica que los servicios definidos en este recurso reciben y devuelven objetos en formato JSON
 * RequestScoped: Inicia una transacción desde el llamado de cada método (servicio). 
 * </pre>
 * @author Camilojaravila  
 * @version 1.0
 */
@Path("documentos/{documentoId: \\d+}/imagenes")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class ImagenResource {

    /**
     * <h1>POST /api/imagen : Crear una imagen.</h1>
     * 
     * <pre>Cuerpo de petición: JSON {@link ImagenDTO}.
     * 
     * Crea una nueva imagen con la informacion que se recibe en el cuerpo 
     * de la petición y se regresa un objeto identico con un id auto-generado 
     * por la base de datos.
     * 
     * Codigos de respuesta:
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Creó la nueva imagen .
     * </code>
     * <code style="color: #c7254e; background-color: #f9f2f4;">
     * 412 Precodition Failed: Ya existe la imagen.
     * </code>
     * </pre>
     * @param imagen {@link ImagenDTO} - La imagen que se desea guardar.
     * @return JSON {@link ImagenDTO}  - La imagen guardada con el atributo id autogenerado.
     * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} - Error de lógica que se genera cuando ya existe la imagen.
     */
    @Path("documentos/{documentoId: \\d+}")
    @POST
    public ImagenDTO createImagen(ImagenDTO imagen) throws BusinessLogicException {
        return imagen;
    }

    /**
     * <h1>GET /api/cities : Obtener todas las imagenes.</h1>
     * 
     * <pre>Busca y devuelve todas las imagenes que existen en la aplicacion.
     * 
     * Codigos de respuesta:
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Devuelve todas las imagenes de la aplicacion.</code> 
     * </pre>
     * @return JSONArray {@link ImagenDTO} - Las imagenes encontradas en la aplicación. Si no hay ninguna retorna una lista vacía.
     */
    @GET
    public List<ImagenDTO> getCities() {
        return new ArrayList<>();
    }

    /**
     * <h1>GET /api/cities/{id} : Obtener imagen por id.</h1>
     * 
     * <pre>Busca la imagen con el id asociado recibido en la URL y la devuelve.
     * 
     * Codigos de respuesta:
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Devuelve la imagen correspondiente al id.
     * </code> 
     * <code style="color: #c7254e; background-color: #f9f2f4;">
     * 404 Not Found No existe una imagen con el id dado.
     * </code> 
     * </pre>
     * @param id Identificador de la imagen que se esta buscando. Este debe ser una cadena de dígitos.
     * @return JSON {@link ImagenDTO} - La imagen buscada
     */
    @GET
    @Path("documentos/{documentoId: \\d+}/imagenes/{id: \\d+}")
    public ImagenDTO getImagen(@PathParam("id") Long id) {
        return null;
    }
    
    /**
     * <h1>PUT /api/cities/{id} : Actualizar imagen con el id dado.</h1>
     * <pre>Cuerpo de petición: JSON {@link ImagenDTO}.
     * 
     * Actualiza la imagen con el id recibido en la URL con la informacion que se recibe en el cuerpo de la petición.
     * 
     * Codigos de respuesta:
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Actualiza la imagen con el id dado con la información enviada como parámetro. Retorna un objeto identico.</code> 
     * <code style="color: #c7254e; background-color: #f9f2f4;">
     * 404 Not Found. No existe una imagen con el id dado.
     * </code> 
     * </pre>
     * @param id Identificador de la imagen que se desea actualizar.Este debe ser una cadena de dígitos.
     * @param imagen {@link ImagenDTO} La imagen que se desea guardar.
     * @return JSON {@link ImagenDTO} - La imagen guardada.
     * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} - Error de lógica que se genera al no poder actualizar la imagen porque ya existe una con ese nombre.
     */
    @PUT
    @Path("documentos/{documentoId: \\d+}/imagenes/{id: \\d+}")
    public ImagenDTO updateImagen(@PathParam("id") Long id, ImagenDTO imagen) throws BusinessLogicException {
        return imagen;
    }
    
    /**
     * <h1>DELETE /api/cities/{id} : Borrar imagen por id.</h1>
     * 
     * <pre>Borra la imagen con el id asociado recibido en la URL.
     * 
     * Códigos de respuesta:<br>
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Elimina la imagen correspondiente al id dado.</code>
     * <code style="color: #c7254e; background-color: #f9f2f4;">
     * 404 Not Found. No existe una imagen con el id dado.
     * </code>
     * </pre>
     * @param id Identificador de la imagen que se desea borrar. Este debe ser una cadena de dígitos.
     */
    @DELETE
    @Path("documentos/{documentoId: \\d+}/imagenes/{id: \\d+}")
     public void deleteImagen(@PathParam("id") Long id) {
        // Void
    }
}
