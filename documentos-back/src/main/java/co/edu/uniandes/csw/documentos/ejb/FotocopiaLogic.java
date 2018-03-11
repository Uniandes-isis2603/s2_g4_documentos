/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.documentos.ejb;

import co.edu.uniandes.csw.documentos.entities.FotocopiaEntity;
import co.edu.uniandes.csw.documentos.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.documentos.persistence.FotocopiaPersistence;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author Ernesto Viana
 */
@Stateless
public class FotocopiaLogic {
    
    /**
     * Logger que se va a utilizar para loguear las operaciones.
     */
    private static final Logger LOGGER = Logger.getLogger(FotocopiaLogic.class.getName());
    
    /**
     * Se inyecta la dependencia de fotocopia persistence para hacer operaciones.
     */
    @Inject
    private FotocopiaPersistence persistence;
    
    /**
     * Devuelve todas las fotocopias que hay en la base de datos.
     * @return Lista de entidades tipo fotocopia.
     */
    public List<FotocopiaEntity> getFotocopias()
    {
        LOGGER.info("Inicia proceso de consultar todas las fotocopias");
        List<FotocopiaEntity> fotocopias = persistence.findAll();
        LOGGER.info("Termina proceso de consultar todas las fotocopias");
        return fotocopias;
    }
    
    /**
     * Busca una fotocopia por id.
     * @param id El id de la fotocopia a buscar.
     * @return La fotocopia encontrada, null si no existe.
     */
    public FotocopiaEntity getFotocopia(Long id){
        LOGGER.log(Level.INFO,"Inicia proceso de consultar fotocopia con id={0}",id);
        FotocopiaEntity fotocopia = persistence.find(id);
        LOGGER.log(Level.INFO,"Termina proceso de consultar fotocopia con id={0}",id);
        return fotocopia;
    }
    
    /**
     * Busca las fotocopias que tengan el nombre del profesor indicado.
     * @param profesor nombre del profesor que dejo las fotocopias.
     * @return lista de fotocopias.
     */
    public List<FotocopiaEntity> getFotocopiaByProfesor(String profesor){
        LOGGER.log(Level.INFO,"Inicia proceso de consultar fotocopia con profesor={}",profesor);
        List<FotocopiaEntity> fotocopias = persistence.findByProfesor(profesor);
        return fotocopias;
    }
    
    /**
     * Metodo que crea una fotocopia en la base de datos.
     * @param entity entidad que va a persistir en la base de datos, en caso de tener profesor nulo
     * definirlo como "N/A".
     * @return la fotocopia creada.
     * @throws BusinessLogicException si los datos estan incompletos, el precio o numero de paginas es incorrecto. 
     */
    public FotocopiaEntity createFotocopia(FotocopiaEntity entity) throws BusinessLogicException
    {
        if(!validarCompletitud(entity))
        {
            throw new BusinessLogicException("La fotocopia que se va a crear no tiene la informacion completa");
        }
        else if(entity.getPrecio() < 0)
        {
            throw new BusinessLogicException("El precio de la fotocopia es negativo");
        }
        else if(entity.getNroPaginas() <= 0)
        {
            throw new BusinessLogicException("El numero de paginas tiene que ser mayor a cero");
        }
        if(entity.getProfesor() == null)
        {
            entity.setProfesor("N/A");
        }
        
        entity.setCalificacionPromedio(-1.0);
        
        
        persistence.create(entity);
        LOGGER.info("Termina proceso de creacion de la fotocopia");
        return entity;
    }
    
    /**
     * Actualiza una fotocopia con el id.
     * @param id el id de la fotocopia a  actualizar.
     * @param entity la entidad fotocopia con los cambios.
     * @return la entidad fotocopia luego de ser actualizada.
     * @throws BusinessLogicException si los datos estan incompletos,  el precio es incorrecto o el numero de paginas
     * es incorrecto.
     */
    public FotocopiaEntity updateFotocopia(Long id, FotocopiaEntity entity) throws BusinessLogicException {
        LOGGER.log(Level.INFO,"Inicia proceso de actualizacion de una fotocopia con id={0}",id);
        if(!validarCompletitud(entity))
        {
            throw new BusinessLogicException("La fotocopia que se va a actualizar no tiene la informacion completa");
        }
        else if(entity.getPrecio() <0 )
        {
            throw new BusinessLogicException("El precio tiene que ser positivo");
                    
        }
        else if(entity.getNroPaginas() <=0)
        {
            throw new BusinessLogicException("El numero de paginas tiene que ser mayor a cero");
        }
        if(entity.getProfesor() == null)
        {
            entity.setProfesor("N/A");
        }
        
        FotocopiaEntity newEntity = persistence.update(entity);
        LOGGER.log(Level.INFO,"Termina proceso de actualizacion de una fotocopia con id={0}", entity.getId());
        return newEntity;
    }
    
    /**
     * Elimina una fotocopia con id.
     * @param id El id de la fotocopia que se va a eliminar.
     */
    public void deleteFotocopia(Long id)
    {
        LOGGER.log(Level.INFO, "Inicia proceso de eliminar una fotocopia con id={0}", id);
        persistence.delete(id);
        LOGGER.log(Level.INFO,"Termina proceso de eliminar una fotocopia con id={0}",id);
    }
    //----------------------
    // Metodos de validacion
    //----------------------
    
    /**
     * Metodo que verifica que los datos basicos de una fotocopia esten completos.
     * @param entity entidad que se va a validar.
     * @return true, si los datos estan completos, false de lo contrario.
     */
    public boolean validarCompletitud(FotocopiaEntity entity) {
        
        return !(entity.getNombre() == null || entity.getPrecio() == null || entity.getCaratula() == null || entity.getDescripcion() == null || entity.getNroPaginas() == null || entity.getCapitulo() == null);
    }
    
    
}
