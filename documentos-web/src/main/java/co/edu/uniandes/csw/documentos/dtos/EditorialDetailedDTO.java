/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.documentos.dtos;
import co.edu.uniandes.csw.documentos.entities.EditorialEntity;
import co.edu.uniandes.csw.documentos.entities.LibroEntity;
import java.util.ArrayList;
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
 * consturctor por defecto
 */
    public EditorialDetailedDTO() 
    {
      //constructor por defecto
    }
    
     public EditorialDetailedDTO( EditorialEntity entidad)
    {
        super(entidad);
        if(entidad!=null)
        {
            List<LibroDTO> lista= new ArrayList<>();
            for (LibroEntity en :entidad.getLibros()) {
                lista.add(new LibroDTO(en));
                
            }
            this.setLibros(lista);
        }
    }
     @Override
     public EditorialEntity toEntity()
    {
       EditorialEntity rta= super.toEntity();
       if (libros != null) {
            List<LibroEntity> listaEntity = new ArrayList<>();
            for (LibroDTO dtoCurso : libros) 
            {
                listaEntity.add(dtoCurso.toEntity());
            }
            rta.setLibros(listaEntity);
        }
       return rta;
    }
     
   
    
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
    
}
