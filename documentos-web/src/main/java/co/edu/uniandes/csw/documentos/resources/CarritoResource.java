/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.documentos.resources;

import co.edu.uniandes.csw.documentos.dtos.CarritoDetailedDTO;
import co.edu.uniandes.csw.documentos.entities.CarritoEntity;
import co.edu.uniandes.csw.documentos.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.documentos.ejb.carritoLogic;
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
 *
 * @author nicolassotelo
 */

@Path("carrito")
@Produces ("application/json")
@Consumes("application/json")
@RequestScoped
public class CarritoResource 
{
 @Inject
private carritoLogic logicaC;

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
     * @param carrito {@link CompraDetailedDTO} - La nueva compra a efectuar
     * @return JSON {@link CompraDetailedDTO}  -La Compra guardada 
     * 
     */
    @POST
    @Produces("application/json")
    @Consumes("application/json")
    public CarritoDetailedDTO createCarrito(CarritoDetailedDTO carrito ) throws BusinessLogicException 
    {
         CarritoEntity carritoEntity = carrito.toEntity();
       
              
         
       CarritoEntity nuevocarrito = logicaC.createCarrito(carritoEntity);
       
       
       return new CarritoDetailedDTO(nuevocarrito);
        
    }
    
     
    
    /**
     * <h1> GET /api/ {usuario/{usuarioId:\\d+} /compras/ : encuentra el carro de compras asociado a un usario </h1>
     * 
     * <pre> Encuentra el carro de compras de un usuario. 
     * * Codigos de respuesta:
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Devuelve el carro de compras asociado a un usuario.
     * </code> 
     * <code style="color: #c7254e; background-color: #f9f2f4;">
     * 404 Not Found No existe un carro de compras con el id dado.
     * </code>
     * </pre>
     * @param id2 Identificador del usuario del cual se desea encontrar la compra.
     * @return JSON {@link CompraDetailedDTO} - La compra deseada
     */
    @GET
    @Path("{id: \\d+}")
    public CarritoDetailedDTO getCarroDeCompra( @PathParam("id") Long id2) throws BusinessLogicException
    {
       
       return new CarritoDetailedDTO(logicaC.getCarrito(id2));
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
     * @param carrito La compra que se desea actualizar.
     * @return JSON - La Compra guardada
     * 
     */
    @PUT
    @Path("{id: \\d+}")
    public CarritoDetailedDTO updateCarrito (@PathParam("id") Long id, CarritoDetailedDTO carrito) throws BusinessLogicException 
    {
        carrito.setId(id);
        
        return new CarritoDetailedDTO(logicaC.updateCarrito(id, carrito.toEntity()));
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
    public void deleteCompra (@PathParam("id") Long id)throws BusinessLogicException 
    {
      logicaC.deleteCarrito(id);
    }
}

   

