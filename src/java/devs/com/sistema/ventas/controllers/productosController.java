/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package devs.com.sistema.ventas.controllers;

import devs.com.sistema.ventas.dao.CategoriaJDBC;
import devs.com.sistema.ventas.dao.MarcaJDBC;
import devs.com.sistema.ventas.dao.ProductoJDBC;
import devs.com.sistema.ventas.dao.ProveedorJDBC;
import devs.com.sistema.ventas.modelos.Categoria;
import devs.com.sistema.ventas.modelos.Marca;
import devs.com.sistema.ventas.modelos.Producto;
import devs.com.sistema.ventas.modelos.Proveedor;
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
@WebServlet(name = "productosController", urlPatterns = {"/productos"})
public class productosController extends HttpServlet {
    
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
            ProductoJDBC DaoProducto = new ProductoJDBC();

            List<Producto> listaProductos = DaoProducto.listAll();

            for (Producto p : listaProductos) {
                System.out.println("producto" + p.getNombre());
                System.out.println("precio: " + p.getPrecioVenta());
            }

            //pasamos por atributo
            request.setAttribute("Productos", listaProductos);
            //redirigimos a la vista
            request.getRequestDispatcher("/WEB-INF/productos/index.jsp").forward(request, response);
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
                    insetarProducto(request, response);
                    break;

                case "borrar":
                    borrarProducto(request, response);
                    break;

                case "actualizar":
                    actualizarProducto(request, response);
                    break;
            }
        }

    }

    private void formNuevo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        MarcaJDBC marcaDAO = new MarcaJDBC();
        CategoriaJDBC catDAO = new CategoriaJDBC();
        ProveedorJDBC proveeDAO = new ProveedorJDBC();

        List<Marca> listaMarcas = marcaDAO.listAll();
        List<Categoria> listacat = catDAO.listAll();
        List<Proveedor> listaProv = proveeDAO.listAll();

        request.setAttribute("marcas", listaMarcas);
        request.setAttribute("categorias", listacat);
        request.setAttribute("proveedores", listaProv);

        request.setAttribute("tipoForm", "crear");
        request.getRequestDispatcher("/WEB-INF/productos/formulario.jsp").forward(request, response);
    }

    private void formEditar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        long idProducto = Long.parseLong(request.getParameter("idProducto"));
        ProductoJDBC productoDAO = new ProductoJDBC();
        Producto producto = productoDAO.findById(idProducto);

        MarcaJDBC marcaDAO = new MarcaJDBC();
        CategoriaJDBC catDAO = new CategoriaJDBC();
        ProveedorJDBC proveeDAO = new ProveedorJDBC();

        //listas
        List<Marca> listaMarcas = marcaDAO.listAll();
        List<Categoria> listacat = catDAO.listAll();
        List<Proveedor> listaProv = proveeDAO.listAll();

        if (producto != null) {
            request.setAttribute("producto", producto);
            request.setAttribute("marcas", listaMarcas);
            request.setAttribute("categorias", listacat);
            request.setAttribute("proveedores", listaProv);

            request.setAttribute("tipoForm", "actualizar");
            request.getRequestDispatcher("/WEB-INF/productos/formulario.jsp").forward(request, response);

        }
    }

    private void insetarProducto(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //obtenemos los valores de los controles de form
        long idProducto = Long.parseLong(request.getParameter("idproducto")); //  
        String medida = request.getParameter("medida");
        String nombre = request.getParameter("nombre");
        String color = request.getParameter("color");
        String serie = request.getParameter("serie");
        String modelo = request.getParameter("modelo");

        String checkActivo = "of";
        int enLinea = 0;
        try {
            if (request.getParameter("enLinea") != null) {
                checkActivo = request.getParameter("enLinea");
            }
        } catch (NullPointerException ex) {
            enLinea = 0;
        }

        if (checkActivo.equalsIgnoreCase("on")) {
            enLinea = 1;
        } else {
            enLinea = 0;
        }

        log(request.getParameter("enLinea"));

        double precioCompra = Double.parseDouble(request.getParameter("precioCompra"));
        double precioVenta = Double.parseDouble(request.getParameter("precioVenta"));
        double stock = Double.parseDouble(request.getParameter("stock"));
        double existencia = Double.parseDouble(request.getParameter("existencia"));
        double averiado = Double.parseDouble(request.getParameter("averiado"));
        long idMarca = Long.parseLong(request.getParameter("idMarca"));
        long idProveedor = Long.parseLong(request.getParameter("idProveedor"));
        long idCategoria = Long.parseLong(request.getParameter("idCategoria"));
        String tipoRegistro = request.getParameter("tipoRegistro");

        Marca marca = new Marca();
        Proveedor proveedor = new Proveedor();
        Categoria categoria = new Categoria();

        marca.setIdMarca(idMarca);
        proveedor.setIdProveedor(idProveedor);
        categoria.setCategoriaId(idCategoria);

        //creamos el obj base para insertar datos
        ProductoJDBC base = new ProductoJDBC();
        String mensaje = base.insert(new Producto(idProducto, medida, nombre, color, serie, modelo, enLinea,
                precioCompra, precioVenta, stock, existencia, averiado, marca, proveedor, categoria, tipoRegistro));
        //redirigimos al index con todo y mensaje
        //con sendRedirect para que los datos insertados no se sigan reeviando
        request.getSession().setAttribute("operacionProducto", mensaje); // lo enviamos por la secion
        response.sendRedirect( this.PATH_SISTEMA + "/productos");
    }

    private void borrarProducto(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        long idProducto = Long.parseLong(request.getParameter("idProducto"));

        //creamos el obj base para insertar datos
        ProductoJDBC base = new ProductoJDBC();
        String mensaje = base.delete(idProducto);
        //redirigimos al index con todo y mensaje
        //con sendRedirect para que los datos insertados no se sigan reeviando
        request.getSession().setAttribute("operacionProducto", mensaje); // lo enviamos por la secion
        response.sendRedirect( this.PATH_SISTEMA + "/productos");
    }

    private void actualizarProducto(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        long idProducto = Long.parseLong(request.getParameter("idActualizar"));
        String medida = request.getParameter("medida");
        String nombre = request.getParameter("nombre");
        String color = request.getParameter("color");
        String serie = request.getParameter("serie");
        String modelo = request.getParameter("modelo");

        String checkActivo = "of";
        int enLinea = 0;
        try {
            if (request.getParameter("enLinea") != null) {
                checkActivo = request.getParameter("enLinea");
            }
        } catch (NullPointerException ex) {
            enLinea = 0;
        }

        if (checkActivo.equalsIgnoreCase("on")) {
            enLinea = 1;
        } else {
            enLinea = 0;
        }

        log(request.getParameter("enLinea"));

        double precioCompra = Double.parseDouble(request.getParameter("precioCompra"));
        double precioVenta = Double.parseDouble(request.getParameter("precioVenta"));
        double stock = Double.parseDouble(request.getParameter("stock"));
        double existencia = Double.parseDouble(request.getParameter("existencia"));
        double averiado = Double.parseDouble(request.getParameter("averiado"));
        long idMarca = Long.parseLong(request.getParameter("idMarca"));
        long idProveedor = Long.parseLong(request.getParameter("idProveedor"));
        long idCategoria = Long.parseLong(request.getParameter("idCategoria"));
        String tipoRegistro = request.getParameter("tipoRegistro");

        Marca marca = new Marca();
        Proveedor proveedor = new Proveedor();
        Categoria categoria = new Categoria();

        marca.setIdMarca(idMarca);
        proveedor.setIdProveedor(idProveedor);
        categoria.setCategoriaId(idCategoria);

        //creamos el obj base para insertar datos
        ProductoJDBC base = new ProductoJDBC();
        String mensaje = base.update(new Producto(idProducto, medida, nombre, color, serie, modelo, enLinea,
                precioCompra, precioVenta, stock, existencia, averiado, marca, proveedor, categoria, tipoRegistro));
        //redirigimos al index con todo y mensaje
        //con sendRedirect para que los datos insertados no se sigan reeviando
        request.getSession().setAttribute("operacionProducto", mensaje); // lo enviamos por la secion
        response.sendRedirect( this.PATH_SISTEMA + "/productos");
    }


}
