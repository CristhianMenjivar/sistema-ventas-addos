/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package devs.com.sistema.ventas.dao;

import devs.com.sistema.ventas.coneccion.BaseDatosMYSQL;
import devs.com.sistema.ventas.coneccion.BaseDatosPG;
import devs.com.sistema.ventas.modelos.Empleado;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author usuario
 */
public class EmpleadoJDBC implements IEmpleadoDao{

    @Override
    public List<Empleado> listAll() {
       List<Empleado> listaEmpleados = new ArrayList<>();
        Empleado emp;
        
        try {
            
            BaseDatosMYSQL base = new BaseDatosMYSQL();
            String sql = "select * from empleados order by idempleado asc";
            PreparedStatement ps = base.getConeccion().prepareCall(sql);
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                
                long id =rs.getInt("idempleado");                
                String nombre =rs.getString("nombre");
                String apellido =rs.getString("apellido");
                String direccion =rs.getString("direccion");
                String telefono =rs.getString("telefono");
                String cargo =rs.getString("cargo");
                double salario = rs.getDouble("salario_devengado");
                double comision = rs.getDouble("comision");
                
                emp = new Empleado(id, nombre, apellido, direccion, telefono, cargo, salario, comision);

                listaEmpleados.add(emp);
            }
            base.desconectraBD();
        } catch (SQLException e) {
           System.out.println("Excepción: " + e.getMessage());
        }
        
        return listaEmpleados;
    }

    @Override
    public Empleado findById(long id) {
        Empleado emp=null;
        
        try {
            BaseDatosMYSQL base = new BaseDatosMYSQL();
            String sql = "select * from empleados where idempleado=? LIMIT 1";
            PreparedStatement ps = base.getConeccion().prepareCall(sql);
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                long clave =rs.getInt("idempleado");                
                String nombre =rs.getString("nombre");
                String apellido =rs.getString("apellido");
                String direccion =rs.getString("direccion");
                String telefono =rs.getString("telefono");
                String cargo =rs.getString("cargo");
                double salario = rs.getDouble("salario_devengado");
                double comision = rs.getDouble("comision");
                
                emp = new Empleado(clave, nombre, apellido, direccion, telefono, cargo, salario, comision);

            }
            base.desconectraBD();
        } catch (SQLException e) {
           System.out.println("Excepción: " + e.getMessage());
        }
        
        return emp;
    }

    @Override
    public String insert(Empleado emp) {
       String mensaje="";
        
        try {
            BaseDatosMYSQL base = new BaseDatosMYSQL();
            String sql = "insert into empleados (nombre,apellido,direccion,telefono,cargo,salario_devengado,comision)"
                    + " values (?,?,?,?,?,?,?)";
            PreparedStatement ps = base.getConeccion().prepareCall(sql);
            ps.setString(1, emp.getNombreEmpleado());
            ps.setString(2, emp.getApellidoEmp());
            ps.setString(3, emp.getDireccion());
            ps.setString(4, emp.getTelefono());
            ps.setString(5, emp.getCargo());
            ps.setDouble(6, emp.getSalario());
            ps.setDouble(7, emp.getComision());
            
            
            ps.executeUpdate();
            
            mensaje="El empleado se ha creado correctamente.";
            base.desconectraBD();
        } catch (SQLException e) {
            mensaje="No fue posible crear El empleado: " + e.getMessage();
        }
        
        return mensaje;
    }
   

    @Override
    public String update(Empleado emp) {
        String mensaje="";
        
        try {
            BaseDatosMYSQL base = new BaseDatosMYSQL();
            String sql = "update empleados set nombre=?,apellido=?,direccion=?,telefono=?,cargo=?,salario_devengado=?,comision=?"
                    + " where idempleado=?";
            PreparedStatement ps = base.getConeccion().prepareCall(sql);
            ps.setString(1, emp.getNombreEmpleado());
            ps.setString(2, emp.getApellidoEmp());
            ps.setString(3, emp.getDireccion());
            ps.setString(4, emp.getTelefono());
            ps.setString(5, emp.getCargo());
            ps.setDouble(6, emp.getSalario());
            ps.setDouble(7, emp.getComision());
            ps.setLong(8, emp.getIdEmpleado());
            
            
            ps.executeUpdate();
            
            mensaje="El empleado se ha actualizado correctamente.";
            base.desconectraBD();
        } catch (SQLException e) {
            mensaje="No fue posible actualizado el empleado: " + e.getMessage();
        }
        
        return mensaje;
    }

    @Override
    public String delete(int id) {
        String mensaje="";
        
        try {
            BaseDatosMYSQL base = new BaseDatosMYSQL();
            String sql = "delete from empleados where idempleado = ?";
            PreparedStatement ps = base.getConeccion().prepareCall(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
            
            mensaje="El empleado se ha eliminado correctamente.";
            base.desconectraBD();
        } catch (SQLException e) {
            mensaje="No fue posible eliminado el empleado: " + e.getMessage();
        }
        
        return mensaje;
    }
    
   
    public static void main(String[] args) {
        EmpleadoJDBC jdbc = new EmpleadoJDBC();
        /*
        jdbc.insert(new Empleado(0, "cris 1", "men 1", "lag 1", "70804980", "jefe", 700, 30));
        jdbc.insert(new Empleado(0, "cris 2", "men 2", "lag 2", "70778899", "vendedor", 350, 30));
        jdbc.insert(new Empleado(0, "cris 3", "men 3", "lag 3", "70663366", "gerente", 500, 30));
        jdbc.insert(new Empleado(0, "cris 4", "men 4", "lag 4", "70888888", "vendedor", 350, 30));
        jdbc.insert(new Empleado(0, "cris 5", "men 5", "lag 5", "70996699", "conserge", 350, 30));
        jdbc.insert(new Empleado(0, "cris 6", "men 6", "lag 6", "70003322", "atencion al cliente", 300, 30));
        */
        
       /* jdbc.update(new Empleado(6, "cristhian 6", "menjivar 6", "laguna seca 6", "6000000", "supervisor", 400, 20));

        Empleado emp = jdbc.findById(6);
        System.out.println(emp.getNombreEmpleado());
        System.out.println(emp.getApellidoEmp());
        System.out.println(emp.getDireccion());
        System.out.println(emp.getTelefono());
        System.out.println(emp.getCargo());
        System.out.println(emp.getSalario());
        System.out.println(emp.getComision());
        */
        
       jdbc.delete(5);
        List<Empleado> lista = jdbc.listAll();
        
        for (Empleado e: lista) {
            System.out.println(e.getNombreEmpleado());
        }
    }
    
}
