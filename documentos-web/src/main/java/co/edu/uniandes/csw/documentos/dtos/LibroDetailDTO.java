/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.documentos.dtos;

import co.edu.uniandes.csw.documentos.entities.LibroEntity;
import java.util.Date;

/**
 *Clase que extiende de {@link DocumentoDetailDTO} para manejar la transformacion entre
 * los objetos JSON y las Entidades de la base de datos. Para conocer el
 * contenido del documento vaya a la documentacion de {@link DocumentoDetailDTO}
 * @author Ernesto Viana
 */
public class LibroDetailDTO extends DocumentoDetailDTO {
    
    /**
     * Constructor por defecto
     */
    public LibroDetailDTO(){
        
    }
    
    /**
     * Constructor para transformar de entity a dto.
     * @param libroE entity que se va a transformar.
     */
    public LibroDetailDTO(LibroEntity libroE)
    {
        super(libroE);
        if(libroE != null)
        {
            this.ISBN = libroE.getIsbn();
            this.fechaPublicacion =libroE.getFechaPublicacion();
            
            if(libroE.getEditorial() != null)
            {
               this.editorial = new EditorialDTO(libroE.getEditorial());
            } else {
                libroE.setEditorial(null);
            }
        }
    }
    
    /**
     * Transforma de dto a entity.
     * @return entity terminado.
     */
    @Override
    public LibroEntity toEntity() {
        LibroEntity libroE = new LibroEntity();
        libroE.setId(super.toEntity().getId());
        libroE.setImagenes(super.toEntity().getImagenes());
        libroE.setIsbn(this.ISBN);
        libroE.setNombre(super.toEntity().getNombre());
        libroE.setPrecio(super.toEntity().getPrecio());
        libroE.setAreas(super.toEntity().getAreas());
        libroE.setAutores(super.toEntity().getAutores());
        libroE.setCalificacionPromedio(super.toEntity().getCalificacionPromedio());
        libroE.setCaratula(super.toEntity().getCaratula());
        libroE.setComentarios(super.toEntity().getComentarios());
        libroE.setCursos(super.toEntity().getCursos());
        libroE.setDescripcion(super.toEntity().getDescripcion());
        libroE.setFechaPublicacion(this.fechaPublicacion);
        if(this.getEditorial() != null) {
          libroE.setEditorial(this.getEditorial().toEntity());
        }
        
        return libroE;
    }
    
    /**
     * Relacion de cero a uno de una editorial
     */
    private EditorialDTO editorial;
    
    /**
     * isbn del libro.
     */
    private String ISBN;
    
    /**
     * fecha de publicacion del libro.
     */
    private Date fechaPublicacion;
    

    /**
     * Devuelve la editorial a la que pertenece el libro.
     * @return editorial del libro.
     */
    public EditorialDTO getEditorial() {
        return editorial;
    }

    /**
     * Modifica la editorial a la que pertenece el libro
     * @param editorial nueva del libro.
     */
    public void setEditorial(EditorialDTO editorial) {
        this.editorial = editorial;
    }
    
    /**
     * 
     * @return el codigo ISBN del libro
     */
    public String getISBN() {
        return ISBN;
    }

    /**
     * 
     * @param ISBN nuevo codigo. 
     */
    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    /**
     * 
     * @return fecha de la publicacion del libro.
     */
    public Date getFechaPublicacion() {
        return fechaPublicacion;
    }

    /**
     * 
     * @param fechaPublicacion nueva fecha de publicacion.
     */
    public void setFechaPublicacion(Date fechaPublicacion) {
        this.fechaPublicacion = fechaPublicacion;
    }
}
