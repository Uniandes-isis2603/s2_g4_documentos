/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.documentos.dtos;

/**
 * UsuarioDTO Objeto de transferencia de datos de Bookzon. Los DTO contienen las
 * representaciones de los JSON que se transfierene entre el cliente y el servidor.
 * 
 * Al serializarse como JSON esta clase implementa el siguiente modelo: <br>
 * <pre>
 *  {
 *      "id": Long,
 *      "nombre": String,
 *      "nombreUsuario": String,
 *      "contraseña": String,
 *    
 *  }
 * </pre>
 * un usuario se representa así:<br>
 * 
 * <pre>
 * 
 *  {
 *      "id": 1203,
 *      "nombre": "Federico Marroquín Baquero",
 *      "nombreUsuario": "fedeGeek"
 *      "contraseña": "losEnanitosVerdes123"
 *  }
 * </pre>
 * 
 * @author f.marroquin10
 */
public class UsuarioDTO {
    
    private long id;
    
    private String nombre;
    
    private String nombreUsuario;
    
    private String contraseña;
    
    
    
    /**
     * constructor por defecto
     */
    public UsuarioDTO()
    {
        
    }
    
   
     /**
     * @return  el id del usuario
     */
    public long getId()
    {
        return id;
    }
    
     /**
     * @return  el nombre del usuario
     */
    public String getnombre()
    {
        return nombre;
    } 
    
     /**
     * @return  el nombre del usuario dentro de la plataforma
     */
     public String getnombreUsuario()
    {
        return nombreUsuario;
    } 
     
      /**
     * @return  la contraseña del usuario
     */
     public String getContraseña()
     {
         return contraseña;
     }
     
     
     /**
     * 
     * @param pId nuevo id
     */
     public void setId(Long pId)
     {
         id=pId;
     }
     
     
     /**
     * 
     * @param pNombre nuevo nombre
     */
      public void setNombre(String pNombre)
      {
          nombre= pNombre;
         
     }
      
    /**
     * 
     * @param pNombre nuevo nombre
     */
       public void setNombreUsuario(String pNombre)
     {
         nombreUsuario= pNombre;
     }
       
       
       /**
     * 
     * @param pContraseña nuevo pContraseña
     */
        public void setContraseña(String pContraseña)
     {
         contraseña=pContraseña;
     }
        
        
       
}
