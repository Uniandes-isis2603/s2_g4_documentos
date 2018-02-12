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
 *<pre> Clase que implemeta el recurso PayPal
 * URL: /api/metodosdepago/paypal
 * </pre> 
 * 
 * <h2> Anotaciones </h2>
 * <pre> 
 * Path : indica la direccion despues de "api" para acceder al recurso
 * Produces/Consume : indica que los servicios definidos reciben y devuelven objetos JSON
 * RequesScoped :  Inicia una transaccion desde el llamado de cada metodo
 * </pre>
 * 
 * @author g.ospinaa
 */
@Path ("metodosdepago/paypal")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class PayPalResources {
 
     /**
     * <h1> POST /api/metdodosdepago/paypal : crea una nueva cuenta paypal. </h1>
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
     * @param payPal {@link PayPalDetailDTO} - el PayPal a agregar.
     * @return JSON
     * @throws BusinessLogicException
     * 
     */
    @POST
    public PayPalDetailDTO createPayPal(PayPalDetailDTO payPal) throws BusinessLogicException
    {
        return payPal;
    }
    
    /**
     * 
     * <pre> Encuentra la cuenta PayPal identificado por un ID unico recibido en la URL
     * y la devuelve.
     * </pre>
     * @param id Identificador del usuario cuya cuenta PayPal se busca,
     * @return JSON {@link PayPalDetailDTO} - El PP buscado
     */
    @GET
    @Path("{id: \\d+}")
    public PayPalDetailDTO getPayPal(@PathParam("id") Long id)
    {
      return null;
    }
    
    /**
     * 
     * <pre> cuerpo de peticion : JSON.
     * </pre>
     * Actuliza el payPal identificado con el identificador, con la
     * informacion en el cuerpo de peticion.
     * 
     * @param id IDentificador del PayPal que se desea actualizar representado
     * como una cadena de digitos.
     * @param paypal del PayPal que se desea actualizar.
     * @return JSON - La ciudad guardada
     * @throws BusinessLogicException.
     */
    @PUT
    @Path("{id: \\d+}")
    public PayPalDetailDTO updatePayPal(@PathParam("id)") Long id, PayPalDetailDTO paypal) throws BusinessLogicException
    {
        return paypal;
    }
    
    /**
     * <pre> Borra el PayPal identificado con un id unico
     * pasado por parametro.
     * </pre>
     * @param id Identificador unico del usuario cuyo PayPal se desea borrar.
     */
    @DELETE
    @Path("{id: \\d+}")
    public void deletePayPal (@PathParam("id") Long id)
    {
        // Void
    }
}
