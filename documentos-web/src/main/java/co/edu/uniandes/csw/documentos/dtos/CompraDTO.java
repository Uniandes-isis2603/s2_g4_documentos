/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.documentos.dtos;

import co.edu.uniandes.csw.documentos.entities.CompraEntity;

import java.util.Date;

/**
 *
 * CompraDTO Objeto de transferencia de datos de una compra . Los DTO contienen las
 * represnetaciones de los JSON que se transfieren entre el cliente y el
 * servidor.
 * 
 * Al serializarse como JSON esta clase implementa el siguiente modelo: <br>
 * <pre>
 *   {
 *      "id":long,
 *      "costo": double,
 *      "fecha": date,
 *      "tipoDecompra":string
 *   }
 * </pre>
 * Por ejemplo una compra se representa asi:<br>
 * 
 * <pre>
 * 
 *   {
 *      "id": 91852,
 *      "costo": 23000 ,
 *      "fecha": 06/05/2001
 *      "tipoDecompra":"efectivo"
 *   }
 *
 * </pre>
 * @author n.sotelo
 */
public class CompraDTO {
    private long id;
    private double costo;
    private Date fecha;
    private String tipoDecompra;

     /**
     * Constructor por defecto
     */
    public CompraDTO() 
    {
        
    }
    /**
     * 
     * @return el id de la compra
     */
    public long getId() {
        return id;
    }
 /**
     * 
     * @param id nueva id de la compra. 
     */
    public void setId(long id) {
        this.id = id;
    }
 /**
     * 
     * @return el codigo costo de la compra
     */
    public double getCosto() {
        return costo;
    }
 /**
     * 
     * @param costo nuevo costo de la compra. 
     */
    public void setCosto(double costo) {
        this.costo = costo;
    }
 /**
     * 
     * @return la fecha en que se efectuo la compra 
     */
    public Date getFecha() {
        return fecha;
    }
 /**
     * 
     * @param fecha nueva fecha en la que se efectuo la compra. 
     */
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
   /**
     * 
     * @return el tipo de compra
     */
    public String getTipoDecompra() {
        return tipoDecompra;
    }
 /**
     * 
     * @param tipoDecompra  nuevo tipo de compra. 
     */
    public void setTipoDecompra(String tipoDecompra) {
        this.tipoDecompra = tipoDecompra;
    }
    
   public CompraDTO(CompraEntity entidad)
    {
        
              this.id= entidad.getId();
               this.fecha=entidad.getFecha();
               this.costo=entidad.getCosto();
               this.tipoDecompra= entidad.getTipoDeCompra();
        
     
    }
        public CompraEntity toEntity()
        {
            CompraEntity rta= new CompraEntity();
           
           rta.setId(this.id);
           rta.setCosto(this.costo);
           rta.setFecha(this.fecha);
           rta.setTipoDeCompra(this.tipoDecompra);
          
           return rta;
        }
    
}
