package devs.com.sistema.ventas.dao;

import devs.com.sistema.ventas.coneccion.BaseDatosMYSQL;
import devs.com.sistema.ventas.modelos.Gasto;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GastoJDBC implements IGastoDao {

    @Override
    public List<Gasto> ListAll() {
        List<Gasto> lista = new ArrayList<>();
        Gasto gasto;

        try {
            BaseDatosMYSQL base = new BaseDatosMYSQL();
            String sql = "select * from gastos order by idgasto asc";
            PreparedStatement ps = base.getConeccion().prepareCall(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                gasto = new Gasto();

                gasto.setIdgasto(rs.getLong("idgasto"));
                gasto.setTipoGasto(rs.getString("tipo_gasto"));
                gasto.setTotalGasto(rs.getDouble("total"));
                gasto.setFechaGasto(rs.getDate("fecha"));

                lista.add(gasto);
            }
            base.desconectraBD();
        } catch (SQLException e) {
            System.out.println("Excepción: " + e.getMessage());
        }

        return lista;

    }

    @Override
    public Gasto findById(long clave) {
        
        Gasto gasto=null;

        try {
            BaseDatosMYSQL base = new BaseDatosMYSQL();
            String sql = "select * from gastos where idgasto=? LIMIT 1";
            PreparedStatement ps = base.getConeccion().prepareCall(sql);
            ps.setLong(1, clave);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                gasto = new Gasto();

                gasto.setIdgasto(rs.getLong("idgasto"));
                gasto.setTipoGasto(rs.getString("tipo_gasto"));
                gasto.setTotalGasto(rs.getDouble("total"));
                gasto.setFechaGasto(rs.getDate("fecha"));

            }
            base.desconectraBD();
        } catch (SQLException e) {
            System.out.println("Excepción: " + e.getMessage());
        }

        return gasto;

    }

    @Override
    public List<Gasto> BusquedaCriterio(String criterio) {
        List<Gasto> lista = new ArrayList<>();
        Gasto gasto;
        
        try {
            BaseDatosMYSQL base = new BaseDatosMYSQL();
            String sql = "select * from gastos where tipo_gasto like '%"+criterio+"%' or fecha=like '%"+criterio+"' order by idgasto asc";
            PreparedStatement ps = base.getConeccion().prepareCall(sql);
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                gasto = new Gasto();
                
                gasto.setIdgasto(rs.getLong("idgasto"));                
                gasto.setTipoGasto(rs.getString("tipo_gasto"));                
                gasto.setTotalGasto(rs.getDouble("total"));
                gasto.setFechaGasto(rs.getDate("fecha"));

                lista.add(gasto);
            }
            base.desconectraBD();
        } catch (SQLException e) {
           System.out.println("Excepción: " + e.getMessage());
        }
        
        return lista;
    }

    @Override
    public String insert(Gasto gasto) {
        String mensaje="";
        
        try {
            BaseDatosMYSQL base = new BaseDatosMYSQL();
            String sql = "insert into gastos (tipo_gasto,total,fecha) values (?,?,?)";
            PreparedStatement ps = base.getConeccion().prepareCall(sql);
           // ps.setLong(1, cat.getCategoriaId());
            ps.setString(1, gasto.getTipoGasto());
            ps.setDouble(2, gasto.getTotalGasto());
            ps.setDate(3, (Date) gasto.getFechaGasto());
            ps.executeUpdate();
            
            mensaje="El gasto se ha creado correctamente.";
            base.desconectraBD();
        } catch (SQLException e) {
            mensaje="No fue posible crear el gasto: " + e.getMessage();
        }
        
        return mensaje;

    }

    @Override
    public String delete(long idgasto) {
         String mensaje="";
        
        try {
            BaseDatosMYSQL base = new BaseDatosMYSQL();
            String sql = "delete from gastos where idgasto=?";
            PreparedStatement ps = base.getConeccion().prepareCall(sql);
           // ps.setLong(1, cat.getCategoriaId());
            ps.setLong(1, idgasto);
            ps.executeUpdate();
            
            mensaje="El gasto se ha eliminado correctamente.";
            base.desconectraBD();
        } catch (SQLException e) {
            mensaje="No fue posible eliminar el gasto: " + e.getMessage();
        }
        
        return mensaje;
    }

    @Override
    public String update(Gasto gasto) {
      String mensaje="";
        
        try {
            BaseDatosMYSQL base = new BaseDatosMYSQL();
            String sql = "update gastos set tipo_gasto=?,total=?,fecha=? where idgasto=?";
            PreparedStatement ps = base.getConeccion().prepareCall(sql);
            ps.setString(1, gasto.getTipoGasto());
            ps.setDouble(2, gasto.getTotalGasto());
            ps.setDate(3, (Date) gasto.getFechaGasto());
            ps.setLong(4, gasto.getIdgasto());
            ps.executeUpdate();
            
            mensaje="El gasto se ha actualizado correctamente.";
            base.desconectraBD();
        } catch (SQLException e) {
            mensaje="No fue posible actualizado el gasto: " + e.getMessage();
        }
        
        return mensaje;

    }

}
