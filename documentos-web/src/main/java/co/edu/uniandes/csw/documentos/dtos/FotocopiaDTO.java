/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.documentos.dtos;

import co.edu.uniandes.csw.documentos.entities.FotocopiaEntity;

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
    
    /**
     * Constructor a partir de la entidad
     * @param fotocopiaE La entidad de la fotocopia.
     */
    public FotocopiaDTO(FotocopiaEntity fotocopiaE) {
        super(fotocopiaE);
        if(fotocopiaE != null) {
            this.capitulos = fotocopiaE.getCapitulo();
            this.nroPaginas = fotocopiaE.getNroPaginas();
            this.profesor = fotocopiaE.getProfesor();
            
        }
    }
    /**
     * Transforma un dto a entity.
     * @return un entity que tiene la informacion del dto.
     */
    @Override
    public FotocopiaEntity toEntity(){
        
        FotocopiaEntity fotocopiaE = new FotocopiaEntity();
        fotocopiaE.setId(this.getId());
        fotocopiaE.setNombre(this.getNombre());
        fotocopiaE.setNroPaginas(this.nroPaginas);
        fotocopiaE.setPrecio(this.getPrecio());
        fotocopiaE.setProfesor(this.profesor);
        fotocopiaE.setCalificacionPromedio(this.getCalificacionPromedio());
        fotocopiaE.setCapitulo(this.capitulos);
        fotocopiaE.setCaratula(this.getCaratula());
        fotocopiaE.setDescripcion(this.getDescripcion());
        fotocopiaE.setPdf(this.getPdf());
        return fotocopiaE;
    }
    
    /**
     * Profesor de la fotocopia.
     */
    private String profesor;
    
    /**
     * nroPaginas de la fotocopia.
     */
    private Integer nroPaginas;
    
    /**
     * capitulos de la fotocopia.
     */
    private String capitulos;
    
    /**
     * PDF del documento
     */
    private String pdf;

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
    public Integer getNroPaginas() {
        return nroPaginas;
    }

    /**
     * 
     * @param nroPaginas nuevo numero de paginas.
     */
    public void setNroPaginas(Integer nroPaginas) {
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
