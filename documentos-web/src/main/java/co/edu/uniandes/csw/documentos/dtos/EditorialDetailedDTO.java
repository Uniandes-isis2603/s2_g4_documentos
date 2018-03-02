/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.documentos.dtos;

import co.edu.uniandes.csw.documentos.entities.CursoEntity;
import co.edu.uniandes.csw.documentos.entities.EditorialEntity;
import java.util.List;

/**
 * Clase que extiende de {@link EditorialDTO} para manejar la transformacion entre
 * los objetos JSON y las Entidades de la base de datos.Para conocer el
 * contenido del documento vaya a la documentacion de {@link EditorialDTO}
 * 
 * @author n.sotelo
 */
 
public class EditorialDetailedDTO extends EditorialDTO{
    
    private List<LibroDTO> libros;

   
/**
 * 
 * @return lista de libros que pertenecen a la editorial
 */
    public List<LibroDTO> getLibros() {
        return libros;
    }
/**
 * 
 * @param libros que se decean añadir a la editorial 
 */
    public void setLibros(List<LibroDTO> libros) 
    {
        this.libros = libros;
    }
    
/**
 * consturctor por defecto
 */
    public EditorialDetailedDTO() 
    {
      
    }
     public EditorialEntity toEntity()
    {
       EditorialEntity rta= super.toEntity();
       return rta;
    }
    public EditorialDetailedDTO( EditorialEntity entidad)
    {
        super(entidad);
    }
}
