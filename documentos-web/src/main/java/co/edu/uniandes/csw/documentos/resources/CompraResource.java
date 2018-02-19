/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.documentos.resources;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import co.edu.uniandes.csw.documentos.dtos.CompraDetailedDTO;

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
 * <pre> Clase que implementa el recurso "Compra".
 * URL: /api/compras
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
 * @author n.sotelo
 */@Path("compras")
@Produces ("application/json")
@Consumes("application/json")
@RequestScoped
 public class CompraResource {

     /**
     * <h1> POST /api/compras : crear una nueva Compra. </h1>
     * <pre> Cuerpo de peticion : JSON {CompraDetailedDTO}
     * 
     * Crea una nueva compra que se recibe en el el cuerpo 
     * de la petición y se regresa un objeto identico.
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Creó una nueva Compra .
     * </code>
     * </pre>
     * 
     * @param Compra {@link CompraDetailedDTO} - La nueva compra a efectuar
     * @return JSON {@link CompraDetailedDTO}  -La Compra guardada 
     * 
     */
    @POST
    public CompraDetailedDTO createCompra(CompraDetailedDTO Compra ) 
    {
        return Compra;
    }
    
     /**
     * <h1> GET /api/compras : encuentra todas los compras de la tienda</h1>
     * <pre> Busca y devuelve todas las compras de la aplicacion
     * Codigos de respuesta:
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Devuelve las compras de la aplicacion.
     * </code> 
     *
     * </pre>
     * 
     * @return JSONArray {@link CompraDetailedDTO} - Las compras  encontradas en la aplicación. Si no hay ninguna retorna una lista vacía.
     */
    @GET
    public List<CompraDetailedDTO> getCompras()
    {
        return new ArrayList<>();
    }
    
    /**
     * <h1> GET /api/compras/{id} : encuentra una compra ,la cual se encuentra identificada por un id </h1>
     * 
     * <pre> Encuentra una compra identificada por un id unico 
     * * Codigos de respuesta:
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Devuelve la Compra correspondiente al id.
     * </code> 
     * <code style="color: #c7254e; background-color: #f9f2f4;">
     * 404 Not Found No existe una Compra con el id dado.
     * </code>
     * </pre>
     * @param id Identificador de la compra que se busca.
     * @return JSON {@link CompraDetailedDTO} - La compra deseada
     */
    @GET
    @Path("{id: \\d+}")
    public CompraDetailedDTO getCompra(@PathParam("id") Long id)
    {
      return null;
    }
    
     /**
     * <h1> PUT /api/compras/{id} : actualiza una Compra </h1>
     * <pre> cuerpo de peticion : JSON.
     * 
     * Actuliza la compra , con la informacion en el cuerpo de peticion.
     * 
     * Codigos de respuesta:
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Actualiza la compra con el id dado con la información enviada como parámetro. Retorna un objeto identico.</code> 
     * <code style="color: #c7254e; background-color: #f9f2f4;">
     * 404 Not Found. No existe una compra con el id dado.
     * </code> 
     * 
     * </pre>
     * @param id Identificador de la compra que se desea actualizar.
     * @param Compra La compra que se desea actualizar.
     * @return JSON - La Compra guardada
     * 
     */
    @PUT
    @Path("{id: \\d+}")
    public CompraDetailedDTO updateCompra (@PathParam("id)") Long id, CompraDetailedDTO Compra) 
    {
        return Compra;
    }
    
    /**
     * <h1> DELETE /api/compras/{id} : elimina una compra </h1>
     * <pre> Borra la compra identificado con un id unico
     * pasado por parametro.
     * 
     * Códigos de respuesta:<br>
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Elimina la compra correspondiente al id dado.</code>
     * <code style="color: #c7254e; background-color: #f9f2f4;">
     * 404 Not Found. No existe una compra con el id dado.
     * </code>
     * </pre>
     * @param id Identificador unico de la Compra que se desea borrar.
     */
    @DELETE
    @Path("{id: \\d+}")
    public void deleteCompra (@PathParam("id") Long id)
    {
        // Void
    }
}


    


    



    

