
package devs.com.sistema.ventas.modelos;

import java.util.Date;

public class Gasto {
    
    private long idgasto;
    private String tipoGasto;
    private double totalGasto;
    private Date fechaGasto;

    public Gasto(long idgasto, String tipoGasto, double totalGasto, Date fechaGasto) {
        this.idgasto = idgasto;
        this.tipoGasto = tipoGasto;
        this.totalGasto = totalGasto;
        this.fechaGasto = fechaGasto;
    }

    public Gasto() {
    }

    public long getIdgasto() {
        return idgasto;
    }

    public void setIdgasto(long idgasto) {
        this.idgasto = idgasto;
    }

    public String getTipoGasto() {
        return tipoGasto;
    }

    public void setTipoGasto(String tipoGasto) {
        this.tipoGasto = tipoGasto;
    }

    public double getTotalGasto() {
        return totalGasto;
    }

    public void setTotalGasto(double totalGasto) {
        this.totalGasto = totalGasto;
    }

    public Date getFechaGasto() {
        return fechaGasto;
    }

    public void setFechaGasto(Date fechaGasto) {
        this.fechaGasto = fechaGasto;
    }
    
    
}
