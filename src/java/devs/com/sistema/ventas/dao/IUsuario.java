
package devs.com.sistema.ventas.dao;

import devs.com.sistema.ventas.modelos.Usuario;

public interface IUsuario {
    public Usuario crearUsuario(Usuario user);
    public Usuario validarUsuario(String usuario, String pass);
    public Usuario findByUser(String usuario);
    public boolean cambiarContraseña(Usuario usuario);
    public Usuario findById(long idusuario);
    
}
