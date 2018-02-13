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
public class CursoDetailedDTO  extends CursoDTO{
    List<DocumentoDTO> bibliografiaDelCurso;

    public List<DocumentoDTO> getBibliografiaDelCurso() {
        return bibliografiaDelCurso;
    }

    public void setBibliografiaDelCurso(List<DocumentoDTO> bibliografiaDelCurso) {
        this.bibliografiaDelCurso = bibliografiaDelCurso;
    }

    public CursoDetailedDTO() {
    }
    
    
}
