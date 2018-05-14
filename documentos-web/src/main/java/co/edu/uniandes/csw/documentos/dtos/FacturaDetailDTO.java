/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.documentos.dtos;

import co.edu.uniandes.csw.documentos.entities.DocumentoEntity;
import co.edu.uniandes.csw.documentos.entities.FacturaEntity;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author g.ospinaa
 */
public class FacturaDetailDTO extends FacturaDTO{

    private List<DocumentoDTO> documentos;
    
    private UsuarioDTO usuario;
    
    /**
     * representa un metodo de pago con TDC
     */
    TarjetaDeCreditoDTO metodoDePagoTDC;
    /**
     *  represeta el metodo de pago con paypal
     */
    PayPalDTO metodoDePagoPayP;
    
    private Double costo;
    
    public FacturaDetailDTO()
    {
        
    }
    
    
    @Override
    public FacturaEntity toEntity() {
        FacturaEntity rta = super.toEntity();
        
        Double costox = 0.0;
        for(DocumentoDTO doc: documentos){
            costox = costox + doc.getPrecio() ;
            }
        rta.setCosto(costox);
        
        List<DocumentoEntity> lista= new ArrayList<>();
            for (DocumentoDTO agregar: documentos)
            {
                lista.add(agregar.toEntity());
                
            }
        rta.setDocumentos(lista);

        if(metodoDePagoPayP != null){
            rta.setMetodoDePagoPayP(metodoDePagoPayP.toEntity());
        }
        if(metodoDePagoTDC != null){
            rta.setMetodoDePagoTDC(metodoDePagoTDC.toEntity());
        }
        if(getUsuario() != null)
        {
            rta.setUsuario(getUsuario().toEntity());
        }
        return rta;
    }

    public FacturaDetailDTO(FacturaEntity entity) {
        super(entity);
        if (entity != null) {
            
            documentos= new ArrayList<>();
            for(DocumentoEntity  documento:entity.getDocumentos())
            {
                documentos.add(new DocumentoDTO(documento));
            }
            if(entity.getMetodoDePagoTDC() != null){
                metodoDePagoTDC = new TarjetaDeCreditoDTO(entity.getMetodoDePagoTDC());
            }
            else if(entity.getMetodoDePagoPayP() != null ){
                metodoDePagoPayP = new PayPalDTO(entity.getMetodoDePagoPayP());
            }
            if(entity.getUsuario() != null){
                usuario = new UsuarioDTO(entity.getUsuario());
            }
            if(entity.getCosto() != null){
                costo = entity.getCosto();
            }
        }

    }
    
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

    /**
     * @return the documentos
     */
    public List<DocumentoDTO> getDocumentos() {
        return documentos;
    }

    /**
     * @param documentos the documentos to set
     */
    public void setDocumentos(List<DocumentoDTO> documentos) {
        this.documentos = documentos;
    }

    /**
     * @return the usuario
     */
    public UsuarioDTO getUsuario() {
        return usuario;
    }

    /**
     * @param usuario the usuario to set
     */
    public void setUsuario(UsuarioDTO usuario) {
        this.usuario = usuario;
    }

    /**
     * @return the costo
     */
    public Double getCosto() {
        return costo;
    }

    /**
     * @param costo the costo to set
     */
    public void setCosto(Double costo) {
        this.costo = costo;
    }

}
