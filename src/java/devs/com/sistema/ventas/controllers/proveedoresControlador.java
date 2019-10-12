/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package devs.com.sistema.ventas.controllers;

import devs.com.sistema.ventas.dao.ProveedorJDBC;
import devs.com.sistema.ventas.modelos.Proveedor;
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
@WebServlet(name = "proveedoresControlador", urlPatterns = {"/proveedores"})
public class proveedoresControlador extends HttpServlet {
    
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
            ProveedorJDBC proveedorDao = new ProveedorJDBC();

            List<Proveedor> listaProveedor = proveedorDao.listAll();

            //pasamos por atributo
            request.setAttribute("proveedores", listaProveedor);
            //redirigimos a la vista
            request.getRequestDispatcher("/WEB-INF/proveedores/index.jsp").forward(request, response);
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
                    insertarProveedor(request, response);
                    break;
                    
                case "borrar":
                    borrarProveedor(request, response);
                    break;
                    
                case "actualizar":
                    actualizarProveedor(request,response);
                    break;
            }
        }
        
    }

    private void formNuevo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        request.setAttribute("tipoForm", "crear");
       request.getRequestDispatcher("/WEB-INF/proveedores/formulario.jsp").forward(request, response);
    }

    private void formEditar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        long idProv = Long.parseLong(request.getParameter("idProv"));
        ProveedorJDBC proveedorDao = new ProveedorJDBC();
        Proveedor prov = proveedorDao.findById(idProv);
        
        if (prov !=null) {
            request.setAttribute("proveedor", prov);
            request.setAttribute("tipoForm", "actualizar");
            request.getRequestDispatcher("/WEB-INF/proveedores/formulario.jsp").forward(request, response);

        }
    }

    private void insertarProveedor(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        //obtenemos los valores de los controles de form
//        long id = Long.parseLong(request.getParameter("idProv"));        
        String emitidopor =request.getParameter("emitidopor");
        String moneda =request.getParameter("moneda");
        String tipodocumento =request.getParameter("tipodocumento");
        String fecha =request.getParameter("fecha");
        String periododeclarado =request.getParameter("periododeclarado");
        String comentario =request.getParameter("comentario");
        String mtoaf =request.getParameter("mtoaf");
        String mtoe =request.getParameter("mtoe");
        double iva =Double.parseDouble(request.getParameter("iva"));
        double total =Double.parseDouble(request.getParameter("total"));
        
        SimpleDateFormat fechaFormato = new SimpleDateFormat("yyyy-MM-dd");
        
        java.util.Date date = null;
        try {
            date = fechaFormato.parse(fecha);
        } catch (ParseException ex) {
            Logger.getLogger(proveedoresControlador.class.getName()).log(Level.SEVERE, null, ex);
        }
        java.sql.Date sqlDate = new java.sql.Date(date.getTime());
        
        //creamos el obj base para insertar datos
        ProveedorJDBC base = new ProveedorJDBC();
        String mensaje= base.insert(new Proveedor(0, emitidopor, moneda, tipodocumento, sqlDate, periododeclarado,
                                    comentario, mtoaf, mtoe, iva, total));
        
        //redirigimos al index con todo y mensaje
        //con sendRedirect para que los datos insertados no se sigan reeviando
        request.getSession().setAttribute("operacionProveedor", mensaje); // lo enviamos por la secion
        response.sendRedirect( this.PATH_SISTEMA + "/proveedores");
    }

    private void borrarProveedor(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        long idProv = Long.parseLong(request.getParameter("idProv"));
        ProveedorJDBC base = new ProveedorJDBC();
        String mensaje =base.delete(idProv);
        
        request.getSession().setAttribute("operacionProveedor", mensaje); // lo enviamos por la secion
        response.sendRedirect( this.PATH_SISTEMA + "/proveedores");
    }

    private void actualizarProveedor(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        long id = Long.parseLong(request.getParameter("idProv"));        
        String emitidopor =request.getParameter("emitidopor");
        String moneda =request.getParameter("moneda");
        String tipodocumento =request.getParameter("tipodocumento");
        String fecha =request.getParameter("fecha");
        String periododeclarado =request.getParameter("periododeclarado");
        String comentario =request.getParameter("comentario");
        String mtoaf =request.getParameter("mtoaf");
        String mtoe =request.getParameter("mtoe");
        double iva =Double.parseDouble(request.getParameter("iva"));
        double total =Double.parseDouble(request.getParameter("total"));
        
        SimpleDateFormat fechaFormato = new SimpleDateFormat("yyyy-MM-dd");
        
        java.util.Date date = null;
        try {
            date = fechaFormato.parse(fecha);
        } catch (ParseException ex) {
            Logger.getLogger(proveedoresControlador.class.getName()).log(Level.SEVERE, null, ex);
        }
        java.sql.Date sqlDate = new java.sql.Date(date.getTime());
        
        //creamos el obj base para insertar datos
        ProveedorJDBC base = new ProveedorJDBC();
        String mensaje= base.update(new Proveedor(id, emitidopor, moneda, tipodocumento, sqlDate, periododeclarado,
                                    comentario, mtoaf, mtoe, iva, total));
        
        //redirigimos al index con todo y mensaje
        //con sendRedirect para que los datos insertados no se sigan reeviando
        request.getSession().setAttribute("operacionProveedor", mensaje); // lo enviamos por la secion
        response.sendRedirect( this.PATH_SISTEMA + "/proveedores");
    }

}
