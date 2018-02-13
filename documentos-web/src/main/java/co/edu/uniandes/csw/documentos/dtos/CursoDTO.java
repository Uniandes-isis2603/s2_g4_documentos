/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.documentos.dtos;

/**
 * * CompraDTO Objeto de transferencia de datos de un curso . Los DTO contienen las
 * represnetaciones de los JSON que se transfieren entre el cliente y el
 * servidor.
 * 
 * Al serializarse como JSON esta clase implementa el siguiente modelo: <br>
 * <pre>
 *   {
 *      "nombre":string,
 *      "codigo":string,
 *      "departamento": string,
 * 
 *   }
 * </pre>
 * Por ejemplo un curso se representa asi:<br>
 * 
 * <pre>
 * 
 *   {
 *      "nombre": "Fisica II",
 *      "codigo": "0001" ,
 *      "departamento": "Fisica"
 *      
 *   }
 *
 * </pre>
 * 
 * @author n.sotelo
 */
public class CursoDTO {
   private String nombre;
   private String codigo;
   private String departamento;
 /**
     * Constructor por defecto
     */
    public CursoDTO() 
    {
    }
 /**
  * 
  * @return el nombre del curso 
  */
    public String getNombre() {
        return nombre;
    }
  /**
     * 
     * @param nombre nuevo nombre del curso. 
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    /**
     * 
     * @return el codigo que representa el curso
     */
    
    public String getCodigo() {
        return codigo;
    }
 /**
     * 
     * @param codigo nuevo id que representa una compra . 
     */
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
/**
 * 
 * @return El departamento a la cual pertenece un curso 
 */
    public String getDepartamento() {
        return departamento;
    }

    /**
     * 
     * @param departamento nuevo departamento a la cual pertenece el curso
     */
    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }
    
}
