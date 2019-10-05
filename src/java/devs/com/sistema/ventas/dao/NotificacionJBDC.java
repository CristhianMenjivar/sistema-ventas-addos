/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package devs.com.sistema.ventas.dao;

import devs.com.sistema.ventas.coneccion.BaseDatosMYSQL;
import devs.com.sistema.ventas.modelos.Notificacion;
import devs.com.sistema.ventas.modelos.Usuario;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author usuario
 */
public class NotificacionJBDC implements INotificacion {

    @Override
    public int NuevasNotificaciones() {
        int total = 0;

        try {

            BaseDatosMYSQL base = new BaseDatosMYSQL();
            String sql = "select * from notificaciones where visto=1";
            PreparedStatement ps = base.getConeccion().prepareCall(sql);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                total++;

            }
            base.desconectraBD();
        } catch (SQLException e) {
            System.out.println("Excepción: " + e.getMessage());
        }

        return total;
    }

    @Override
    public List<Notificacion> listAll() {
        List<Notificacion> lista = new ArrayList<>();
        Notificacion noti;

        try {
            BaseDatosMYSQL base = new BaseDatosMYSQL();
            String sql = "select * from notificaciones order by idNotificacion desc";
            PreparedStatement ps = base.getConeccion().prepareCall(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                noti = new Notificacion();

                noti.setIdNotificacion(rs.getInt("idNotificacion"));
                noti.setNombreNoti(rs.getString("NombreNoti"));
                noti.setAsuntoNoti(rs.getString("AsuntoNoti"));
                noti.setFechaNoti(rs.getDate("fechaNoti"));
                noti.setVisto(rs.getInt("visto"));

                lista.add(noti);
            }
            base.desconectraBD();
        } catch (SQLException e) {
            System.out.println("Excepción: " + e.getMessage());
        }

        return lista;
    }

    @Override
    public Notificacion findById(long id) {
        Notificacion noti = null;

        try {
            BaseDatosMYSQL base = new BaseDatosMYSQL();
            String sql = "select * from notificaciones where idNotificacion =? LIMIT 1";
            PreparedStatement ps = base.getConeccion().prepareCall(sql);
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                noti = new Notificacion();

                noti.setIdNotificacion(rs.getInt("idNotificacion"));
                noti.setNombreNoti(rs.getString("NombreNoti"));
                noti.setAsuntoNoti(rs.getString("AsuntoNoti"));
                noti.setFechaNoti(rs.getDate("fechaNoti"));
                noti.setVisto(rs.getInt("visto"));

                long idUsuario = rs.getLong("idusuario");

                UsuarioJDBC usuarioDao = new UsuarioJDBC();
                Usuario user = usuarioDao.findById(idUsuario);

                noti.setUser(user);
            }
            base.desconectraBD();
        } catch (SQLException e) {
            System.out.println("Excepción: " + e.getMessage());
        }

        return noti;
    }

    @Override
    public String insert(Notificacion noti) {
        String mensaje = "";

        try {
            BaseDatosMYSQL base = new BaseDatosMYSQL();
            String sql = "insert into notificaciones (NombreNoti,AsuntoNoti,fechaNoti,visto,idusuario) values (?,?,?,?,?)";
            PreparedStatement ps = base.getConeccion().prepareCall(sql);

            ps.setString(1, noti.getNombreNoti());
            ps.setString(2, noti.getAsuntoNoti());
            ps.setDate(3, (java.sql.Date) (Date) noti.getFechaNoti());
            ps.setInt(4, noti.getVisto());
            ps.setLong(5, noti.getUser().getIdUsuario());
            ps.executeUpdate();

            mensaje = "La actividad se ha creado correctamente.";
            base.desconectraBD();
        } catch (SQLException e) {
            mensaje = "No fue posible crear la actividad: " + e.getMessage();
        }

        return mensaje;
    }

    @Override
    public String update(Notificacion noti) {
        String mensaje = "";

        try {
            BaseDatosMYSQL base = new BaseDatosMYSQL();
            String sql = "UPDATE notificaciones SET NombreNoti=?,AsuntoNoti=?,fechaNoti=?,visto=? WHERE idNotificacion=?";
            PreparedStatement ps = base.getConeccion().prepareCall(sql);

            ps.setString(1, noti.getNombreNoti());
            ps.setString(2, noti.getAsuntoNoti());
            ps.setDate(3, (java.sql.Date) (Date) noti.getFechaNoti());
            ps.setInt(4, noti.getVisto());
            ps.setLong(5, noti.getIdNotificacion());
            ps.executeUpdate();

            mensaje = "La actividad se ha actualizado correctamente.";
            base.desconectraBD();
        } catch (SQLException e) {
            mensaje = "No fue posible actualizar la actividad: " + e.getMessage();
        }

        return mensaje;
    }

    @Override
    public String delete(long id) {
        String mensaje = "";

        try {
            BaseDatosMYSQL base = new BaseDatosMYSQL();
            String sql = "delete from notificaciones where idNotificacion=?";
            PreparedStatement ps = base.getConeccion().prepareCall(sql);

            ps.setLong(1, id);
            ps.executeUpdate();

            mensaje = "La actividad se ha eliminado correctamente.";
            base.desconectraBD();
        } catch (SQLException e) {
            mensaje = "No fue posible eliminado la actividad: " + e.getMessage();
        }

        return mensaje;
    }

    @Override
    public List<Notificacion> listActivas() {
        List<Notificacion> lista = new ArrayList<>();
        Notificacion noti;

        try {
            BaseDatosMYSQL base = new BaseDatosMYSQL();
            String sql = "select * from notificaciones where visto=1 order by idNotificacion desc";
            PreparedStatement ps = base.getConeccion().prepareCall(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                noti = new Notificacion();

                noti.setIdNotificacion(rs.getInt("idNotificacion"));
                noti.setNombreNoti(rs.getString("NombreNoti"));
                noti.setAsuntoNoti(rs.getString("AsuntoNoti"));
                noti.setFechaNoti(rs.getDate("fechaNoti"));
                noti.setVisto(rs.getInt("visto"));

                lista.add(noti);
            }
            base.desconectraBD();
        } catch (SQLException e) {
            System.out.println("Excepción: " + e.getMessage());
        }

        return lista;
    }

}
