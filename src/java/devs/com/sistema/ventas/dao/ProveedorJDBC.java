/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package devs.com.sistema.ventas.dao;

import devs.com.sistema.ventas.coneccion.BaseDatosMYSQL;
import devs.com.sistema.ventas.modelos.Proveedor;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author usuario
 */
public class ProveedorJDBC implements IProveedorDao{

    @Override
    public List<Proveedor> listAll() {
        List<Proveedor> listaProveedor = new ArrayList<>();
        Proveedor provedor;
        
        try {
            
            BaseDatosMYSQL base = new BaseDatosMYSQL();
            String sql = "select * from proveedor order by idproveedor asc";
            PreparedStatement ps = base.getConeccion().prepareCall(sql);
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                
                long id =rs.getInt("idproveedor");                
                String emitidopor =rs.getString("emitidopor");
                String moneda =rs.getString("moneda");
                String tipodocumento =rs.getString("tipodocumento");
                Date fecha =rs.getDate("fecha");
                String periododeclarado =rs.getString("periododeclarado");
                String comentario =rs.getString("comentario");
                String mtoaf =rs.getString("mtoaf");
                String mtoe =rs.getString("mtoe");
                Double iva =rs.getDouble("iva");
                Double total =rs.getDouble("total");
                
                
                provedor = new Proveedor(id, emitidopor, moneda, tipodocumento, fecha, periododeclarado, comentario,
                                     mtoaf, mtoe, iva, total);
                
                listaProveedor.add(provedor);
            }
            base.desconectraBD();
        } catch (SQLException e) {
           System.out.println("Excepción: " + e.getMessage());
        }
        
        return listaProveedor;
    }

    @Override
    public Proveedor findById(long id) {
        
        Proveedor provedor=null;
        
        try {
            
            BaseDatosMYSQL base = new BaseDatosMYSQL();
            String sql = "select * from proveedor where idproveedor=? LIMIT 1";
            PreparedStatement ps = base.getConeccion().prepareCall(sql);
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                
                long clave =rs.getInt("idproveedor");                
                String emitidopor =rs.getString("emitidopor");
                String moneda =rs.getString("moneda");
                String tipodocumento =rs.getString("tipodocumento");
                Date fecha =rs.getDate("fecha");
                String periododeclarado =rs.getString("periododeclarado");
                String comentario =rs.getString("comentario");
                String mtoaf =rs.getString("mtoaf");
                String mtoe =rs.getString("mtoe");
                Double iva =rs.getDouble("iva");
                Double total =rs.getDouble("total");
                
                provedor = new Proveedor(clave, emitidopor, moneda, tipodocumento, fecha, periododeclarado, comentario,
                                    mtoaf, mtoe, iva, total);
                
            }
            base.desconectraBD();
        } catch (SQLException e) {
           System.out.println("Excepción: " + e.getMessage());
        }
        
        return provedor;   
    }

    @Override
    public String insert(Proveedor prov) {
        String mensaje="";
        
        try {
            BaseDatosMYSQL base = new BaseDatosMYSQL();
            String sql = "insert into proveedor (emitidopor,moneda,tipodocumento,"
                    + "fecha,periododeclarado,comentario,mtoaf,mtoe,iva,total) "
                    + "values (?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement ps = base.getConeccion().prepareCall(sql);
//            ps.setLong(1, prov.getIdProveedor());
            ps.setString(1, prov.getEmitidopor());
            ps.setString(2, prov.getMoneda());
            ps.setString(3, prov.getTipodocumento());
            ps.setDate(4, (java.sql.Date)prov.getFecha());
            ps.setString(5, prov.getPeriododeclarado());
            ps.setString(6, prov.getComentario());
            ps.setString(7, prov.getMtoaf());
            ps.setString(8, prov.getMtoe());
            ps.setDouble(9, prov.getIva());
            ps.setDouble(10, prov.getTotal());
            
            
            ps.executeUpdate();
            
            mensaje="El Proveedor se ha creado correctamente.";
            base.desconectraBD();
        } catch (SQLException e) {
            mensaje="No fue posible crear El Proveedor: " + e.getMessage();
        }
        
        return mensaje;
    }

    @Override
    public String update(Proveedor prov) {
        String mensaje="";
        
        try {
            BaseDatosMYSQL base = new BaseDatosMYSQL();
             String sql = "update proveedor set emitidopor=?,moneda=?,tipodocumento=?,fecha=?,"
                    + "periododeclarado=?,comentario=?,mtoaf=?,mtoe=?,iva=?,total=?"
                    + "where idproveedor=?";
            PreparedStatement ps = base.getConeccion().prepareCall(sql);
            ps.setString(1, prov.getEmitidopor());
            ps.setString(2, prov.getMoneda());
            ps.setString(3, prov.getTipodocumento());
            ps.setDate(4, (java.sql.Date)(Date)prov.getFecha());
            ps.setString(5, prov.getPeriododeclarado());
            ps.setString(6, prov.getComentario());
            ps.setString(7, prov.getMtoaf());
            ps.setString(8, prov.getMtoe());
            ps.setDouble(9, prov.getIva());
            ps.setDouble(10, prov.getTotal());
            ps.setLong(11, prov.getIdProveedor());
            
            ps.executeUpdate();
            
            mensaje="El proveedor se ha actualizado correctamente.";
            base.desconectraBD();
        } catch (SQLException e) {
            mensaje="No fue posible actualizar el proveedor: " + e.getMessage();
        }
        
        return mensaje;
    }

    @Override
    public String delete(long id) {
        String mensaje="";
        
        try {
            BaseDatosMYSQL base = new BaseDatosMYSQL();
            String sql = "delete from proveedor where idproveedor = ?";
            PreparedStatement ps = base.getConeccion().prepareCall(sql);
            ps.setLong(1, id);
            
            ps.executeUpdate();
            
            mensaje="El Proveedor se ha eliminado correctamente.";
            base.desconectraBD();
        } catch (SQLException e) {
            mensaje="No fue posible eliminar el Proveedor: " + e.getMessage();
        }
        
        return mensaje;
    }
    
    public static void main(String[] args) {
        
        // PRUEVAS DE LOS METODOS FUNCIONANDO 100%
//        ProveedorJDBC obj = new ProveedorJDBC();
        
        //System.out.println(obj.delete(130));
        
        //System.out.println(obj.insert(new Proveedor(0, "nueva empresa", "la nueva", "70889999")));
//        System.out.println(obj.update(new Proveedor(1, "lactosa sa", "san salvador", "80809999")));
        
//        Proveedor prov1 = obj.findById(1);
//        System.out.println("Proveedor encontrado es: "+ prov1.getEmitidopor());
        
//        List<Proveedor> lista = obj.listAll();
//        for ( Proveedor pro : lista) {
//            System.out.println("Nombre: "+ pro.getEmitidopor());
//        }
//        
    }
            
    
}
