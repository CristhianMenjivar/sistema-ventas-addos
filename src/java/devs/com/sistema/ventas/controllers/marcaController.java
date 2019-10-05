/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package devs.com.sistema.ventas.controllers;

import devs.com.sistema.ventas.dao.MarcaJDBC;
import devs.com.sistema.ventas.dao.ProductoJDBC;
import devs.com.sistema.ventas.modelos.Marca;
import devs.com.sistema.ventas.modelos.Producto;
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
@WebServlet(name = "marcaController", urlPatterns = {"/marcas"})
public class marcaController extends HttpServlet {

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
                    mostraProductos(request, response);
            }
        } else {
            //para obtener lista de categorias
            MarcaJDBC DAOmarca = new MarcaJDBC();

            List<Marca> listaMarca = DAOmarca.listAll();

            //pasamos por atributo
            request.setAttribute("marcas", listaMarca);
            //redirigimos a la vista
            request.getRequestDispatcher("/WEB-INF/marcas/index.jsp").forward(request, response);
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
            switch (accion) {
                case "crear":
                    //desplegar el formulario para nuevas categorias
                    insertarCliente(request, response);
                    break;

                case "borrar":
                    borrarCliente(request, response);
                    break;

                case "actualizar":
                    actualizarCliente(request, response);
                    break;
            }
        }

    }

    private void formNuevo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("tipoForm", "crear");
        request.getRequestDispatcher("/WEB-INF/marcas/formulario.jsp").forward(request, response);
    }

    private void formEditar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        long idMarca = Long.parseLong(request.getParameter("idMar"));
        MarcaJDBC DAOCat = new MarcaJDBC();
        Marca marca = DAOCat.findById(idMarca);

        if (marca != null) {
            request.setAttribute("marca", marca);
            request.setAttribute("tipoForm", "actualizar");
            request.getRequestDispatcher("/WEB-INF/marcas/formulario.jsp").forward(request, response);

        }
    }

    private void insertarCliente(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //obtenemos los valores de los controles de form    
        String nombre = request.getParameter("nombre");

        //creamos el obj base para insertar datos
        MarcaJDBC base = new MarcaJDBC();
        String mensaje = base.insert(new Marca(0, nombre));
        //redirigimos al index con todo y mensaje
        //con sendRedirect para que los datos insertados no se sigan reeviando
        request.getSession().setAttribute("operacionMar", mensaje); // lo enviamos por la secion
        response.sendRedirect("/sistema-ventas/marcas");
    }

    private void borrarCliente(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        long idMarca = Long.parseLong(request.getParameter("idMar"));

        //creamos el obj base para insertar datos
        MarcaJDBC base = new MarcaJDBC();
        String mensaje = base.delete(idMarca);
        //redirigimos al index con todo y mensaje
        //con sendRedirect para que los datos insertados no se sigan reeviando
        request.getSession().setAttribute("operacionMar", mensaje); // lo enviamos por la secion
        response.sendRedirect("/sistema-ventas/marcas");
    }

    private void actualizarCliente(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        long idMar = Long.parseLong(request.getParameter("idActualizar"));
        String nombre = request.getParameter("nombre");

        //creamos el obj base para insertar datos
        MarcaJDBC base = new MarcaJDBC();
        String mensaje = base.update(new Marca(idMar, nombre));

        //redirigimos al index con todo y mensaje
        //con sendRedirect para que los datos insertados no se sigan reeviando
        request.getSession().setAttribute("operacionMar", mensaje); // lo enviamos por la secion
        response.sendRedirect("/sistema-ventas/marcas");
    }

    private void mostraProductos(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        long idMarca = Long.parseLong(request.getParameter("idMar"));
        ProductoJDBC DAOprod = new ProductoJDBC();
        MarcaJDBC marcaDAO = new MarcaJDBC();
        
        Marca marca = marcaDAO.findById(idMarca);
        List<Producto> listaProducto = DAOprod.gatProductosByMarca(idMarca);
        
        marca.setProductos(listaProducto);
        
        request.getSession().setAttribute("marca", marca);
        request.getRequestDispatcher("/WEB-INF/marcas/show.jsp").forward(request, response);

    }

}
