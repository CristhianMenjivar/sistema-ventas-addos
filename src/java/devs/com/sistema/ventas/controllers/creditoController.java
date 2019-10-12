/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package devs.com.sistema.ventas.controllers;

import devs.com.sistema.ventas.dao.ClienteJDBC;
import devs.com.sistema.ventas.dao.CreditoJBDC;
import devs.com.sistema.ventas.dao.DetalleCreditoJBDC;
import devs.com.sistema.ventas.dao.OrdenJDBC;
import devs.com.sistema.ventas.modelos.Cliente;
import devs.com.sistema.ventas.modelos.Credito;
import devs.com.sistema.ventas.modelos.DetalleCredito;
import devs.com.sistema.ventas.modelos.Orden;
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
@WebServlet(name = "creditoController", urlPatterns = {"/creditos"})
public class creditoController extends HttpServlet {
    
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
            //para obtener lista de creditos
            CreditoJBDC daoCredito = new CreditoJBDC();

            List<Credito> lista = daoCredito.listAll();

            //pasamos por atributo
            request.setAttribute("creditos", lista);
            //redirigimos a la vista
            request.getRequestDispatcher("/WEB-INF/creditos/index.jsp").forward(request, response);
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
                    insetarCredito(request, response);
                    break;

                case "borrar":
                    borrarCredito(request, response);
                    break;

                case "actualizar":
                    actualizarCredito(request, response);
                    break;
                case "addDetalleCredito":
                    addDetalleCredito(request, response);
                    break;
                case "eliminarDetalle":
                    eliminarDetalle(request, response);
                    break;
            }
        }

    }

    private void formNuevo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        OrdenJDBC DAOorden = new OrdenJDBC();
        ClienteJDBC DAOcliente = new ClienteJDBC();

        //listas
        List<Orden> listaOrden = DAOorden.listAll();
        List<Cliente> listaClientes = DAOcliente.listAll();

        request.setAttribute("ventas", listaOrden);
        request.setAttribute("clientes", listaClientes);

        request.setAttribute("tipoForm", "crear");
        request.getRequestDispatcher("/WEB-INF/creditos/formulario.jsp").forward(request, response);

    }

    private void formEditar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        long idcredito = Long.parseLong(request.getParameter("idcredito"));
        CreditoJBDC DAOcredito = new CreditoJBDC();
        Credito credito = DAOcredito.findById(idcredito);

        OrdenJDBC DAOorden = new OrdenJDBC();
        ClienteJDBC DAOcliente = new ClienteJDBC();

        //listas
        List<Orden> listaOrden = DAOorden.listAll();
        List<Cliente> listaClientes = DAOcliente.listAll();

        if (credito != null) {
            request.setAttribute("credito", credito);
            request.setAttribute("ventas", listaOrden);
            request.setAttribute("clientes", listaClientes);

            request.setAttribute("tipoForm", "actualizar");
            request.getRequestDispatcher("/WEB-INF/creditos/formulario.jsp").forward(request, response);

        }
    }

    private void insetarCredito(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Date fecha = null;
        long idventa = 0;
        long idcliente = 0;
        double monto = 0;
        String mensaje = "";
        boolean resultado = false;

        try {
            fecha = Date.valueOf(request.getParameter("fecha"));
            idventa = Long.parseLong(request.getParameter("idventa"));
            idcliente = Long.parseLong(request.getParameter("idcliente"));
            monto = Double.parseDouble(request.getParameter("monto"));
            resultado = true;

        } catch (Exception e) {
            mensaje = "Datos incorrectos : " + e.getMessage();
        }

        if (resultado) {
            CreditoJBDC DAOcred = new CreditoJBDC();

            Credito credito = new Credito();
            Cliente cliente = new Cliente();
            Orden venta = new Orden();

            cliente.setIdCliente(idcliente);
            venta.setOrdenId(idventa);

            credito.setCliente(cliente);
            credito.setVenta(venta);
            credito.setFecha(fecha);
            credito.setMontoCredito(monto);

            mensaje = DAOcred.insert(credito);

        }
        //con sendRedirect para que los datos insertados no se sigan reeviando
        request.getSession().setAttribute("operacionCredito", mensaje); // lo enviamos por la secion
        response.sendRedirect( this.PATH_SISTEMA + "/creditos");
    }

    private void borrarCredito(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        long idCredito = Long.parseLong(request.getParameter("idcredito"));

        CreditoJBDC DAOcred = new CreditoJBDC();
        String mensaje = DAOcred.delete(idCredito);
        //con sendRedirect para que los datos insertados no se sigan reeviando
        request.getSession().setAttribute("operacionCredito", mensaje); // lo enviamos por la secion
        response.sendRedirect( this.PATH_SISTEMA + "/creditos");

    }

    private void actualizarCredito(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        long idCredito = 0; //  
        Date fecha = null;
        long idventa = 0;
        long idcliente = 0;
        double monto = 0;
        String mensaje = "";
        boolean resultado = false;

        try {
            idCredito = Long.parseLong(request.getParameter("idActualizar"));
            fecha = Date.valueOf(request.getParameter("fecha"));
            idventa = Long.parseLong(request.getParameter("idventa"));
            idcliente = Long.parseLong(request.getParameter("idcliente"));
            monto = Double.parseDouble(request.getParameter("monto"));
            resultado = true;

        } catch (Exception e) {
            mensaje = "Datos incorrectos : " + e.getMessage();
        }

        if (resultado) {
            CreditoJBDC DAOcred = new CreditoJBDC();

            Credito credito = new Credito();
            Cliente cliente = new Cliente();
            Orden venta = new Orden();

            cliente.setIdCliente(idcliente);
            venta.setOrdenId(idventa);

            credito.setIdCredito(idCredito);
            credito.setCliente(cliente);
            credito.setVenta(venta);
            credito.setFecha(fecha);
            credito.setMontoCredito(monto);

            mensaje = DAOcred.update(credito);

        }
        
        //con sendRedirect para que los datos insertados no se sigan reeviando
        request.getSession().setAttribute("operacionCredito", mensaje); // lo enviamos por la secion
        response.sendRedirect( this.PATH_SISTEMA + "/creditos");
        
    }

    private void formMostrar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        long idcredito = Long.parseLong(request.getParameter("idcredito"));
        CreditoJBDC DAOcredito = new CreditoJBDC();
        Credito credito = DAOcredito.findById(idcredito);

        if (credito != null) {
            request.getSession().setAttribute("credito", credito);
            request.getRequestDispatcher("/WEB-INF/creditos/show.jsp").forward(request, response);

        }

    }

    private void addDetalleCredito(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        long idcredito = 0;
        Date fecha = null;
        double cuota = 0;
        String mensaje = "";
        boolean resultado = false;

        try {
            idcredito = Long.parseLong(request.getParameter("idcredito"));
            fecha = Date.valueOf(request.getParameter("fecha"));
            cuota = Double.parseDouble(request.getParameter("cuota"));
            resultado = true;
        } catch (IllegalArgumentException ex) {
            mensaje = "Datos incorrectos";
        }

        if (resultado) {
            DetalleCredito detalleCredito = new DetalleCredito(0, idcredito, cuota, fecha);
            DetalleCreditoJBDC DAOdetalle = new DetalleCreditoJBDC();
            mensaje = DAOdetalle.insertarDetalle(detalleCredito);
        }

        request.getSession().setAttribute("mensajeForm", mensaje);
        response.sendRedirect( this.PATH_SISTEMA + "/creditos?accion=mostrar&idcredito=" + idcredito + "");

    }

    private void eliminarDetalle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        long iddetalle = Long.parseLong(request.getParameter("idDetalle"));
        long idcredito = Long.parseLong(request.getParameter("idcredito"));

        DetalleCreditoJBDC DAOdetalle = new DetalleCreditoJBDC();
        String mensaje = DAOdetalle.eliminarDetalle(iddetalle);

        request.getSession().setAttribute("mensajeTabla", mensaje);
        response.sendRedirect( this.PATH_SISTEMA + "/creditos?accion=mostrar&idcredito=" + idcredito + "");
    }

}
