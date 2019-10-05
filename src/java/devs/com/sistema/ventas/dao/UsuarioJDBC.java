package devs.com.sistema.ventas.dao;

import devs.com.sistema.ventas.coneccion.BaseDatosMYSQL;
import devs.com.sistema.ventas.modelos.Usuario;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsuarioJDBC implements IUsuario {

    @Override
    public Usuario crearUsuario(Usuario user) {
        BaseDatosMYSQL base = new BaseDatosMYSQL();
        try {

            String sql = "insert into usuarios (usuario,contraseña,tipo_usuario) values(?,?,?)";
            PreparedStatement ps = base.getConeccion().prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setString(1, user.getUsuario());
            ps.setString(2, user.getPass());
            ps.setString(3, user.getTipoUsuario());

            ps.executeUpdate();

            int idGenerado;
            ResultSet generateKey = ps.getGeneratedKeys();
            if (generateKey.next()) {
                idGenerado = generateKey.getInt(1);
                user.setIdUsuario(idGenerado);
            }

            base.desconectraBD();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (base.getConeccion() != null) {
                base.desconectraBD();
            }
        }
        return user;
    }

    @Override
    public Usuario validarUsuario(String usuario, String pass) {
        Usuario user = null;
        BaseDatosMYSQL base = new BaseDatosMYSQL();

        try {

            String sql = "select * from usuarios where usuario=? and contraseña=? LIMIT 1";
            PreparedStatement ps = base.getConeccion().prepareCall(sql);
            ps.setString(1, usuario);
            ps.setString(2, pass);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                long id = rs.getLong("idusuario");
                String nombreUs = rs.getString("usuario");
                String password = rs.getString("contraseña");
                String tipo = rs.getString("tipo_usuario");

                if (usuario.equals(nombreUs) && pass.equals(password)) {
                    user = new Usuario(id, usuario, pass, tipo);
                }
            }

            base.desconectraBD();
        } catch (SQLException e) {
            System.out.println("Excepción: " + e.getMessage());
        } finally {
            if (base.getConeccion() != null) {
                base.desconectraBD();
            }
        }
        return user;
    }

    @Override
    public Usuario findByUser(String usuario) {
        Usuario user = null;
        BaseDatosMYSQL base = new BaseDatosMYSQL();

        try {

            String sql = "select * from usuarios where usuario=? LIMIT 1";
            PreparedStatement ps = base.getConeccion().prepareCall(sql);
            ps.setString(1, usuario);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                int id = rs.getInt("idusuario");
                String nombreUs = rs.getString("usuario");
                String tipo = rs.getString("tipo_usuario");

                user = new Usuario(id, nombreUs, "", tipo);

            }

            base.desconectraBD();
        } catch (SQLException e) {
            System.out.println("Excepción: " + e.getMessage());
        } finally {
            if (base.getConeccion() != null) {
                base.desconectraBD();
            }
        }
        return user;
    }

    @Override
    public boolean cambiarContraseña(Usuario usuario) {
        boolean resultado = false;
        BaseDatosMYSQL base = new BaseDatosMYSQL();
        try {
            String sql = "UPDATE usuarios SET contraseña=? where usuario=?";
            PreparedStatement ps = base.getConeccion().prepareStatement(sql);
            ps.setString(1, usuario.getPass());
            ps.setString(2, usuario.getUsuario());

            ps.executeUpdate();

            resultado = true;
            //permite desconectar la bd
            base.desconectraBD();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (base.getConeccion() != null) {
                base.desconectraBD();
            }
        }
        return resultado;
    }

    @Override
    public Usuario findById(long idusuario) {
        Usuario user = null;
        BaseDatosMYSQL base = new BaseDatosMYSQL();

        try {

            String sql = "select * from usuarios where idusuario=? LIMIT 1";
            PreparedStatement ps = base.getConeccion().prepareCall(sql);
            ps.setLong(1, idusuario);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                int id = rs.getInt("idusuario");
                String nombreUs = rs.getString("usuario");
                String tipo = rs.getString("tipo_usuario");

                user = new Usuario(id, nombreUs, "", tipo);

            }

            base.desconectraBD();
        } catch (SQLException e) {
            System.out.println("Excepción: " + e.getMessage());
        } finally {
            if (base.getConeccion() != null) {
                base.desconectraBD();
            }
        }
        return user;
    }

}
