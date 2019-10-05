package devs.com.sistema.ventas.dao;

import devs.com.sistema.ventas.coneccion.BaseDatosMYSQL;
import devs.com.sistema.ventas.modelos.Categoria;
import devs.com.sistema.ventas.modelos.Compra;
import devs.com.sistema.ventas.modelos.DetalleCompra;
import devs.com.sistema.ventas.modelos.Marca;
import devs.com.sistema.ventas.modelos.Producto;
import devs.com.sistema.ventas.modelos.Proveedor;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductoJDBC implements IProductosDao{

    @Override
    public List<Producto> listAll() {
        List<Producto> listaProductos = new ArrayList<>();
        Producto product;
        
        try {
            
            BaseDatosMYSQL base = new BaseDatosMYSQL();
            String sql = "select * from productos order by idproducto asc";
            PreparedStatement ps = base.getConeccion().prepareCall(sql);
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                
                long clave =rs.getLong("idproducto");  
                String medida =rs.getString("medida");
                String nombre = rs.getString("nombre");
                String color = rs.getString("color");
                String serie = rs.getString("serie");
                String modelo = rs.getString("modelo");
                int enLinea = rs.getInt("enLinea");
                double precioCompra =rs.getDouble("precio_compra");
                double precioVenta =rs.getDouble("precio_sugerido");
                int stocks = rs.getInt("stock");
                double existencias = rs.getDouble("inventario");
                double averiado = rs.getDouble("averiado");
                long idMarca =rs.getLong("idmarca"); 
                long idProv =rs.getLong("idproveedor"); 
                long idCat =rs.getLong("idcategoria"); 
                String tiporegistro = rs.getString("tipoRegistro");
                
                Marca mar = new MarcaJDBC().findById(idMarca); // buscar marcas
                Categoria cat =new CategoriaJDBC().findById(idCat);
                Proveedor prov = new ProveedorJDBC().findById(idProv);
                
                product = new Producto(clave, medida, nombre, color, serie,modelo, enLinea, 
                        precioCompra, precioVenta,stocks ,existencias, averiado, mar, 
                        prov, cat, tiporegistro);
                
                listaProductos.add(product);
            }
            base.desconectraBD();
        } catch (SQLException e) {
           System.out.println("Excepción: " + e.getMessage());
        }
        
        return listaProductos;
    }

    @Override
    public List<Producto> getProductosByCategoria(Categoria cat) {
        List<Producto> listaProductos = new ArrayList<>();
        Producto product;
        
        try {
            
            BaseDatosMYSQL base = new BaseDatosMYSQL();
            String sql = "select * from productos where idcategoria=? order by idproducto asc";
            PreparedStatement ps = base.getConeccion().prepareCall(sql);
            ps.setLong(1, cat.getCategoriaId());
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                
                long clave =rs.getLong("idproducto");  
                String medida =rs.getString("medida");
                String nombre = rs.getString("nombre");
                String color = rs.getString("color");
                String serie = rs.getString("serie");
                String modelo = rs.getString("modelo");
                int enLinea = rs.getInt("enLinea");
                double precioCompra =rs.getDouble("precio_compra");
                double precioVenta =rs.getDouble("precio_sugerido");
                int stocks = rs.getInt("stock");
                double existencias = rs.getDouble("inventario");
                double averiado = rs.getDouble("averiado");
                long idMarca =rs.getLong("idmarca"); 
                long idProv =rs.getLong("idproveedor"); 
                long idCat =rs.getLong("idcategoria"); 
                String tiporegistro = rs.getString("tipoRegistro");
                Marca mar = new MarcaJDBC().findById(idMarca);
                Categoria cate =new CategoriaJDBC().findById(idCat);
                Proveedor prov = new ProveedorJDBC().findById(idProv);
                
                product = new Producto(clave, medida, nombre, color, serie,modelo, enLinea, 
                        precioCompra, precioVenta,stocks ,existencias, averiado, mar, 
                        prov, cate, tiporegistro);
                listaProductos.add(product);
            }
            base.desconectraBD();
        } catch (SQLException e) {
           System.out.println("Excepción: " + e.getMessage());
        }
        
        return listaProductos;
    }

    @Override
    public Producto findById(long id) {
       Producto product=null;
        
        try {
            
            BaseDatosMYSQL base = new BaseDatosMYSQL();
            String sql = "select * from productos where idproducto = ? LIMIT 1";
            PreparedStatement ps = base.getConeccion().prepareCall(sql);
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                
                long clave =rs.getLong("idproducto");  
                String medida =rs.getString("medida");
                String nombre = rs.getString("nombre");
                String color = rs.getString("color");
                String serie = rs.getString("serie");
                String modelo = rs.getString("modelo");
                int enLinea = rs.getInt("enLinea");
                double precioCompra =rs.getDouble("precio_compra");
                double precioVenta =rs.getDouble("precio_sugerido");
                int stocks = rs.getInt("stock");
                double existencias = rs.getDouble("inventario");
                double averiado = rs.getDouble("averiado");
                long idMarca =rs.getLong("idmarca"); 
                long idProv =rs.getLong("idproveedor"); 
                long idCat =rs.getLong("idcategoria"); 
                String tiporegistro = rs.getString("tipoRegistro");
                
                Marca mar = new MarcaJDBC().findById(idMarca);
                Categoria cat =new CategoriaJDBC().findById(idCat);
                Proveedor prov = new ProveedorJDBC().findById(idProv);
                
                product = new Producto(clave, medida, nombre, color, serie,modelo, enLinea, 
                        precioCompra, precioVenta,stocks ,existencias, averiado, mar, 
                        prov, cat, tiporegistro);
                
            }
            base.desconectraBD();
        } catch (SQLException e) {
           System.out.println("Excepción: " + e.getMessage());
        }
        
        return product;
    }

    @Override
    public String insert(Producto prod) {
        String mensaje = "";
       try {
           
           Proveedor prov =  prod.getProveedor();
           Categoria cat =  prod.getCategoria();
            
            BaseDatosMYSQL base = new BaseDatosMYSQL();
            String sql = "insert into productos (idproducto,medida,nombre,color,serie,modelo,enLinea,"
                    + "precio_compra,precio_sugerido,stock,inventario,averiado,idmarca,idproveedor,"
                    + "idcategoria,tipoRegistro) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement ps = base.getConeccion().prepareCall(sql);
            
            ps.setLong(1, prod.getIdProducto());
            ps.setString(2, prod.getMedida());
            ps.setString(3, prod.getNombre());
            ps.setString(4, prod.getColor());
            ps.setString(5, prod.getSerie());
            ps.setString(6, prod.getModelo());
            ps.setInt(7, prod.getEnLinea());
            ps.setDouble(8, prod.getPrecioCompra());
            ps.setDouble(9, prod.getPrecioVenta());
            ps.setDouble(10, prod.getStocks());
            ps.setDouble(11, prod.getExistencias());
            ps.setDouble(12, prod.getAveriado());
            ps.setLong(13, prod.getMarca().getIdMarca());
            ps.setLong(14, prod.getProveedor().getIdProveedor());
            ps.setLong(15, prod.getCategoria().getCategoriaId());
            ps.setString(16, prod.getTipoRegistro());
            
            ps.executeUpdate();
            
          mensaje="El producto se ha creado correctamente.";
            base.desconectraBD();
        } catch (SQLException e) {
            mensaje="No fue posible crear el producto: " + e.getMessage();
        }
        
        return mensaje;
    }

    @Override
    public String update(Producto prod) {
        String mensaje = "";
       try {
            
            BaseDatosMYSQL base = new BaseDatosMYSQL();
            String sql = "update productos set medida=?,nombre=?,color=?,serie=?,modelo=?,enLinea=?,"
                    + "precio_compra=?,precio_sugerido=?,stock=?,inventario=?,averiado=?,idmarca=?,idproveedor=?,"
                    + "idcategoria=?,tipoRegistro=? where idproducto=?";
            PreparedStatement ps = base.getConeccion().prepareCall(sql);
            
            ps.setString(1, prod.getMedida());
            ps.setString(2, prod.getNombre());
            ps.setString(3, prod.getColor());
            ps.setString(4, prod.getSerie());
            ps.setString(5, prod.getModelo());
            ps.setInt(6, prod.getEnLinea());
            ps.setDouble(7, prod.getPrecioCompra());
            ps.setDouble(8, prod.getPrecioVenta());
            ps.setDouble(9, prod.getStocks());
            ps.setDouble(10, prod.getExistencias());
            ps.setDouble(11, prod.getAveriado());
            ps.setLong(12, prod.getMarca().getIdMarca());
            ps.setLong(13, prod.getProveedor().getIdProveedor());
            ps.setLong(14, prod.getCategoria().getCategoriaId());
            ps.setString(15, prod.getTipoRegistro());
            ps.setLong(16, prod.getIdProducto());
            
            ps.executeUpdate();
            
          mensaje="El producto se ha actualizado correctamente.";
            base.desconectraBD();
        } catch (SQLException e) {
            mensaje="No fue posible actualizar el producto: " + e.getMessage();
        }
        
        return mensaje;
    }

    @Override
    public String delete(long id) {
      
        String mensaje = "";
       try {
            
            BaseDatosMYSQL base = new BaseDatosMYSQL();
            String sql = "delete from productos where idproducto =?";
            PreparedStatement ps = base.getConeccion().prepareCall(sql);
            ps.setLong(1, id);
            ps.executeUpdate();
            
          mensaje="El producto se ha eliminado correctamente.";
            base.desconectraBD();
        } catch (SQLException e) {
            mensaje="No fue posible eliminar el producto: " + e.getMessage();
        }
        
        return mensaje;

    }

    @Override
    public List<Producto> busquedaPorCriterio(String criterio) {
         
        long cod = 0;
        try {
            cod = Long.parseLong(criterio);
        } catch (Exception e) {
        }
            
        List<Producto> listaProductos = new ArrayList<>();
        Producto product;
        
        try {
            
            BaseDatosMYSQL base = new BaseDatosMYSQL();
            String sql = "select * from productos where nombre like '%"+criterio+"%' or medida like '%"+criterio+"%' or serie like '%"+criterio+"%' or modelo like '%"+criterio+"%' or idproducto ="+cod+" order by idproducto asc";
            PreparedStatement ps = base.getConeccion().prepareCall(sql);
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                
                long clave =rs.getLong("idproducto");  
                String medida =rs.getString("medida");
                String nombre = rs.getString("nombre");
                String color = rs.getString("color");
                String serie = rs.getString("serie");
                String modelo = rs.getString("modelo");
                int enLinea = rs.getInt("enLinea");
                double precioCompra =rs.getDouble("precio_compra");
                double precioVenta =rs.getDouble("precio_sugerido");
                int stocks = rs.getInt("stock");
                double existencias = rs.getDouble("inventario");
                double averiado = rs.getDouble("averiado");
                long idMarca =rs.getLong("idmarca"); 
                long idProv =rs.getLong("idproveedor"); 
                long idCat =rs.getLong("idcategoria"); 
                String tiporegistro = rs.getString("tipoRegistro");
                
                Marca mar = new MarcaJDBC().findById(idMarca);
                Categoria cat =new CategoriaJDBC().findById(idCat);
                Proveedor prov = new ProveedorJDBC().findById(idProv);
                
                product = new Producto(clave, medida, nombre, color, serie,modelo, enLinea, 
                        precioCompra, precioVenta,stocks ,existencias, averiado, mar, 
                        prov, cat, tiporegistro);
                
                listaProductos.add(product);
            }
            base.desconectraBD();
        } catch (SQLException e) {
           System.out.println("Excepción: " + e.getMessage());
        }
        
        return listaProductos;

    }

    @Override
    public List<Producto> gatProductosByMarca(long id) {
       List<Producto> listaProductos = new ArrayList<>();
        Producto product;
        
        try {
            
            BaseDatosMYSQL base = new BaseDatosMYSQL();
            String sql = "select * from productos where idmarca=? order by idproducto asc";
            PreparedStatement ps = base.getConeccion().prepareCall(sql);
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                
                long clave =rs.getLong("idproducto");  
                String medida =rs.getString("medida");
                String nombre = rs.getString("nombre");
                String color = rs.getString("color");
                String serie = rs.getString("serie");
                String modelo = rs.getString("modelo");
                int enLinea = rs.getInt("enLinea");
                double precioCompra =rs.getDouble("precio_compra");
                double precioVenta =rs.getDouble("precio_sugerido");
                int stocks = rs.getInt("stock");
                double existencias = rs.getDouble("inventario");
                double averiado = rs.getDouble("averiado");
                long idMarca =rs.getLong("idmarca"); 
                long idProv =rs.getLong("idproveedor"); 
                long idCat =rs.getLong("idcategoria"); 
                String tiporegistro = rs.getString("tipoRegistro");
                
                Marca mar = new Marca();
                mar.setIdMarca(idMarca);
                
                Categoria cate =new CategoriaJDBC().findById(idCat);
                Proveedor prov = new ProveedorJDBC().findById(idProv);
                
                product = new Producto(clave, medida, nombre, color, serie,modelo, enLinea, 
                        precioCompra, precioVenta,stocks ,existencias, averiado, mar, 
                        prov, cate, tiporegistro);
                listaProductos.add(product);
            }
            base.desconectraBD();
        } catch (SQLException e) {
           System.out.println("Excepción: " + e.getMessage());
        }
        
        return listaProductos;

    }

   
}
