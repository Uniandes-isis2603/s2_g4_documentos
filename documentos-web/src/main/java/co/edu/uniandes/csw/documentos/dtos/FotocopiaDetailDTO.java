/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.documentos.dtos;

import co.edu.uniandes.csw.documentos.entities.FotocopiaEntity;

/**
 * Clase que extiende de {@link DocumentoDetailDTO} para manejar la transformacion entre
 * los objetos JSON y las Entidades de la base de datos. Para conocer el
 * contenido del documento vaya a la documentacion de {@link DocumentoDetailDTO}
 * @author Ernesto Viana
 */
public class FotocopiaDetailDTO extends DocumentoDetailDTO {
    
    /**
     * Constructor por defecto
     */
    public FotocopiaDetailDTO(){
        
    }
    
    /**
     * Constructor basado en entity.
     * @param fotocopiaE entity que se va a crear.
     */
    public FotocopiaDetailDTO(FotocopiaEntity fotocopiaE) {
        super(fotocopiaE);
        if(fotocopiaE != null){
            this.capitulos = fotocopiaE.getCapitulo();
            this.nroPaginas = fotocopiaE.getNroPaginas();
            this.profesor = fotocopiaE.getProfesor();
        }
    }
    
    /**
     * Transforma un dto a entity
     * @return entidad que representa la fotocopia.
     */
    @Override
    public FotocopiaEntity toEntity() {
        FotocopiaEntity fotocopiaE = new FotocopiaEntity();
        
        fotocopiaE.setId(super.toEntity().getId());
        fotocopiaE.setImagenes(super.toEntity().getImagenes());
        fotocopiaE.setNombre(super.toEntity().getNombre());
        fotocopiaE.setNroPaginas(this.nroPaginas);
        fotocopiaE.setPrecio(super.toEntity().getPrecio());
        fotocopiaE.setProfesor(this.profesor);
        fotocopiaE.setAreas(super.toEntity().getAreas());
        fotocopiaE.setAutores(super.toEntity().getAutores());
        fotocopiaE.setCalificacionPromedio(super.toEntity().getCalificacionPromedio());
        fotocopiaE.setCapitulo(this.capitulos);
        fotocopiaE.setCaratula(super.toEntity().getCaratula());
        fotocopiaE.setComentarios(super.toEntity().getComentarios());
        fotocopiaE.setCursos(super.toEntity().getCursos());
        fotocopiaE.setDescripcion(super.toEntity().getDescripcion());

        return fotocopiaE;
    }
    
    
    private String profesor;
    private Integer nroPaginas;
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
