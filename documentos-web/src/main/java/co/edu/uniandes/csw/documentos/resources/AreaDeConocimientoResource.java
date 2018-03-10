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


import co.edu.uniandes.csw.documentos.dtos.AreaDeConocimientoDetailDTO;
import co.edu.uniandes.csw.documentos.ejb.AreaDeConocimientoLogic;
import co.edu.uniandes.csw.documentos.entities.AreaDeConocimientoEntity;
import co.edu.uniandes.csw.documentos.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.documentos.mappers.BusinessLogicExceptionMapper;
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
 * <pre>Clase que implementa el recurso "areas".
 * URL: /api/areas
 * </pre>
 * <i>Note que la aplicación (definida en {@link RestConfig}) define la ruta "/api" y
 * este recurso tiene la ruta "areas".</i>
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
@Path("areas")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class AreaDeConocimientoResource {
    
    @Inject
    private AreaDeConocimientoLogic areaLogic;
    
    /**
     * Convierte una lista de AreaDeConocimientoEntity a una lista de AreaDeConocimientoDetailDTO.
     *
     * @param entityList Lista de AreaDeConocimientoEntity a convertir.
     * @return Lista de AreaDeConocimientoDetailDTO convertida.
     * 
     */
    private List<AreaDeConocimientoDetailDTO> listEntity2DTO(List<AreaDeConocimientoEntity> entityList) {
        List<AreaDeConocimientoDetailDTO> list = new ArrayList<>();
        for (AreaDeConocimientoEntity entity : entityList) {
            list.add(new AreaDeConocimientoDetailDTO(entity));
        }
        return list;
    }

    /**
     * <h1>POST /api/areas : Crear un area.</h1>
     * 
     * <pre>Cuerpo de petición: JSON {@link AreaDeConocimientoDetailDTO}.
     * 
     * Crea un nuevo area de conocimiento con la informacion que se recibe en el cuerpo 
     * de la petición y se regresa un objeto identico con un id auto-generado 
     * por la base de datos.
     * 
     * Codigos de respuesta:
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Creó el nuevo area de conocimiento .
     * </code>
     * <code style="color: #c7254e; background-color: #f9f2f4;">
     * 412 Precodition Failed: Ya existe el area de conocimiento
     * .
     * </code>
     * </pre>
     * @param area {@link AreaDeConocimientoDetailDTO} - El area que se desea guardar.
     * @return JSON {@link AreaDeConocimientoDetailDTO}  - El area guardado con el atributo id autogenerado.
     * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} - Error de lógica que se genera cuando ya existe el area.
     */
    @POST
    public AreaDeConocimientoDetailDTO createAreaDeConocimiento(AreaDeConocimientoDetailDTO area) throws BusinessLogicException {
        return new AreaDeConocimientoDetailDTO(areaLogic.createArea(area.toEntity()));
    }

    /**
     * <h1>GET /api/areas : Obtener todas las areas.</h1>
     * 
     * <pre>Busca y devuelve todas las areas que existen en la aplicacion.
     * 
     * Codigos de respuesta:
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Devuelve todas las areas de la aplicacion.</code> 
     * </pre>
     * @return JSONArray {@link AreaDeConocimientoDetailDTO} - Las areas encontradas en la aplicación. Si no hay ninguna retorna una lista vacía.
     */
    @GET
    public List<AreaDeConocimientoDetailDTO> getAreas() {
        return listEntity2DTO(areaLogic.getAreas());        
    }

    /**
     * <h1>GET /api/areas/{id} : Obtener areas por id.</h1>
     * 
     * <pre>Busca el area con el id asociado recibido en la URL y la devuelve.
     * 
     * Codigos de respuesta:
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Devuelve el area correspondiente al id.
     * </code> 
     * <code style="color: #c7254e; background-color: #f9f2f4;">
     * 404 Not Found No existe una area con el id dado.
     * </code> 
     * </pre>
     * @param id Identificador del area que se esta buscando. Este debe ser una cadena de dígitos.
     * @return JSON {@link AreaDeConocimientoDetailDTO} - El area buscada
     */
    @GET
    @Path("{id: \\d+}")
    public AreaDeConocimientoDetailDTO getArea(@PathParam("id") Long id) {
        
        return new AreaDeConocimientoDetailDTO(areaLogic.getArea(id));
    }
    
    /**
     * <h1>PUT /api/areas/{id} : Actualizar areas con el id dado.</h1>
     * <pre>Cuerpo de petición: JSON {@link AreaDeConocimientoDetailDTO}.
     * 
     * Actualiza el area con el id recibido en la URL con la informacion que se recibe en el cuerpo de la petición.
     * 
     * Codigos de respuesta:
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Actualiza el area con el id dado con la información enviada como parámetro. Retorna un objeto identico.</code> 
     * <code style="color: #c7254e; background-color: #f9f2f4;">
     * 404 Not Found. No existe un area con el id dado.
     * </code> 
     * </pre>
     * @param id Identificador del area que se desea actualizar. Este debe ser una cadena de dígitos.
     * @param area {@link AreaDeConocimientoDetailDTO} El area que se desea guardar.
     * @return JSON {@link AreaDeConocimientoDetailDTO} - El Area de Conocimiento guardado.
     * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} - Error de lógica que se genera al no poder actualizar el area porque ya existe una con ese nombre.
     */
    @PUT
    @Path("{id: \\d+}")
    public AreaDeConocimientoDetailDTO updateArea(@PathParam("id") Long id, AreaDeConocimientoDetailDTO area) throws BusinessLogicException {
        AreaDeConocimientoEntity entity = area.toEntity();
        entity.setId(id);
        return new AreaDeConocimientoDetailDTO(areaLogic.updateArea(entity));
    }
    
    /**
     * <h1>DELETE /api/cities/{id} : Borrar areas por id.</h1>
     * 
     * <pre>Borra el area con el id asociado recibido en la URL.
     * 
     * Códigos de respuesta:<br>
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Elimina el area correspondiente al id dado.</code>
     * <code style="color: #c7254e; background-color: #f9f2f4;">
     * 404 Not Found. No existe un area con el id dado.
     * </code>
     * </pre>
     * @param id Identificador del area que se desea borrar. Este debe ser una cadena de dígitos.
     * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} - Error de lógica que se genera al no encontrar el area.
     */
    @DELETE
    @Path("{id: \\d+}")
     public void deleteArea(@PathParam("id") Long id) throws BusinessLogicException {
        // Void
        areaLogic.deleteArea(id);
    }
}
