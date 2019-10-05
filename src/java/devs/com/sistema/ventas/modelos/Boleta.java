package devs.com.sistema.ventas.modelos;

import java.util.Date;

public class Boleta {
    private long idboleta;
    private String boleta;
    private String tipoboleta;
    private String estadoboleta;
    private double total;
    private String formapago;
    private long numdocumento;
    private String banco;
    private Date fecha;
    private double monto;

    public Boleta() {
    }

    public Boleta(long idboleta, String boleta, String tipoboleta, String estadoboleta, double total, String formapago, long numdocumento, String banco, Date fecha, double monto) {
        this.idboleta = idboleta;
        this.boleta = boleta;
        this.tipoboleta = tipoboleta;
        this.estadoboleta = estadoboleta;
        this.total = total;
        this.formapago = formapago;
        this.numdocumento = numdocumento;
        this.banco = banco;
        this.fecha = fecha;
        this.monto = monto;
    }


    public long getIdboleta() {
        return idboleta;
    }

    public void setIdboleta(long idboleta) {
        this.idboleta = idboleta;
    }

    public String getBoleta() {
        return boleta;
    }

    public void setBoleta(String boleta) {
        this.boleta = boleta;
    }

    public String getTipoboleta() {
        return tipoboleta;
    }

    public void setTipoboleta(String tipoboleta) {
        this.tipoboleta = tipoboleta;
    }

    public String getEstadoboleta() {
        return estadoboleta;
    }

    public void setEstadoboleta(String estadoboleta) {
        this.estadoboleta = estadoboleta;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public String getFormapago() {
        return formapago;
    }

    public void setFormapago(String formapago) {
        this.formapago = formapago;
    }

    public long getNumdocumento() {
        return numdocumento;
    }

    public void setNumdocumento(long numdocumento) {
        this.numdocumento = numdocumento;
    }

    public String getBanco() {
        return banco;
    }

    public void setBanco(String banco) {
        this.banco = banco;
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
    
    
}
