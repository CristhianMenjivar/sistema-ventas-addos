package devs.com.sistema.ventas.controllers;

import devs.com.sistema.ventas.dao.CuentasJDBC;
import devs.com.sistema.ventas.modelos.Cuentas;
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
 * @author HP
 */
@WebServlet(name = "Cuentas", urlPatterns = {"/cuentas"})
public class CuentasController extends HttpServlet {
    
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

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
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
            }
        } else {
            //para obtener lista de categorias
            CuentasJDBC DaoCuenta = new CuentasJDBC();

            List<Cuentas> listaCuentas = DaoCuenta.listAll();

            //pasamos por atributo
            request.setAttribute("cuentas", listaCuentas);
            //redirigimos a la vista
            request.getRequestDispatcher("/WEB-INF/cuentas/index.jsp").forward(request, response);
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
                    insertarCuenta(request, response);
                    break;
                    
                case "borrar":
                    borrarCuenta(request, response);
                    break;
                    
                case "actualizar":
                    actualizarCuenta(request,response);
                    break;
            }
        }
    }

    private void formNuevo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       request.setAttribute("tipoForm", "crear");
       request.getRequestDispatcher("/WEB-INF/cuentas/formulario.jsp").forward(request, response);
    }

    private void formEditar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        long idcuenta = Long.parseLong(request.getParameter("idcuenta"));
        CuentasJDBC cuentaDao = new CuentasJDBC();
        Cuentas cuentas = cuentaDao.findById(idcuenta);
        
        if (cuentas !=null) {
            request.setAttribute("cuentas", cuentas);
            request.setAttribute("tipoForm", "actualizar");
            request.getRequestDispatcher("/WEB-INF/cuentas/formulario.jsp").forward(request, response);

        }
    }

    private void insertarCuenta(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //obtenemos los valores de los controles de form
//        long id = Long.parseLong(request.getParameter("idProv"));        
        String o =request.getParameter("o");
        String t =request.getParameter("t");
        String fecha =request.getParameter("fecha");
        String emisor =request.getParameter("emisor");
        String descripcion =request.getParameter("descripcion");
        String ctacont =request.getParameter("ctacont");
        String centro =request.getParameter("centro");
        String usuario =request.getParameter("usuario");
        double pago =Double.parseDouble(request.getParameter("pago"));
        double monto =Double.parseDouble(request.getParameter("monto"));
        
        SimpleDateFormat fechaFormato = new SimpleDateFormat("yyyy-MM-dd");
        
        java.util.Date date = null;
        try {
            date = fechaFormato.parse(fecha);
        } catch (ParseException ex) {
            Logger.getLogger(CuentasController.class.getName()).log(Level.SEVERE, null, ex);
        }
        java.sql.Date sqlDate = new java.sql.Date(date.getTime());
        
        //creamos el obj base para insertar datos
        CuentasJDBC base = new CuentasJDBC();
        String mensaje= base.insert(new Cuentas(0, o, t, sqlDate, emisor,
                                    descripcion, ctacont, centro, usuario, pago, monto));
        
        //redirigimos al index con todo y mensaje
        //con sendRedirect para que los datos insertados no se sigan reeviando
        request.getSession().setAttribute("operacionCuenta", mensaje); // lo enviamos por la secion
        
        //local y heroku
        response.sendRedirect( this.PATH_SISTEMA + "/cuentas");
    }

    private void borrarCuenta(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        long idcuenta = Long.parseLong(request.getParameter("idcuenta"));
        CuentasJDBC base = new CuentasJDBC();
        String mensaje =base.delete(idcuenta);
        
        request.getSession().setAttribute("operacionCuenta", mensaje); // lo enviamos por la secion
        
        //heroku: 
        response.sendRedirect( this.PATH_SISTEMA + "/cuentas");
    }

    private void actualizarCuenta(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        long id = Long.parseLong(request.getParameter("idcuenta"));        
        String o =request.getParameter("o");
        String t =request.getParameter("t");
        String fecha =request.getParameter("fecha");
        String emisor =request.getParameter("emisor");
        String descripcion =request.getParameter("descripcion");
        String ctacont =request.getParameter("ctacont");
        String centro =request.getParameter("centro");
        String usuario =request.getParameter("usuario");
        double pago =Double.parseDouble(request.getParameter("pago"));
        double monto =Double.parseDouble(request.getParameter("monto"));
        
        SimpleDateFormat fechaFormato = new SimpleDateFormat("yyyy-MM-dd");
        
        java.util.Date date = null;
        try {
            date = fechaFormato.parse(fecha);
        } catch (ParseException ex) {
            Logger.getLogger(CuentasController.class.getName()).log(Level.SEVERE, null, ex);
        }
        java.sql.Date sqlDate = new java.sql.Date(date.getTime());
        
        //creamos el obj base para insertar datos
        CuentasJDBC base = new CuentasJDBC();
        String mensaje= base.update(new Cuentas(id, o, t, sqlDate, emisor,descripcion, ctacont, centro, usuario, pago, monto));
        
        //redirigimos al index con todo y mensaje
        //con sendRedirect para que los datos insertados no se sigan reeviando
        request.getSession().setAttribute("operacionCuenta", mensaje); // lo enviamos por la secion
        //local y heroku : 
        response.sendRedirect( this.PATH_SISTEMA + "/cuentas");
    }

}
