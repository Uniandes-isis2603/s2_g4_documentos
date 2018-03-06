/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.documentos.dtos;

import co.edu.uniandes.csw.documentos.entities.ComentarioEntity;
import co.edu.uniandes.csw.documentos.entities.CompraEntity;
import co.edu.uniandes.csw.documentos.entities.DeseadoEntity;
import co.edu.uniandes.csw.documentos.entities.TarjetaDeCreditoEntity;
import java.util.List;

/**
 *
 * @author f.marroquin10
 */
public class UsuarioDetailedDTO extends UsuarioDTO {

    
    /**
     * representa la lista de documentos deseados del usuario
     */
    private DeseadoDTO deseado;

    /**
     * cero a muchos
     */
    private List<ComentarioDTO> comentarios;

    /**
     * cero a muchos
     */
    private List<CompraDTO> compras;

    /**
     * uno a muchos
     */
    private List<MetodoDePagoDTO> metodosDePago;

    /**
     * cero a muchos
     */
    private List<ReservaDTO> reservas;

    /**
     * constructor por defecto
     */
    public UsuarioDetailedDTO() {

    }

}
