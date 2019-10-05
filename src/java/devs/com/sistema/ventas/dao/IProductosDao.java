package devs.com.sistema.ventas.dao;

import devs.com.sistema.ventas.modelos.Categoria;
import devs.com.sistema.ventas.modelos.Compra;
import devs.com.sistema.ventas.modelos.Producto;
import java.util.List;

public interface IProductosDao {
    
    public List<Producto> listAll();
    
    public List<Producto> getProductosByCategoria(Categoria cat);
    
    public List<Producto> gatProductosByMarca(long id);
    //buscar 1 proveedor por id
    public Producto findById(long id);
    
    //crear Proveedor
    public String insert(Producto prod);
    
    //editar ¨proveedor
    public String update(Producto prod);
    
    //eliminar proveedor
    public String delete(long id);
    
    public List<Producto> busquedaPorCriterio(String criterio);
}
