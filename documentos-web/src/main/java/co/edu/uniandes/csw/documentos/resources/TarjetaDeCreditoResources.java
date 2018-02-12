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
 *
 * @author g.ospinaa
 * <pre> Clase que implementa el recurso  "Metodos de Pago"
 * </pre>
 */
@Path("metodosdepago/tarjetaDeCredito")
@Produces ("application/json")
@Consumes("application/json")
@RequestScoped
public class TarjetaDeCreditoResources {
 
    
    /**
     * Crea una nueva tarjetaDeCredito para el usuario con la informacion que recibe 
     * por parametro y se regresa un objeto identico con un id unico creado por la base de datos.
     * 
     * @param TarjetaDeCredito {@link TarjetaDeCreditoDetailDTO} - La nueva tarjetaDeCredito
     * @return JDON
     * @throws BusinessLogicException
     */
    @POST
    public TarjetaDeCreditoDetailDTO createTDC(TarjetaDeCreditoDetailDTO TDC) throws BusinessLogicException
    {
        return TDC;
    }
    
     /**
     * 
     * Busca y devuelve todas las tarjetasDeCredito que tiene el usuario.
     * 
     * @return todas las TarjetasDeCredito que tiene el usuario.
     */
    @GET
    public List<TarjetaDeCreditoDetailDTO> getTDC()
    {
        return new ArrayList<>();
    }
    
    /**
     * 
     * <pre> Encuentra una tarjetaDeCredito identificada por un ID unico recibido en la URL y la devuelve.
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
     * 
     * <pre> cuerpo de peticion : JSON.
     * </pre>
     * Actuliza la tarjetaDeCredito identificada con el identificador, con la
     * informacion en el cuerpo de peticion.
     * 
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
     * <pre> Borra la tarjetaDeCredito identificado con un id unico
     * pasado por parametro.
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
