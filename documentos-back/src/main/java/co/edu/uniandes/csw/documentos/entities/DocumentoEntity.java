/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.documentos.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author Ernesto Viana
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class DocumentoEntity implements Serializable {
    
    /**
     * Representa el id y llave primaria de la tabla. 
     * Se genera automaticamente.
     * 
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    /**
     * Representa el nombre del documento.
     */
    private String nombre;
    
    /**
     * Representa la califiacion del documento.
     */
    private Double calificacionPromedio;
    
    /**
     * Representa la descripcion del usuario que vende el documento.
     */
    private String descripcion;
    
    /**
     * Representa el precio del documento.
     */
    private Double precio;
    
    /**
     * Representa la direccion de la caratula.
     */
    private String caratula;
    
    /**
     * Representa los comentarios hechos a un documento.
     */
    @PodamExclude
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ComentarioEntity> comentarios = new ArrayList<>();
    
    /**
     * Representa las imagenes asociadas al documento.
     */
    @PodamExclude
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ImagenEntity> imagenes = new ArrayList<>();
    
    /**
     * Representa los autores del documento.
     */
    @PodamExclude
    @ManyToMany
    private List<AutorEntity> autores = new ArrayList<>();
    
    /**
     * Representa a las areas de coocimientos a las cuales el documento pertenece.
     */
    @PodamExclude
    @ManyToMany
    private List<AreaDeConocimientoEntity> areas = new ArrayList<>();
    
    /**
     * Representa a los cursos a los cuales puede pertenecer un documento.
     */
    @PodamExclude
    @ManyToMany
    private List<CursoEntity> cursos = new ArrayList<>();

    /**
     * Devuelve los comentarios de un documento.
     * @return los comentarios del documento.
     */
    public List<ComentarioEntity> getComentarios() {
        return comentarios;
    }

    /**
     * Modifica los comentarios de un documento.
     * @param comentarios nuevos.
     */
    public void setComentarios(List<ComentarioEntity> comentarios) {
        this.comentarios = comentarios;
    }

    /**
     * Devuelve las imagenes de un documento.
     * @return imagenes del documento.
     */
    public List<ImagenEntity> getImagenes() {
        return imagenes;
    }

    /**
     * Modifica las imagenes de un documento.
     * @param imagenes nuevas.
     */
    public void setImagenes(List<ImagenEntity> imagenes) {
        this.imagenes = imagenes;
    }

    /**
     * Devuelve los autores del documento.
     * @return autores del documento.
     */
    public List<AutorEntity> getAutores() {
        return autores;
    }

    /**
     * Modifica a los autores del documento.
     * @param autores nuevos.
     */
    public void setAutores(List<AutorEntity> autores) {
        this.autores = autores;
    }

    /**
     * Devuelve las areas de conocimiento del documento.
     * @return las areas de conocimiento del documento.
     */
    public List<AreaDeConocimientoEntity> getAreas() {
        return areas;
    }

    /**
     * Modifica las areas de conocimiento del documento.
     * @param areas nuevas.
     */
    public void setAreas(List<AreaDeConocimientoEntity> areas) {
        this.areas = areas;
    }

    /**
     * Devuelve los cursos a los que pertenece el documento.
     * @return curos del documento.
     */
    public List<CursoEntity> getCursos() {
        return cursos;
    }

    /**
     * Modifica los cursos a los que pertenece un documento.
     * @param cursos nuevos.
     */
    public void setCursos(List<CursoEntity> cursos) {
        this.cursos = cursos;
    }

    /**
     * 
     * @return el identificador.
     */
    public Long getId() {
        return id;
    }

    /**
     * 
     * @param id id nuevo.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 
     * @return el nombre del documento.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * 
     * @param nombre nombre nuevo.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * 
     * @return la califiacion promedio del documento.
     */
    public Double getCalificacionPromedio() {
        return calificacionPromedio;
    }

    /**
     * 
     * @param calificacionPromedio calificacion nueva.
     */
    public void setCalificacionPromedio(Double calificacionPromedio) {
        this.calificacionPromedio = calificacionPromedio;
    }

    /**
     * 
     * @return la descripcion del documento.
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * 
     * @param descripcion nueva descripcion.
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * 
     * @return El precio del documento.
     */
    public Double getPrecio() {
        return precio;
    }

    /**
     * 
     * @param precio nuevo precio.
     */
    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    /**
     * 
     * @return direccion de la caratula del documento
     */
    public String getCaratula() {
        return caratula;
    }

    /**
     * 
     * @param caratula nueva del documento.
     */
    public void setCaratula(String caratula) {
        this.caratula = caratula;
    }
    
}
