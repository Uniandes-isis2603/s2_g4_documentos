/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.documentos.dtos;

import co.edu.uniandes.csw.documentos.entities.UsuarioEntity;

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
    private String correo;
    private int genero;
    private int edad;
    
    
    
    
      /**
     * Constructor a partir de la entidad
     * @param usuario  La entidad del usuario
     */
    public UsuarioDTO(UsuarioEntity usuario) {
        if (usuario != null) {
            this.id = usuario.getId();
            this.nombre = usuario.getNombre();
            this.nombreUsuario = usuario.getNombreUsuario();
            this.correo = usuario.getCorreo();
            this.genero = usuario.getGenero();
            this.edad = usuario.getEdad();
            
        }
    }

    /**
     * Método para transformar el DTO a una entidad.
     * @return La entidad del usuario asociado.
     */
    public UsuarioEntity toEntity() {

        UsuarioEntity user = new UsuarioEntity();
        user.setId(this.getId());
        user.setNombre(this.getNombre());
        user.setNombreUsuario(this.getNombreUsuario());
        user.setCorreo(this.getCorreo());
        user.setEdad(this.getEdad());
        user.setGenero(this.getGenero());
     
        return user;
    }
    
    
    /**
     * constructor por defecto
     */
    public UsuarioDTO()
    {
        
    }

    /**
     * @return the id
     */
    public long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the nombreUsuario
     */
    public String getNombreUsuario() {
        return nombreUsuario;
    }

    /**
     * @param nombreUsuario the nombreUsuario to set
     */
    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
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
