package devs.com.sistema.ventas.dao;

import devs.com.sistema.ventas.modelos.Gasto;
import java.util.List;

public interface IGastoDao {
    
    public List<Gasto> ListAll();
    public Gasto findById(long clave);
    public List<Gasto> BusquedaCriterio(String criterio);
    public String insert(Gasto gasto);
    public String delete(long id);
    public String update(Gasto gasto);
}
