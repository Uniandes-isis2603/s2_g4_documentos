/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.documentos.resources;

import co.edu.uniandes.csw.documentos.dtos.*;
import co.edu.uniandes.csw.documentos.ejb.PayPalLogic;
import co.edu.uniandes.csw.documentos.ejb.UsuarioLogic;
import co.edu.uniandes.csw.documentos.entities.PayPalEntity;
import co.edu.uniandes.csw.documentos.entities.UsuarioEntity;
import co.edu.uniandes.csw.documentos.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.documentos.mappers.BusinessLogicExceptionMapper;
import java.util.List;
import java.util.ArrayList;
import java.util.Objects;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;


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
@Path ("usuario/{usuarioId: \\d+}/metodosdepago/paypal")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class PayPalResources {
    
    @Inject
    PayPalLogic PPLogic;
    
    @Inject
    UsuarioLogic uLogic;
 
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
     * @return JSON {@link PayPalDetailDTO} - la cuenta PayPal updated
     * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} - Error de lógica que se genera cuando ya existe la cuenta.
     * 
     */
    @POST
    public PayPalDetailDTO createPayPal(@PathParam("usuarioId") Long Uid, PayPalDetailDTO payPal) throws BusinessLogicException
    {
        UsuarioEntity entity = uLogic.getUsuario(Uid);
        if(entity != null)
        {
        PayPalEntity p = PPLogic.createPayPal(payPal.toEntity());
        return new PayPalDetailDTO(p);
        }
        
        throw new WebApplicationException("el usuario al que le quiere agregar el recurso no existe");
    }

    
     /**
     * <h1> GET /api/metdodosdepago/paypal : encuentra todas las cuentas Paypal asociadas al usuario</h1>
     * <pre> Busca y devuelve todas las cuentas paypal que tiene el usuario
     * Codigos de respuesta:
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Devuelve las TDC correspondiente al id.
     * </code> 
     * <code style="color: #c7254e; background-color: #f9f2f4;">
     * 404 Not Found El usuario no tiene tarjetas.
     * </code> 
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 500 No se pudo.
     * </code> 
     * </pre>
     * 
     * @return todas las cuentas paypal que tiene el usuario.
     */
    @GET
    public List<PayPalDetailDTO> getPayPal(@PathParam("usuarioId") Long Uid) throws BusinessLogicException
    {
        UsuarioEntity entity = uLogic.getUsuario(Uid);
        if(entity != null)
        {
          return listaPP(PPLogic.getPayPal());  
        }
        throw new WebApplicationException("el usuario al que le quiere agregar el recurso no existe");
    }
    
    private List<PayPalDetailDTO> listaPP(List<PayPalEntity> entityList)
    {
        List<PayPalDetailDTO> list = new ArrayList<>();
        for(PayPalEntity entity : entityList)
        {
            list.add(new PayPalDetailDTO(entity));
        }
        return list;
    }
    
    
    /**
     * <h1> GET /api/metdodosdepago/paypal/{id} : encuentra una cuenta Paypal del usuario, la cual esta identificada por un id </h1>
     * 
     * <pre> Encuentra una cuenta Paypal identificada por un ID unico recibido en la URL y la devuelve.
     * * Codigos de respuesta:
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Devuelve la PP correspondiente al id.
     * </code> 
     * <code style="color: #c7254e; background-color: #f9f2f4;">
     * 404 Not Found No existe una PP con el id dado.
     * </code>
     * </pre>
     * @param id Identificador de la cuentasPaypal que se busca,
     * @return JSON {@link PayPalDetailDTO} - cuenta Paypal buscada
     */
    @GET
    @Path("{id: \\d+}")
    public PayPalDetailDTO getPayPal(@PathParam("usuarioId") Long Uid, @PathParam("id") Long id) throws BusinessLogicException
    {
        UsuarioEntity Uentity = uLogic.getUsuario(Uid);
        if(Uentity == null)
        {
                throw new WebApplicationException("el usuario al que le quiere agregar el recurso no existe", 404);
        }
      PayPalEntity entity = PPLogic.getPayPal(id);
      if(entity == null)
      {
          throw new WebApplicationException("el recurso no existe", 404);
         
      }
      return new PayPalDetailDTO(entity);
    }
    
     /**
     * <h1> DELETE /api/metdodosdepago/paypal/{id} : elimina una PP </h1>
     * <pre> Borra la cuenta Paypal identificado con un id unico
     * pasado por parametro.
     * 
     * Códigos de respuesta:<br>
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Elimina la PP correspondiente al id dado.</code>
     * <code style="color: #c7254e; background-color: #f9f2f4;">
     * 404 Not Found. No existe una PP con el id dado.
     * </code>
     * </pre>
     * @param id Identificador unico de la PP que se desea borrar.
     */
    @DELETE
    @Path("{id: \\d+}")
    public void deletePayPal (@PathParam("usuarioId") Long Uid, @PathParam("id") Long id) throws BusinessLogicException
    {
        UsuarioEntity Uentity = uLogic.getUsuario(Uid);
        if(Uentity == null)
        {
                throw new WebApplicationException("el usuario al que le quiere modificar el recurso no existe");
        }
         PayPalEntity entity = PPLogic.getPayPal(id);
        if (entity == null) {
            throw new WebApplicationException("La cuenta paypal no existe", 404);
        }
        
        PPLogic.deletePayPal(id);
       
       
    }

    
     /**
     * <h1> PUT /api/metdodosdepago/paypal/{id} : actualiza una cuenta Paypal </h1>
     * <pre> cuerpo de peticion : JSON.
     * 
     * Actuliza la cuenta Paypal identificada con el identificador, con la
     * informacion en el cuerpo de peticion.
     * 
     * Codigos de respuesta:
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Actualiza la cuenta Paypal con el id dado con la información enviada como parámetro. Retorna un objeto identico.</code> 
     * <code style="color: #c7254e; background-color: #f9f2f4;">
     * 404 Not Found. No existe una cuenta Paypal con el id dado.
     * </code> 
     * 
     * </pre>
     * @param Uid
     * @param id IDentificador de la cuenta Paypal que se desea actualizar representado
     * como una cadena de digitos.
     * @param paypal {@link PayPalDetailDTO} - La cuenta Paypal que se desea actualizar.
     * @return JSON - La cuenta Paypal guardada
     * @throws BusinessLogicException {@link BusinessLogicExceptionMapper}.
     */
    @PUT
    @Path("{id: \\d+}")
    public PayPalDetailDTO updatePayPal(@PathParam("usuarioId") Long Uid, @PathParam("id") Long id, PayPalDetailDTO paypal) throws BusinessLogicException
    {
        UsuarioEntity Uentity = uLogic.getUsuario(Uid);
        if(Uentity == null)
        {
                throw new WebApplicationException("el usuario al que le quiere agregar el recurso no existe");
        }
        PayPalEntity entity = paypal.toEntity();
        entity.setId(id);
        PayPalEntity oldEntity = PPLogic.getPayPal(id);
        if(oldEntity == null )
        {
            throw new WebApplicationException("La cuenta paypal no existe");
        }
        return new PayPalDetailDTO(PPLogic.updatePayPal(entity));
        
    }
    
    
    
}
