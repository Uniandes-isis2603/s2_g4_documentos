
package co.edu.uniandes.csw.documentos.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.OneToMany;
import uk.co.jemos.podam.common.PodamExclude;


/**
 *
 * @author n.sotelo
 */
@Entity
public class EditorialEntity implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    /**
     * id de la identidad
     */
    private Long id;
    /**
     * Nombre que identifica a la entidad
     */
    private String nombre;
    /**
     * Representa la lista de libros que pertenecen a una editorial.
     */
    @PodamExclude
    @OneToMany(mappedBy = "editorial",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<LibroEntity> libros;
    /**
     *
     * @return lista de libros a lod cuales pertnece una editorial
     */
    public List<LibroEntity> getLibros() {
        return libros;
    }
    /**
     *
     * @param libros  que se añadiran a una editorial
     */
    public void setLibros(List<LibroEntity> libros) {
        this.libros = libros;
    }
    /**
     *
     * @return de la entidad
     */
    public Long getId() {
        return id;
    }
    /**
     *
     * @param id nuevo para la editorial
     */
    
    public void setId(Long id) {
        this.id = id;
    }
    /**
     *
     * @return nombre de la editorial
     */
    public String getNombre() {
        return nombre;
    }
    /**
     *
     * @param nombre nuevo para la editorial
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
}
