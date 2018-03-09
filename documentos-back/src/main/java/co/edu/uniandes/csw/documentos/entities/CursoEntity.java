
package co.edu.uniandes.csw.documentos.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.List;
import javax.persistence.ManyToMany;
import uk.co.jemos.podam.common.PodamExclude;


/**
 *
 * @author n.sotelo
 */
@Entity
public class CursoEntity implements Serializable
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    /**
     * id que identifica la entidad
     */
    private Long id;
    /**
     *  nombre del curso
     */
    private String nombre;
    /**
     *  codigo del curso
     */
    private String codigo;
    /**
     *  departamento al cual participa un curso
     */
    private String departamento;
    /**
     * representa toda la bibliografia que pertenece a un curso
     */
    @PodamExclude
    @ManyToMany
    private List<DocumentoEntity> bibliografiaDelCurso;
    /**
     *
     * @return la bibliografia del curso
     */
    public List<DocumentoEntity> getBibliografiaDelCurso() {
        return bibliografiaDelCurso;
    }
    /**
     *
     * @param bibliografiaDelCurso nueva bibliografia del curso
     */
    public void setBibliografiaDelCurso(List<DocumentoEntity> bibliografiaDelCurso) {
        this.bibliografiaDelCurso = bibliografiaDelCurso;
    }
    
    
    
    /**
     *
     * @return id que identifica al curso
     */
    
    public Long getId() {
        return id;
    }
    /**
     *
     * @param id nuevo que se desea establecer al curso
     */
    
    public void setId(Long id) {
        this.id = id;
    }
    
    /**
     *
     * @return Nombre del curso
     */
    public String getNombre() {
        return nombre;
    }
    
    /**
     *
     * @param nombre nuevo para el curso
     */
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    /**
     *
     * @return codigo del curso
     */
    public String getCodigo() {
        return codigo;
    }
    /**
     *
     * @param codigo nuevo codigo para el curso
     */
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
    /**
     *
     * @return departamento al cual pertence el curso
     */
    public String getDepartamento() {
        return departamento;
    }
    /**
     *
     * @param departamento nuevo departamento al que pertenece el curso
     */
    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }
    
    
    
}
