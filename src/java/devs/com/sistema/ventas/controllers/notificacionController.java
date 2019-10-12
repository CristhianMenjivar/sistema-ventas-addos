/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package devs.com.sistema.ventas.controllers;

import devs.com.sistema.ventas.dao.NotificacionJBDC;
import devs.com.sistema.ventas.modelos.Notificacion;
import devs.com.sistema.ventas.modelos.Usuario;
import java.io.IOException;
import java.sql.Date;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author usuario
 */
@WebServlet(name = "notificacionController", urlPatterns = {"/notificaciones"})
public class notificacionController extends HttpServlet {
    
    /* variable global, cuando el sistema está en desarrollo en nuestra maquina local se usa a base de glassfish
       para redireccion "/sistema ventas" necesaria para glassfish en Local
     * En heroku u otro hosting se usa la raiz de la app para redirigir "/"
    */
    
   // sistema en desarrollo
    private String SISTEMA_DEVELOPERS = "/sistema-ventas";
    //sistema en producción
    private String SISTEMA_PRODUCCTION = "";
    //direccion de la raiz del sistema
    private String PATH_SISTEMA = SISTEMA_PRODUCCTION;

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //checar si si el parametro esta llegando
        if (request.getParameter("accion") != null) {
            String accion = (String) request.getParameter("accion");
            switch (accion) {
                case "nuevo":
                    //desplegar el formulario para nuevas categorias
                    formNuevo(request, response);
                    break;

                case "editar":
                    formEditar(request, response);
                    break;

                case "mostrar":
                    formMostrar(request, response);
                    break;
            }
        } else {
            //para obtener lista de categorias
            NotificacionJBDC notiDAO = new NotificacionJBDC();

            List<Notificacion> listaNoti = notiDAO.listAll();

            //pasamos por atributo
            request.setAttribute("notificaciones", listaNoti);
            //redirigimos a la vista
            request.getRequestDispatcher("/WEB-INF/actividades/index.jsp").forward(request, response);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (request.getParameter("accion") != null) {
            String accion = (String) request.getParameter("accion");
            switch (accion) {
                case "crear":
                    //desplegar el formulario para nuevas categorias
                    insertarActividad(request, response);
                    break;

                case "borrar":
                    borrarActividad(request, response);
                    break;

                case "actualizar":
                    actualizarActividad(request, response);
                    break;
            }
        }

    }

    private void formNuevo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("tipoForm", "crear");
        request.getRequestDispatcher("/WEB-INF/actividades/formulario.jsp").forward(request, response);
    }

    private void formEditar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        long idNoti = Long.parseLong(request.getParameter("idNoti"));
        NotificacionJBDC notiDAO = new NotificacionJBDC();
        Notificacion notificacion = notiDAO.findById(idNoti);

        if (notificacion != null) {
            request.setAttribute("notificacion", notificacion);
            request.setAttribute("tipoForm", "actualizar");
            request.getRequestDispatcher("/WEB-INF/actividades/formulario.jsp").forward(request, response);

        }
    }

    private void formMostrar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        long idNoti = Long.parseLong(request.getParameter("idNoti"));
        NotificacionJBDC notiDAO = new NotificacionJBDC();
        Notificacion notificacion = notiDAO.findById(idNoti);

        if (notificacion != null) {
            request.setAttribute("notificacion", notificacion);
            request.getRequestDispatcher("/WEB-INF/actividades/show.jsp").forward(request, response);

        }
    }

    private void insertarActividad(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String nombre = request.getParameter("nombre");
        String asunto = request.getParameter("asunto");
        Date fecha = Date.valueOf(request.getParameter("fecha"));
        
        String checkActivo = "of";
        int visto = 0;
        
        try {
            if (request.getParameter("visto") != null) {
                checkActivo = request.getParameter("visto");
            }
        } catch (NullPointerException ex) {
            visto = 0;
        }

        if (checkActivo.equalsIgnoreCase("on")) {
            visto = 1;
        } else {
            visto = 0;
        }
        //creamos el obj base para insertar datos
        NotificacionJBDC base = new NotificacionJBDC();
        
        // este usuario se obtendra de la session para insertarlo
        Usuario user = new Usuario();
        user.setIdUsuario(1);
        
        Notificacion noti = new Notificacion(0, nombre, asunto, fecha, visto);
        noti.setUser(user);
        
        String mensaje = base.insert(noti);
        //redirigimos al index con todo y mensaje
        //con sendRedirect para que los datos insertados no se sigan reeviando
        request.getSession().setAttribute("operacionNoti", mensaje); // lo enviamos por la secion
        response.sendRedirect( this.PATH_SISTEMA + "/notificaciones");
    }

    private void borrarActividad(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        long idEliminar = Long.parseLong(request.getParameter("idNoti"));
        
        //creamos el obj base para insertar datos
        NotificacionJBDC base = new NotificacionJBDC();
        String mensaje = base.delete(idEliminar);
        //redirigimos al index con todo y mensaje
        //con sendRedirect para que los datos insertados no se sigan reeviando
        request.getSession().setAttribute("operacionNoti", mensaje); // lo enviamos por la secion
        response.sendRedirect( this.PATH_SISTEMA + "/notificaciones");
    }

    private void actualizarActividad(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        long idActualizar = Long.parseLong(request.getParameter("idActualizar"));
        String nombre = request.getParameter("nombre");
        String asunto = request.getParameter("asunto");
        Date fecha = Date.valueOf(request.getParameter("fecha"));
        
        String checkActivo = "of";
        int visto = 0;
        
        try {
            if (request.getParameter("visto") != null) {
                checkActivo = request.getParameter("visto");
            }
        } catch (NullPointerException ex) {
            visto = 0;
        }

        if (checkActivo.equalsIgnoreCase("on")) {
            visto = 1;
        } else {
            visto = 0;
        }
        //creamos el obj base para insertar datos
        NotificacionJBDC base = new NotificacionJBDC();
        String mensaje = base.update(new Notificacion(idActualizar, nombre, asunto, fecha, visto));
        //redirigimos al index con todo y mensaje
        //con sendRedirect para que los datos insertados no se sigan reeviando
        request.getSession().setAttribute("operacionNoti", mensaje); // lo enviamos por la secion
        response.sendRedirect( this.PATH_SISTEMA + "/notificaciones");
    }

}
