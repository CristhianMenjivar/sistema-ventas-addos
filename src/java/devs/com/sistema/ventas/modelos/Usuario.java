
package devs.com.sistema.ventas.modelos;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Usuario {
    private long idUsuario;
    private String usuario;
    private String pass;
    private String tipoUsuario;

    public Usuario() {
    }

    public Usuario(long idUsuario, String usuario, String pass, String tipoUsuario) {
        this.idUsuario = idUsuario;
        this.usuario = usuario;
        this.pass = pass;
        this.tipoUsuario = tipoUsuario;
    }

    public long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(String tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }
   
    
    
    
}
