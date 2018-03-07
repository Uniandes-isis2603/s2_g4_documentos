/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.documentos.dtos;

import co.edu.uniandes.csw.documentos.entities.ReservaEntity;
import co.edu.uniandes.csw.documentos.entities.UsuarioEntity;
import java.util.Date;

/**
 *
 * @author f.marroquin10
 */
public class ReservaDTO {
    
    private long id;
    private double costo;
    private Date fecha;
    
    
          /**
     * Constructor a partir de la entidad
     * @param reserva  La entidad de la reserva
     */
    public ReservaDTO(ReservaEntity reserva) {
        if (reserva != null) {
            this.id = reserva.getId();
            this.costo = reserva.getCosto();
            this.fecha = reserva.getFecha();
          
        }
    }

    /**
     * Método para transformar el DTO a una entidad.
     * @return La entidad de la reserva asociada.
     */
    public ReservaEntity toEntity() {

        ReservaEntity reserva = new ReservaEntity();
        reserva.setId(this.id);
        reserva.setCosto(this.costo);
        reserva.setFecha(this.fecha);
       
     
        return reserva;
    }
    
     /**
     * constructor por defecto
     */
    public ReservaDTO()
    {
        
    }
    
     /**
     * @return  el id de la reserva
     */
    public long getid()
    {
        return id;
    }
    
     /**
     * @return  el costo  de la reserva
     */
    public double getCosto()
    {
        return costo;
    }
    
     /**
     * @return  la fehca de la reserva
     */
    public Date getFecha()
    {
        return fecha;
    }
    
      /**
     * 
     * @param pId nuevo id
     */
    public void setId(long pId)
    {
        this.id= pId;
    }
    
    
       /**
     * 
     * @param pCost nuevo costo
     */
    public void setCosto(double pCost)
    {
        this.costo= pCost;
    }
    
    
       /**
     * 
     * @param pFecha nuevo fecha
     */
    public void setFecha(Date pFecha)
    {
        this.fecha= pFecha;
    }
}
