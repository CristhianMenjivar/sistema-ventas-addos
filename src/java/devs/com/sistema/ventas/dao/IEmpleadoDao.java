/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package devs.com.sistema.ventas.dao;

import devs.com.sistema.ventas.modelos.Empleado;
import java.util.List;

/**
 *
 * @author usuario
 */
public interface IEmpleadoDao {
    //este metodo devolvera una lista de objetos categorias
    public List<Empleado> listAll();
    
    //buscar 1 categoria por id
    public Empleado findById(long id);
    
    //crear categoria
    public String insert(Empleado emp);
    
    //editar categoria
    public String update(Empleado emp);
    
    //eliminar categoria
    public String delete(int id);
}
