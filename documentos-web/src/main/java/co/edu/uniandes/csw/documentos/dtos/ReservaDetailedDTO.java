/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.documentos.dtos;

import co.edu.uniandes.csw.documentos.entities.ComentarioEntity;
import co.edu.uniandes.csw.documentos.entities.CompraEntity;
import co.edu.uniandes.csw.documentos.entities.DocumentoEntity;
import co.edu.uniandes.csw.documentos.entities.ReservaEntity;
import co.edu.uniandes.csw.documentos.entities.ReservaEntity;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author f.marroquin10
 */
public class ReservaDetailedDTO extends ReservaDTO{
    
    /**
     * uno a maximo uno
     */
    private List<DocumentoDTO> documento;
    
    
    /**
     * constructor por defecto
     */
    
    public ReservaDetailedDTO()
    {
        
    }

        /**
     * Constructor para transformar un Entity a un DTO
     *
     * @param entity La entidad de la cual se construye el DTO
     */
    public ReservaDetailedDTO(ReservaEntity entity) {
        super(entity);
        if(entity.getDocumentos()!=null)
        {
           //this.documento= new DocumentoDTO( entity.getDocumentos());
            
        }
    }

    /**
     * Transformar el DTO a una entidad
     *
     * @return La entidad que representa el libro.
     */
    @Override
    public ReservaEntity toEntity() {
        
        ReservaEntity Reserva = super.toEntity();
        
        if (getDocumentos() != null) {
            List<DocumentoEntity> DocumentoEntity = new ArrayList<>();
            for (DocumentoDTO documento : getDocumentos()) {
                //DocumentoEntity.add(documento.toEntity());
            }
           Reserva.setDocumentos(DocumentoEntity);
        }

 
        return Reserva;
    }
    /**
     * @return the documento
     */
    public List<DocumentoDTO> getDocumentos() {
        return documento;
    }

    /**
     * @param documento the documento to set
     */
    public void setDocumentos(List<DocumentoDTO> documento) {
        this.documento = documento;
    }
}