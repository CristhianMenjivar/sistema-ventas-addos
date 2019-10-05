package devs.com.sistema.ventas.dao;

import devs.com.sistema.ventas.modelos.Proveedor;
import java.util.List;

public interface IProveedorDao {
    public List<Proveedor> listAll();
    
    //buscar 1 proveedor por id
    public Proveedor findById(long id);
    
    //crear Proveedor
    public String insert(Proveedor prov);
    
    //editar ¨proveedor
    public String update(Proveedor prov);
    
    //eliminar proveedor
    public String delete(long id);
}
