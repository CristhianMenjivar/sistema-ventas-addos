/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package devs.com.sistema.ventas.controllers;


import devs.com.sistema.ventas.dao.GuiaJDBC;
import devs.com.sistema.ventas.modelos.Guia;
import java.io.IOException;
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
@WebServlet(name = "guiaControlador", urlPatterns = {"/guias"})
public class guiaControlador extends HttpServlet {
    
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
            switch(accion){
                case "nuevo":
                    //desplegar el formulario para nuevas categorias
                    formNuevo(request, response);
                    break;
                    
                case "editar":
                    formEditar(request, response);
                    break;
            }
        } else {
            //para obtener lista de categorias
            GuiaJDBC DaoGuia = new GuiaJDBC();

            List<Guia> listaGuia = DaoGuia.listAll();

            //pasamos por atributo
            request.setAttribute("guias", listaGuia);
            //redirigimos a la vista
            request.getRequestDispatcher("/WEB-INF/guias/index.jsp").forward(request, response);
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
        //checar si si el parametro esta llegando
        if (request.getParameter("accion") != null) {
            String accion = (String) request.getParameter("accion");
            switch(accion){
                case "crear":
                    //desplegar el formulario para nuevas categorias
                    insertarGuia(request, response);
                    break;
                    
                case "borrar":
                    borrarGuia(request, response);
                    break;
                    
                case "actualizar":
                    actualizarGuia(request,response);
                    break;
            }
        }
        
    }

    private void formNuevo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
       request.setAttribute("tipoForm", "crear");
       request.getRequestDispatcher("/WEB-INF/guias/formulario.jsp").forward(request, response);
    }

    private void formEditar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        long idGuia = Long.parseLong(request.getParameter("idGuia"));
        GuiaJDBC guiaDAO = new GuiaJDBC();
        Guia guia = guiaDAO.findById(idGuia);
        
        if (guia !=null) {
            request.setAttribute("guias", guia);
            request.setAttribute("tipoForm", "actualizar");
            request.getRequestDispatcher("/WEB-INF/guias/formulario.jsp").forward(request, response);

        }
    }

    private void insertarGuia(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
         //obtenemos los valores de los controles de form
       // long idGuia = Long.parseLong(request.getParameter("idGuia")); 
        String rutguia = request.getParameter("rutguia");
        String numguia = request.getParameter("numguia");
        String tipoguia = request.getParameter("tipoguia");
        String estadoguia = request.getParameter("estadoguia");
        double total = Double.parseDouble(request.getParameter("total"));
        
        
        //creamos el obj base para insertar datos
        GuiaJDBC base = new GuiaJDBC();
        String mensaje= base.insert(new Guia(0, rutguia, numguia, tipoguia, estadoguia, total));
        //redirigimos al index con todo y mensaje
        //con sendRedirect para que los datos insertados no se sigan reeviando
        request.getSession().setAttribute("operacionGuia", mensaje); // lo enviamos por la secion
        response.sendRedirect( this.PATH_SISTEMA + "/guias");
    }

    private void borrarGuia(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        long idGuia = Long.parseLong(request.getParameter("idGuia"));
        
        //creamos el obj base para insertar datos
        GuiaJDBC base = new GuiaJDBC();
        String mensaje= base.delete(idGuia);
        //redirigimos al index con todo y mensaje
        //con sendRedirect para que los datos insertados no se sigan reeviando
        request.getSession().setAttribute("operacionGuia", mensaje); // lo enviamos por la secion
        response.sendRedirect( this.PATH_SISTEMA + "/guias");
    }

    private void actualizarGuia(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        long idGuia = Long.parseLong(request.getParameter("idActualizar")); 
        String rutguia = request.getParameter("rutguia");
        String numguia = request.getParameter("numguia");
        String tipoguia = request.getParameter("tipoguia");
        String estadoguia = request.getParameter("estadoguia");
        double total = Double.parseDouble(request.getParameter("total"));
        
        
        //creamos el obj base para insertar datos
        GuiaJDBC base = new GuiaJDBC();
        String mensaje= base.update(new Guia(idGuia, rutguia, numguia, tipoguia, estadoguia, total));
        
        //redirigimos al index con todo y mensaje
        //con sendRedirect para que los datos insertados no se sigan reeviando
        request.getSession().setAttribute("operacionGuia", mensaje); // lo enviamos por la secion
        response.sendRedirect( this.PATH_SISTEMA + "/guias");
    }

}
