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
        super();
    }
    
    public FotocopiaDetailDTO(FotocopiaEntity entity)
    {
        super(entity);
    }
    
    /**
     * Convierte el dto a entity
     * @return el entity.
     */
    @Override
    public FotocopiaEntity toEntity() {
        FotocopiaEntity fotocopiaE = new FotocopiaEntity();
        fotocopiaE.setId(super.getId());
        fotocopiaE.setNombre(super.getNombre());
        fotocopiaE.setPrecio(super.getPrecio());
        fotocopiaE.setCalificacionPromedio(super.getCalificacionPromedio());
        fotocopiaE.setCaratula(super.getCaratula());
        fotocopiaE.setDescripcion(super.getDescripcion());
        
        return fotocopiaE;
    }
    
}
