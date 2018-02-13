/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.documentos.dtos;

/**
 * PayPalDTO Objeto que representa una cuenta del servicio de pago PayPal. Los DTO contienen las
 * represnetaciones de los JSON que se transfieren entre el cliente y el
 * servidor.
 * 
 * Al serializarse como JSON esta clase implementa el siguiente modelo: <br>
 * <pre>
 *   {
 *      "correoElectronico": string,
 *      "usuario: string,
 *   }
 * </pre>
 * Por ejemplo una cuennta PayPal se representa asi:<br>
 * 
 * <pre>
 * 
 *   {
 *      "correoElectronico": "g.ospinaa@uniandes.edu.co",
 *      "usuario: "g.ospinaa",
 *   }
 *
 * </pre>
 * @author g.ospinaa
 */
public class PayPalDTO {
    
    private String correoElectronico;
    private String usuario;
    
    PayPalDTO()
    {
        
    }

    /**
     * @return the correoElectronico
     */
    public String getCorreoElectronico() {
        return correoElectronico;
    }

    /**
     * @param correoElectronico the correoElectronico to set
     */
    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    /**
     * @return the usuario
     */
    public String getUsuario() {
        return usuario;
    }

    /**
     * @param usuario the usuario to set
     */
    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }
    
}
