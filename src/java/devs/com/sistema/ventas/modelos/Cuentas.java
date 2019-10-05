package devs.com.sistema.ventas.modelos;

import java.util.Date;

public class Cuentas {

    private long idcuenta;
    private String o;
    private String t;
    private Date fecha;
    private String emisor;
    private String descripcion;
    private String ctacont;
    private String centro;
    private String usuario;
    private double pago;
    private double monto;

    public Cuentas() {
    }

    public Cuentas(long idcuenta, String o, String t, Date fecha, String emisor, String descripcion, String ctacont, String centro, String usuario, double pago, double monto) {
        this.idcuenta = idcuenta;
        this.o = o;
        this.t = t;
        this.fecha = fecha;
        this.emisor = emisor;
        this.descripcion = descripcion;
        this.ctacont = ctacont;
        this.centro = centro;
        this.usuario = usuario;
        this.pago = pago;
        this.monto = monto;
    }

    public long getIdcuenta() {
        return idcuenta;
    }

    public void setIdcuenta(long idcuenta) {
        this.idcuenta = idcuenta;
    }

    public String getO() {
        return o;
    }

    public void setO(String o) {
        this.o = o;
    }

    public String getT() {
        return t;
    }

    public void setT(String t) {
        this.t = t;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getEmisor() {
        return emisor;
    }

    public void setEmisor(String emisor) {
        this.emisor = emisor;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getCtacont() {
        return ctacont;
    }

    public void setCtacont(String ctacont) {
        this.ctacont = ctacont;
    }

    public String getCentro() {
        return centro;
    }

    public void setCentro(String centro) {
        this.centro = centro;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public double getPago() {
        return pago;
    }

    public void setPago(double pago) {
        this.pago = pago;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }
    
    
}
