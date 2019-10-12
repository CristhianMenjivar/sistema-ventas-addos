/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package devs.com.sistema.ventas.controllers;

import devs.com.sistema.ventas.dao.IProductosDao;
import devs.com.sistema.ventas.dao.ProductoJDBC;
import devs.com.sistema.ventas.dao.compraJBDC;
import devs.com.sistema.ventas.modelos.Compra;
import devs.com.sistema.ventas.modelos.DetalleCompra;
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
@WebServlet(name = "comprasController", urlPatterns = {"/compras"})
public class comprasController extends HttpServlet {
    
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
                case "compras":
                    formInventario(request, response);
                    break;
                case "verDetalle":
                    verDetalle(request, response);

                case "verComprasDia":
                    verComprasDias(request, response);
                    break;
            }
        } else {
            compraJBDC daoCompra = new compraJBDC();
            List<Compra> listaCompras = daoCompra.listAll();

            request.setAttribute("compras", listaCompras);
            request.getRequestDispatcher("/WEB-INF/compras/index.jsp").forward(request, response);
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
            String accion = request.getParameter("accion");

            switch (accion) {
                case "addProducto":
                    addProducto(request, response);
                    break;
                case "terminarCompra":
                    terminarCompra(request, response);
                    break;
                case "buscarProducto":
                    buscarProducto(request, response);
                    break;

                case "eliminarProducto":
                    eliminarProducto(request, response);
                    break;
                case "cancelarCompra":
                    cancelarCompra(request, response);
                    break;
            }
        }

    }

    private void formInventario(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Compra compra = (Compra) request.getSession().getAttribute("compra");

        ProductoJDBC daoproductos = new ProductoJDBC();

        List<Producto> productos = daoproductos.listAll();

        request.setAttribute("productos", productos);

        if (compra == null) {
            compra = new Compra();
            compra.setMonto(0.0);
        } else {
            double importeOrden = 0.0;
            for (DetalleCompra detalle : compra.getDetalle()) {
                importeOrden += detalle.getImporte();
            }
            compra.setMonto(importeOrden);
        }

        request.getSession().setAttribute("compra", compra);
        request.getRequestDispatcher("/WEB-INF/compras/new.jsp").forward(request, response);
    }

    private void addProducto(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        long idprod = 0;
        double cantidad = 0.0;
        String mensaje = "";
        double importe = 0.00;
        Compra compra = null;

        if (request.getParameter("idProd") != null) {

            if (!request.getParameter("catidadProd").isEmpty()) {
                idprod = Long.parseLong(request.getParameter("idProd"));
                cantidad = Double.parseDouble(request.getParameter("catidadProd"));

                IProductosDao productoDao = new ProductoJDBC();
                Producto producto = productoDao.findById(idprod);

                if (cantidad > 0) {

                    importe = producto.getPrecioCompra() * cantidad;

                    compra = (Compra) request.getSession().getAttribute("compra");

                    DetalleCompra detalle = new DetalleCompra();
                    detalle.setCantidad(cantidad);
                    detalle.setProducto(producto);
                    detalle.setImporte(importe);

                    if (compra == null) {
                        compra = new Compra();
                        request.getSession().setAttribute("compra", compra);
                    }

                    compra.getDetalle().add(detalle);

                } else {
                    mensaje = "La cantidad debe ser mayor a cero...";
                }

            } else {
                mensaje = "Ingrese la cantidad a comprar...";
            }
        } else {
            mensaje = "No ha seléccionado un producto...";
        }

        request.getSession().setAttribute("mensaje", mensaje);
        response.sendRedirect( this.PATH_SISTEMA + "/compras?accion=compras");

    }

    private void buscarProducto(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //buscar productos por codigo o nombre
        String parametro = request.getParameter("paramBuscar");

        ProductoJDBC daoproductos = new ProductoJDBC();

        List<Producto> productos = daoproductos.busquedaPorCriterio(parametro);

        request.setAttribute("productos", productos);
        request.getRequestDispatcher("/WEB-INF/compras/new.jsp").forward(request, response);
    }

    private void eliminarProducto(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        long idProd = Long.parseLong(request.getParameter("idProd"));
        double cantidad = Double.parseDouble(request.getParameter("cantidad"));

        Compra compra = (Compra) request.getSession().getAttribute("compra");

        // for para eliminar un producto que sea igual al ID y Cantidad que vienen por parametros al objeto orden que tiene a detalles y productos
        for (int i = 0; i < compra.getDetalle().size(); i++) {
            if (compra.getDetalle().get(i).getProducto().getIdProducto() == idProd && compra.getDetalle().get(i).getCantidad() == cantidad) {
                compra.getDetalle().remove(i);
                break;
            }
        }

        request.getSession().setAttribute("compra", compra);
        response.sendRedirect(this.PATH_SISTEMA + "/compras?accion=compras");
    }

    private void cancelarCompra(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getSession().removeAttribute("compra");
        request.getSession().setAttribute("mensaje", "La compra ha sido cancelada");
        response.sendRedirect( this.PATH_SISTEMA + "/compras?accion=compras");
    }

    private void terminarCompra(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Compra compra = (Compra) request.getSession().getAttribute("compra");

        try {
            compra.getDetalle().get(0).getCantidad(); // si no hay un producto en el indice 0 se producira una exepcion

            compra.setFecha(new java.sql.Date(new java.util.Date().getTime()));

            //ProductoJDBC DAOProd = new ProductoJDBC();
            compraJBDC DAOcompra = new compraJBDC();

            String mensaje = "";
            
            if (DAOcompra.insert(compra) != null) {
                mensaje = "Los productos han sido cargados al inventario";
                request.getSession().removeAttribute("compra");
            } else {
                mensaje = "No se pudo cargar el inventario";
            }

            request.getSession().setAttribute("mensaje", mensaje);

        } catch (Exception ex) {
            request.getSession().setAttribute("mensaje", "No hay productos agregados");
        }

        //local es asi: 
        response.sendRedirect( this.PATH_SISTEMA + "/compras");
    }

    private void verDetalle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        long idcompra = Long.parseLong(request.getParameter("idcompra"));

        compraJBDC daoCompra = new compraJBDC();
        Compra compra = daoCompra.findById(idcompra);
        List<DetalleCompra> listaDetalles = (List<DetalleCompra>) daoCompra.detalles(compra);

        compra.setDetalle(listaDetalles);
        request.setAttribute("compra", compra);
        request.getRequestDispatcher("/WEB-INF/compras/show.jsp").forward(request, response);
    }

    private void verComprasDias(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        compraJBDC daoCompra = new compraJBDC();
        List<Compra> listaCompras = daoCompra.listComprasDia(new java.sql.Date(new java.util.Date().getTime()));

        request.setAttribute("compras", listaCompras);
        request.getRequestDispatcher("/WEB-INF/compras/index.jsp").forward(request, response);
    }

}
