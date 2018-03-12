/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package co.edu.uniandes.csw.documentos.dtos;


import co.edu.uniandes.csw.documentos.entities.CompraEntity;
import co.edu.uniandes.csw.documentos.entities.DocumentoEntity;
import co.edu.uniandes.csw.documentos.dtos.TarjetaDeCreditoDetailDTO;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase que extiende de {@link CompraDTO} para manejar la transformacion entre
 * los objetos JSON y las Entidades de la base de datos.Para conocer el
 * contenido del documento vaya a la documenstacion de {@link CompraDTO}
 *
 * @author n.sotelo
 */
public class CompraDetailedDTO extends CompraDTO
{  

    public TarjetaDeCreditoDTO getMetodoDePagoTDC() {
        return metodoDePagoTDC;
    }

    public void setMetodoDePagoTDC(TarjetaDeCreditoDTO metodoDePagoTDC) {
        this.metodoDePagoTDC = metodoDePagoTDC;
    }

    public PayPalDTO getMetodoDePagoPayP() {
        return metodoDePagoPayP;
    }

    /**
     *  representa los documentos de que hacen parte de compras
     */
    public void setMetodoDePagoPayP(PayPalDTO metodoDePagoPayP) {
        this.metodoDePagoPayP = metodoDePagoPayP;
    }

     List<DocumentoDTO> documentos;
    /**
     * representa un metodo de pago con TDC
     */
    TarjetaDeCreditoDTO metodoDePagoTDC;
    /**
     *  represeta el metodo de pago con paypal
     */
    PayPalDTO metodoDePagoPayP;
    
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
        if(documentos!=null)
        {
            
            List<DocumentoEntity> lista= new ArrayList<>();
            for (DocumentoDTO agregar: documentos)
            {
                lista.add(agregar.toEntity());
                
            }
            rta.setDocumentos(lista);
            if ( metodoDePagoPayP!=null )
            {
                rta.setMetodoDePagoPayPal(metodoDePagoPayP.toEntity());
            }
            else if (metodoDePagoTDC!=null)
            {
             rta.setMetodoDePagoTDC(metodoDePagoTDC.toEntity());   
            }
        }
        return rta;
    }
    public CompraDetailedDTO(CompraEntity compra)
            
    {
        super(compra);
        if (compra!=null)
        {
            documentos= new ArrayList<>();
            for(DocumentoEntity  documento:compra.getDocumentos())
            {
                documentos.add(new DocumentoDTO(documento));
            }
            if (compra.getMetodoDePagoTDC()!=null)
            {
                metodoDePagoTDC= new TarjetaDeCreditoDTO(compra.getMetodoDePagoTDC());
            }
            else if (compra.getMetodoDePagoPayPal()!=null)
            {
                metodoDePagoPayP= new PayPalDTO(compra.getMetodoDePagoPayPal());
            }
        }
    }
}
