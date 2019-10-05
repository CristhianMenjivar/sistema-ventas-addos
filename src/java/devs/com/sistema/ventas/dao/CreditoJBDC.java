
package devs.com.sistema.ventas.dao;

import devs.com.sistema.ventas.coneccion.BaseDatosMYSQL;
import devs.com.sistema.ventas.modelos.Cliente;
import devs.com.sistema.ventas.modelos.Credito;
import devs.com.sistema.ventas.modelos.DetalleCredito;
import devs.com.sistema.ventas.modelos.Orden;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CreditoJBDC implements ICreditoDAO{

    @Override
    public List<Credito> listAll() {
        List<Credito> lista = new ArrayList<>();
        Credito cred =null;
        
        try {
            BaseDatosMYSQL base = new BaseDatosMYSQL();
            ClienteJDBC clienteDAO = new ClienteJDBC();
            OrdenJDBC ventaDAO = new OrdenJDBC();
            
            String sql ="SELECT * FROM creditos";
            PreparedStatement ps = base.getConeccion().prepareCall(sql);
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {         
                
                long clave = rs.getLong("idcredito");
                long idCliente = rs.getLong("idcliente");
                long idVenta = rs.getLong("idventa");
                double monto = rs.getDouble("monto");
                Date fecha = rs.getDate("fecha");
                
                Cliente cliente = clienteDAO.findById(idCliente);
                Orden venta = ventaDAO.findById(idVenta);
                
                cred = new Credito(clave, cliente, venta, monto, fecha);
                lista.add(cred);
            }
            base.desconectraBD();
            
        } catch (SQLException e) {
            System.out.println("Exepción: " + e.getMessage());
        }
          
        return lista;
    }

    @Override
    public Credito findById(long id) {
       Credito cred =null;
        
        try {
            BaseDatosMYSQL base = new BaseDatosMYSQL();
            ClienteJDBC clienteDAO = new ClienteJDBC();
            OrdenJDBC ventaDAO = new OrdenJDBC();
            DetalleCreditoJBDC detalleDAO = new DetalleCreditoJBDC();
            
            String sql ="SELECT * FROM creditos WHERE idcredito=? LIMIT 1";
            PreparedStatement ps = base.getConeccion().prepareCall(sql);
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {         
                
                long clave = rs.getLong("idcredito");
                long idCliente = rs.getLong("idcliente");
                long idVenta = rs.getLong("idventa");
                double monto = rs.getDouble("monto");
                Date fecha = rs.getDate("fecha");
                
                Cliente cliente = clienteDAO.findById(idCliente);
                Orden venta = ventaDAO.findById(idVenta);
                List<DetalleCredito> listaDetalle = detalleDAO.findCreditoID(clave);
                
                cred = new Credito(clave, cliente, venta, monto, fecha);
                cred.setListaDetalles(listaDetalle);
            }
            base.desconectraBD();
            
        } catch (SQLException e) {
            System.out.println("Exepción: " + e.getMessage());
        }
          
        return cred;
    }

    @Override
    public String insert(Credito cred) {
        String mensaje ="";
        
        try {
             BaseDatosMYSQL base = new BaseDatosMYSQL();
             String sql = "insert into creditos "
                     + "(idcliente,idventa,monto,fecha) "
                     + "values (?,?,?,?)";
             PreparedStatement ps = base.getConeccion().prepareCall(sql);
             ps.setLong(1, cred.getCliente().getIdCliente());
             ps.setLong(2, cred.getVenta().getOrdenId());
             ps.setDouble(3, cred.getMontoCredito());
             ps.setDate(4, (java.sql.Date)(Date)cred.getFecha());
             
             ps.executeUpdate();
             
            mensaje="El crédito se ha creado correctamente.";
            base.desconectraBD();
        } catch (SQLException e) {
            mensaje="No fue posible crear el credito: " + e.getMessage();
        }
        
        return mensaje;
    }

    @Override
    public String update(Credito cred) {
        String mensaje ="";
        
        try {
             BaseDatosMYSQL base = new BaseDatosMYSQL();
             String sql = "update creditos set idcliente=?,idventa=?,monto=?,fecha=? where idcredito=?";
             PreparedStatement ps = base.getConeccion().prepareCall(sql);
             ps.setLong(1, cred.getCliente().getIdCliente());
             ps.setLong(2, cred.getVenta().getOrdenId());
             ps.setDouble(3, cred.getMontoCredito());
             ps.setDate(4, (java.sql.Date)(Date)cred.getFecha());
             ps.setLong(5, cred.getIdCredito());
             
             ps.executeUpdate();
             
            mensaje="El crédito se ha actualizado correctamente.";
            base.desconectraBD();
        } catch (SQLException e) {
            mensaje="No fue posible actualizar el crédito: " + e.getMessage();
        }
        
        return mensaje;
    }

    @Override
    public String delete(long id) {
        String mensaje ="";
        
        try {
             BaseDatosMYSQL base = new BaseDatosMYSQL();
             String sql = "delete from creditos where idcredito=?";
             PreparedStatement ps = base.getConeccion().prepareCall(sql);
             ps.setLong(1, id);
             
             ps.executeUpdate();
             
            mensaje="El crédito se ha eliminado correctamente.";
            base.desconectraBD();
        } catch (SQLException e) {
            mensaje="No fue posible eliminar el crédito: " + e.getMessage();
        }
        
        return mensaje;
    }
    
}
