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
 */@Path("compra")
@Produces ("application/json")
@Consumes("application/json")
@RequestScoped
 public class CompraResource {

    
/**
 *
 * @author n.sotelo
 */


    
    
 


    
     /**
     * <h1> POST /api/compra : crear un nuevO Compra. </h1>
     * <pre> Cuerpo de peticion : JSON {CompraDetailedDTO}
     * 
     * Crea un nuevo Compra que se recibe en el el cuerpo 
     * de la petición y se regresa un objeto identico con un id auto-generado 
     * por la base de datos.
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Creó un nuevo Compra .
     * </code>
     * <code style="color: #c7254e; background-color: #f9f2f4;">
     * 412 Precodition Failed: Ya existe el Compra .
     * </code>
     * </pre>
     * 
     * @param Compra {@link CompraDetailDTO} - La nueva Compra
     * @return JSON {@link CompraDetailDTO}  -La Compra guardada con el atributo id autogenerado.
     * @throws BusinessLogicException
     */
    @POST
    public CompraDetailedDTO createCompra(CompraDetailedDTO Compra ) throws BusinessLogicException
    {
        return Compra;
    }
    
     /**
     * <h1> GET /api/compras : encuentra todas los Compras de la tienda</h1>
     * <pre> Busca y devuelve todas las Compraes de la aplicacion
     * Codigos de respuesta:
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Devuelve los Compras de la aplicacion.
     * </code> 
     *
     * </pre>
     * 
     * @return JSONArray {@link AutorDetailDTO} - Las Compras  encontradas en la aplicación. Si no hay ninguna retorna una lista vacía.
     */
    @GET
    public List<CompraDetailedDTO> getCompras()
    {
        return new ArrayList<>();
    }
    
    /**
     * <h1> GET /api/compra/{id} : encuentra una Compra , la cual esta identificada por un id </h1>
     * 
     * <pre> Encuentra una Compra identificada por un ID unico recibido en la URL y la devuelve.
     * * Codigos de respuesta:
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Devuelve la Compra correspondiente al id.
     * </code> 
     * <code style="color: #c7254e; background-color: #f9f2f4;">
     * 404 Not Found No existe una Compra con el id dado.
     * </code>
     * </pre>
     * @param id Identificador de la eidtorial que se busca,
     * @return JSON {@lnk CompraDeitailedDTO} - La Compra
     */
    @GET
    @Path("{id: \\d+}")
    public CompraDetailedDTO getCompra(@PathParam("id") Long id)
    {
      return null;
    }
    
     /**
     * <h1> PUT /api/compra/{id} : actualiza una Compra </h1>
     * <pre> cuerpo de peticion : JSON.
     * 
     * Actuliza la tarjetaDeCredito identificada con el identificador, con la
     * informacion en el cuerpo de peticion.
     * 
     * Codigos de respuesta:
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Actualiza la Compra con el id dado con la información enviada como parámetro. Retorna un objeto identico.</code> 
     * <code style="color: #c7254e; background-color: #f9f2f4;">
     * 404 Not Found. No existe un comentario con el id dado.
     * </code> 
     * 
     * </pre>
     * @param id IDentificador de la Compra que se desea actualizar representado
     * como una cadena de digitos.
     * @param Compra La tarjetaDeCredito que se desea actualizar.
     * @return JSON - La Compra guardada
     * @throws BusinessLogicException.
     */
    @PUT
    @Path("{id: \\d+}")
    public CompraDetailedDTO updateCompra (@PathParam("id)") Long id, CompraDetailedDTO Compra) throws BusinessLogicException
    {
        return Compra;
    }
    
    /**
     * <h1> DELETE /api/compra/{id} : elimina una eidtorial </h1>
     * <pre> Borra la Compra identificado con un id unico
     * pasado por parametro.
     * 
     * Códigos de respuesta:<br>
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Elimina la Compra correspondiente al id dado.</code>
     * <code style="color: #c7254e; background-color: #f9f2f4;">
     * 404 Not Found. No existe una Compra con el id dado.
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


    


    



    

