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
    
    private String correo;
    
    private int genero;
    private int edad;
    
    
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

    /**
     * @return the correo
     */
    public String getCorreo() {
        return correo;
    }

    /**
     * @param correo the correo to set
     */
    public void setCorreo(String correo) {
        this.correo = correo;
    }

    /**
     * @return the genero
     */
    public int getGenero() {
        return genero;
    }

    /**
     * @param genero the genero to set
     */
    public void setGenero(int genero) {
        this.genero = genero;
    }

    /**
     * @return the edad
     */
    public int getEdad() {
        return edad;
    }

    /**
     * @param edad the edad to set
     */
    public void setEdad(int edad) {
        this.edad = edad;
    }
        
        
       
}
