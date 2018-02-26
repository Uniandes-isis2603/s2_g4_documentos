/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.documentos.ejb;

import co.edu.uniandes.csw.documentos.entities.AreaDeConocimientoEntity;
import co.edu.uniandes.csw.documentos.persistence.AreaDeConocimientoPersistence;
import co.edu.uniandes.csw.documentos.persistence.AutorPersistence;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author jc.jaramillo10
 */
@Stateless
public class AreaDeConocimientoLogic{
    
    private static final Logger LOGGER = Logger.getLogger(AreaDeConocimientoLogic.class.getName());

    @Inject
    private AreaDeConocimientoPersistence persistence;

    //@Inject
    //private DocumentoLogic DocumentoLogic;

    /**
     * Se encarga de crear un Area De Conocimiento en la base de datos.
     *
     * @param entity Objeto de AreaDeConocimientoEntity con los datos nuevos
     * @return Objeto de AutorEntity con los datos nuevos y su ID.
     */
    public AreaDeConocimientoEntity createAutor(AreaDeConocimientoEntity entity) {
        LOGGER.log(Level.INFO, "Inicia proceso de crear un Area de Conocimiento ");
       
        AreaDeConocimientoEntity buscado = persistence.find(entity.getId());
        if (buscado != null){
            LOGGER.log(Level.INFO, "El Area de Conocimiento con el id {0} ya existe ", entity.getId());
        }
        else if (!entity.getTipo().matches("([A-Z]|[a-z]|\\s)+")){
            LOGGER.log(Level.INFO, "El nombre del area de conocimiento no puede contener caracteres especiales");
        }
        else{
            return persistence.create(entity);
         }
        
        return null;
    }
}
