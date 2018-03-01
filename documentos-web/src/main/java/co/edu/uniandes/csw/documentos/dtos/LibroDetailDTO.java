/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.documentos.dtos;

import co.edu.uniandes.csw.documentos.entities.LibroEntity;

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
     * Constructor que recibe un libroentity y lo convierte a dto.
     * @param libroE libro que va a convertir a dto.
     */
    public LibroDetailDTO(LibroEntity libroE){
        
        super(libroE);
    }
    
    @Override
    public LibroEntity toEntity(){
        LibroEntity libroE =  new LibroEntity();
        libroE.setId(super.getId());
        libroE.setNombre(super.getNombre());
        libroE.setCalificacionPromedio(super.getCalificacionPromedio());
        libroE.setPrecio(super.getPrecio());
        libroE.setDescripcion(super.getDescripcion());
        libroE.setCaratula(super.getCaratula());
        return libroE;
    }
    
    /**
     * Relacion de cero a uno de una editorial
     */
    private EditorialDTO editorial;

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
    
}
