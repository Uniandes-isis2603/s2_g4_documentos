/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.documentos.dtos;


import co.edu.uniandes.csw.documentos.entities.CompraEntity;
import java.util.List;

/**
 * Clase que extiende de {@link CompraDTO} para manejar la transformacion entre
 * los objetos JSON y las Entidades de la base de datos.Para conocer el
 * contenido del documento vaya a la documentacion de {@link CompraDTO}
 * 
 * @author n.sotelo
 */
public class CompraDetailedDTO extends CompraDTO 
{
     List<DocumentoDTO> documentos;
     
     MetodoDePagoDTO metodoDePago; 
/**
 * 
 * @return Metodo de pago con el cual se va a efectuar la compra
 */
    public MetodoDePagoDTO getMetodoDePago() {
        return metodoDePago;
    }
/**
 * 
 * @param metodoDePago el nuevo metodo de pago 
 */
    public void setMetodoDePago(MetodoDePagoDTO metodoDePago) {
        this.metodoDePago = metodoDePago;
    }
     /**
      * Constructor por defecto
      */
    public CompraDetailedDTO() 
    {
      
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
     @Override
      public CompraEntity toEntity()
    {
       CompraEntity rta= super.toEntity();
       return rta;
    }
    public CompraDetailedDTO(CompraEntity compra)
            
    {
        super(compra);
    }
}
