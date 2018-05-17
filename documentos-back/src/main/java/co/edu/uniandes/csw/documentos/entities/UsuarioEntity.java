/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.documentos.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
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
    private String correo;
    private int edad;
    private int genero;

    /**
     * reservas, relación de composición unidireccional
     */
    @PodamExclude
    @OneToMany( cascade = CascadeType.PERSIST, orphanRemoval = true)
    private List<ReservaEntity> reservas;

    /**
     * reservas, relación de composición unidireccional
     */
    @PodamExclude
    @OneToMany( cascade = CascadeType.ALL, orphanRemoval = true)
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
    @OneToMany( cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PayPalEntity> paypal;

    /**
     * tarjetasCredito, relación de composición bidireccional
     */
    @PodamExclude
    @OneToMany( cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TarjetaDeCreditoEntity> tarjetasCredito;

    /**
     * deseado, relación de composición unidireccional
     */
    @PodamExclude
    @OneToMany( cascade = CascadeType.PERSIST, orphanRemoval = true)
    private List<DeseadoEntity> deseados;

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
    public String getNombreUsuario() {
        return nombreUsuario;
    }

    /**
     *
     * @return edad del usuario
     */
    public int getEdad() {
        return edad;
    }

    /**
     *
     * @param pEdad nueva edad del usuario.
     */
    public void setEdad(int pEdad) {
        this.edad = pEdad;
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
    public List<DeseadoEntity> getDeseados() {
        return deseados;
    }

    /**
     * @param deseado the deseado to set
     */
    public void setDeseados(List<DeseadoEntity> deseado) {
        this.deseados = deseado;
    }

    /**
     * @return the correo
     */
    public String getCorreo() {
        return correo;
    }

    /**
     * @param correo the correo to set
     */
    public void setCorreo(String correo) {
        this.correo = correo;
    }

    /**
     * @return the genero
     */
    public int getGenero() {
        return genero;
    }

    /**
     * @param genero the genero to set
     */
    public void setGenero(int genero) {
        this.genero = genero;
    }

}
