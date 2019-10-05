package devs.com.sistema.ventas.modelos;

import java.util.Date;

public class DetalleCredito {
    private long idDetalle;
    private long idCredito;
    private double cuota;
    private Date fecha;

    public DetalleCredito() {
    }

    public DetalleCredito(long idDetalle, long idCredito, double cuota, Date fecha) {
        this.idDetalle = idDetalle;
        this.idCredito = idCredito;
        this.cuota = cuota;
        this.fecha = fecha;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public long getIdDetalle() {
        return idDetalle;
    }

    public void setIdDetalle(long idDetalle) {
        this.idDetalle = idDetalle;
    }

    public long getIdCredito() {
        return idCredito;
    }

    public void setIdCredito(long idCredito) {
        this.idCredito = idCredito;
    }

    public double getCuota() {
        return cuota;
    }

    public void setCuota(double cuota) {
        this.cuota = cuota;
    }
    
    
}
