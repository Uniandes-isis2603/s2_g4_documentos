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
public class AutorEntity implements Serializable{
    
    /**
     * Id que identifica el Autor
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    /**
     * Nombre del Autor
    */
    private String nombre;
    
    /**
     * Relación de Documentos a Autores
     */
    @PodamExclude
    @ManyToMany(mappedBy = "autores", fetch = FetchType.LAZY)
    private List<DocumentoEntity> documentos = new ArrayList<DocumentoEntity>();
    
    /**
     * Retorna el Id del Autor
     * @return id. El Id del Autor
     */
    public Long getId() {
        return id;
    }

    /**
     * Asigna un nuevo id al Autor
     * @param id Nuevo id del Autor
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Retorna el nombre del autor
     * @return nombre. El nombre del autor
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Asigna el nuevo nombre al autor
     * @param nombre Nuevo nombre del autor 
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    /**
     * Retorna una nueva lista de Documentos al Autor
     * @return documentos La lista de documentos asociada al area de Conocimiento
     */
    public List<DocumentoEntity> getDocumentos() {
        return documentos;
    }
    /**
     * Coloca una nueva lista de Documentos al Autor
     * @param documentos La nueva lista de documentos asociada al autor
     */
    public void setDocumentos(List<DocumentoEntity> documentos) {
        this.documentos = documentos;
    }
     
}
