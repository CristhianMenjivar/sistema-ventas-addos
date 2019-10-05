/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package devs.com.sistema.ventas.dao;

import devs.com.sistema.ventas.modelos.Marca;
import java.util.List;

/**
 *
 * @author usuario
 */
public interface IMarcaDao {
    public List<Marca> listAll();
    
    //buscar 1 categoria por id
    public Marca findById(long id);
    
    //crear categoria
    public String insert(Marca marca);
    
    //editar categoria
    public String update(Marca marca);
    
    //eliminar categoria
    public String delete(long id);
}
