/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.documentos.dtos;

import co.edu.uniandes.csw.documentos.entities.LibroEntity;
import java.util.Date;

/**
 * LibroDTO Objeto de transferencia de datos de Bookzon. Los DTO contienen 
 * las representaciones de los JSON que se transfiere entre el cliente y el servidor.
 * 
 * Extiende de DocumentoDTO debido a que tiene la misma informacion que este
 * si se quiere saber la documentacion ir a {@link DocumentoDTO}
 * Al serializarse como JSON esta clase implementa el modelo de {@link DocumentoDTO}
 * seguido de la informacion propia del libro: <br>
 * <pre>
 *  {
 *      *"id": Long,
 *      *"nombre": String,
 *      *"calificacion promedio": Double,
 *      *"descripcion" : String,
 *      *"precio" : Double,
 *      *"caratula" : String
 *      "ISBN": String,
 *      "FechaPublicacion": Date
 *  }
 * Los atributos con asterisco estan en la clase padre.
 * </pre>
 * por ejemplo un libro se representa asi: <br>
 * 
 * <pre>
 *  {
 *      "id": 1203,
 *      "nombre": "Amor en los tiempos del colera",
 *      "calificacion promedio": 4,1,
 *      "descripcion" : "El amor en los tiempos del cólera es una novela del escritor colombiano Gabriel García Márquez, publicada en 1985.Es una novela dedicada al verdadero amor. La novela se inspiró en la forma en que se desarrolló la relación de los padres de García Márquez. Para escribirla se entrevistó durante varios días con sus padres, cada uno por separado, para encontrar más detalles de cómo iba a escribir la novela.",
 *      "precio" : 20500,
 *      "caratula" : "https://imagessl1.casadellibro.com/a/l/t0/51/9788497592451.jpg",
 *      "ISBN": "9788497592451",
 *      "FechaPublicacion" : 22/11/1985
 *  }
 * </pre>
 * @author Ernesto Viana
 */
public class LibroDTO extends DocumentoDTO {
    
    /**
     * Constructor por defecto
     */
    public LibroDTO() {
        
    }
    
    /**
     * Constructor para transformar un entity a dto.
     * @param libroE la entidad de la cual se construye el dto.
     */
    public LibroDTO(LibroEntity libroE) {
        if(libroE != null)
        {
            super.setId(libroE.getId());
            super.setNombre(libroE.getNombre());
            super.setPrecio(libroE.getPrecio());
            super.setCalificacionPromedio(libroE.getCalificacionPromedio());
            super.setCaratula(libroE.getCaratula());
            super.setDescripcion(libroE.getDescripcion());
            this.ISBN = libroE.getIsbn();
            this.fechaPublicacion = libroE.getFechaPublicacion();
        }
    }
    
    @Override
    public LibroEntity toEntity(){
        LibroEntity libroE = new LibroEntity();
        libroE.setId(super.getId());
        libroE.setNombre(super.getNombre());
        libroE.setPrecio(super.getPrecio());
        libroE.setCalificacionPromedio(super.getCalificacionPromedio());
        libroE.setCaratula(super.getCaratula());
        libroE.setDescripcion(super.getDescripcion());
        libroE.setFechaPublicacion(this.fechaPublicacion);
        libroE.setIsbn(this.ISBN);
        return libroE;
    }
    
    private String ISBN;
    private Date fechaPublicacion;

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
