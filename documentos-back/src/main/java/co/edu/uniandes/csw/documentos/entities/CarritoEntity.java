/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.documentos.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author nicolassotelo
 */
@Entity
public class CarritoEntity implements Serializable

{
    @Id
    private Long id;
     
    @OneToMany
    @PodamExclude
    private List<DocumentoEntity> documentos; 

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<DocumentoEntity> getDocumentos() {
        return documentos;
    }

    public void setDocumentos(List<DocumentoEntity> documentos) {
        this.documentos = documentos;
    }
    
    
    
     
    
}
