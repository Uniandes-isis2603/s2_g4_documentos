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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author f.marroquin10
 */
@Entity
public class DeseadoEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private double cantidad;

    /**
     * documentos, relación unidireccional
     */
    @PodamExclude
    @OneToMany
    private List<DocumentoEntity> documentos;

    @PodamExclude
    @ManyToOne
    private UsuarioEntity usuario;

    /**
     *
     * @return id.
     */
    public Long getId() {
        return id;
    }

    /**
     *
     * @return nombre .
     */
    public String getNombre() {
        return nombre;
    }

    /**
     *
     * @return cantidad de documentos deseados.
     */
    public double getCantidad() {
        return cantidad;
    }

    /**
     *
     * @param pId nuevo id.
     */
    public void setId(Long pId) {
        this.id = pId;
    }

    /**
     *
     * @param pNombre nuevo nombre de la lista de deseados.
     */
    public void setNombre(String pNombre) {
        this.nombre = pNombre;

    }

    /**
     *
     * @param pCantidad nuevo número de objetos en deseados.
     */
    public void setCantidad(double pCantidad) {
        this.cantidad = pCantidad;

    }

    /**
     * @return the documentos
     */
    public List<DocumentoEntity> getDocumentos() {
        return documentos;
    }

    /**
     * @param documentos the documentos to set
     */
    public void setDocumentos(List<DocumentoEntity> documentos) {
        this.documentos = documentos;
    }

    /**
     * @return the usuario
     */
    public UsuarioEntity getUsuario() {
        return usuario;
    }

    /**
     * @param usuario the usuario to set
     */
    public void setUsuario(UsuarioEntity usuario) {
        this.usuario = usuario;
    }

}
