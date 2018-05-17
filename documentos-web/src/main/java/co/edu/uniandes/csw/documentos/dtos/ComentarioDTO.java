/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package co.edu.uniandes.csw.documentos.dtos;


import co.edu.uniandes.csw.documentos.entities.ComentarioEntity;
import java.util.Date;

/**
 *
 * ComentarioDTO Objeto de transferencia de datos de una editorial . Los DTO contienen las
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
    private long id;
    private String comentario;
    private Date fecha;
    
    /**
     * Consturctor por defecto
     */
    public ComentarioDTO()
    {
        //este es el constructor por defecto
    }
    
    
    public ComentarioDTO( ComentarioEntity entidad)
    {    id= entidad.getId();
        comentario=entidad.getComentario();
        fecha= entidad.getFecha();
    
    }
    public ComentarioEntity toEntity()
    {
        ComentarioEntity rta= new ComentarioEntity();
        
        rta.setId(this.id);
        rta.setFecha(this.fecha);
        rta.setComentario(this.comentario);
        
        return rta;
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
    
    public long getId() {
        return id;
    }
    
    public void setId(long id) {
        this.id = id;
    }
    /**
     *
     * @param fecha en la que se realizo un comentario
     */
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
    
    
}
