/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.documentos.resources;
import co.edu.uniandes.csw.documentos.dtos.*;
import co.edu.uniandes.csw.documentos.exceptions.BusinessLogicException;
import java.util.List;
import java.util.ArrayList;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

/**
 * <pre> Clase que implementa el recurso "TarjetaDeCredito"
 * URL : /api/metodosdepago/tarjetasdecredito
 * </pre>
 ** <h2> Anotaciones </h2>
 * <pre> 
 * Path : indica la direccion despues de "api" para acceder al recurso
 * Produces/Consume : indica que los servicios definidos reciben y devuelven objetos JSON
 * RequesScoped :  Inicia una transaccion desde el llamado de cada metodo
 * </pre>
 * 
 * @author g.ospinaa
 */
@Path("metodosdepago/tarjetasdecredito")
@Produces ("application/json")
@Consumes("application/json")
@RequestScoped
public class TarjetaDeCreditoResources {
 
    
    /**
     * <h1> POST /api/metdodosdepago/tarjetasdecredito : crear una nueva TarjetaDeCredito. </h1>
     * <pre> Cuerpo de peticion : JSON {TarjetaDeCreditoDetailDTO}
     * 
     * Crea una nueva tarjetaDeCredito para el usuario con la informacion que recibe 
     * por parametro y se regresa un objeto identico con un id unico creado por la base de datos.
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Creó la nueva TDC .
     * </code>
     * <code style="color: #c7254e; background-color: #f9f2f4;">
     * 412 Precodition Failed: Ya existe la TDC.
     * </code>
     * </pre>
     * 
     * @param TarjetaDeCredito {@link TarjetaDeCreditoDetailDTO} - La nueva tarjetaDeCredito
     * @return JSON
     * @throws BusinessLogicException
     */
    @POST
    public TarjetaDeCreditoDetailDTO createTDC(TarjetaDeCreditoDetailDTO TDC) throws BusinessLogicException
    {
        return TDC;
    }
    
     /**
     * <h1> GET /api/metdodosdepago/tarjetasdecredito : encuentra todas las TDC asociadas al usuario</h1>
     * <pre> Busca y devuelve todas las tarjetasDeCredito que tiene el usuario
     * Codigos de respuesta:
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Devuelve las TDC correspondiente al id.
     * </code> 
     * <code style="color: #c7254e; background-color: #f9f2f4;">
     * 404 Not Found El usuario no tiene tarjetas.
     * </code> 
     * </pre>
     * 
     * @return todas las TarjetasDeCredito que tiene el usuario.
     */
    @GET
    public List<TarjetaDeCreditoDetailDTO> getTDC()
    {
        return new ArrayList<>();
    }
    
    /**
     * <h1> GET /api/metdodosdepago/tarjetasdecredito/{id} : encuentra una tarjeta del usuario, la cual esta identificada por un id <h1>
     * 
     * <pre> Encuentra una tarjetaDeCredito identificada por un ID unico recibido en la URL y la devuelve.
     * * Codigos de respuesta:
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Devuelve la TDC correspondiente al id.
     * </code> 
     * <code style="color: #c7254e; background-color: #f9f2f4;">
     * 404 Not Found No existe una TDC con el id dado.
     * </code>
     * </pre>
     * @param id Identificador de la TarjetaDeCredito que se busca,
     * @return JSON {@link MetodoDePagoDetailDTO} - El metodo buscado
     */
    @GET
    @Path("{id: \\d+}")
    public TarjetaDeCreditoDetailDTO getTDC(@PathParam("id") Long id)
    {
      return null;
    }
    
     /**
     * <h1> PUT /api/metdodosdepago/tarjetasdecredito/{id} : actualiza una TDC </h1>
     * <pre> cuerpo de peticion : JSON.
     * 
     * Actuliza la tarjetaDeCredito identificada con el identificador, con la
     * informacion en el cuerpo de peticion.
     * 
     * Codigos de respuesta:
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Actualiza la TDC con el id dado con la información enviada como parámetro. Retorna un objeto identico.</code> 
     * <code style="color: #c7254e; background-color: #f9f2f4;">
     * 404 Not Found. No existe una TDC con el id dado.
     * </code> 
     * 
     * </pre>
     * @param id IDentificador de la tarjetaDeCredito que se desea actualizar representado
     * como una cadena de digitos.
     * @param tdc La tarjetaDeCredito que se desea actualizar.
     * @return JSON - La tarjetaDeCredito guardada
     * @throws BusinessLogicException.
     */
    @PUT
    @Path("{id: \\d+}")
    public TarjetaDeCreditoDetailDTO updateTDC (@PathParam("fid)") Long id, TarjetaDeCreditoDetailDTO tdc) throws BusinessLogicException
    {
        return tdc;
    }
    
    /**
     * <h1> DELETE /api/metdodosdepago/tarjetasdecredito/{id} : elimina una TDC </h1>
     * <pre> Borra la tarjetaDeCredito identificado con un id unico
     * pasado por parametro.
     * 
     * Códigos de respuesta:<br>
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Elimina la PP correspondiente al id dado.</code>
     * <code style="color: #c7254e; background-color: #f9f2f4;">
     * 404 Not Found. No existe una PP con el id dado.
     * </code>
     * </pre>
     * @param id Identificador unico de la TDC que se desea borrar.
     */
    @DELETE
    @Path("{id: \\d+}")
    public void deleteTDC (@PathParam("id") Long id)
    {
        // Void
    }
}
