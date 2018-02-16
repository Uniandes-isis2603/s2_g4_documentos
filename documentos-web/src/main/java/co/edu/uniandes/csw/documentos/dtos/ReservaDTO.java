/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.documentos.dtos;

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
