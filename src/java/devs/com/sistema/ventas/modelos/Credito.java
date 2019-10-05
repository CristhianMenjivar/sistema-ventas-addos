package devs.com.sistema.ventas.modelos;

import java.util.Date;
import java.util.List;

public class Credito {
    
    private long idCredito;
    private Cliente cliente;
    private Orden venta;
    private double montoCredito;
    private Date fecha;
    private List<DetalleCredito> listaDetalles;
    
    //obtener la deuda
    public double getDeuda(){
        double monto = this.montoCredito;
        
        for(DetalleCredito det : listaDetalles){
            monto -= det.getCuota();
        }
        
        return monto;
    }

    public Credito() {
    }

    public Credito(long idCredito, Cliente cliente, Orden venta, double montoCredito, Date fecha) {
        this.idCredito = idCredito;
        this.cliente = cliente;
        this.venta = venta;
        this.montoCredito = montoCredito;
        this.fecha = fecha;
    }

    public double getMontoCredito() {
        return montoCredito;
    }

    public void setMontoCredito(double montoCredito) {
        this.montoCredito = montoCredito;
    }

    public long getIdCredito() {
        return idCredito;
    }

    public void setIdCredito(long idCredito) {
        this.idCredito = idCredito;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Orden getVenta() {
        return venta;
    }

    public void setVenta(Orden venta) {
        this.venta = venta;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public List<DetalleCredito> getListaDetalles() {
        return listaDetalles;
    }

    public void setListaDetalles(List<DetalleCredito> listaDetalles) {
        this.listaDetalles = listaDetalles;
    }
    
    
}
