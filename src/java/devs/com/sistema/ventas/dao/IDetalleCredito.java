package devs.com.sistema.ventas.dao;

import devs.com.sistema.ventas.modelos.DetalleCredito;
import java.util.List;

public interface IDetalleCredito {
    public List<DetalleCredito> findCreditoID(long clave);
    public String insertarDetalle(DetalleCredito detalle);
    public String eliminarDetalle(long id);
}
