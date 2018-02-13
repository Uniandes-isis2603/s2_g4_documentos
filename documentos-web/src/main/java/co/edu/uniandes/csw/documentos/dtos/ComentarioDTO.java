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
public class ComentarioDTO 
{
private String comentario;
private Date fecha;
private Integer calificacion;

    public ComentarioDTO() {
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Integer getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(Integer calificacion) 
    {
        this.calificacion = calificacion;
    }
}
