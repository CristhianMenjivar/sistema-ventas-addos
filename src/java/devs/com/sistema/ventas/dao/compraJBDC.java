/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package devs.com.sistema.ventas.dao;

import devs.com.sistema.ventas.coneccion.BaseDatosMYSQL;
import devs.com.sistema.ventas.modelos.Compra;
import devs.com.sistema.ventas.modelos.DetalleCompra;
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
public class compraJBDC implements IcompraDAO {

    @Override
    public Compra insert(Compra compra) {

        BaseDatosMYSQL base = new BaseDatosMYSQL();
        try {
            int idGenerado = 0;
            base.getConeccion().setAutoCommit(false); //para que todos los procesos no se ejecute en la BD
            String sql = "insert into compras(fecha,monto) "
                    + "values(?,?) ";
            PreparedStatement ps = base.getConeccion().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setDate(1, (java.sql.Date) (Date) compra.getFecha());
            ps.setDouble(2, compra.getMonto());

            ps.executeUpdate();

            ResultSet generatedKeys = ps.getGeneratedKeys(); // agarrar la yave generada
            if (generatedKeys.next()) {
                idGenerado = generatedKeys.getInt(1);
            }

            PreparedStatement ps2;
            PreparedStatement ps3;

            for (DetalleCompra detalle : compra.getDetalle()) {
                String sqlDetalles = "INSERT INTO detalle_compra(idproducto, idcompra, cantidad, importe) VALUES (?,?,?,?)";
                ps2 = base.getConeccion().prepareStatement(sqlDetalles);
                ps2.setLong(1, detalle.getProducto().getIdProducto());
                ps2.setLong(2, idGenerado);
                ps2.setDouble(3, detalle.getCantidad());
                ps2.setDouble(4, detalle.getImporte());

                ps2.executeUpdate();

                // restamos a cada producto su existencias
                String sqlInventario = "update productos set inventario = inventario + ?  where idproducto=?";

                ps3 = base.getConeccion().prepareStatement(sqlInventario);
                ps3.setDouble(1, detalle.getCantidad());
                ps3.setLong(2, detalle.getProducto().getIdProducto());

                ps3.executeUpdate();
            }

            //si todas los procesos se completaron con exito se ejecuta el comit sino el roll back
            base.getConeccion().commit();
            compra.setCompraId(idGenerado);

        } catch (SQLException ex) {
            if (base.getConeccion() != null) {
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
        } finally {
            if (base.getConeccion() != null) {
                base.desconectraBD();
            }
        }

        return compra;
    }

    @Override
    public List<Compra> listAll() {

        Compra compra = null;
        List<Compra> Listacompras = new ArrayList<>();

        BaseDatosMYSQL base = new BaseDatosMYSQL();

        try {
            String sql = "select * from compras";
            PreparedStatement ps = base.getConeccion().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                long id = rs.getLong("idcompra");
                java.sql.Date fecha = rs.getDate("fecha");
                double monto = rs.getDouble("monto");

                compra = new Compra(id, fecha, monto);
                Listacompras.add(compra);

            }

            base.desconectraBD();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (base.getConeccion() != null) {
                base.desconectraBD();
            }
        }

        return Listacompras;
    }

    @Override
    public Compra findById(long idCompra) {
        Compra compra = null;

        BaseDatosMYSQL base = new BaseDatosMYSQL();

        try {
            String sql = "select * from compras where idcompra = ? LIMIT 1";
            PreparedStatement ps = base.getConeccion().prepareStatement(sql);
            ps.setLong(1, idCompra);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                long id = rs.getLong("idcompra");
                java.sql.Date fecha = rs.getDate("fecha");
                double monto = rs.getDouble("monto");

                compra = new Compra(id, fecha, monto);

            }

            base.desconectraBD();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (base.getConeccion() != null) {
                base.desconectraBD();
            }
        }

        return compra;
    }

    @Override
    public List<DetalleCompra> detalles(Compra compra) {
        DetalleCompra detalle = null;
        List<DetalleCompra> listaDetalle = new ArrayList<>();

        BaseDatosMYSQL base = new BaseDatosMYSQL();
        IProductosDao daoProducto = new ProductoJDBC();

        try {
            String sql = "select * from detalle_compra where idcompra=?";
            PreparedStatement ps = base.getConeccion().prepareStatement(sql);
            ps.setLong(1, compra.getCompraId());

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                long idDetalle = rs.getLong("iddetalle");
                long idProducto = rs.getLong("idproducto");
                long idcompra = rs.getLong("idcompra");
                double cantidad = rs.getDouble("cantidad");
                Double importe = rs.getDouble("importe");

                Producto prod = daoProducto.findById(idProducto);

                detalle = new DetalleCompra(compra, idDetalle, prod, cantidad, importe);
                listaDetalle.add(detalle);
            }

            base.desconectraBD();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (base.getConeccion() != null) {
                base.desconectraBD();
            }
        }

        return listaDetalle;
    }

//    public static void main(String[] args) {
//        compraJBDC daoCompra = new compraJBDC();
//        List<Compra> listaCompras = daoCompra.listAll();
//
//        for (Compra c : listaCompras) {
//            System.out.println(c.getCompraId());
//        }
//    }

    @Override
    public int comprasDelDia(java.util.Date fecha) {
        int total=0;
           BaseDatosMYSQL base = new BaseDatosMYSQL();
       
        try {
            String sql ="select * from compras where fecha=?";
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
    public List<Compra> listComprasDia(java.util.Date fecha) {
       Compra compra = null;
        List<Compra> Listacompras = new ArrayList<>();

        BaseDatosMYSQL base = new BaseDatosMYSQL();

        try {
            String sql = "select * from compras where fecha=? order by idcompra desc";
            PreparedStatement ps = base.getConeccion().prepareStatement(sql);
            ps.setDate(1, (Date) fecha);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                long id = rs.getLong("idcompra");
                double monto = rs.getDouble("monto");
                compra = new Compra(id, fecha, monto);
                Listacompras.add(compra);

            }

            base.desconectraBD();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (base.getConeccion() != null) {
                base.desconectraBD();
            }
        }

        return Listacompras;
    }
}
