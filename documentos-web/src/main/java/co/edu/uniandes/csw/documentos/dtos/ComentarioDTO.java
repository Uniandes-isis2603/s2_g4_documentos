/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.documentos.dtos;

import java.util.Date;

/**
 *
  * CompraDTO Objeto de transferencia de datos de una editorial . Los DTO contienen las
 * represnetaciones de los JSON que se transfieren entre el cliente y el
 * servidor.
 * 
 * Al serializarse como JSON esta clase implementa el siguiente modelo: <br>
 * <pre>
 *   {
 *      "comentario":string,
 *      "fecha":date
 *     
 *      
 *   }
 * </pre>
 * Por ejemplo un comentario se representa asi:<br>
 * 
 * <pre>
 * 
 *   {
 *      "comentario": "El libro es muy interesante",
 *      "fecha": 05/06/2017
 *     
 *   }
 *
 * </pre>
 *
 * @author n.sotelo
 */
public class ComentarioDTO 
{
private String comentario;
private Date fecha;

/**
 * Consturctor por defecto
 */
    public ComentarioDTO() {
    }
/**
 * 
 * @return el comentario 
 */
    public String getComentario() {
        return comentario;
    }
/**
 * 
 * @param comentario nuevo comentario 
 */
    public void setComentario(String comentario) {
        this.comentario = comentario;
    }
/**
 * 
 * @return fecha en la que se realizo un comentario
 */
    public Date getFecha() {
        return fecha;
    }
/**
 * 
 * @param fecha en la que se realizo un comentario
 */
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }


}
