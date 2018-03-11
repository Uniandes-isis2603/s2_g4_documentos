
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.documentos.resources;

import co.edu.uniandes.csw.documentos.dtos.UsuarioDetailedDTO;
import co.edu.uniandes.csw.documentos.ejb.UsuarioLogic;
import co.edu.uniandes.csw.documentos.entities.UsuarioEntity;
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
 * <pre> Clase que implemente el recurso "Usuario".
 * URL: /api/usuarios
 * </pre>
 * <i> Note que la aplicación (definida en {@link RestConfig}) define la ruta "/api" y
 * este recurso tiene la ruta "usuarios". </i>
 * 
 * <h2> Anotaciones </h2>
 * <pre> 
 * Path: indica la direccion despues de "api" para acceder al recurso
 * Produces/Consumes: indica que los servicios definidos en este recurso reciben y devuelven objetos en formato JSON
 * RequestScoped: Inicia una transaccion desde el llamado de cada metodo (servicio).
 * </pre>
 * @author federico
 */
@Path("usuarios")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class UsuarioResource {
    
    @Inject
    UsuarioLogic logic;
    
     private List<UsuarioDetailedDTO> listUsuarioEntity2DetailDTO(List<UsuarioEntity> entityList) {
        List<UsuarioDetailedDTO> list = new ArrayList<>();
        for(UsuarioEntity entity : entityList) {
            list.add(new UsuarioDetailedDTO(entity));
        }
        return list;
    }
    /**
     * <h1> POST /api/usuarios : Crear un usuario. </h1>
     * 
     * <pre> Cuerpo de peticion: JSON (@link UsuarioDetailedDTO}.
     * 
     * Crea un nuevo usuario con la informacion que se recibe en el cuerpo
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
     * @param Usuario {@link UsuarioDetailedDTO} - El Usuario que se desea guardar.
     * @return JSON {@link UsuarioDetailedDTO} - El Usuario guardado con el id generado.
     */
    @POST
    public UsuarioDetailedDTO createUsuario(UsuarioDetailedDTO Usuario) throws BusinessLogicException {
        return new UsuarioDetailedDTO(logic.createUsuario(Usuario.toEntity()));
    }
    
    /**
     * <h1> GET /api/usuarios : Obtener todos los Usuarios. </h1>
     * 
     * <pre> Busca los usuarios creados en la aplicación
     * 
     * Codigos de respuesta:
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Devuelve todos los Usuarios de la aplicacion.</code>
     * </pre>
     * @return JSONArray {@link  UsuarioDetailedDTO} - Los Usuarios encontrados en la plataforma
     */
    @GET
    public List<UsuarioDetailedDTO> getUsuarioDTO() {
       List<UsuarioDetailedDTO> lista= new ArrayList<>();
       lista= listUsuarioEntity2DetailDTO(logic.getUsuarios());
       return lista;
    }
    
    /**
     * <h1> GET /api/usuarios/{id} : Obtener el Usuario por id.</h1>
     * 
     * <pre> Busca el Usuario con el id asociado recibido en la URL y lo devuelve.
     * 
     * Codigos de respuesta:
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Devuelve el Usuario correspondiente al id.
     * </code>
     * <code style="color: #c7254e; background-color: #f9f2f4;">
     * 404 Not Found No existe un usuario con el id dado.
     * </code>
     * </pre>
     * @param id Id del Usuario que se esta buscando. Debe ser una cadena de digitos.
     * @return JSON {@link UsuarioDetailedDTO} - El Usuario buscado.
     */
    @GET
    @Path("{id: \\d+}")
    public UsuarioDetailedDTO getUsuario(@PathParam("id") Long id) throws BusinessLogicException {
        return new UsuarioDetailedDTO(logic.getUsuario(id));
    }
    
    /**
     * <h1> GET /api/Usuarios/{nombre} : Obtener el Usuario por el nombre de usuario.</h1>
     * 
     * <pre> Busca el Usuario con el nombre de usuario recibido en la URL y lo devuelve.
     * 
     * Codigos de respuesta:
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Devuelve el Usuario correspondiente al nombre.
     * </code>
     * <code style="color: #c7254e; background-color: #f9f2f4;">
     * 404 Not Found No existe un Usuario con el nombre dado.
     * </code>
     * </pre>
     * @param nombre nombre del Usuario que se quiere buscar. Debe ser letras y/o digitos.
     * @return JSON {@link UsuarioDetailedDTO} - El Usuario con el nombre buscado.
     */
    @GET
    @Path("{nombre: [a-zA-Z][a-zA-Z_0-9]}")
    public UsuarioDetailedDTO getUsuarioByName(@PathParam("nombre") String nombre) throws BusinessLogicException {
        return new UsuarioDetailedDTO(logic.getUsuarioByName(nombre));
    }
    
    /**
     * <h1> PUT /api/Usuarios/{id} : Actualizar Usuario con el id dado.</h1>
     * <pre> Cuerpo de peticion: JSON {@link UsuarioDetailedDTO}.
     * 
     * Actualiza el Usuario con el id recibido en la URL con la informacion que se recibe en el cuerpo de la peticion.
     * 
     * Codigos de respuesta:
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Actualiza el Usuario con el id dado con la informacion enviada como parametro. Retorna un objeto identico.</code>
     * <code style="color: #c7254e; background-color: #f9f2f4;">
     * 404 Not Found. No existe un Usuario con el id dado.
     * </code>
     * </pre>
     * @param id Id del Usuario que se desea actualizar.
     * @param Usuario {@link UsuarioDetailedDTO} El Usuario que se desea guardar.
     * @return Usuario {@link UsuarioDetailedDTO} El Usuario guardado.
     */
    @PUT
    @Path("{id: \\d+}")
    public UsuarioDetailedDTO updateUsuario(@PathParam("id") Long id, UsuarioDetailedDTO Usuario) throws BusinessLogicException {
        Usuario.setId(id);
        return  new UsuarioDetailedDTO(logic.updateUsuario(Usuario.toEntity()));
    }
    
    /**
     * <h1> DELETE /api/Usuarios/{id} : Eliminar Usuario por id. </h1>
     * 
     * <pre> Elimina el Usuario con el id asociado en la URL.
     * 
     * Códigos de respuesta:<br>
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Elimina el Usuario correspondiente al id dado.</code>
     * <code style="color: #c7254e; background-color: #f9f2f4;">
     * 404 Not Found. No existe un Usuario con el id dado.
     * </code>
     * </pre>
     * 
     * @param id Id del Usuario que se desea eliminar.
     */
    @DELETE
    @Path("{id: \\d+}")
    public void deleteUsuario(@PathParam("id") Long id) throws BusinessLogicException{
        logic.deleteUsuario(id);
    }
    
    
}
