package devs.com.sistema.ventas.dao;

import devs.com.sistema.ventas.coneccion.BaseDatosMYSQL;
import devs.com.sistema.ventas.modelos.Boleta;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BoletaJBDC implements IBoleta{

    private PreparedStatement getC;

    @Override
    public Boleta findById(long id) {
        Boleta bole =null;
        
        try {
            BaseDatosMYSQL base = new BaseDatosMYSQL();
            String sql ="SELECT * FROM boleta WHERE idboleta = ?  LIMIT 1";
            PreparedStatement ps = base.getConeccion().prepareCall(sql);
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {         
                
                long clave = rs.getLong("idboleta");
                String boleta = rs.getString("boleta");
                String tipoboleta = rs.getString("tipoboleta");
                String estadoboleta = rs.getString("estadoboleta");
                double total = rs.getDouble("total");
                String formapago = rs.getString("formapago");
                long numdocumento = rs.getLong("numdocumento");
                String banco = rs.getString("banco");
                Date fecha = rs.getDate("fecha");
                double monto = rs.getDouble("monto");
                
                
                bole = new Boleta(clave, boleta, tipoboleta, estadoboleta, total, formapago,
                                    numdocumento, banco, fecha, monto);
            }
            base.desconectraBD();
            
        } catch (SQLException e) {
            System.out.println("Exepción: " + e.getMessage());
        }
          
        return bole;
    }

    @Override
    public List<Boleta> listAll() {
       
        List<Boleta> listaBoleta = new ArrayList<>();
         Boleta bole =null;
        
        try {
            BaseDatosMYSQL base = new BaseDatosMYSQL();
            String sql ="SELECT * FROM boleta order by idboleta asc";
            PreparedStatement ps = base.getConeccion().prepareCall(sql);
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {         
                
                long clave = rs.getLong("idboleta");
                String boleta = rs.getString("boleta");
                String tipoboleta = rs.getString("tipoboleta");
                String estadoboleta = rs.getString("estadoboleta");
                double total = rs.getDouble("total");
                String formapago = rs.getString("formapago");
                long numdocumento = rs.getLong("numdocumento");
                String banco = rs.getString("banco");
                Date fecha = rs.getDate("fecha");
                double monto = rs.getDouble("monto");
                
                bole = new Boleta(clave, boleta, tipoboleta, estadoboleta, total, formapago,
                                    numdocumento, banco, fecha, monto);
           
                listaBoleta.add(bole);
            }
            base.desconectraBD();
            
        } catch (SQLException e) {
            System.out.println("Exepción: " + e.getMessage());
        }
          
        return listaBoleta;
    }

    @Override
    public String insert(Boleta boleta) {
        String mensaje ="";
        
        try {
             BaseDatosMYSQL base = new BaseDatosMYSQL();
             String sql = "insert into boleta "
                     + "(idboleta,boleta,tipoboleta,estadoboleta,total,formapago,"
                     + "numdocumento,banco,fecha,monto) "
                     + "values (?,?,?,?,?,?,?,?,?,?)";
             PreparedStatement ps = base.getConeccion().prepareCall(sql);
             ps.setLong(1, boleta.getIdboleta());
             ps.setString(2, boleta.getBoleta());
             ps.setString(3, boleta.getTipoboleta());
             ps.setString(4, boleta.getEstadoboleta());
             ps.setDouble(5, boleta.getTotal());
             ps.setString(6, boleta.getFormapago());
             ps.setLong(7, boleta.getNumdocumento());
             ps.setString(8, boleta.getBanco());
             ps.setDate(9, (java.sql.Date)boleta.getFecha());
             ps.setDouble(10, boleta.getMonto());
             
             ps.executeUpdate();
             
            mensaje="La boleta se ha creado correctamente.";
            base.desconectraBD();
        } catch (SQLException e) {
            mensaje="No fue posible crear La boleta: " + e.getMessage();
        }
       
        
        return mensaje;
    }

    @Override
    public String update(Boleta boleta) {
        
        String mensaje ="";
        
        try {
             BaseDatosMYSQL base = new BaseDatosMYSQL();
             String sql = "update boleta "
                     + "set boleta=?,tipoboleta=?,estadoboleta=?,total=?,formapago=?,"
                     + "numdocumento=?,banco=?,fecha=?,monto=? where idboleta=?";
             PreparedStatement ps = base.getConeccion().prepareCall(sql);
             ps.setString(1, boleta.getBoleta());
             ps.setString(2, boleta.getTipoboleta());
             ps.setString(3, boleta.getEstadoboleta());
             ps.setDouble(4, boleta.getTotal());
             ps.setString(5, boleta.getFormapago());
             ps.setLong(6, boleta.getNumdocumento());
             ps.setString(7, boleta.getBanco());
             ps.setDate(8, (java.sql.Date)boleta.getFecha());
             ps.setDouble(9, boleta.getMonto());
             ps.setLong(10, boleta.getIdboleta());
             
             ps.executeUpdate();
             
            mensaje="La boleta se ha actualizado correctamente.";
            base.desconectraBD();
        } catch (SQLException e) {
            mensaje="No fue posible actualizar La boleta: " + e.getMessage();
        }
       
        
        return mensaje;
    }

    @Override
    public String delete(long id) {
        String mensaje ="";
       
        try {
            BaseDatosMYSQL base = new BaseDatosMYSQL();
            
            String sql ="delete from boleta where idboleta=?";
            PreparedStatement ps = base.getConeccion().prepareCall(sql);
            ps.setLong(1, id);
            
            ps.executeUpdate();
            
            mensaje="La boleta se ha eliminar correctamente.";
            base.desconectraBD();
            
        } catch (SQLException e) {
            mensaje="No fue posible eliminar la boleta: " + e.getMessage();
        }
        
       return mensaje;
    }
    
    
}
