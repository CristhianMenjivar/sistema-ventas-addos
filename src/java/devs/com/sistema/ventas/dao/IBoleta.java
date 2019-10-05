package devs.com.sistema.ventas.dao;

import devs.com.sistema.ventas.modelos.Boleta;
import java.util.List;

public interface IBoleta {
    
    public Boleta findById(long id);
    
    public List<Boleta> listAll();
    
    public String insert(Boleta bole);
    
    public String update(Boleta bole);
    
    public String delete(long id);
}
