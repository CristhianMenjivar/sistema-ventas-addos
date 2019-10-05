package devs.com.sistema.ventas.modelos;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Compra {
    private long compraId;
    private Date fecha;
    private double monto;
    private List<DetalleCompra> detalle = new ArrayList<>();

    public Compra() {
    }

    public Compra(long compraId, Date fecha, double monto) {
        this.compraId = compraId;
        this.fecha = fecha;
        this.monto = monto;
    }

    public long getCompraId() {
        return compraId;
    }

    public void setCompraId(long compraId) {
        this.compraId = compraId;
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

    public List<DetalleCompra> getDetalle() {
        return detalle;
    }

    public void setDetalle(List<DetalleCompra> detalle) {
        this.detalle = detalle;
    }

    
    
}
