/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.documentos.ejb;
import co.edu.uniandes.csw.documentos.entities.TarjetaDeCreditoEntity;
import co.edu.uniandes.csw.documentos.entities.TarjetaDeCreditoEntity;
import co.edu.uniandes.csw.documentos.entities.TarjetaDeCreditoEntity;
import co.edu.uniandes.csw.documentos.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.documentos.persistence.TarjetaDeCreditoPersistence;
import co.edu.uniandes.csw.documentos.persistence.TarjetaDeCreditoPersistence;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;


/**
 *
 * @author g.ospinaa
 */
@Stateless
public class TarjetaDeCreditoLogic 
{
       private static final Logger LOGGER = Logger.getLogger(TarjetaDeCreditoLogic.class.getName());

    @Inject
    private TarjetaDeCreditoPersistence persistence;
    
     /**
     * Se encarga de crear una TarjetaDeCredito
     * 
     * @param TDC tarjeta de TarjetaDeCredito a crear
     * @return instancia de TarjetaDeCredito
     */
    public TarjetaDeCreditoEntity createTarjetaDeCredito(TarjetaDeCreditoEntity TDC) throws BusinessLogicException
    {
        
     LOGGER.log(Level.INFO, "Empezando la creacion de nueva TarjetaDeCredito");
     TarjetaDeCreditoEntity entity = persistence.findByNumber(TDC.getNroDeLaTarjeta());
     boolean check = true;
     
     if(entity != null)
     {
         LOGGER.log(Level.INFO, "La TDC ya existe");
         throw new BusinessLogicException("La TDC ya existe");
     }
     
     if (TDC.getTipoDeTarjeta().equals("Visa"))
        {
            if (TDC.getNroDeLaTarjeta().length() != 16){
                LOGGER.log(Level.INFO, "Error en el numero de la tarjeta");
                check = false;
            }
            
            
            if(!TDC.getNroDeLaTarjeta().startsWith("4")){
                LOGGER.log(Level.INFO, "Error en el numero de la tarjeta");  
                check = false;
            }

        }
     if (TDC.getTipoDeTarjeta().equals("MasterCard")){
               if (entity.getNroDeLaTarjeta().length() != 16){
                LOGGER.log(Level.INFO, "Error en el numero de la tarjeta tipo");
                check = false;
               }

            if(!TDC.getNroDeLaTarjeta().startsWith("(5[1-5]\\s)+")){
                LOGGER.log(Level.INFO, "Error en el numero de la tarjeta tipo"); 
                check = false;            
            }
            }
     if (TDC.getNumeroDeSeguridad().toString().length() != 3){
            LOGGER.log(Level.INFO, "Error en el numero de seguridad de la tarjeta tipo "); 
                check = false;  
        }
     if (!TDC.getNombreEnLaTarjeta().matches("([A-Z]|[a-z]|[0-9]\\s)+")){
            LOGGER.log(Level.INFO, "Error en el nombre del usuario de la tarjeta nro. "); 
            check = false;
        }
     if(check == true)
     {
         return persistence.create(TDC);
     }
     
     
     throw new BusinessLogicException("Error creando la tarjeta");
    }
    
    
    
    /**
     * Obtiene la lista de las TarjetaDeCredito
     * 
     * @return Coleccion de objetos TarjetaDeCreditoEntity
     */
    public List<TarjetaDeCreditoEntity> getTarjetaDeCredito()
    {
        LOGGER.log(Level.INFO,"Inicia proceso de consultar TarjetaDeCredito");
        return persistence.findAll();
    }
    
    /**
     * Obtiene los datos de una instancia de TarjetaDeCredito a partir de su ID.
     * 
     * @para id Identificacion de la tarjeta
     * @return Instancia de TarjetaDeCreditoENtity con los datos de la tarjeta consultada
     */
    public TarjetaDeCreditoEntity getTarjetaDeCredito(Long Id)
    {
        LOGGER.log(Level.INFO, "Inicia proceso de encontrar tarjeta con id = {0}", Id);
        return persistence.find(Id);
    }
   
    
    /**
     * Actualiza la informacion de una instancia de TarjetaDeCredito.
     * 
     * @param TDC Instancia de TarjetaDeCredito con los nuevos datos
     * @return Instancia de TarjetaDeCredito actualizada.
     */
    public TarjetaDeCreditoEntity updateEntity(TarjetaDeCreditoEntity TDC)
    {
        LOGGER.log(Level.INFO, "COmenzando proceso para actualizar instancia de TarjetaDeCredito");

        boolean check = true;
        TarjetaDeCreditoEntity entity = persistence.find(TDC.getId());
        if(entity == null)
        {
        LOGGER.log(Level.INFO, "La TDC con el numero {0} no existe ", entity.getNroDeLaTarjeta());
        }
        else if (entity.getTipoDeTarjeta().equals("Visa"))
        {
            if (entity.getNroDeLaTarjeta().length() != 16){
                LOGGER.log(Level.INFO, "Error en el numero de la tarjeta, {0} ", entity.getTipoDeTarjeta());
                check = false;
            }
            
            
            else if(!entity.getNroDeLaTarjeta().startsWith("4")){
                LOGGER.log(Level.INFO, "Error en el numero de la tarjeta, {0} ", entity.getTipoDeTarjeta());    
                check = false;
            }

        }
        else if (entity.getTipoDeTarjeta().equals("MasterCard")){
            
               if (entity.getNroDeLaTarjeta().length() != 16){
                LOGGER.log(Level.INFO, "Error en el numero de la tarjeta tipo {0} ", entity.getTipoDeTarjeta());
                check = false;
               }

            else if(!entity.getNroDeLaTarjeta().startsWith("(5[1-5])")){
                LOGGER.log(Level.INFO, "Error en el numero de la tarjeta tipo {0} ", entity.getTipoDeTarjeta()); 
                check = false;            
            }
            }
        if (entity.getNumeroDeSeguridad().toString().length() != 3){
            LOGGER.log(Level.INFO, "Error en el numero de seguridad de la tarjeta tipo {0} ", entity.getTipoDeTarjeta()); 
                check = false;  
        }
        
        if (!entity.getNombreEnLaTarjeta().matches("([A-Z]|[a-z]\\s)+")){
            LOGGER.log(Level.INFO, "Error en el nombre del usuario de la tarjeta nro. {0} ", entity.getNroDeLaTarjeta()); 
            check = false;
        }
            
        if(check == true){
        return persistence.update(TDC);}
        
        return null;
    }
    
    /**
     * Elimina una instancia de TarjetaDeCredito de la base de datos.
     * 
     * @param Id Identificador de TarjetaDeCredito a borrar
     */
    public void deleteTarjetaDeCredito(Long Id)
    {
        LOGGER.log(Level.INFO, "Comenzando proceso de eliminacion de Instancia de TarjetaDeCredito");
        persistence.delete(Id);
    }
    
    
    
}
