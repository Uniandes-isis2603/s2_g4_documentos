/*
MIT License

Copyright (c) 2017 ISIS2603

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
 */
package co.edu.uniandes.csw.documentos.dtos;

import co.edu.uniandes.csw.documentos.entities.CityEntity;
import java.util.Date;

/**
 * AreaDeConocimientoDTO Objeto de transferencia de datos de AreaDeConocimiento. Los DTO contienen las
 * represnetaciones de los JSON que se transfieren entre el cliente y el
 * servidor.
 * 
 * Al serializarse como JSON esta clase implementa el siguiente modelo: <br>
 * <pre>
 *   {
 *      "id": number,
 *      "nombre": string,
 *      "fechaNacimiento": Date
 *   }
 * </pre>
 * Por ejemplo una ciudad se representa asi:<br>
 * 
 * <pre>
 * 
 *   {
 *      "id": 91852,
 *      "nombre": "Gabriel Garcia Marquez",
 *      "fechaNacimiento": "06/03/1927"
 *   }
 *
 * </pre>
 * @author Camilojaravila
 */
public class AutorDTO {

    private Long id;
    private String nombre;
    private Date fechaNacimiento;

    /**
     * Constructor por defecto
     */
    public AutorDTO() {
    }

    /**
     * Conviertir Entity a DTO (Crea un nuevo DTO con los valores que recibe en
     * la entidad que viene de argumento.
     *
     * @param area: Es la entidad que se va a convertir a DTO
     */
//    public AreaDeConocimientoDTO(AreaDeConocimientoEntity area) {
//        this.id = area.getId();
//        this.tipo = area.getTipo();
//    }

    /**
     * @return El ID del autor
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id El nuevo ID
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return El tipo de area de conocimiento
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre El nuevo nombre del autor
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
        /**
     * @return la fecha de nacimiento del autor
     */
    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    /**
     * @param fechaNacimiento la nueva fecha de nacimiento del autor
     */
    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    /**
     * Convertir DTO a Entity
     *
     * @return Un Entity con los valores del DTO
     */
//    public CityEntity toEntity() {
//        CityEntity entity = new CityEntity();
//        entity.setId(this.id);
//        entity.setName(this.name);
//        entity.setZipcode(this.zipcode);
//        return entity;
//    }
}
