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
import javax.persistence.*;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author f.marroquin10
 */
@Entity
public class UsuarioEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String nombreUsuario;
    private String contraseña;

    /**
     * reservas, relación de composición unidireccional
     */
    @PodamExclude
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ReservaEntity> reservas;

    /**
     * reservas, relación de composición unidireccional
     */
    @PodamExclude
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CompraEntity> compras;

    /**
     * comentarios, relación unidireccional
     */
    @PodamExclude
    @OneToMany
    private List<ComentarioEntity> comentarios;

    /**
     * paypal, relación de composición biidireccional
     */
    @PodamExclude
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PayPalEntity> paypal;

    /**
     * tarjetasCredito, relación de composición bidireccional
     */
    @PodamExclude
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TarjetaDeCreditoEntity> tarjetasCredito;

    /**
     * deseado, relación de composición unidireccional
     */
    @PodamExclude
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DeseadoEntity> deseado;

    /**
     *
     * @return id del usuario.
     */
    public Long getId() {
        return id;
    }

    /**
     *
     * @return nombre del usuario
     */
    public String getNombre() {
        return nombre;
    }

    /**
     *
     * @return nombre de usuario del usuario.
     */
    public String getUserName() {
        return nombreUsuario;
    }

    /**
     *
     * @return contraseña del usuario
     */
    public String getContraseña() {
        return contraseña;
    }

    /**
     *
     * @param pId nuevo id del usuario.
     */
    public void setId(Long pId) {
        this.id = pId;
    }

    /**
     *
     * @param pNombre nuevo nombre del usuario.
     */
    public void setNombre(String pNombre) {
        this.nombre = pNombre;

    }

    /**
     *
     * @param pNombre nuevo nombre de usuari0.
     */
    public void setNombreUsuario(String pNombre) {
        this.nombreUsuario = pNombre;
    }

    /**
     *
     * @param pContraseña nueva contraseña del usuario.
     */
    public void setContraseña(String pContraseña) {
        this.contraseña = pContraseña;

    }

}
