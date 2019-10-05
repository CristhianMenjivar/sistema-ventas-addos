package devs.com.sistema.ventas.modelos;

import java.util.Date;

public class Notificacion {
    private long idNotificacion;
    private String nombreNoti;
    private String asuntoNoti;
    private Date fechaNoti;
    private int visto;
    private Usuario user;


    public Notificacion() {
    }

    public Notificacion(long idNotificacion, String nombreNoti, String asuntoNoti, Date fechaNoti, int visto) {
        this.idNotificacion = idNotificacion;
        this.nombreNoti = nombreNoti;
        this.asuntoNoti = asuntoNoti;
        this.fechaNoti =fechaNoti;
        this.visto = visto;
    }

    public int getVisto() {
        return visto;
    }

    public void setVisto(int visto) {
        this.visto = visto;
    }

    public long getIdNotificacion() {
        return idNotificacion;
    }

    public void setIdNotificacion(long idNotificacion) {
        this.idNotificacion = idNotificacion;
    }

    public String getNombreNoti() {
        return nombreNoti;
    }

    public void setNombreNoti(String nombreNoti) {
        this.nombreNoti = nombreNoti;
    }

    public String getAsuntoNoti() {
        return asuntoNoti;
    }

    public void setAsuntoNoti(String asuntoNoti) {
        this.asuntoNoti = asuntoNoti;
    }

    public Date getFechaNoti() {
        return fechaNoti;
    }

    public void setFechaNoti(Date fechaNoti) {
        this.fechaNoti = fechaNoti;
    }

    public Usuario getUser() {
        return user;
    }

    public void setUser(Usuario user) {
        this.user = user;
    }
    
   
    
}
