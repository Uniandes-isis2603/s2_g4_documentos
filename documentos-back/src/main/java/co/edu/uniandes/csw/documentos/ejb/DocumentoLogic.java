/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.documentos.ejb;

import co.edu.uniandes.csw.documentos.entities.DocumentoEntity;
import co.edu.uniandes.csw.documentos.persistence.DocumentoPersistence;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 * Clase que solo va a tener unos metodos de get, debido a que no deberia cambiarse un documento directamente,
 * es para que las clases de logica ajenas comprueben la existencia de un documento.
 * @author Ernesto Viana
 */
@Stateless
public class DocumentoLogic {
    
    
    /**
     * Logger que se va a utilizar para loguear las operaciones.
     */
    private static final Logger LOGGER = Logger.getLogger(DocumentoLogic.class.getName());
    
    /**
     * Se inyecta la dependencia de fotocopia persistence para hacer operaciones.
     */
    @Inject
    private DocumentoPersistence persistence;
    
    /**
     * Devuelve todos los documentos que estan en la base de datos.
     * @return Lista con todos los documentos de la aplicacion.
     */
    public List<DocumentoEntity> getDocumentos(){
         LOGGER.info("Inicia proceso de consultar todos los documentos");
        List<DocumentoEntity> documentos = persistence.findAll();
        LOGGER.info("Termina proceso de consultar todos los documentos");
        return documentos;
    }
    
    /**
     * Busca un documento por id.
     * @param id, del documento que se va a buscar.
     * @return El documento encontrado, null si no existe.
     */
    public DocumentoEntity getDocumento(Long id) {
         LOGGER.log(Level.INFO,"Inicia proceso de consultar documento con id={0}",id);
        DocumentoEntity documento = persistence.find(id);
        LOGGER.log(Level.INFO,"Termina proceso de consultar documento con id={0}",id);
        return documento;
    }
}
