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

import co.edu.uniandes.csw.documentos.entities.AreaDeConocimientoEntity;

/**
 * AreaDeConocimientoDTO Objeto de transferencia de datos de AreaDeConocimiento. Los DTO contienen las
 * represnetaciones de los JSON que se transfieren entre el cliente o usuario y el
 * servidor
 * 
 * Al serializarse como JSON esta clase implementa el siguiente modelo: <br>
 * <pre>
 *   {
 *      "id": number,
 *      "tipo": string,
 *   }
 * </pre>
 * Por ejemplo una area se representa asi:<br>
 * 
 * <pre>
 * 
 *   {
 *      "id": 91852,
 *      "tipo": "Calculo",
 *   }
 *
 * </pre>
 * @author Camilojaravila
 */
public class AreaDeConocimientoDTO {

    private Long id;
    private String tipo;

    
    
    /**
     * Constructor por defecto.
     */
    public AreaDeConocimientoDTO() {
        //Este es un constructor por defecto
    }
    
     /**
     * Crea un objeto AreaDeConocimientoDTO a partir de un objeto AreaDeConocimientoEntity.
     *
     * @param entity Entidad AreaDeConocimientoEntity desde la cual se va a crear el nuevo
     * objeto.
     */
    public AreaDeConocimientoDTO(AreaDeConocimientoEntity entity) {
        if (entity != null) {
            this.id = entity.getId();
            this.tipo = entity.getTipo();
        }
    }

    /**
     * @return El ID del area
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
    public String getTipo() {
        return tipo;
    }

    /**
     * @param tipo El nuevo tipo de area de conocimiento
     */
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

     /**
     * Convierte un objeto AreaDeConocimientoDTO a AreaDeConocimientoEntity.
     *
     * @return Nueva objeto AreaDeConocimientoEntity.
     * 
     */
    public AreaDeConocimientoEntity toEntity() {
        AreaDeConocimientoEntity entity = new AreaDeConocimientoEntity();
        entity.setId(this.getId());
        entity.setTipo(this.getTipo());
        return entity;
    }
}
