/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.documentos.dtos;

import java.util.List;

/**
 *
 * @author n.sotelo
 */
public class DeseadoDetailedDTO extends DesesadoDTO{
    
    private List<DocumentoDTO> documentos;

    public DeseadoDetailedDTO() {
        super();
    }

    public List<DocumentoDTO> getDocumentos() {
        return documentos;
    }

    public void setDocumentos(List<DocumentoDTO> documentos) {
        this.documentos = documentos;
    }
    
}
