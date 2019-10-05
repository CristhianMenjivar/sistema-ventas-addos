package devs.com.sistema.ventas.dao;

import devs.com.sistema.ventas.modelos.DetalleOrdenes;
import devs.com.sistema.ventas.modelos.Orden;
import java.util.Date;
import java.util.List;

public interface IOrdenDao {
    public Orden insert(Orden orden);
    
    public List<Orden> listAll();
    
   public List<Orden> listForDate(Date fecha);
    
    public Orden findById(long idOrden);
    
    public List<DetalleOrdenes> detalles(Orden orden);
    
    //contasr cuantas ventas hay
    public int ventasDelDia(Date fecha);
    
}
