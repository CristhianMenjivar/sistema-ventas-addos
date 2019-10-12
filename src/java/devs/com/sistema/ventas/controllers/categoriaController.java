/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package devs.com.sistema.ventas.controllers;

import devs.com.sistema.ventas.dao.CategoriaJDBC;
import devs.com.sistema.ventas.dao.ProductoJDBC;
import devs.com.sistema.ventas.modelos.Categoria;
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
@WebServlet(name = "categoriaController", urlPatterns = {"/categorias"})
public class categoriaController extends HttpServlet {
    
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
                    
                case "mostrar":
                    formMostrar(request,response);
                    break;
            }
        } else {
            //para obtener lista de categorias
            CategoriaJDBC DaoCat = new CategoriaJDBC();

            List<Categoria> listaCategorias = DaoCat.listAll();

            //pasamos por atributo
            request.setAttribute("categorias", listaCategorias);
            //redirigimos a la vista
            request.getRequestDispatcher("/WEB-INF/categorias/index.jsp").forward(request, response);
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
       request.getRequestDispatcher("/WEB-INF/categorias/formulario.jsp").forward(request, response);
    }

    private void formEditar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
       long idCat = Long.parseLong(request.getParameter("idCat"));
        CategoriaJDBC DAOCat = new CategoriaJDBC();
        Categoria cat = DAOCat.findById(idCat);
        
        if (cat !=null) {
            request.setAttribute("categoria", cat);
            request.setAttribute("tipoForm", "actualizar");
            request.getRequestDispatcher("/WEB-INF/categorias/formulario.jsp").forward(request, response);

        }
    }

    private void insertarCliente(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
         //obtenemos los valores de los controles de form    
        String nombre = request.getParameter("nombre");
        String descripcion = request.getParameter("descripcion");
        
        //creamos el obj base para insertar datos
        CategoriaJDBC base = new CategoriaJDBC();
        String mensaje= base.insert(new Categoria(0, nombre, descripcion));
        //redirigimos al index con todo y mensaje
        //con sendRedirect para que los datos insertados no se sigan reeviando
        request.getSession().setAttribute("operacionCat", mensaje); // lo enviamos por la secion
        response.sendRedirect( this.PATH_SISTEMA + "/categorias");
    }

    private void borrarCliente(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        long idCat = Long.parseLong(request.getParameter("idCat"));
        
        //creamos el obj base para insertar datos
        CategoriaJDBC base = new CategoriaJDBC();
        String mensaje= base.delete(idCat);
        //redirigimos al index con todo y mensaje
        //con sendRedirect para que los datos insertados no se sigan reeviando
        request.getSession().setAttribute("operacionCat", mensaje); // lo enviamos por la secion
        response.sendRedirect( this.PATH_SISTEMA + "/categorias");
    }

    private void actualizarCliente(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        long idCat = Long.parseLong(request.getParameter("idActualizar"));   
        String nombre = request.getParameter("nombre");
        String descripcion = request.getParameter("descripcion");
        
        //creamos el obj base para insertar datos
        CategoriaJDBC base = new CategoriaJDBC();
        String mensaje= base.update(new Categoria(idCat, nombre, descripcion));
        
        //redirigimos al index con todo y mensaje
        //con sendRedirect para que los datos insertados no se sigan reeviando
        request.getSession().setAttribute("operacionCat", mensaje); // lo enviamos por la secion
        response.sendRedirect( this.PATH_SISTEMA + "/categorias");
    }

    private void formMostrar(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
       long idCat = Long.parseLong(request.getParameter("idCat"));
        CategoriaJDBC DAOCat = new CategoriaJDBC();
        Categoria cat = DAOCat.findById(idCat);
        
        ProductoJDBC DAOProd = new ProductoJDBC();
        List<Producto> listaProducto = DAOProd.getProductosByCategoria(cat);
        
        cat.setListaProducto(listaProducto);
        
        if (cat !=null) {
            request.setAttribute("categoria", cat);
            request.getRequestDispatcher("/WEB-INF/categorias/show.jsp").forward(request, response);

        }
    }

}
