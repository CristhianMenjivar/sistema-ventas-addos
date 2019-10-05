package devs.com.sistema.ventas.dao;

import devs.com.sistema.ventas.coneccion.BaseDatosMYSQL;
import devs.com.sistema.ventas.modelos.Cliente;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ClienteJDBC implements IClienteDao{

    private PreparedStatement getC;

    @Override
    public Cliente findById(long id) {
        Cliente client =null;
        
        try {
            BaseDatosMYSQL base = new BaseDatosMYSQL();
            String sql ="SELECT * FROM clientes WHERE idcliente = ?  LIMIT 1";
            PreparedStatement ps = base.getConeccion().prepareCall(sql);
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {         
                
                long clave = rs.getLong("idcliente");
                String nombre = rs.getString("nombre");
                String apellido = rs.getString("apellido");
                String direccion = rs.getString("direccion");
                String telefono = rs.getString("telefono");
                String numfactura = rs.getString("numfactura");
                String tipofactura = rs.getString("tipofactura");
                String estadofactura = rs.getString("estadofactura");
                double total = rs.getDouble("total");
                String formapago = rs.getString("formapago");
                long numdocumento = rs.getLong("numdocumento");
                String banco = rs.getString("banco");
                Date fecha = rs.getDate("fecha");
                double monto = rs.getDouble("monto");
                String rut = rs.getString("rut");
                
                client = new Cliente(clave, nombre, apellido, direccion, telefono, numfactura, tipofactura,
                                estadofactura, total, formapago, numdocumento, banco, fecha, monto, rut);
            }
            base.desconectraBD();
            
        } catch (SQLException e) {
            System.out.println("Exepción: " + e.getMessage());
        }
          
        return client;
     }

    @Override
    public List<Cliente> listAll() {
        
        List<Cliente> listaClientes = new ArrayList<>();
         Cliente client =null;
        
        try {
            BaseDatosMYSQL base = new BaseDatosMYSQL();
            String sql ="SELECT * FROM clientes order by idcliente asc";
            PreparedStatement ps = base.getConeccion().prepareCall(sql);
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {         
                
                long clave = rs.getLong("idcliente");
                String nombre = rs.getString("nombre");
                String apellido = rs.getString("apellido");
                String direccion = rs.getString("direccion");
                String telefono = rs.getString("telefono");
                String numfactura = rs.getString("numfactura");
                String tipofactura = rs.getString("tipofactura");
                String estadofactura = rs.getString("estadofactura");
                double total = rs.getDouble("total");
                String formapago = rs.getString("formapago");
                long numdocumento = rs.getLong("numdocumento");
                String banco = rs.getString("banco");
                Date fecha = rs.getDate("fecha");
                double monto = rs.getDouble("monto");
                String rut = rs.getString("rut");
                
                client = new Cliente(clave, nombre, apellido, direccion, telefono, numfactura, tipofactura,
                                estadofactura, total, formapago, numdocumento, banco, fecha, monto, rut);
           
                listaClientes.add(client);
            }
            base.desconectraBD();
            
        } catch (SQLException e) {
            System.out.println("Exepción: " + e.getMessage());
        }
          
        return listaClientes;
    }

    @Override
    public String insert(Cliente cliente) {
        String mensaje ="";
        
        try {
             BaseDatosMYSQL base = new BaseDatosMYSQL();
             String sql = "insert into clientes "
                     + "(nombre,apellido,direccion,telefono,numfactura,tipofactura,estadofactura,"
                     + "total,formapago,numdocumento,banco,fecha,monto,rut) "
                     + "values (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
             PreparedStatement ps = base.getConeccion().prepareCall(sql);
             ps.setString(1, cliente.getNombre());
             ps.setString(2, cliente.getApellido());
             ps.setString(3, cliente.getDireccion());
             ps.setString(4, cliente.getTelefono());
             ps.setString(5, cliente.getNumfactura());
             ps.setString(6, cliente.getTipofactura());
             ps.setString(7, cliente.getEstadofactura());
             ps.setDouble(8, cliente.getTotal());
             ps.setString(9, cliente.getFormapago());
             ps.setLong(10, cliente.getNumdocumento());
             ps.setString(11, cliente.getBanco());
             ps.setDate(12, (java.sql.Date)cliente.getFecha());
             ps.setDouble(13, cliente.getMonto());
             ps.setString(14, cliente.getRut());
             
             ps.executeUpdate();
             
            mensaje="El cliente se ha creado correctamente.";
            base.desconectraBD();
        } catch (SQLException e) {
            mensaje="No fue posible crear el cliente: " + e.getMessage();
        }
       
        
        return mensaje;
    }

    @Override
    public String update(Cliente cliente) {
       
        String mensaje ="";
        
        try {
             BaseDatosMYSQL base = new BaseDatosMYSQL();
             String sql = "update clientes "
                     + "set nombre=?,apellido=?,direccion=?,telefono=?,numfactura=?,tipofactura=?,"
                     + "estadofactura=?,total=?,formapago=?,numdocumento=?,banco=?,fecha=?,monto=?,rut=? where idcliente=?";
             PreparedStatement ps = base.getConeccion().prepareCall(sql);
             ps.setString(1, cliente.getNombre());
             ps.setString(2, cliente.getApellido());
             ps.setString(3, cliente.getDireccion());
             ps.setString(4, cliente.getTelefono());
             ps.setString(5, cliente.getNumfactura());
             ps.setString(6, cliente.getTipofactura());
             ps.setString(7, cliente.getEstadofactura());
             ps.setDouble(8, cliente.getTotal());
             ps.setString(9, cliente.getFormapago());
             ps.setLong(10, cliente.getNumdocumento());
             ps.setString(11, cliente.getBanco());
             ps.setDate(12, (java.sql.Date)cliente.getFecha());
             ps.setDouble(13, cliente.getMonto());
             ps.setString(14, cliente.getRut());
             ps.setLong(15, cliente.getIdCliente());
             
             ps.executeUpdate();
             
            mensaje="El cliente se ha actualizado correctamente.";
            base.desconectraBD();
        } catch (SQLException e) {
            mensaje="No fue posible actualizar el cliente: " + e.getMessage();
        }
       
        
        return mensaje;
    
    }

    @Override
    public String delete(long id) {
       String mensaje ="";
       
        try {
            BaseDatosMYSQL base = new BaseDatosMYSQL();
            
            String sql ="delete from clientes where idcliente=?";
            PreparedStatement ps = base.getConeccion().prepareCall(sql);
            ps.setLong(1, id);
            
            ps.executeUpdate();
            
            mensaje="El cliente se ha eliminar correctamente.";
            base.desconectraBD();
            
        } catch (SQLException e) {
            mensaje="No fue posible eliminar el cliente: " + e.getMessage();
        }
        
       return mensaje;
       
    }
    
    public static void main(String[] args) {
        //METODOS 100% PROBADOS FUNCIONANDO
          ClienteJDBC base = new ClienteJDBC();
        
//          System.out.println(base.update(new Cliente(0,"cristhian 3","Menjivar","launa seca","70804980")));
          
//       
//        
//        System.out.println(base.update(new Cliente(2,"cristhian 2","Menjivar 2","launa seca 2","80808080")));
//        
//        Cliente cl = base.findById(1);
//        System.out.println("cliente encontrado: "+ cl.getNombre());
//        
//       // System.out.println("borrar: "+ base.delete(1));
//        
//      
//         List<Cliente> lista = base.listAll();
//         System.out.println("Lista de clientes:");
//        
//        for (Cliente c : lista) {
//            System.out.println(c.getNombre());
//            System.out.println(c.getApellido());
//            System.out.println(c.getDireccion());
//        }
        
        
    }
    
}
