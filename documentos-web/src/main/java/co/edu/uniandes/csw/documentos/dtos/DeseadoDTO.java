/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.documentos.dtos;

/**
 * DeseadoDTO Objeto de transferencia de datos de Bookzon. Los DTO contienen las
 * representaciones de los JSON que se transfierene entre el cliente y el servidor.
 * 
 * Al serializarse como JSON esta clase implementa el siguiente modelo: <br>
 * <pre>
 *  {
 *      "id": Long,
 *      "nombre": String,
 *      "cantidad": double,  
 *  }
 * </pre>
 * un deseado se representa así:<br>
 * 
 * <pre>
 * 
 *  {
 *      "id": 1203,
 *      "nombre": "lista",
 *      "cantidad": "14"
 *  }
 * </pre>
 * 
 * @author f.marroquin10
 */
public class DeseadoDTO {
    
    private double cantidad;
    private long id;
    private String nombre;
    
    
    /**
     * constructor por defecto
     */
    public DeseadoDTO()
    {
        
    }
    
    /**
     * @return  el nombre
     */
    public String getNombre()
    {
        return nombre;
    }
    
    /**
     * @return  el id del deseado
     */
    public long getId()
    {
        return id;
    }
    
    /**
     * @return  el id del usuario
     */
    public double getCantidad()
    {
        return cantidad;
    }
    
      /**
     * 
     * @param pId nuevo id
     */
    public void setId(long pId)
    {
        this.id=pId;
    }
    
      /**
     * 
     * @param pCantidad nuevo cantidad
     */
    public void setCantidad(double pCantidad)
    {
        this.cantidad = pCantidad;
    }
    
      /**
     * 
     * @param pNombre nuevo nombre
     */
    public void setNombre(String pNombre)
    {
        this.nombre =pNombre;
    }
}

