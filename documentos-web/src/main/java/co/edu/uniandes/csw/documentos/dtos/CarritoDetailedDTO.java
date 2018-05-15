/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.documentos.dtos;

import co.edu.uniandes.csw.documentos.entities.CarritoEntity;
import co.edu.uniandes.csw.documentos.entities.DocumentoEntity;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author nicolassotelo
 */
public class CarritoDetailedDTO  extends CarritoDTO

{
    
    
    /**
 * Lista de los documentos asociados al carro
 */
     List<DocumentoDTO> documentos;
/**
 * Constructor por defecto
 */
   public CarritoDetailedDTO()
    {
    super();
    }
   /**
    * Consructor para pasar una entidad a dto
    * @param carrito entidad a convertir 
    */
    public CarritoDetailedDTO(CarritoEntity carrito)
            
    {
        super(carrito);
        if (carrito!=null)
        {
            List<DocumentoDTO> lista= new ArrayList<>();
            
          for (DocumentoEntity en :carrito.getDocumentos()) 
          {
                lista.add(new DocumentoDTO(en));
                
            }
         this.documentos=lista;
        }
         
    }

    /**
     *
     * @return
     */
    @Override
    public CarritoEntity toEntity()
    {
        CarritoEntity rta= super.toEntity();
        if(documentos!=null)
        {
            
            List<DocumentoEntity> lista= new ArrayList<>();
            
          for (DocumentoDTO en :documentos) 
          {
                lista.add(en.toEntity());
                
            }
            rta.setDocumentos(lista);
           
            
        }
        return rta;
    }
   
    /**
     *
     * @return Documentos pertenecientes a una compra
     */
    public List<DocumentoDTO> getDocumentos() {
        return documentos;
    }
    /**
     *
     * @param documentos con los cuales se desea efectuar una compra
     */
    public void setDocumentos(List<DocumentoDTO> documentos) {
        this.documentos = documentos;
    }
 
  
}
