package devs.com.sistema.ventas.modelos;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Orden {
    private long ordenId;
    private Date fecha;
    private double monto;
    private String tipoPago;
    private String tipoVenta;
    private List<DetalleOrdenes> detalle = new ArrayList<>();

    public Orden() {
    }

    public Orden(long ordenId, Date fecha, double monto,  String tipoPago, String tipoVenta) {
        this.ordenId = ordenId;
        this.fecha = fecha;
        this.monto = monto;
        this.tipoPago = tipoPago;
        this.tipoVenta = tipoVenta;
    }

    public long getOrdenId() {
        return ordenId;
    }

    public void setOrdenId(long ordenId) {
        this.ordenId = ordenId;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public String getTipoPago() {
        return tipoPago;
    }

    public void setTipoPago(String tipoPago) {
        this.tipoPago = tipoPago;
    }

    public List<DetalleOrdenes> getDetalle() {
        return detalle;
    }

    public void setDetalle(List<DetalleOrdenes> detalle) {
        this.detalle = detalle;
    }

   
    public String getImporteRedondeado() {
        return new DecimalFormat("#.##").format(monto);
    }

    public String getTipoVenta() {
        return tipoVenta;
    }

    public void setTipoVenta(String tipoVenta) {
        this.tipoVenta = tipoVenta;
    }
    
}
