package devs.com.sistema.ventas.dao;

import devs.com.sistema.ventas.modelos.Cuentas;
import java.util.List;

public interface ICuentaDao {
    public List<Cuentas> listAll();
    
    //buscar 1 Cuentas Proveedores por id
    public Cuentas findById(long id);
    
    //crear Cuentas Proveedores
    public String insert(Cuentas cuentas);
    
    //editar ¨Cuentas Proveedores
    public String update(Cuentas cuentas);
    
    //eliminar Cuentas Proveedores
    public String delete(long id);
}
