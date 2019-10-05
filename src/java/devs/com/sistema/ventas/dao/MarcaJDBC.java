/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package devs.com.sistema.ventas.dao;

import devs.com.sistema.ventas.coneccion.BaseDatosMYSQL;
import devs.com.sistema.ventas.modelos.Marca;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author usuario
 */
public class MarcaJDBC implements IMarcaDao{

    @Override
    public List<Marca> listAll() {
       List<Marca> listaMarcas = new ArrayList<>();
        Marca marca;
        
        try {
            
            BaseDatosMYSQL base = new BaseDatosMYSQL();
            String sql = "select * from marca order by idmarca asc";
            PreparedStatement ps = base.getConeccion().prepareCall(sql);
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                
                long id =rs.getInt("idmarca");                
                String nombre =rs.getString("marca");
                
                marca = new Marca(id, nombre);
                
                listaMarcas.add(marca);
            }
            base.desconectraBD();
        } catch (SQLException e) {
           System.out.println("Excepción: " + e.getMessage());
        }
        
        return listaMarcas;
    }

    @Override
    public Marca findById(long id) {
        Marca marca=null;
        
        try {
            
            BaseDatosMYSQL base = new BaseDatosMYSQL();
            String sql = "select * from marca where idmarca="+id+" LIMIT 1";
            PreparedStatement ps = base.getConeccion().prepareCall(sql);
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                
                long clave =rs.getInt("idmarca");                
                String nombre =rs.getString("marca");
                
                marca = new Marca(clave, nombre);
            }
            base.desconectraBD();
        } catch (SQLException e) {
           System.out.println("Excepción: " + e.getMessage());
        }
        
        return marca;
    }

    @Override
    public String insert(Marca marca) {
         String mensaje="";
        
        try {
            BaseDatosMYSQL base = new BaseDatosMYSQL();
            String sql = "insert into marca (marca) values(?)";
            PreparedStatement ps = base.getConeccion().prepareCall(sql);
            ps.setString(1, marca.getMarca());
            
            ps.executeUpdate();
            
            mensaje="La marca se ha creado correctamente.";
            base.desconectraBD();
        } catch (SQLException e) {
            mensaje="No fue posible crear la marca: " + e.getMessage();
        }
        
        return mensaje;
    }

    @Override
    public String update(Marca marca) {
      String mensaje="";
        
        try {
            BaseDatosMYSQL base = new BaseDatosMYSQL();
            String sql = "update marca set marca=? where idmarca =?";
            PreparedStatement ps = base.getConeccion().prepareCall(sql);
            ps.setString(1, marca.getMarca());
            ps.setLong(2, marca.getIdMarca());
            
            ps.executeUpdate();
            
            mensaje="La marca se ha actualizado correctamente.";
            base.desconectraBD();
        } catch (SQLException e) {
            mensaje="No fue posible actualizar la marca: " + e.getMessage();
        }
        
        return mensaje;
    }

    @Override
    public String delete(long id) {
       String mensaje="";
        
        try {
            BaseDatosMYSQL base = new BaseDatosMYSQL();
            String sql = "delete from marca  where idmarca ="+id+"";
            PreparedStatement ps = base.getConeccion().prepareCall(sql);
            
            ps.executeUpdate();
            
            mensaje="La marca se ha eliminado correctamente.";
            base.desconectraBD();
        } catch (SQLException e) {
            mensaje="No fue posible eliminar la marca: " + e.getMessage();
        }
        
        return mensaje;
    }
    
}
