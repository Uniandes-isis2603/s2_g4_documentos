/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.documentos.dtos;

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
