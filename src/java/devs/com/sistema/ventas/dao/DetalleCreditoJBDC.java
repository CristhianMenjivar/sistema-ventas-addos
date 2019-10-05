package devs.com.sistema.ventas.dao;

import devs.com.sistema.ventas.coneccion.BaseDatosMYSQL;
import devs.com.sistema.ventas.modelos.DetalleCredito;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DetalleCreditoJBDC implements IDetalleCredito {

    @Override
    public List<DetalleCredito> findCreditoID(long clave) {
        List<DetalleCredito> lista = new ArrayList<>();
        DetalleCredito detalle = null;

        try {
            BaseDatosMYSQL base = new BaseDatosMYSQL();

            String sql = "SELECT * FROM detalle_credito where idcredito=?";
            PreparedStatement ps = base.getConeccion().prepareCall(sql);
            ps.setLong(1, clave);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                long idDetalle = rs.getLong("iddetalle_credito");
                long idCredito = rs.getLong("idcredito");
                double pago = rs.getDouble("pago");
                Date fecha = rs.getDate("fecha");

                detalle = new DetalleCredito(idDetalle, idCredito, pago, fecha);
                lista.add(detalle);
            }
            base.desconectraBD();

        } catch (SQLException e) {
            System.out.println("Exepción: " + e.getMessage());
        }

        return lista;
    }

    public static void main(String[] args) {
        DetalleCreditoJBDC obj = new DetalleCreditoJBDC();

        List<DetalleCredito> lista = obj.findCreditoID(1);

        for (DetalleCredito detalleCredito : lista) {
            System.out.println(detalleCredito.getIdDetalle() + ", " + detalleCredito.getCuota());
        }
    }

    @Override
    public String insertarDetalle(DetalleCredito detalle) {
        String mensaje = "";

        try {
            BaseDatosMYSQL base = new BaseDatosMYSQL();
            String sql = "insert into detalle_credito(idcredito,pago,fecha) values(?,?,?)";
            PreparedStatement ps = base.getConeccion().prepareCall(sql);
            ps.setLong(1, detalle.getIdCredito());
            ps.setDouble(2, detalle.getCuota());
            ps.setDate(3, (java.sql.Date) (Date) detalle.getFecha());

            ps.executeUpdate();

            mensaje = "EL pago se ha realizado correctamente.";
            base.desconectraBD();
        } catch (SQLException e) {
            mensaje = "No fue posible realizar el pago: " + e.getMessage();
        }

        return mensaje;
    }

    @Override
    public String eliminarDetalle(long id) {
        String mensaje = "";

        try {
            BaseDatosMYSQL base = new BaseDatosMYSQL();
            String sql = "delete from detalle_credito where iddetalle_credito=?";
            PreparedStatement ps = base.getConeccion().prepareCall(sql);
            ps.setLong(1, id);

            ps.executeUpdate();

            mensaje = "EL pago se ha eliminado correctamente.";
            base.desconectraBD();
        } catch (SQLException e) {
            mensaje = "No fue posible eliminar el pago: " + e.getMessage();
        }

        return mensaje;
    }

}
