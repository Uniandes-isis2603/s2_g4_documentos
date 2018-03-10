/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.documentos.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author Camilojaravila
 */
@Entity
public class AreaDeConocimientoEntity implements Serializable {
    
    /**
     * Id que identifica el Area de Conocimiento
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    /**
     * Tipo de Area de Conocimiento
     */
    private String tipo;
    
    /**
     * Relación de Documentos a Areas de Conocimiento
     */
    @PodamExclude
    @ManyToMany(mappedBy = "areas", fetch = FetchType.LAZY)
    private List<DocumentoEntity> documentos = new ArrayList<DocumentoEntity>();

    /**
     * Retorna el Id del Area de Conocimiento
     * @return id. El id del area de conocimitno
     */
    public Long getId() {
        return id;
    }

    /**
     * Coloca un nuevo id al Area de Conocimiento
     * @param id El nuevo Id de Area de Conocimiento
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Retorna el Tipo de Area de Conocimiento
     * @return tipo. El tipo de Area de Conocimiento
     */
    public String getTipo() {
        return tipo;
    }

    /**
     * Coloca un nuevo tipo al Area de Conocimiento
     * @param tipo El nuevo tipo de Area de Conocimiento
     */
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    /**
     * Retorna una nueva lista de Documentos al Area de Conocimiento
     * @return documentos La lista de documentos asociada al area de Conocimiento
     */
    public List<DocumentoEntity> getDocumentos() {
        return documentos;
    }
    /**
     * Coloca una nueva lista de Documentos al Area de Conocimiento
     * @param documentos La nueva lista de documentos asociada al area de conocimiento
     */
    public void setDocumentos(List<DocumentoEntity> documentos) {
        this.documentos = documentos;
    }
     
}
