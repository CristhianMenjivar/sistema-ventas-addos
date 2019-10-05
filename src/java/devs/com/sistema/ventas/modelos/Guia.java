package devs.com.sistema.ventas.modelos;

public class Guia {
    
    private long idguia;
    private String rutguia;
    private String numguia;
    private String tipoguia;
    private String estadoguia;
    private double total;

    public Guia() {
    }

    public Guia(long idguia, String rutguia, String numguia, String tipoguia, String estadoguia, double total) {
        this.idguia = idguia;
        this.rutguia = rutguia;
        this.numguia = numguia;
        this.tipoguia = tipoguia;
        this.estadoguia = estadoguia;
        this.total = total;
    }

    public long getIdguia() {
        return idguia;
    }

    public void setIdguia(long idguia) {
        this.idguia = idguia;
    }

    public String getRutguia() {
        return rutguia;
    }

    public void setRutguia(String rutguia) {
        this.rutguia = rutguia;
    }

    public String getNumguia() {
        return numguia;
    }

    public void setNumguia(String numguia) {
        this.numguia = numguia;
    }

    public String getTipoguia() {
        return tipoguia;
    }

    public void setTipoguia(String tipoguia) {
        this.tipoguia = tipoguia;
    }

    public String getEstadoguia() {
        return estadoguia;
    }

    public void setEstadoguia(String estadoguia) {
        this.estadoguia = estadoguia;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
    
    
}
