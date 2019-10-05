
package devs.com.sistema.ventas.modelos;

import java.util.Date;

public class Proveedor {
    
    private long idProveedor;
    private String emitidopor;
    private String moneda;
    private String tipodocumento;
    private Date fecha;
    private String periododeclarado;
    private String comentario;
    private String mtoaf;
    private String mtoe;
    private Double iva;
    private Double total;
    
    public Proveedor(){}

    public Proveedor(long idProveedor, String emitidopor, String moneda, String tipodocumento, Date fecha, String periododeclarado, String comentario, String mtoaf, String mtoe, Double iva, Double total) {
        this.idProveedor = idProveedor;
        this.emitidopor = emitidopor;
        this.moneda = moneda;
        this.tipodocumento = tipodocumento;
        this.fecha = fecha;
        this.periododeclarado = periododeclarado;
        this.comentario = comentario;
        this.mtoaf = mtoaf;
        this.mtoe = mtoe;
        this.iva = iva;
        this.total = total;
    }

    public long getIdProveedor() {
        return idProveedor;
    }

    public void setIdProveedor(long idProveedor) {
        this.idProveedor = idProveedor;
    }

    public String getEmitidopor() {
        return emitidopor;
    }

    public void setEmitidopor(String emitidopor) {
        this.emitidopor = emitidopor;
    }

    public String getMoneda() {
        return moneda;
    }

    public void setMoneda(String moneda) {
        this.moneda = moneda;
    }

    public String getTipodocumento() {
        return tipodocumento;
    }

    public void setTipodocumento(String tipodocumento) {
        this.tipodocumento = tipodocumento;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getPeriododeclarado() {
        return periododeclarado;
    }

    public void setPeriododeclarado(String periododeclarado) {
        this.periododeclarado = periododeclarado;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public String getMtoaf() {
        return mtoaf;
    }

    public void setMtoaf(String mtoaf) {
        this.mtoaf = mtoaf;
    }

    public String getMtoe() {
        return mtoe;
    }

    public void setMtoe(String mtoe) {
        this.mtoe = mtoe;
    }

    public Double getIva() {
        return iva;
    }

    public void setIva(Double iva) {
        this.iva = iva;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }
}
