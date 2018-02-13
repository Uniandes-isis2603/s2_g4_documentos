/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.documentos.dtos;

import java.util.Date;

/**
 *
 * @author n.sotelo
 */
public class CompraDTO {
    private long id;
    private double costo;
    private Date fecha;
    private String tipoDecompra;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getCosto() {
        return costo;
    }

    public void setCosto(double costo) {
        this.costo = costo;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getTipoDecompra() {
        return tipoDecompra;
    }

    public void setTipoDecompra(String tipoDecompra) {
        this.tipoDecompra = tipoDecompra;
    }
    
    
}
