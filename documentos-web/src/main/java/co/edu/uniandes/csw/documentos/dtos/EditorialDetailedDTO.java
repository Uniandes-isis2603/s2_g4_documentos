/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.documentos.dtos;

import java.util.List;

/**
 *
 * @author n.sotelo
 */
public class EditorialDetailedDTO extends EditorialDTO{
    
    private List<LibrosDTO> libros;

    public List<LibrosDTO> getLibros() {
        return libros;
    }

    public void setLibros(List<LibrosDTO> libros) 
    {
        this.libros = libros;
    }
    

    public EditorialDetailedDTO() {
        super();
    }
    
}
