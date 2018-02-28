/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.documentos.ejb;

import co.edu.uniandes.csw.documentos.entities.UsuarioEntity;
import co.edu.uniandes.csw.documentos.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.documentos.persistence.UsuarioPersistence;
import javax.inject.Inject;

/**
 *
 * @author f.marroquin10
 */
public class UsuarioLogic {
    
     @Inject
    private UsuarioPersistence persistence; // Variable para acceder a la persistencia de la aplicación. Es una inyección de dependencias.

     public UsuarioEntity getUsuario(Long id)
     {
         return persistence.find(id);
     }
     
     public void CreateUsuario(UsuarioEntity user)throws BusinessLogicException
     {
         
     }
     
     
    
}
