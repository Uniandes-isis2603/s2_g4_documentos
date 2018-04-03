
package co.edu.uniandes.csw.documentos.entities;


import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import uk.co.jemos.podam.common.PodamExclude;




/**
 *
 * @author n.sotelo
 */
@Entity
public class CompraEntity implements Serializable
        
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    /**
     * id que representa una compra
     */
    private Long id;
    /**
     * representa el costo total de la compra
     */
    private Double costo;
    /**
     * representa la fecha en la cual se realizo la compra
     */
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;
    /**
     * Representa el tipo de compra
     */
    private String tipoDeCompra;
    /**
     * representa la lista de documentos que hacen parte de una compra
     */
    @OneToMany
    @PodamExclude
    private List<DocumentoEntity> documentos;
    /**
     * representa el metodo de pago usado para realizar un pago con paypal
     */
    @OneToOne
    @JoinColumn(name="METODODEPAGOPAYPAL_ID")
    @PodamExclude
    private PayPalEntity metodoDePagoPayPal;
    /**
     * representa el metodo de pago usado para realizar un pago con tarjeta de credito
     */
    @OneToOne
    @JoinColumn(name="METODODEPAGOTDC_ID")
    @PodamExclude
    private TarjetaDeCreditoEntity metodoDePagoTDC;
    /**
     *
     * @return metodo de pago con paypal
     */
    public PayPalEntity getMetodoDePagoPayPal() {
        return metodoDePagoPayPal;
    }
    /**
     *
     * @param metodoDePagoPayPal nuevo metodo de pago
     */
    public void setMetodoDePagoPayPal(PayPalEntity metodoDePagoPayPal) {
        this.metodoDePagoPayPal = metodoDePagoPayPal;
    }
    /**
     *
     * @return metodo de pago con tarjeta de credito
     */
    public TarjetaDeCreditoEntity getMetodoDePagoTDC() {
        return metodoDePagoTDC;
    }
    /**
     *
     * @param metodoDePagoTDC nuevo metodo de pago.
     */
    public void setMetodoDePagoTDC(TarjetaDeCreditoEntity metodoDePagoTDC) {
        this.metodoDePagoTDC = metodoDePagoTDC;
    }
    
    
    
    /**
     *
     * @return lista de documentos que hacen parte de una compra
     */
    public List<DocumentoEntity> getDocumentos() {
        return documentos;
    }
    /**
     *
     * @param documentos nuevos que se desean agregar a una compra
     */
    public void setDocumentos(List<DocumentoEntity> documentos) {
        this.documentos = documentos;
    }
    
    
    /**
     *
     * @return el id de la compra
     */
    public Long getId() {
        return id;
    }
    /**
     *
     * @param id nuevo de la compra
     */
    
    public void setId(Long id) {
        this.id = id;
    }
    /**
     *
     * @return costo total de la compra
     */
    
    public Double getCosto() {
        return costo;
    }
    
    /**
     *
     * @param costo total de la compra
     */
    public void setCosto(Double costo) {
        this.costo = costo;
    }
    /**
     *
     * @return fecha en que se efectuo la compra
     */
    public Date getFecha() {
        return fecha;
    }
    /**
     *
     * @param fecha en que se efectuo la compra
     */
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
    /**
     *
     * @return tipo de compra
     */
    
    public String getTipoDeCompra() {
        return tipoDeCompra;
    }
    
    
    /**
     *
     * @param tipoDeCompra  nuevo costo de la compra.
     */
    public void setTipoDeCompra(String tipoDeCompra) {
        this.tipoDeCompra = tipoDeCompra;
    }
    
}
