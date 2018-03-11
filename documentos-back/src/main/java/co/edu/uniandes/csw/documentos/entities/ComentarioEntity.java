
package co.edu.uniandes.csw.documentos.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;

/**
 *
 * @author n.sotelo
 */
@Entity
public class ComentarioEntity implements Serializable
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    
    /**
     * representa el id de una entidad
     */
    private Long id;
    /**
     * representa el comentario
     */
    private String comentario;
    /**
     * representa la fecha en la cual se hizo el comentario
     */
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date fecha;
    /**
     *
     * @return el id del comentario
     */
    public Long getId() {
        return id;
    }
    /**
     *
     * @param id nuevo id del comentario
     */
    public void setId(Long id) {
        this.id = id;
    }
    /**
     *
     * @return el contenido del comentario
     */
    
    public String getComentario() {
        return comentario;
    }
    /**
     *
     * @param comentario nuevo contenido par un comentario
     */
    public void setComentario(String comentario) {
        this.comentario = comentario;
    }
    /**
     *
     * @return la fecha en la cual serealizo el comentario
     */
    public Date getFecha() {
        return fecha;
    }
    /**
     *
     * @param fecha nueva fecha
     */
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
    
    
}
