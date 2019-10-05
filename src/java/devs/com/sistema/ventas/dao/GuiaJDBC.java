package devs.com.sistema.ventas.dao;

import devs.com.sistema.ventas.coneccion.BaseDatosMYSQL;
import devs.com.sistema.ventas.modelos.Guia;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GuiaJDBC implements IGuia {

    @Override
    public Guia findById(long id) {
       Guia gui =null;
        
        try {
            BaseDatosMYSQL base = new BaseDatosMYSQL();
            String sql ="SELECT * FROM guia WHERE idguia = ?  LIMIT 1";
            PreparedStatement ps = base.getConeccion().prepareCall(sql);
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {         
                
                long clave = rs.getLong("idguia");
                String rutguia = rs.getString("rutguia");
                String numguia = rs.getString("numguia");
                String tipoguia = rs.getString("tipoguia");
                String estadoguia = rs.getString("estadoguia");
                double total = rs.getDouble("total");
                
                gui = new Guia(clave, rutguia, numguia, tipoguia, estadoguia, total);
            }
            base.desconectraBD();
            
        } catch (SQLException e) {
            System.out.println("Exepción: " + e.getMessage());
        }
          
        return gui;
    }

    @Override
    public List<Guia> listAll() {
        
        List<Guia> listaGuia = new ArrayList<>();
        Guia gui =null;
        
        try {
            BaseDatosMYSQL base = new BaseDatosMYSQL();
            String sql ="SELECT * FROM guia order by idguia asc";
            PreparedStatement ps = base.getConeccion().prepareCall(sql);
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {         
                
                long clave = rs.getLong("idguia");
                String rutguia = rs.getString("rutguia");
                String numguia = rs.getString("numguia");
                String tipoguia = rs.getString("tipoguia");
                String estadoguia = rs.getString("estadoguia");
                double total = rs.getDouble("total");
                
                gui = new Guia(clave, rutguia, numguia, tipoguia, estadoguia, total);
                
                listaGuia.add(gui);
            }
            base.desconectraBD();
            
        } catch (SQLException e) {
            System.out.println("Exepción: " + e.getMessage());
        }
          
        return listaGuia;
    }

    @Override
    public String insert(Guia gui) {
        String mensaje ="";
        
        try {
             BaseDatosMYSQL base = new BaseDatosMYSQL();
             String sql = "insert into guia "
                     + "(rutguia,numguia,tipoguia,estadoguia,total) "
                     + "values (?,?,?,?,?)";
             PreparedStatement ps = base.getConeccion().prepareCall(sql);
             ps.setString(1, gui.getRutguia());
             ps.setString(2, gui.getNumguia());
             ps.setString(3, gui.getTipoguia());
             ps.setString(4, gui.getEstadoguia());
             ps.setDouble(5, gui.getTotal());
             
             ps.executeUpdate();
             
            mensaje="La guía se ha creado correctamente.";
            base.desconectraBD();
        } catch (SQLException e) {
            mensaje="No fue posible crear la guía: " + e.getMessage();
        }
       
        
        return mensaje;
    }

    @Override
    public String update(Guia gui) {
        
        String mensaje ="";
        
        try {
             BaseDatosMYSQL base = new BaseDatosMYSQL();
             String sql = "update guia "
                     + " set rutguia=?,numguia=?,tipoguia=?,estadoguia=?,total=? where idguia=? ";
                    
             PreparedStatement ps = base.getConeccion().prepareCall(sql);
             ps.setString(1, gui.getRutguia());
             ps.setString(2, gui.getNumguia());
             ps.setString(3, gui.getTipoguia());
             ps.setString(4, gui.getEstadoguia());
             ps.setDouble(5, gui.getTotal());
             ps.setLong(6, gui.getIdguia());
             
             ps.executeUpdate();
             
            mensaje="La guía se ha actualizado correctamente.";
            base.desconectraBD();
        } catch (SQLException e) {
            mensaje="No fue posible actualizado la guía: " + e.getMessage();
        }
       
        
        return mensaje;
    }

    @Override
    public String delete(long id) {
       String mensaje ="";
       
        try {
            BaseDatosMYSQL base = new BaseDatosMYSQL();
            
            String sql ="delete from guia where idguia=?";
            PreparedStatement ps = base.getConeccion().prepareCall(sql);
            ps.setLong(1, id);
            
            ps.executeUpdate();
            
            mensaje="La guía se ha eliminar correctamente.";
            base.desconectraBD();
            
        } catch (SQLException e) {
            mensaje="No fue posible eliminar la guía: " + e.getMessage();
        }
        
       return mensaje;
    }
    
    
}
