/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package devs.com.sistema.ventas.controllers;

import devs.com.sistema.ventas.dao.ClienteJDBC;
import devs.com.sistema.ventas.modelos.Cliente;
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
@WebServlet(name = "clienteControlador", urlPatterns = {"/clientes"})
public class clienteControlador extends HttpServlet {
    
    /* variable global, cuando el sistema está en desarrollo en nuestra maquina local se usa a base de glassfish
       para redireccion "/sistema ventas" necesaria para glassfish en Local
     * En heroku u otro hosting se usa la raiz de la app para redirigir "/"
    */
    
    // sistema en desarrollo
    private String SISTEMA_DEVELOPERS = "/sistema-ventas";
    //sistema en producción
    private String SISTEMA_PRODUCCTION = "";
    //direccion de la raiz del sistema
    private String PATH_SISTEMA = SISTEMA_DEVELOPERS;


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
            ClienteJDBC DaoCliente = new ClienteJDBC();

            List<Cliente> listaCliente = DaoCliente.listAll();

            //pasamos por atributo
            request.setAttribute("clientes", listaCliente);
            //redirigimos a la vista
            request.getRequestDispatcher("/WEB-INF/clientes/index.jsp").forward(request, response);
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

    private void formNuevo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
       request.setAttribute("tipoForm", "crear");
       request.getRequestDispatcher("/WEB-INF/clientes/formulario.jsp").forward(request, response);
    }

    private void formEditar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
       long idCliente = Long.parseLong(request.getParameter("idCliente"));
        ClienteJDBC categoriaDAO = new ClienteJDBC();
        Cliente cliente = categoriaDAO.findById(idCliente);
        
        if (cliente !=null) {
            request.setAttribute("clientes", cliente);
            request.setAttribute("tipoForm", "actualizar");
            request.getRequestDispatcher("/WEB-INF/clientes/formulario.jsp").forward(request, response);

        }
    }

    private void insertarCliente(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
         //obtenemos los valores de los controles de form
       // long idCliente = Long.parseLong(request.getParameter("idCliente"));   
        String nombre = request.getParameter("nombre");
        String apellido = request.getParameter("apellido");
        String direccion = request.getParameter("direccion");
        String telefono = request.getParameter("telefono");
        String numfactura = request.getParameter("numfactura");
        String tipofactura = request.getParameter("tipofactura");
        String estadofactura = request.getParameter("estadofactura");
        double total = Double.parseDouble(request.getParameter("total"));
        String formapago = request.getParameter("formapago");
        long numdocumento = Long.parseLong(request.getParameter("numdocumento"));
        String banco = request.getParameter("banco");
        String fecha = request.getParameter("fecha");
        double monto = Double.parseDouble(request.getParameter("monto"));
        String rut = request.getParameter("rut");
        
        SimpleDateFormat fechaFormato = new SimpleDateFormat("yyyy-MM-dd");
        
        java.util.Date date = null;
        try {
            date = fechaFormato.parse(fecha);
        } catch (ParseException ex) {
            Logger.getLogger(clienteControlador.class.getName()).log(Level.SEVERE, null, ex);
        }
        java.sql.Date sqlDate = new java.sql.Date(date.getTime());
        
        //creamos el obj base para insertar datos
        ClienteJDBC base = new ClienteJDBC();
        String mensaje= base.insert(new Cliente(0, nombre, apellido, direccion, telefono, numfactura, tipofactura,
                                            estadofactura, total, formapago, numdocumento, banco, sqlDate, monto, rut));
        //redirigimos al index con todo y mensaje
        //con sendRedirect para que los datos insertados no se sigan reeviando
        request.getSession().setAttribute("operacionCliente", mensaje); // lo enviamos por la secion
        response.sendRedirect( this.PATH_SISTEMA + "/clientes");
    }

    private void borrarCliente(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        long idCliente = Long.parseLong(request.getParameter("idCliente"));
        
        //creamos el obj base para insertar datos
        ClienteJDBC base = new ClienteJDBC();
        String mensaje= base.delete(idCliente);
        //redirigimos al index con todo y mensaje
        //con sendRedirect para que los datos insertados no se sigan reeviando
        request.getSession().setAttribute("operacionCliente", mensaje); // lo enviamos por la secion
        response.sendRedirect( this.PATH_SISTEMA + "/clientes");
    }

    private void actualizarCliente(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        long idCliente = Long.parseLong(request.getParameter("idActualizar"));   
        String nombre = request.getParameter("nombre");
        String apellido = request.getParameter("apellido");
        String direccion = request.getParameter("direccion");
        String telefono = request.getParameter("telefono");
        String numfactura = request.getParameter("numfactura");
        String tipofactura = request.getParameter("tipofactura");
        String estadofactura = request.getParameter("estadofactura");
        double total = Double.parseDouble(request.getParameter("total"));
        String formapago = request.getParameter("formapago");
        long numdocumento = Long.parseLong(request.getParameter("numdocumento"));
        String banco = request.getParameter("banco");
        String fecha = request.getParameter("fecha");
        double monto = Double.parseDouble(request.getParameter("monto"));
        String rut = request.getParameter("rut");
        
        SimpleDateFormat fechaFormato = new SimpleDateFormat("yyyy-MM-dd");
        
        java.util.Date date = null;
        try {
            date = fechaFormato.parse(fecha);
        } catch (ParseException ex) {
            Logger.getLogger(clienteControlador.class.getName()).log(Level.SEVERE, null, ex);
        }
        java.sql.Date sqlDate = new java.sql.Date(date.getTime());
        
        //creamos el obj base para insertar datos
        ClienteJDBC base = new ClienteJDBC();
        String mensaje= base.update(new Cliente(idCliente, nombre, apellido, direccion, telefono, numfactura, tipofactura,
                                            estadofactura, total, formapago, numdocumento, banco, sqlDate, monto, rut));
        
        //redirigimos al index con todo y mensaje
        //con sendRedirect para que los datos insertados no se sigan reeviando
        request.getSession().setAttribute("operacionCliente", mensaje); // lo enviamos por la secion
        response.sendRedirect( this.PATH_SISTEMA + "/clientes");
    }

}
