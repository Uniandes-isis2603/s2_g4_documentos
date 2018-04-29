/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.documentos.dtos;

import co.edu.uniandes.csw.documentos.entities.ComentarioEntity;
import co.edu.uniandes.csw.documentos.entities.CompraEntity;
import co.edu.uniandes.csw.documentos.entities.DeseadoEntity;
import co.edu.uniandes.csw.documentos.entities.ReservaEntity;
import co.edu.uniandes.csw.documentos.entities.TarjetaDeCreditoEntity;
import co.edu.uniandes.csw.documentos.entities.UsuarioEntity;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author f.marroquin10
 */
public class UsuarioDetailedDTO extends UsuarioDTO {

    /**
     * representa la lista de documentos deseados del usuario
     */
    private List<DeseadoDTO> deseados;

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
     * Constructor para transformar un Entity a un DTO
     *
     * @param entity La entidad de la cual se construye el DTO
     */
    public UsuarioDetailedDTO(UsuarioEntity entity) {
        super(entity);
        if (entity.getComentarios() != null) {
            comentarios = new ArrayList();
            for (ComentarioEntity comentario : entity.getComentarios()) {
                //comentarios.add(new ComentarioDTO(comentario));
            }
        } else {
            entity.setComentarios(null);
        }
        if (entity.getReservas() != null) {
            reservas = new ArrayList<>();
            for (ReservaEntity entityReserva : entity.getReservas()) {
                reservas.add(new ReservaDTO(entityReserva));
            }
        }
        
        // }
        //if (entity.getMetodosDePago() != null) {
        //  MetodosDePago = new ArrayList<>();
        //for (AuthorEntity entityAuthor : entity.getMetodosDePago()) {
        //  MetodosDePago.add(new AuthorDTO(entityAuthor));
        //}
        //}

        if (entity.getCompras() != null) {
            compras = new ArrayList<>();
            for (CompraEntity entityCompra : entity.getCompras()) {
                // reservas.add(new ReservaDTO(entityReserva));
            }
        }

        
    }

    /**
     * Transformar el DTO a una entidad
     *
     * @return La entidad que representa el libro.
     */
    @Override
    public UsuarioEntity toEntity() {

        UsuarioEntity usuario = super.toEntity();

        if (getDeseados() != null) {
            List<DeseadoEntity> deseadoEntity = new ArrayList<>();
            for (DeseadoDTO deseado : getDeseados()) {
                deseadoEntity.add(deseado.toEntity());
            }
           // usuario.setDeseados(deseadoEntity);
        }
        if (getReservas() != null) {
            List<ReservaEntity> ReservasEntity = new ArrayList<>();
            for (ReservaDTO reserva : getReservas()) {
                ReservasEntity.add(reserva.toEntity());
            }
            usuario.setReservas(ReservasEntity);
        }
        if (getCompras() != null) {
            List<CompraEntity> comprasEntity = new ArrayList<>();
            for (CompraDTO compra : getCompras()) {
                //comprasEntity.add(compra.toEntity());
            }
            usuario.setCompras(comprasEntity);
        }
        if (getComentarios() != null) {
            List<ComentarioEntity> ComentarioEntity = new ArrayList<>();
            for (ComentarioDTO comentario : getComentarios()) {
                //ComentarioEntity.add(comentario.toEntity());
            }
            usuario.setComentarios(ComentarioEntity);
        }

        //if (getMetodosDePago() != null) {
        // List<ComentarioEntity> ComentarioEntity = new ArrayList<>();
        // for (MetodoDePagoDTO metodo : getMetodosDePago()) {
        //ComentarioEntity.add(comentario.toEntity());
        //}
        // usuario.setComentarios(ComentarioEntity);
        // }
        return usuario;
    }

    /**
     * constructor por defecto
     */
    public UsuarioDetailedDTO() {
        super();
    }

    /**
     * @return the deseado
     */
    public List<DeseadoDTO> getDeseados() {
        return deseados;
    }

    /**
     * @param deseado the deseado to set
     */
    public void setDeseados(List<DeseadoDTO> deseado) {
        this.deseados = deseado;
    }

    /**
     * @return the comentarios
     */
    public List<ComentarioDTO> getComentarios() {
        return comentarios;
    }

    /**
     * @param comentarios the comentarios to set
     */
    public void setComentarios(List<ComentarioDTO> comentarios) {
        this.comentarios = comentarios;
    }

    /**
     * @return the compras
     */
    public List<CompraDTO> getCompras() {
        return compras;
    }

    /**
     * @param compras the compras to set
     */
    public void setCompras(List<CompraDTO> compras) {
        this.compras = compras;
    }

    /**
     * @return the metodosDePago
     */
    public List<MetodoDePagoDTO> getMetodosDePago() {
        return metodosDePago;
    }

    /**
     * @param metodosDePago the metodosDePago to set
     */
    public void setMetodosDePago(List<MetodoDePagoDTO> metodosDePago) {
        this.metodosDePago = metodosDePago;
    }

    /**
     * @return the reservas
     */
    public List<ReservaDTO> getReservas() {
        return reservas;
    }

    /**
     * @param reservas the reservas to set
     */
    public void setReservas(List<ReservaDTO> reservas) {
        this.reservas = reservas;
    }

}
