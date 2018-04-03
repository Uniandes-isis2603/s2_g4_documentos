/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.documentos.ejb;

import co.edu.uniandes.csw.documentos.entities.MetodoDePagoEntity;
import co.edu.uniandes.csw.documentos.persistence.MetodoDePagoPersistence;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;

/**
 *
 * @author g.ospinaa
 */
public class MetodoDePagoLogic {
    
       
    
    /**
     * Logger que se va a utilizar para loguear las operaciones.
     */
    private static final Logger LOGGER = Logger.getLogger(MetodoDePagoLogic.class.getName());
    
    /**
     * Se inyecta la dependencia de fotocopia persistence para hacer operaciones.
     */
    @Inject
    private MetodoDePagoPersistence persistence;
    
    /**
     * Devuelve todos los metodoDePagos que estan en la base de datos.
     * @return Lista con todos los metodoDePagos de la aplicacion.
     */
    public List<MetodoDePagoEntity> getMetodoDePagos(){
         LOGGER.info("Inicia proceso de consultar todos los metodoDePagos");
        List<MetodoDePagoEntity> metodoDePagos = persistence.findAll();
        LOGGER.info("Termina proceso de consultar todos los metodoDePagos");
        return metodoDePagos;
    }
    
    /**
     * Busca un metodoDePago por id.
     * @param id, del metodoDePago que se va a buscar.
     * @return El metodoDePago encontrado, null si no existe.
     */
    public MetodoDePagoEntity getMetodoDePago(Long id) {
         LOGGER.log(Level.INFO,"Inicia proceso de consultar metodoDePago con id={0}",id);
        MetodoDePagoEntity metodoDePago = persistence.find(id);
        LOGGER.log(Level.INFO,"Termina proceso de consultar metodoDePago con id={0}",id);
        return metodoDePago;
    }
}
