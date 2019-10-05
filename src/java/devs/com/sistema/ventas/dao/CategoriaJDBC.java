package devs.com.sistema.ventas.dao;

import devs.com.sistema.ventas.coneccion.BaseDatosMYSQL;
import devs.com.sistema.ventas.coneccion.BaseDatosPG;
import devs.com.sistema.ventas.modelos.Categoria;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author cristhian menjivar
 */
public class CategoriaJDBC implements  ICategoriaDao{

    @Override
    public List<Categoria> busquedaPorCriterio(String criterio) {
       List<Categoria> listaCategorias = new ArrayList<>();
        Categoria cat;
        
        try {
            BaseDatosMYSQL base = new BaseDatosMYSQL();
            String sql = "select * from categorias where nom_categoria like '%"+criterio+"%'";
            PreparedStatement ps = base.getConeccion().prepareCall(sql);
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                cat = new Categoria();
                
                cat.setCategoriaId(rs.getInt("idcategoria"));                
                cat.setNombreCat(rs.getString("nom_categoria"));                
                cat.setDescripcion(rs.getString("descripcion"));


                listaCategorias.add(cat);
            }
            base.desconectraBD();
        } catch (SQLException e) {
           System.out.println("Excepción: " + e.getMessage());
        }
        
        return listaCategorias;
    }

    //una ves implementada la interfase de ICategoria devemos implementar todos los metodos
    @Override
    public List<Categoria> listAll() {
        
        List<Categoria> listaCategorias = new ArrayList<>();
        Categoria cat;
        
        try {
            BaseDatosMYSQL base = new BaseDatosMYSQL();
            String sql = "select * from categorias order by idcategoria asc";
            PreparedStatement ps = base.getConeccion().prepareCall(sql);
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                cat = new Categoria();
                
                cat.setCategoriaId(rs.getInt("idcategoria"));                
                cat.setNombreCat(rs.getString("nom_categoria"));                
                cat.setDescripcion(rs.getString("descripcion"));

                listaCategorias.add(cat);
            }
            base.desconectraBD();
        } catch (SQLException e) {
           System.out.println("Excepción: " + e.getMessage());
        }
        
        return listaCategorias;
    }
    
    //otros metodos sobrescritos

    @Override
    public String insert(Categoria cat) {
        String mensaje="";
        
        try {
            BaseDatosMYSQL base = new BaseDatosMYSQL();
            String sql = "insert into categorias (nom_categoria,descripcion) values (?,?)";
            PreparedStatement ps = base.getConeccion().prepareCall(sql);
           // ps.setLong(1, cat.getCategoriaId());
            ps.setString(1, cat.getNombreCat());
            ps.setString(2, cat.getDescripcion());
            ps.executeUpdate();
            
            mensaje="La categorìa se ha creado correctamente.";
            base.desconectraBD();
        } catch (SQLException e) {
            mensaje="No fue posible crear la categorìa: " + e.getMessage();
        }
        
        return mensaje;
    }

    @Override
    public String update(Categoria cat) {
       String mensaje="";
        
        try {
            BaseDatosMYSQL base = new BaseDatosMYSQL();
            String sql = "update categorias set nom_categoria = ?, descripcion=? where idcategoria = ?";
            PreparedStatement ps = base.getConeccion().prepareCall(sql);
            ps.setString(1, cat.getNombreCat());
            ps.setString(2, cat.getDescripcion());
            ps.setLong(3, cat.getCategoriaId());
            ps.executeUpdate();
            
            mensaje="La categorìa se ha actualizado correctamente.";
            base.desconectraBD();
        } catch (SQLException e) {
            mensaje="No fue posible actualizar la categorìa: " + e.getMessage();
        }
        
        return mensaje;
    }

    @Override
    public String delete(long id) {
        String mensaje="";
        
        try {
            BaseDatosMYSQL base = new BaseDatosMYSQL();
            String sql = "delete from categorias where idcategoria = ?";
            PreparedStatement ps = base.getConeccion().prepareCall(sql);
            ps.setLong(1, id);
            ps.executeUpdate();
            
            mensaje="La categorìa se ha eliminado correctamente.";
            base.desconectraBD();
        } catch (SQLException e) {
            mensaje="No fue posible eliminado la categorìa: " + e.getMessage();
        }
        
        return mensaje;
    }

    @Override
    public Categoria findById(long id) {
        Categoria categoria=null;
        
        try {
            BaseDatosMYSQL base = new BaseDatosMYSQL();
            String sql = "select * from categorias where idcategoria=? LIMIT 1";
            PreparedStatement ps = base.getConeccion().prepareCall(sql);
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                categoria = new Categoria();
                
                categoria.setCategoriaId(rs.getInt("idcategoria"));                
                categoria.setNombreCat(rs.getString("nom_categoria"));                
                categoria.setDescripcion(rs.getString("descripcion"));
            }
            base.desconectraBD();
        } catch (SQLException e) {
           System.out.println("Excepción: " + e.getMessage());
        }
        
        return categoria;
    }
    
    public static void main(String[] args) {
       // CategoriaJDBC jdbc = new CategoriaJDBC();
        
        
       // System.out.println(jdbc.delete(5));
//        Categoria e = jdbc.findById(3);
//        System.out.println("categoria: 3 "+ e.getNombreCat());
//        
//        List<Categoria> lista = jdbc.listAll();
//        
//        for(Categoria p : lista){
//            System.out.println("producto: " + p.getNombreCat());
//        }
    }
    
}
