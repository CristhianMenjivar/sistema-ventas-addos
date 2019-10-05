package devs.com.sistema.ventas.dao;

import devs.com.sistema.ventas.modelos.Guia;
import java.util.List;

public interface IGuia {
    
    public Guia findById(long id);
    
    public List<Guia> listAll();
    
    public String insert(Guia gui);
    
    public String update(Guia gui);
    
    public String delete(long id);
}
