/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.documentos.ejb;

import co.edu.uniandes.csw.documentos.entities.ImagenEntity;
import co.edu.uniandes.csw.documentos.persistence.ImagenPersistence;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author Camilojaravila
 */
@Stateless
public class ImagenLogic {
    
    private static final Logger LOGGER = Logger.getLogger(ImagenLogic.class.getName());

    @Inject
    private ImagenPersistence persistence;

    //@Inject
    //private DocumentoLogic DocumentoLogic;
    
    /**
     * Se encarga de crear una Imagen en la base de datos.
     *
     * @param entity Objeto de ImagenEntity con los datos nuevos
     * @return Objeto de ImagenEntity con los datos nuevos y su ID.
     */
    public ImagenEntity createImagen(ImagenEntity entity) {
        LOGGER.log(Level.INFO, "Inicia proceso de crear un autor ");
       
        ImagenEntity buscado = persistence.find(entity.getId());
        if (buscado != null){
            LOGGER.log(Level.INFO, "La Imagen con el id {0} ya existe ", entity.getId());
        }
        else if (!entity.getNombre().matches("([A-Z]|[a-z]|[0-9]|\\s)+")){
            LOGGER.log(Level.INFO, "El nombre de la imagen no puede contener caracteres especiales");
        }
        else if(!(entity.getImg().endsWith(".jpg") | entity.getImg().endsWith(".png") | entity.getImg().endsWith(".pdf") | entity.getImg().endsWith(".bmp") | entity.getImg().endsWith(".tif"))){
            LOGGER.log(Level.INFO, "La Imagen tiene un formato no soportado");
        }
        else{
            return persistence.create(entity);
         }
        
        return null;
    }
    
             /**
     * Obtiene la lista de los registros de Imagenes de un Documento dado.
     *
     * @return Colección de objetos de ImagenEntity.
     */
    public List<ImagenEntity> getImagenes() {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar todas las imagenes");
        return persistence.findAll();
    }
    
     /**
     * Obtiene los datos de una instancia de una Imagen a partir de su ID.
     *
     * @param id Identificador de la instancia a consultar
     * @return Instancia de ImagenEntity con los datos de la Imagen consultada.
     */
    public ImagenEntity getImagen(Long id) {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar una imagen con id = {0}", id);
        return persistence.find(id);
    }

    /**
     * Actualiza la información de una instancia de Imagen.
     *
     * @param entity Instancia de ImagenEntity con los nuevos datos.
     * @return Instancia de ImagenEntity con los datos actualizados.
     */
    public ImagenEntity updateImagen(ImagenEntity entity) {
        LOGGER.log(Level.INFO, "Inicia proceso de actualizar una Imagen ");
        
        ImagenEntity buscado = persistence.find(entity.getId());
        if (buscado == null){
            LOGGER.log(Level.INFO, "La Imagen con el id {0} no existe ", entity.getId());
        }
        else if (!entity.getNombre().matches("([A-Z]|[a-z]|[0-9]|\\s)+")){
            LOGGER.log(Level.INFO, "El nombre de la Imagen no puede contener caracteres especiales");
        }
        else if(!(entity.getImg().endsWith(".jpg") | entity.getImg().endsWith(".png") | entity.getImg().endsWith(".pdf") | entity.getImg().endsWith(".bmp") | entity.getImg().endsWith(".tif"))){
            LOGGER.log(Level.INFO, "La Imagen tiene un formato no soportado");
        }
        else{
             return persistence.update(entity);
         }
        
        return null;
    }

    /**
     * Elimina una instancia de Imagen de la base de datos.
     *
     * @param id Identificador de la instancia a eliminar.
     */
    public void deleteImagen(Long id) {
        LOGGER.log(Level.INFO, "Inicia proceso de borrar un area ");
        
        ImagenEntity buscado = persistence.find(id);
        if (buscado == null){
            LOGGER.log(Level.INFO, "El area de conocimiento con el id {0} no existe ", id);
        }

        else{
             persistence.delete(id);
         }
    }

}
