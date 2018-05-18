/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.documentos.dtos;

/**
 *UserDTO Objeto de transferencia de datos de Usuarios. Los DTO contienen las
 * represnetaciones de los JSON que se transfieren entre el cliente y el
 * servidor.
 *
 * Al serializarse como JSON esta clase implementa el siguiente modelo: <br>
 * <pre>
 *   {
 *      "username": string,
 *      "password: string,
 *      "rol": string
 *   }
 * </pre>
 * Por ejemplo una reseña se representa asi:<br>
 * 
 * <pre>
 * 
 *   {
 *      "username": Juan Pérez,
 *      "password: 123456,
 *      "rol": admin
 *   }
 *
 * </pre>
 * @author le.viana
 */
public class UserDTO {
 
    
    private String username;
    private String password;
    private String rol;
   
    public UserDTO(){}

 
    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the rol
     */
    public String getRol() {
        return rol;
    }

    /**
     * @param rol the rol to set
     */
    public void setRol(String rol) {
        this.rol = rol;
    }

    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }
}
