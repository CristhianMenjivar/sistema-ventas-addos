package devs.com.sistema.ventas.controllers;

import devs.com.sistema.ventas.dao.BoletaJBDC;
import devs.com.sistema.ventas.modelos.Boleta;
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

@WebServlet(name = "boletaController", urlPatterns = {"/boleta"})
public class boletaController extends HttpServlet {

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
            BoletaJBDC DaoBoleta = new BoletaJBDC();

            List<Boleta> listaBoleta = DaoBoleta.listAll();

            //pasamos por atributo
            request.setAttribute("boleta", listaBoleta);
            //redirigimos a la vista
            request.getRequestDispatcher("/WEB-INF/boleta/index.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //checar si si el parametro esta llegando
        if (request.getParameter("accion") != null) {
            String accion = (String) request.getParameter("accion");
            switch(accion){
                case "crear":
                    //desplegar el formulario para nuevas categorias
                    insertarBoleta(request, response);
                    break;
                    
                case "borrar":
                    borrarBoleta(request, response);
                    break;
                    
                case "actualizar":
                    actualizarBoleta(request,response);
                    break;
            }
        }
    }

    private void formNuevo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       request.setAttribute("tipoForm", "crear");
       request.getRequestDispatcher("/WEB-INF/boleta/formulario.jsp").forward(request, response);
    }

    private void formEditar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        long idBoleta = Long.parseLong(request.getParameter("idBoleta"));
        BoletaJBDC categoriaDAO = new BoletaJBDC();
        Boleta boleta = categoriaDAO.findById(idBoleta);
        
        if (boleta !=null) {
            request.setAttribute("boleta", boleta);
            request.setAttribute("tipoForm", "actualizar");
            request.getRequestDispatcher("/WEB-INF/boleta/formulario.jsp").forward(request, response);

        }
    }

    private void insertarBoleta(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //obtenemos los valores de los controles de form
       // long idboleta = Long.parseLong(request.getParameter("idboleta"));   
        String boleta = request.getParameter("boleta");
        String tipoboleta = request.getParameter("tipoboleta");
        String estadoboleta = request.getParameter("estadoboleta");
        double total = Double.parseDouble(request.getParameter("total"));
        String formapago = request.getParameter("formapago");
        long numdocumento = Long.parseLong(request.getParameter("numdocumento"));
        String banco = request.getParameter("banco");
        String fecha = request.getParameter("fecha");
        double monto = Double.parseDouble(request.getParameter("monto"));
        
        SimpleDateFormat fechaFormato = new SimpleDateFormat("yyyy-MM-dd");
        
        java.util.Date date = null;
        try {
            date = fechaFormato.parse(fecha);
        } catch (ParseException ex) {
            Logger.getLogger(clienteControlador.class.getName()).log(Level.SEVERE, null, ex);
        }
        java.sql.Date sqlDate = new java.sql.Date(date.getTime());
        
        //creamos el obj base para insertar datos
        BoletaJBDC base = new BoletaJBDC();
        String mensaje= base.insert(new Boleta(0, boleta, tipoboleta, estadoboleta, total, formapago,
                                        numdocumento, banco, sqlDate, monto));
        //redirigimos al index con todo y mensaje
        //con sendRedirect para que los datos insertados no se sigan reeviando
        request.getSession().setAttribute("operacionBoleta", mensaje); // lo enviamos por la secion
        
        // local y heroku
        response.sendRedirect( this.PATH_SISTEMA + "/boleta");
    }

    private void borrarBoleta(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        long idBoleta = Long.parseLong(request.getParameter("idBoleta"));
        
        //creamos el obj base para insertar datos
        BoletaJBDC base = new BoletaJBDC();
        String mensaje= base.delete(idBoleta);
        //redirigimos al index con todo y mensaje
        //con sendRedirect para que los datos insertados no se sigan reeviando
        request.getSession().setAttribute("operacionBoleta", mensaje); // lo enviamos por la secion
        response.sendRedirect( this.PATH_SISTEMA + "/boleta");
    }

    private void actualizarBoleta(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        long idBoleta = Long.parseLong(request.getParameter("idActualizar"));   
        String boleta = request.getParameter("boleta");
        String tipoboleta = request.getParameter("tipoboleta");
        String estadoboleta = request.getParameter("estadoboleta");
        double total = Double.parseDouble(request.getParameter("total"));
        String formapago = request.getParameter("formapago");
        long numdocumento = Long.parseLong(request.getParameter("numdocumento"));
        String banco = request.getParameter("banco");
        String fecha = request.getParameter("fecha");
        double monto = Double.parseDouble(request.getParameter("monto"));
        
        SimpleDateFormat fechaFormato = new SimpleDateFormat("yyyy-MM-dd");
        
        java.util.Date date = null;
        try {
            date = fechaFormato.parse(fecha);
        } catch (ParseException ex) {
            Logger.getLogger(clienteControlador.class.getName()).log(Level.SEVERE, null, ex);
        }
        java.sql.Date sqlDate = new java.sql.Date(date.getTime());
        
        //creamos el obj base para insertar datos
        BoletaJBDC base = new BoletaJBDC();
        String mensaje= base.update(new Boleta(idBoleta, boleta, tipoboleta, estadoboleta, total,
                                            formapago, numdocumento, banco, sqlDate, monto));
        
        //redirigimos al index con todo y mensaje
        //con sendRedirect para que los datos insertados no se sigan reeviando
        request.getSession().setAttribute("operacionBoleta", mensaje); // lo enviamos por la secion
        response.sendRedirect( this.PATH_SISTEMA + "/boleta");
    }

}
