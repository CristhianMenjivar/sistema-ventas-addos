/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package devs.com.sistema.ventas.controllers;

import devs.com.sistema.ventas.dao.GastoJDBC;
import devs.com.sistema.ventas.modelos.Gasto;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author usuario
 */
@WebServlet(name = "gastoController", urlPatterns = {"/gastos"})
public class gastoController extends HttpServlet {
    
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
            }
        } else {
            //para obtener lista de categorias
            GastoJDBC gastoDAO = new GastoJDBC();

            List<Gasto> listaCategorias = gastoDAO.ListAll();

            //pasamos por atributo
            request.setAttribute("gastos", listaCategorias);
            //redirigimos a la vista
            request.getRequestDispatcher("/WEB-INF/gastos/index.jsp").forward(request, response);
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
            switch(accion){
                case "crear":
                    //desplegar el formulario para nuevas categorias
                    insertarCliente(request, response);
                    break;
                    
                case "borrar":
                    borrarCliente(request, response);
                    break;
                    
                case "actualizar":
                    actualizarCliente(request,response);
                    break;
            }
        }

    }

    private void formNuevo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("tipoForm", "crear");
        request.getRequestDispatcher("/WEB-INF/gastos/formulario.jsp").forward(request, response);
    }

    private void formEditar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        long idGasto = Long.parseLong(request.getParameter("idGasto"));
        GastoJDBC gastoDAO = new GastoJDBC();
        Gasto cat = gastoDAO.findById(idGasto);

        if (cat != null) {
            request.setAttribute("gasto", cat);
            request.setAttribute("tipoForm", "actualizar");
            request.getRequestDispatcher("/WEB-INF/gastos/formulario.jsp").forward(request, response);

        }
    }

    private void insertarCliente(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
           
        String tipoGasto = request.getParameter("tipoGasto");
        String fechaCadena = request.getParameter("fecha");
        double total = Double.parseDouble(request.getParameter("total"));
        
        SimpleDateFormat fechaFormato = new SimpleDateFormat("yyyy-MM-dd");
        
        java.util.Date date = null;
        try {
            date = fechaFormato.parse(fechaCadena);
        } catch (ParseException ex) {
            Logger.getLogger(gastoController.class.getName()).log(Level.SEVERE, null, ex);
        }
        java.sql.Date sqlDate = new java.sql.Date(date.getTime());
        
       
        //creamos el obj base para insertar datos
        GastoJDBC base = new GastoJDBC();
        String mensaje= base.insert(new Gasto(0, tipoGasto, total, sqlDate));
        
        //redirigimos al index con todo y mensaje
        //con sendRedirect para que los datos insertados no se sigan reeviando
        request.getSession().setAttribute("operacionGasto", mensaje); // lo enviamos por la secion
        response.sendRedirect( this.PATH_SISTEMA + "/gastos");
    }

    private void borrarCliente(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException  {
        long idGasto = Long.parseLong(request.getParameter("idGasto"));
       
        //creamos el obj base para insertar datos
        GastoJDBC base = new GastoJDBC();
        String mensaje= base.delete(idGasto);
        
        //redirigimos al index con todo y mensaje
        //con sendRedirect para que los datos insertados no se sigan reeviando
        request.getSession().setAttribute("operacionGasto", mensaje); // lo enviamos por la secion
        response.sendRedirect( this.PATH_SISTEMA + "/gastos");

    }

    private void actualizarCliente(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        long idGasto = Long.parseLong(request.getParameter("idActualizar"));   
        String tipoGasto = request.getParameter("tipoGasto");
        String fechaCadena = request.getParameter("fecha");
        double total = Double.parseDouble(request.getParameter("total"));
        
        SimpleDateFormat fechaFormato = new SimpleDateFormat("yyyy-MM-dd");
        
        java.util.Date date = null;
        try {
            date = fechaFormato.parse(fechaCadena);
        } catch (ParseException ex) {
            Logger.getLogger(gastoController.class.getName()).log(Level.SEVERE, null, ex);
        }
        java.sql.Date sqlDate = new java.sql.Date(date.getTime());
        
       
        //creamos el obj base para insertar datos
        GastoJDBC base = new GastoJDBC();
        String mensaje= base.update(new Gasto(idGasto, tipoGasto, total, sqlDate));
        
        //redirigimos al index con todo y mensaje
        //con sendRedirect para que los datos insertados no se sigan reeviando
        request.getSession().setAttribute("operacionGasto", mensaje); // lo enviamos por la secion
        response.sendRedirect( this.PATH_SISTEMA + "/gastos");
    }

}
