/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.documentos.ejb;

import co.edu.uniandes.csw.documentos.entities.ImagenEntity;
import co.edu.uniandes.csw.documentos.exceptions.BusinessLogicException;
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
     * @throws BusinessLogicException Excepción cuando se incumple una regla de negocio.
     */
    public ImagenEntity createImagen(ImagenEntity entity) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de crear una imagen");
       
        ImagenEntity buscado = persistence.find(entity.getId());
        if (buscado != null){
            LOGGER.log(Level.INFO, "La Imagen con el id {0} ya existe", entity.getId());
            throw new BusinessLogicException("La Imagen con el id "+ entity.getId()+ " ya existe");
        }
        else if (!(entity.getNombre().matches("([A-Z]|[a-z]|[0-9]|\\s)+") || entity.getNombre().contains(" "))){
            LOGGER.log(Level.INFO, "El nombre de la imagen no puede contener caracteres especiales");
            throw new BusinessLogicException("El nombre de la imagen no puede contener caracteres especiales");
        }
        else if(!(entity.getImg().endsWith(".jpg") || entity.getImg().endsWith(".png") || entity.getImg().endsWith(".pdf") || 
                entity.getImg().endsWith(".bmp") || entity.getImg().endsWith(".tif"))){
            LOGGER.log(Level.INFO, "La Imagen tiene un formato no soportado");
            throw new BusinessLogicException( "La Imagen tiene un formato no soportado");
        }

        return persistence.create(entity);

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
     * @throws BusinessLogicException Excepción cuando se incumple una regla de negocio.
     */
    public ImagenEntity updateImagen(ImagenEntity entity) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de actualizar una Imagen ");
        
        ImagenEntity buscado = persistence.find(entity.getId());
        if (buscado == null){
            LOGGER.log(Level.INFO, "La Imagen con el id {0} no existe", entity.getId());
            throw new BusinessLogicException("La Imagen con el id "+ entity.getId()+ " no existe");
        }
        else if (!(entity.getNombre().matches("([A-Z]|[a-z]|[0-9]|\\s)+") || entity.getNombre().contains(" "))){
            LOGGER.log(Level.INFO, "El nombre de la imagen no puede contener caracteres especiales");
            throw new BusinessLogicException("El nombre de la imagen no puede contener caracteres especiales");
        }
        else if(!(entity.getImg().endsWith(".jpg") || entity.getImg().endsWith(".png") || entity.getImg().endsWith(".pdf") || 
                entity.getImg().endsWith(".bmp") || entity.getImg().endsWith(".tif"))){
            LOGGER.log(Level.INFO, "La Imagen tiene un formato no soportado");
            throw new BusinessLogicException( "La Imagen tiene un formato no soportado");
        }
        
        return persistence.update(entity);
    }

    /**
     * Elimina una instancia de Imagen de la base de datos.
     *
     * @param id Identificador de la instancia a eliminar.
     * @throws BusinessLogicException Excepción cuando se incumple una regla de negocio.
     */
    public void deleteImagen(Long id) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de borrar un area ");
        
        ImagenEntity buscado = persistence.find(id);
        if (buscado == null){
            LOGGER.log(Level.INFO, "La Imagen con el id {0} no existe", id);
            throw new BusinessLogicException("La Imagen con el id "+ id+ " no existe");
        }
        persistence.delete(id);
    }

}
