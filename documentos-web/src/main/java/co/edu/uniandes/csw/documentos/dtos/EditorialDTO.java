/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.documentos.dtos;

/**
 *Clase para modelar una editorial 
 * @author n.sotelo
 */
public class EditorialDTO 
{
    private long ISBN;
    private String nombre;

    public EditorialDTO() 
    {
     
    }

    public void setISBN(long ISBN) {
        this.ISBN = ISBN;
    }

    public void setNombre(String nombre) 
    {
        this.nombre = nombre;
    }

    public long getISBN() {
        return ISBN;
    }

    public String getNombre() {
        return nombre;
    }
}
