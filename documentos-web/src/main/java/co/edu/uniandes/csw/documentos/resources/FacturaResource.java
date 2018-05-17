/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.documentos.resources;

import co.edu.uniandes.csw.documentos.dtos.FacturaDetailDTO;
import co.edu.uniandes.csw.documentos.ejb.FacturaLogic;
import co.edu.uniandes.csw.documentos.entities.FacturaEntity;
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
 *
 * @author g.ospinaa
 */
@Path("facturas")
@Produces ("application/json")
@Consumes("application/json")
@RequestScoped
public class FacturaResource {
    
    @Inject
    private FacturaLogic logica;
    
    @POST
    public FacturaDetailDTO createFactura(FacturaDetailDTO fac ) throws BusinessLogicException 
    {
         FacturaEntity editorialEntity = fac.toEntity();
              
         
       FacturaEntity nuevoEditorial = logica.createFactura(editorialEntity);
       
       
       return new FacturaDetailDTO(nuevoEditorial);
        
    }
    
    @GET
    public List<FacturaDetailDTO> getTodasFacturas()throws BusinessLogicException 
    {
         List<FacturaDetailDTO> lista=new ArrayList<>();
        for (FacturaEntity en :logica.getFacturas()) 
        {
           
           lista.add(new FacturaDetailDTO(en));
        }
        
        return lista;
    }
    
    @GET
    @Path("{idFac: \\d+}")
    public FacturaDetailDTO getFactura(@PathParam("idFac") Long idFac) throws BusinessLogicException
    {
      return new  FacturaDetailDTO(logica.getFactura(idFac));
    }
    
    
    @GET
    @Path("usuario/{id: \\d+}")
    public List<FacturaDetailDTO> getFacturasUsuario(@PathParam("id") Long id) throws BusinessLogicException
    {
       List<FacturaDetailDTO> lista=new ArrayList<>();
        for (FacturaEntity en :logica.getFacturasUsuario(id)) 
        {
           lista.add(new FacturaDetailDTO(en));
        }
        
        return lista;
    }

    
}
