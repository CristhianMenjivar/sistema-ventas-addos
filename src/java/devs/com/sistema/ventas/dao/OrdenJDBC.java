/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package devs.com.sistema.ventas.dao;

import devs.com.sistema.ventas.coneccion.BaseDatosMYSQL;
import devs.com.sistema.ventas.modelos.DetalleOrdenes;
import devs.com.sistema.ventas.modelos.Orden;
import devs.com.sistema.ventas.modelos.Producto;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author usuario
 */
public class OrdenJDBC implements IOrdenDao{

    @Override
    public Orden insert(Orden orden) {

        BaseDatosMYSQL base = new BaseDatosMYSQL();
        try {
            int idGenerado=0;
            base.getConeccion().setAutoCommit(false); //para que todos los procesos no se ejecute en la BD
            String sql="insert into ventas(fecha,monto,tipo_venta,tipo_pago) "
                    + "values(?,?,?,?) ";
            PreparedStatement ps = base.getConeccion().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setDate(1, (Date) orden.getFecha());
            ps.setDouble(2, orden.getMonto());
            ps.setString(3, orden.getTipoVenta());
            ps.setString(4, orden.getTipoPago());
            
            ps.executeUpdate();
            
            ResultSet generatedKeys = ps.getGeneratedKeys(); // agarrar la yave generada
            if (generatedKeys.next()) {
                idGenerado=generatedKeys.getInt(1);
            }
            
            PreparedStatement ps2;
            PreparedStatement ps3;
            
            for(DetalleOrdenes detalle: orden.getDetalle()){
                String sqlDetalles = "INSERT INTO detalle_ventas(idproducto, idventa, cantidad, importe) VALUES (?,?,?,?)";
                ps2 = base.getConeccion().prepareStatement(sqlDetalles);
                ps2.setLong(1, detalle.getProducto().getIdProducto());
                ps2.setLong(2, idGenerado);
                ps2.setDouble(3, detalle.getCantidad());
                ps2.setDouble(4, detalle.getImporte());
                
                ps2.executeUpdate();
                
                // restamos a cada producto su existencias
                String sqlInventario = "update productos set inventario = case when  (?>= inventario)  then 0 else inventario - ?  end  where idproducto=?";
                
                ps3 = base.getConeccion().prepareStatement(sqlInventario);
                ps3.setDouble(1, detalle.getCantidad());
                ps3.setDouble(2, detalle.getCantidad());
                ps3.setLong(3, detalle.getProducto().getIdProducto());
                
                ps3.executeUpdate();
            }
            
            //si todas los procesos se completaron con exito se ejecuta el comit sino el roll back
            base.getConeccion().commit();
            orden.setOrdenId(idGenerado);
            
        } catch (SQLException ex) {
            if (base.getConeccion() !=null) {
                try {
                    System.err.print("La transacción no pudo realizarse");
                    base.getConeccion().rollback();
                    System.err.print("Se hiso rollBack");
                } catch (SQLException e) {
                    System.err.println("No pudo hacerce el rollBack de la transacción");
                }
            }
            ex.printStackTrace();
            base.desconectraBD();
        } finally{
            if (base.getConeccion()!=null) {
                base.desconectraBD();
            }
        }
            
      return orden;
    }

    @Override
    public List<Orden> listAll() {
       Orden orden =null;
       List<Orden> ordenes = new ArrayList<>();

       BaseDatosMYSQL base = new BaseDatosMYSQL();
       
        try {
            String sql ="select * from ventas order by idventa desc";
            PreparedStatement ps = base.getConeccion().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {                
                long id = rs.getLong("idventa");
                java.sql.Date fecha = rs.getDate("fecha");
                double monto = rs.getDouble("monto");
                String tipoVenta = rs.getString("tipo_venta");
                String tipopago =rs.getString("tipo_pago");
                
                orden = new Orden(id, fecha, monto, tipopago, tipoVenta);
                ordenes.add(orden);
                
            }
            
            base.desconectraBD();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally{
            if(base.getConeccion() !=null){
                base.desconectraBD();
            }
        }
       
       
       return ordenes;
    }

    @Override
    public Orden findById(long idOrden) {
       Orden orden =null;
       
       BaseDatosMYSQL base = new BaseDatosMYSQL();
       
        try {
            String sql ="select * from ventas where idventa=? LIMIT 1";
            PreparedStatement ps = base.getConeccion().prepareStatement(sql);
            ps.setLong(1, idOrden);
            
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {      
                
                long id = rs.getLong("idventa");
                java.sql.Date fecha = rs.getDate("fecha");
                double monto = rs.getDouble("monto");
                String tipoVenta = rs.getString("tipo_venta");
                String tipopago =rs.getString("tipo_pago");
                
                orden = new Orden(id, fecha, monto, tipopago, tipoVenta);
                
            }
            
            base.desconectraBD();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally{
            if(base.getConeccion() !=null){
                base.desconectraBD();
            }
        }
       
       
       return orden;

    }

    @Override
    public List<DetalleOrdenes> detalles(Orden orden) {
       DetalleOrdenes detalle =null;
       List<DetalleOrdenes> listaDetalle = new ArrayList<>();
       
       BaseDatosMYSQL base = new BaseDatosMYSQL();
       IProductosDao daoProducto = new ProductoJDBC();
       
        try {
            String sql ="select * from detalle_ventas where idventa=?";
            PreparedStatement ps = base.getConeccion().prepareStatement(sql);
            ps.setLong(1, orden.getOrdenId());
            
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {                
                long idDetalle = rs.getLong("iddetalle_ventas");
                long idProducto = rs.getLong("idproducto");
                long idVenta = rs.getLong("idventa");
                double cantidad = rs.getDouble("cantidad");
                Double importe = rs.getDouble("importe");
                
                Producto prod = daoProducto.findById(idProducto);
                
                detalle = new DetalleOrdenes(orden, idDetalle, prod, cantidad, importe);
                listaDetalle.add(detalle);
            }
            
            base.desconectraBD();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally{
            if(base.getConeccion() !=null){
                base.desconectraBD();
            }
        }
       
       
       return listaDetalle;
    }

    @Override
    public int ventasDelDia(java.util.Date fecha) {
         int total=0;
           BaseDatosMYSQL base = new BaseDatosMYSQL();
       
        try {
            String sql ="select * from ventas where fecha=?";
            PreparedStatement ps = base.getConeccion().prepareStatement(sql);
            ps.setDate(1, (Date) fecha);
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {                
                total++;
            }
            
            base.desconectraBD();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally{
            if(base.getConeccion() !=null){
                base.desconectraBD();
            }
        }
       
       
       return total;
    }

    @Override
    public List<Orden> listForDate(java.util.Date fecha) {
      
        Orden orden =null;
       List<Orden> ordenes = new ArrayList<>();

       BaseDatosMYSQL base = new BaseDatosMYSQL();
       
        try {
            String sql ="select * from ventas where fecha=? order by idventa desc";
            PreparedStatement ps = base.getConeccion().prepareStatement(sql);
            ps.setDate(1, (Date) fecha);
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {                
                long id = rs.getLong("idventa");
                double monto = rs.getDouble("monto");
                String tipoVenta = rs.getString("tipo_venta");
                String tipopago =rs.getString("tipo_pago");
                
                orden = new Orden(id, fecha, monto, tipopago, tipoVenta);
                ordenes.add(orden);
                
            }
            
            base.desconectraBD();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally{
            if(base.getConeccion() !=null){
                base.desconectraBD();
            }
        }
       
       
       return ordenes;

    }
    
}
