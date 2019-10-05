package devs.com.sistema.ventas.dao;

import devs.com.sistema.ventas.modelos.Notificacion;
import java.util.List;

public interface INotificacion {

    public int NuevasNotificaciones();

    public List<Notificacion> listAll();

    public List<Notificacion> listActivas();

    public Notificacion findById(long id);

    public String insert(Notificacion noti);

    public String update(Notificacion noti);

    public String delete(long id);

}
