/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package devs.com.sistema.ventas.controllers;

import devs.com.sistema.ventas.dao.IProductosDao;
import devs.com.sistema.ventas.dao.OrdenJDBC;
import devs.com.sistema.ventas.dao.ProductoJDBC;
import devs.com.sistema.ventas.modelos.DetalleOrdenes;
import devs.com.sistema.ventas.modelos.Orden;
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
@WebServlet(name = "VentasController", urlPatterns = {"/ventas"})
public class VentasController extends HttpServlet {

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
            String accion = request.getParameter("accion");

            switch (accion) {
                case "verPedido":
                    verPedidos(request, response);
                    break;
                case "hacerPedido":
                    hacerPedido(request, response);
                    break;
                case "verDetalle":
                    verDetalle(request, response);
                case "verVentasDia":
                    verVentasDia(request, response);
                    break;
            }
        } else {
            request.getRequestDispatcher("/sistema-ventas/").forward(request, response);
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
                case "terminarVenta":
                    terminarVenta(request, response);
                    break;
                case "buscarProducto":
                    buscarProducto(request, response);
                    break;

                case "eliminarProducto":
                    eliminarProducto(request, response);
                    break;
                case "cancelarVenta":
                    cancelarVenta(request, response);
                    break;
            }
        }

    }

    private void verPedidos(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        OrdenJDBC daOrden = new OrdenJDBC();
        List<Orden> listaOrdenes = daOrden.listAll();

        request.setAttribute("ordenes", listaOrdenes);
        request.getRequestDispatcher("/WEB-INF/ventas/index.jsp").forward(request, response);

    }

    private void hacerPedido(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Orden orden = (Orden) request.getSession().getAttribute("orden");

        ProductoJDBC daoproductos = new ProductoJDBC();

        List<Producto> productos = daoproductos.listAll();

        request.setAttribute("productos", productos);

        if (orden == null) {
            orden = new Orden();
            orden.setMonto(0.0);
        } else {
            double importeOrden = 0.0;
            for (DetalleOrdenes detalle : orden.getDetalle()) {
                importeOrden += detalle.getImporte();
            }
            orden.setMonto(importeOrden);
        }

        request.getSession().setAttribute("orden", orden);
        request.getRequestDispatcher("/WEB-INF/ventas/new.jsp").forward(request, response);

    }

    //valida si el producto esta activo y si tiene suficiente existencias
    public boolean ValidarProducto(long id, double cant) {
        ProductoJDBC base = new ProductoJDBC();
        Producto p = base.findById(id);

        if (p != null) {

            if (p.getEnLinea() == 1 && p.getExistencias() >= cant) {
                return true;
            }
        }

        return false;
    }

    private void addProducto(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        long idprod = 0;
        double cantidad = 0.0;
        String mensaje = "";
        double importe = 0.00;
        Orden orden = null;

        if (request.getParameter("idProd") != null) {

            if (!request.getParameter("catidadProd").isEmpty()) {
                idprod = Long.parseLong(request.getParameter("idProd"));
                cantidad = Double.parseDouble(request.getParameter("catidadProd"));
                double cantAnterior = 0;

                IProductosDao productoDao = new ProductoJDBC();
                Producto producto = productoDao.findById(idprod);

                orden = (Orden) request.getSession().getAttribute("orden");

                // el fro para ver si el producto ya ha sido agregado antes, asi le agregams la nueva cantidad
                //para ques sea verificada  por las existencias
                try {
                    for (int i = 0; i < orden.getDetalle().size(); i++) {
                    if (orden.getDetalle().get(i).getProducto().getIdProducto()==idprod) {
                        cantAnterior += orden.getDetalle().get(i).getCantidad();
                    }
                }
                } catch (Exception e) {
                }
                

                if (ValidarProducto(idprod, cantidad + cantAnterior)) {

                    if (cantidad > 0) {

                        importe = producto.getPrecioVenta() * cantidad;

                        DetalleOrdenes detalle = new DetalleOrdenes();
                        detalle.setCantidad(cantidad);
                        detalle.setProducto(producto);
                        detalle.setOrden(orden);
                        detalle.setImporte(importe);

                        if (orden == null) {
                            orden = new Orden();
                            request.getSession().setAttribute("orden", orden);
                        }

                        orden.getDetalle().add(detalle);

                    } else {
                        mensaje = "La cantidad de compra debe ser mayor a cero...";
                    }

                } else {
                    String estado = "desactivado";
                    if (producto.getEnLinea() == 1) {
                        estado = "Activo";
                    }
                    mensaje = "El producto está: " + estado + ", Existencias: " + producto.getExistencias() + ", Agregados: " + cantAnterior;
                }

            } else {
                mensaje = "Ingrese la cantidad a vender...";
            }
        } else {
            mensaje = "No ha seléccionado un producto...";
        }

        //si la insercion viene del formulario producto
        if (request.getParameter("envio") != null && request.getParameter("envio").equals("productos")) {
            request.getSession().setAttribute("operacionProducto", "EL producto " + idprod + " "
                    + "se agrego corréctamente a la venta.");
            request.getSession().setAttribute("venta", "venta"); // para validar en productos que es una venta
            response.sendRedirect("/sistema-ventas/productos");

        } else {

            request.getSession().setAttribute("mensaje", mensaje);
            response.sendRedirect(request.getContextPath() + "/ventas?accion=hacerPedido");
        }
    }

    private void terminarVenta(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Orden orden = (Orden) request.getSession().getAttribute("orden");
        String tipoVenta = request.getParameter("tipoVenta");
        String tipoPago = request.getParameter("tipoPago");

        orden.setFecha(new java.sql.Date(new java.util.Date().getTime()));
        orden.setTipoPago(tipoPago);
        orden.setTipoVenta(tipoVenta);

        OrdenJDBC daoOrden = new OrdenJDBC();

        if (orden.getMonto() > 0) {
            Orden ordenCreada = daoOrden.insert(orden);
            //verificamos si la orden se creo
            if (ordenCreada != null) {
                request.getSession().setAttribute("ordenCreada", ordenCreada);
                response.sendRedirect("/ventas?accion=verPedido");
            }

        } else {
            request.getSession().setAttribute("mensaje", "EL monto está vacio...");
            // local: response.sendRedirect(request.getContextPath() + "/ventas?accion=hacerPedido");
            response.sendRedirect("/ventas?accion=hacerPedido");
        }
    }

    private void verDetalle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        long idPedido = Long.parseLong(request.getParameter("idPedido"));

        OrdenJDBC daoOrden = new OrdenJDBC();
        Orden orden = daoOrden.findById(idPedido);
        List<DetalleOrdenes> listaDetalles = (List<DetalleOrdenes>) daoOrden.detalles(orden);

        orden.setDetalle(listaDetalles);
        request.setAttribute("orden", orden);
        request.getRequestDispatcher("/WEB-INF/ventas/show.jsp").forward(request, response);
    }

    private void buscarProducto(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //buscar productos por codigo o nombre
        String parametro = request.getParameter("paramBuscar");

        ProductoJDBC daoproductos = new ProductoJDBC();

        List<Producto> productos = daoproductos.busquedaPorCriterio(parametro);

        request.setAttribute("productos", productos);
        request.getRequestDispatcher("/WEB-INF/ventas/new.jsp").forward(request, response);
    }

    private void eliminarProducto(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        long idProd = Long.parseLong(request.getParameter("idProd"));
        double cantidad = Double.parseDouble(request.getParameter("cantidad"));

        Orden orden = (Orden) request.getSession().getAttribute("orden");

        // for para eliminar un producto que sea igual al ID y Cantidad que vienen por parametros al objeto orden que tiene a detalles y productos
        for (int i = 0; i < orden.getDetalle().size(); i++) {
            if (orden.getDetalle().get(i).getProducto().getIdProducto() == idProd && orden.getDetalle().get(i).getCantidad() == cantidad) {
                orden.getDetalle().remove(i);
                break;
            }
        }

        request.getSession().setAttribute("orden", orden);
        response.sendRedirect(request.getContextPath() + "/ventas?accion=hacerPedido");
    }

    private void cancelarVenta(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.getSession().setAttribute("mensaje", "La venta ha sido cancelada");
        request.getSession().removeAttribute("orden");
        response.sendRedirect(request.getContextPath() + "/ventas?accion=hacerPedido");
    }


    private void verVentasDia(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        OrdenJDBC daOrden = new OrdenJDBC();
        List<Orden> listaOrdenes = daOrden.listForDate(new java.sql.Date(new java.util.Date().getTime()));

        request.setAttribute("ordenes", listaOrdenes);
        request.getRequestDispatcher("/WEB-INF/ventas/index.jsp").forward(request, response);
    }

}
