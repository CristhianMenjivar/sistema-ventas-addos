package devs.com.sistema.ventas.dao;

import devs.com.sistema.ventas.modelos.Credito;
import java.util.List;

public interface ICreditoDAO {
    
    public List<Credito> listAll();
    
    public Credito findById(long id);
    
    public String insert(Credito emp);
    
    public String update(Credito emp);
    
    public String delete(long id);
}
