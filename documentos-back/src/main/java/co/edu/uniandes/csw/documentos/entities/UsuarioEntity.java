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
    private int edad;

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
     * @return edad del usuario
     */
    public int getEdad()
    {
        return edad;
    }
    
     /**
     *
     * @param pEdad nueva edad del usuario.
     */
    public void setEdad(int pEdad)
    {
        this.edad=pEdad;
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

    /**
     * @return the reservas
     */
    public List<ReservaEntity> getReservas() {
        return reservas;
    }

    /**
     * @param reservas the reservas to set
     */
    public void setReservas(List<ReservaEntity> reservas) {
        this.reservas = reservas;
    }

    /**
     * @return the compras
     */
    public List<CompraEntity> getCompras() {
        return compras;
    }

    /**
     * @param compras the compras to set
     */
    public void setCompras(List<CompraEntity> compras) {
        this.compras = compras;
    }

    /**
     * @return the comentarios
     */
    public List<ComentarioEntity> getComentarios() {
        return comentarios;
    }

    /**
     * @param comentarios the comentarios to set
     */
    public void setComentarios(List<ComentarioEntity> comentarios) {
        this.comentarios = comentarios;
    }

    /**
     * @return the paypal
     */
    public List<PayPalEntity> getPaypal() {
        return paypal;
    }

    /**
     * @param paypal the paypal to set
     */
    public void setPaypal(List<PayPalEntity> paypal) {
        this.paypal = paypal;
    }

    /**
     * @return the tarjetasCredito
     */
    public List<TarjetaDeCreditoEntity> getTarjetasCredito() {
        return tarjetasCredito;
    }

    /**
     * @param tarjetasCredito the tarjetasCredito to set
     */
    public void setTarjetasCredito(List<TarjetaDeCreditoEntity> tarjetasCredito) {
        this.tarjetasCredito = tarjetasCredito;
    }

    /**
     * @return the deseado
     */
    public List<DeseadoEntity> getDeseado() {
        return deseado;
    }

    /**
     * @param deseado the deseado to set
     */
    public void setDeseado(List<DeseadoEntity> deseado) {
        this.deseado = deseado;
    }

    
    
}
