package devs.com.sistema.ventas.dao;

import devs.com.sistema.ventas.coneccion.BaseDatosMYSQL;
import devs.com.sistema.ventas.modelos.Cuentas;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CuentasJDBC implements ICuentaDao{

    @Override
    public List<Cuentas> listAll() {
       List<Cuentas> listaCuentas = new ArrayList<>();
       Cuentas cuentas;
        
        try {
            
            BaseDatosMYSQL base = new BaseDatosMYSQL();
            String sql = "select * from cuentas order by idcuenta asc";
            PreparedStatement ps = base.getConeccion().prepareCall(sql);
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                
                long id =rs.getInt("idcuenta");                
                String o =rs.getString("o");
                String t =rs.getString("t");
                Date fecha =rs.getDate("fecha");
                String emisor =rs.getString("emisor");
                String descripcion =rs.getString("descripcion");
                String ctacont =rs.getString("ctacont");
                String centro =rs.getString("centro");
                String usuario =rs.getString("usuario");
                Double pago =rs.getDouble("pago");
                Double monto =rs.getDouble("monto");
                
                
                cuentas = new Cuentas(id, o, t, fecha, emisor, descripcion,
                                     ctacont, centro, usuario, pago, monto);
                
                listaCuentas.add(cuentas);
            }
            base.desconectraBD();
        } catch (SQLException e) {
           System.out.println("Excepción: " + e.getMessage());
        }
        
        return listaCuentas;
    }

    @Override
    public Cuentas findById(long id) {
        
        Cuentas cuentas = null;
        
        try {
            
            BaseDatosMYSQL base = new BaseDatosMYSQL();
            String sql = "select * from cuentas where idcuenta=? LIMIT 1";
            PreparedStatement ps = base.getConeccion().prepareCall(sql);
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                
                long clave =rs.getInt("idcuenta");                
                String o =rs.getString("o");
                String t =rs.getString("t");
                Date fecha =rs.getDate("fecha");
                String emisor =rs.getString("emisor");
                String descripcion =rs.getString("descripcion");
                String ctacont =rs.getString("ctacont");
                String centro =rs.getString("centro");
                String usuario =rs.getString("usuario");
                Double pago =rs.getDouble("pago");
                Double monto =rs.getDouble("monto");
                
                cuentas = new Cuentas(clave, o, t, fecha, emisor, descripcion,
                                     ctacont, centro, usuario, pago, monto);
                
            }
            base.desconectraBD();
        } catch (SQLException e) {
           System.out.println("Excepción: " + e.getMessage());
        }
        
        return cuentas;
    }

    @Override
    public String insert(Cuentas cuentas) {
        String mensaje="";
        
        try {
            BaseDatosMYSQL base = new BaseDatosMYSQL();
            String sql = "insert into cuentas (o,t,fecha,"
                    + "emisor,descripcion,ctacont,centro,usuario,pago,monto) "
                    + "values (?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement ps = base.getConeccion().prepareCall(sql);
            ps.setString(1, cuentas.getO());
            ps.setString(2, cuentas.getT());
            ps.setDate(3, (java.sql.Date)cuentas.getFecha());
            ps.setString(4, cuentas.getEmisor());
            ps.setString(5, cuentas.getDescripcion());
            ps.setString(6, cuentas.getCtacont());
            ps.setString(7, cuentas.getCentro());
            ps.setString(8, cuentas.getUsuario());
            ps.setDouble(9, cuentas.getPago());
            ps.setDouble(10, cuentas.getMonto());
            
            
            ps.executeUpdate();
            
            mensaje="La Cuenta por pagar se ha creado correctamente.";
            base.desconectraBD();
        } catch (SQLException e) {
            mensaje="No fue posible crear La Cuenta por pagar: " + e.getMessage();
        }
        
        return mensaje;
    }

    @Override
    public String update(Cuentas cuentas) {
        String mensaje="";
        
        try {
            BaseDatosMYSQL base = new BaseDatosMYSQL();
            String sql = "update cuentas set o=?,t=?,fecha=?,"
                    + "emisor=?,descripcion=?,ctacont=?,centro=?,usuario=?,pago=?,monto=?"
                    + "where idcuenta=?";
            PreparedStatement ps = base.getConeccion().prepareCall(sql);
            ps.setString(1, cuentas.getO());
            ps.setString(2, cuentas.getT());
            ps.setDate(3, (java.sql.Date)cuentas.getFecha());
            ps.setString(4, cuentas.getEmisor());
            ps.setString(5, cuentas.getDescripcion());
            ps.setString(6, cuentas.getCtacont());
            ps.setString(7, cuentas.getCentro());
            ps.setString(8, cuentas.getUsuario());
            ps.setDouble(9, cuentas.getPago());
            ps.setDouble(10, cuentas.getMonto());
            ps.setLong(11, cuentas.getIdcuenta());
            
            
            ps.executeUpdate();
            
            mensaje="La Cuenta por pagar se ha actualizado correctamente.";
            base.desconectraBD();
        } catch (SQLException e) {
            mensaje="No fue posible actualizado La Cuenta por pagar: " + e.getMessage();
        }
        
        return mensaje;
    }

    @Override
    public String delete(long id) {
       String mensaje="";
        
        try {
            BaseDatosMYSQL base = new BaseDatosMYSQL();
            String sql = "delete from cuentas where idcuenta = ?";
            PreparedStatement ps = base.getConeccion().prepareCall(sql);
            ps.setLong(1, id);
            
            ps.executeUpdate();
            
            mensaje="La Cuenta por pagar se ha eliminado correctamente.";
            base.desconectraBD();
        } catch (SQLException e) {
            mensaje="No fue posible eliminar la Cuenta por pagar: " + e.getMessage();
        }
        
        return mensaje;
    }
    
    
}
