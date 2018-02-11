/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.documentos.dtos;

/**
 * Fotocopia DTO Objeto de transferencia de datos de Bookzon. Los DTO contienen 
 * las representaciones de los JSON que se transfiere entre el cliente y el servidor.
 * 
 * Extiende de DocumentoDTO debido a que tiene la misma informacion que este
 * si se quiere saber la documentacion ir a {@link DocumentoDTO}
 * Al serializarse como JSON esta clase implementa el modelo de {@link DocumentoDTO}
 * seguido de la informacion propia de la fotocopia: <br>
 * <pre>
 *  {
 *      *"id": Long,
 *      *"nombre": String,
 *      *"calificacion promedio": Double,
 *      *"descripcion" : String,
 *      *"precio" : Double,
 *      *"caratula" : String
 *      "profesor": String,
 *      "nroDePaginas": Integer,
 *      "capitulo" :String
 *  }
 * Los atributos con asterisco estan en la clase padre.
 * </pre>
 * por ejemplo un libro se representa asi: <br>
 * 
 * <pre>
 *  {
 *      "id": 1253,
 *      "nombre": "Ejercicios Fisica 1",
 *      "calificacion promedio": 3,2,
 *      "descripcion" : "ejercicios del libro de fisica"
 *      "precio" : 20500,
 *      "caratula" : "https://imagessl1.casadellibro.com/a/l/t0/51/97884975251.jpg",
 *      "profesor": "Carlos Avila",
 *      "nroPaginas" : 22,
 *      "capitulos" : "1, 2 y 3"
 *  }
 * </pre>
 * @author Ernesto Viana
 */
public class FotocopiaDTO extends DocumentoDTO{
    
    /**
     * Constructor por defecto
     */
    public FotocopiaDTO() {
        
    }
    
    private String profesor;
    private String nroPaginas;
    private String capitulos;

    /**
     * 
     * @return profesor que recomendo estas fotocopias.
     */
    public String getProfesor() {
        return profesor;
    }
    
    /**
     * 
     * @param profesor nuevo profesor
     */
    public void setProfesor(String profesor) {
        this.profesor = profesor;
    }

    /**
     *  
     * @return numero de paginas de las fotocopias.
     */
    public String getNroPaginas() {
        return nroPaginas;
    }

    /**
     * 
     * @param nroPaginas nuevo numero de paginas.
     */
    public void setNroPaginas(String nroPaginas) {
        this.nroPaginas = nroPaginas;
    }

    /**
     * 
     * @return capitulos en las fotocopias.
     */
    public String getCapitulos() {
        return capitulos;
    }

    /**
     * 
     * @param capitulos nuevos capitulos.
     */
    public void setCapitulos(String capitulos) {
        this.capitulos = capitulos;
    }
}
