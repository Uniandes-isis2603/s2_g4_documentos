/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.documentos.ejb;

import co.edu.uniandes.csw.documentos.entities.AutorEntity;
import co.edu.uniandes.csw.documentos.entities.ImagenEntity;
import co.edu.uniandes.csw.documentos.persistence.AutorPersistence;
import co.edu.uniandes.csw.documentos.persistence.ImagenPersistence;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author jc.jaramillo10
 */
@Stateless
public class ImagenLogic {
    
    private static final Logger LOGGER = Logger.getLogger(ImagenLogic.class.getName());

    @Inject
    private ImagenPersistence persistence;

    //@Inject
    //private DocumentoLogic DocumentoLogic;
    
    /**
     * Se encarga de crear un Autor en la base de datos.
     *
     * @param entity Objeto de AutorEntity con los datos nuevos
     * @return Objeto de AutorEntity con los datos nuevos y su ID.
     */
    public ImagenEntity createAutor(ImagenEntity entity) {
        LOGGER.log(Level.INFO, "Inicia proceso de crear un autor ");
       
        ImagenEntity buscado = persistence.find(entity.getId());
        if (buscado != null){
            LOGGER.log(Level.INFO, "La Imagen con el id {0} ya existe ", entity.getId());
        }
        else if (!entity.getNombre().matches("([A-Z]|[a-z]|\\s)+")){
            LOGGER.log(Level.INFO, "El nombre de la imagen no puede contener caracteres especiales");
        }
        else if(entity.getImg().endsWith(".jpg") | entity.getImg().endsWith(".png") | entity.getImg().endsWith(".pdf") | entity.getImg().endsWith(".bmp") | entity.getImg().endsWith(".tif")){
            LOGGER.log(Level.INFO, "La Imagen tiene un formato no soportado");
        }
        else{
            return persistence.create(entity);
         }
        
        return null;
    }
}
