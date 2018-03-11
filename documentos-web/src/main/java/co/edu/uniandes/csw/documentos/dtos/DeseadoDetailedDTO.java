/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.documentos.dtos;

import co.edu.uniandes.csw.documentos.entities.DocumentoEntity;
import co.edu.uniandes.csw.documentos.entities.DeseadoEntity;
import co.edu.uniandes.csw.documentos.entities.ReservaEntity;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author f.marroquin10
 */
public class DeseadoDetailedDTO extends DeseadoDTO{
    
    /**
     * cero a muchos
     */
    private List<DocumentoDTO> documentos;
    /**
     * constructor por defecto
     */
    public DeseadoDetailedDTO()
    {
        
    }
    
          /**
     * Constructor para transformar un Entity a un DTO
     *
     * @param entity La entidad de la cual se construye el DTO
     */
    public DeseadoDetailedDTO(DeseadoEntity entity) {
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
    public DeseadoEntity toEntity() {
        
        DeseadoEntity deseado = super.toEntity();
        
        if (getDocumentos() != null) {
            List<DocumentoEntity> DocumentoEntity = new ArrayList<>();
            for (DocumentoDTO documento : getDocumentos()) {
                //DocumentoEntity.add(documento.toEntity());
            }
           deseado.setDocumentos(DocumentoEntity);
        }

 
        return deseado;
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
}
