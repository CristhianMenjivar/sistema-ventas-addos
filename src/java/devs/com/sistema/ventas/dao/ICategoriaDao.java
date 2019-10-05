/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package devs.com.sistema.ventas.dao;

import devs.com.sistema.ventas.modelos.Categoria;
import java.util.List;

/**
 *
 * @author Usuario
 */

//las interfaces van a definir todos los metodos que se van a usar
//por ley se tienen q implementar
public interface ICategoriaDao {
    
    //este metodo devolvera una lista de objetos categorias
    public List<Categoria> listAll();
    
    //buscar 1 categoria por id
    public Categoria findById(long id);
    
    //crear categoria
    public String insert(Categoria cat);
    
    //editar categoria
    public String update(Categoria cat);
    
    //eliminar categoria
    public String delete(long id);
    
    //este metodo devolvera una lista de objetos categorias
    public List<Categoria> busquedaPorCriterio(String criterio);
}
