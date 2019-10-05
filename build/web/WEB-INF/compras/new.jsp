<%-- 
    Document   : index
    Created on : 01-01-2019, 07:05:47 PM
    Author     : Cristhian menjivar

    Esta pagina index.jsp es para mostrar contenido de categorias y se ha incluido
    dentro de wen-inf/categorias porque es más seguro y solo se podria tener acceso a traves 
    de Servlets(Controladores)...
--%>

<%@page import="devs.com.sistema.ventas.modelos.DetalleCompra"%>
<%@page import="devs.com.sistema.ventas.modelos.Producto"%>
<%@page import="devs.com.sistema.ventas.modelos.Compra"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>compras</title>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <meta name="description" content="">
        <meta name="author" content="">

        <title>Sistema de ventas</title>

        <!-- Bootstrap core CSS-->
        <link href="<%= request.getContextPath()%>/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

        <!-- Custom fonts for this template-->
        <link href="<%= request.getContextPath()%>/vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">

        <!-- Page level plugin CSS-->
        <link href="<%= request.getContextPath()%>/vendor/datatables/dataTables.bootstrap4.css" rel="stylesheet">

        <!-- Custom styles for this template-->
        <link href="<%= request.getContextPath()%>/css/sb-admin.css" rel="stylesheet">
    </head>
    <%
        //recibimos la lista y l aparciamos
        List<Producto> listaProductos = (List<Producto>) request.getAttribute("productos");
        
       // Compra compra = (Compra) request.getAttribute("compra");

        //recurperar el mensaje
        String mensaje = "";
        if (request.getSession().getAttribute("mensaje") != null) {
            mensaje = (String) request.getSession().getAttribute("mensaje");
        }

    %>



    <body id="page-top" id="page-top" style="background-color: #666666">

        <jsp:useBean class="devs.com.sistema.ventas.modelos.Compra" id="compra" scope="session"></jsp:useBean>
            <!-- se incluye la barra de navigacion de heading -->
        <jsp:include page="../layouts/header.jsp"></jsp:include>

            <div id="wrapper">

                <!-- se incluye el nav bar de menu-->
            <jsp:include page="../layouts/sliderbar.jsp"></jsp:include>

                <div id="content-wrapper" >

                    <div class="container-fluid">
                    <%--vamos a usar un alert de boostrap para el mensaje--%>
                    <% if (!mensaje.isEmpty()) {%>
                    <div class="alert alert-success alert-dismissible">
                        <button type="button" class="close" data-dismiss="alert">×</button>
                        <strong>Informe:</strong> <%= mensaje%>
                    </div>
                    <% request.getSession().removeAttribute("mensaje"); %>
                    <% request.getSession().removeAttribute("compra"); %>
                    <% }%>



                    <!-- DataTables Example -->
                    <div class="card mb-3">
                        <div class="card-header text-center" style="background: #38cc82;color: #fff">
                            <i class="fas fa-shopping-bag"></i>
                            <strong>COMPRAS</strong>

                        </div>
                        <div class="card-body">

                            <div class="row">
                                <br>
                                <div class="col-md-12 text-md-right">
                                    <h3 style="color:#009966; vertical-align: baseline; font-family: fantasy; ">
                                        <strong>Total: $ <%= compra.getMonto() %></strong> 
                                    </h3>
                                </div>
                            </div>

                            <div class="row">
                                <br>
                                <div class="col-md-12 text-center btn-large">
                                    <form action="compras" method="post">
                                        <input type="hidden" name="accion" value="terminarCompra">
                                        <button type="submit" class="btn btn-outline-success btn-large">
                                            <span class="fas fa-fw fa-check-circle"></span> Realizar compra
                                        </button>
                                    </form>
                                </div>
                            </div>
                            <br>
                            <hr>

                            <div class="form-group">
                                <br>
                                <div class="form-row text-center">
                                    <div class="col-md-6 col-lg-3">
                                        <form action="compras" method="post">
                                            <input type="hidden" name="accion" value="buscarProducto">
                                            <strong>Buscar productos:</strong>

                                            <div class="input-group">
                                                <input type="text" autofocus="" class="form-control" placeholder="Nombre o código..." name="paramBuscar">
                                                <div class="input-group-append">
                                                    <button  class="btn btn-success" type="submit">
                                                        <i class="fas fa-search"></i>
                                                    </button>
                                                </div>
                                            </div>

                                        </form>
                                    </div>
                                    <div class="col-md-6 col-lg-4">
                                        <form action="compras" method="post">
                                            <input type="hidden" name="accion" value="addProducto">
                                            <strong>Seléccione un producto:</strong>
                                            <select name="idProd" class="form-control">
                                                <% for (Producto prod : listaProductos) {%>
                                                    <option value="<%= prod.getIdProducto() %>"><%= prod.getNombre() %></option>
                                                 <% }%>
                                            </select>
                                    </div>
                                    <div class="col-md-6 col-lg-2">
                                        <strong>Cantidad:</strong>
                                        <input type="number" name="catidadProd" class="form-control" placeholder="Cantidad...">
                                    </div>
                                    <div class="col-md-6 col-lg-3">
                                        <br>
                                        <button type="submit" class="btn btn-outline-success btn-large"><span class="fas fa-fw fa-plus-circle"></span> Agregar producto</button>
                                    </div>
                                    </form>
                                </div>
                            </div> 

                            <br>

                            <hr>


                            <div class="table-responsive">
                                <table class="table table-striped">
                                    <thead>

                                        <tr>
                                            <th>Código</th>
                                            <th>Producto</th>
                                            <th>Cantidad</th>
                                            <th>Precio de Compra</th>
                                            <th>Importe</th>
                                            <th></th>
                                        </tr>

                                    </thead>
                                    <tbody>
                                        <% for (DetalleCompra detalle : compra.getDetalle()) {%>
                                            <tr>
                                                <td><%= detalle.getProducto().getIdProducto() %></td>
                                                <td><%= detalle.getProducto().getNombre()%></td>
                                                <td><%= detalle.getCantidad() %></td>
                                                <td><%= detalle.getProducto().getPrecioCompra() %></td>
                                                <td><%= detalle.getImporte()%></td>
                                        <form action="compras" method="post">
                                            <input type="hidden" name="accion" value="eliminarProducto">
                                            <input type="hidden" name="idProd" value="<%= detalle.getProducto().getIdProducto() %>">
                                            <input type="hidden" name="cantidad" value="<%= detalle.getCantidad() %>">
                                            <td style="width: 40px;">
                                                <button type="submit" class="btn btn-outline-danger">
                                                    <span class="fas fa-fw fa-trash"></span> Borrar
                                                </button>
                                            </td>
                                        </form>
                                        </tr>
                                        <% } %>
                                    </tbody>
                                </table>


                            </div>
                        </div>
                        <br>
                        <div class="col-md-6 col-sm-6 col-lg-4">

                            <form action="compras" method="post">
                                <input type="hidden" name="accion" value="cancelarCompra">
                                <button type="submit" class="btn btn-outline-danger btn-block">
                                    <span class="fas fa-fw fa-exclamation-circle"></span> Cancelar compra
                                </button>
                            </form>
                            <br>
                        </div>
                        <br>
                    </div>

                </div>
            </div>
        </div>
        <!-- /.container-fluid -->

        <!-- se incluye el footer-->
        <jsp:include page="../layouts/footer.jsp"></jsp:include>

    </body>
</html>
