package devs.com.sistema.ventas.dao;

import devs.com.sistema.ventas.modelos.Cliente;
import java.util.List;

public interface IClienteDao {
    
    public Cliente findById(long id);
    
    public List<Cliente> listAll();
    
    public String insert(Cliente cliente);
    
    public String update(Cliente cliente);
    
    public String delete(long id);
}
