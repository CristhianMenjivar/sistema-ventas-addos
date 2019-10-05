
<%@page import="devs.com.sistema.ventas.modelos.DetalleOrdenes"%>
<%@page import="java.util.List"%>
<%@page import="devs.com.sistema.ventas.modelos.Producto"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>ventas</title>
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
        //recurperar el mensaje
        String mensaje = "";
        String tipoPago = "";
        String tipoventa = "";
        if (request.getSession().getAttribute("mensaje") != null) {
            mensaje = (String) request.getSession().getAttribute("mensaje");
        }

        //recibimos la lista y l aparciamos
        List<Producto> listaProductos = (List<Producto>) request.getAttribute("productos");
    %>

    <body id="page-top" style="background-color: #666666">

        <jsp:useBean class="devs.com.sistema.ventas.modelos.Orden" id="ordenCreada" scope="session"></jsp:useBean>
        <jsp:useBean class="devs.com.sistema.ventas.modelos.Orden" id="orden" scope="session"></jsp:useBean>
            <!-- se incluye la barra de navigacion de heading -->
        <jsp:include page="../layouts/header.jsp"></jsp:include>

            <div id="wrapper">

                <!-- se incluye el nav bar de menu-->
            <jsp:include page="../layouts/sliderbar.jsp"></jsp:include>

                <div id="content-wrapper">

                    <div class="container-fluid">

                    <%--vamos a usar un alert de boostrap para el mensaje--%>
                    <% if (!mensaje.isEmpty()) {%>
                    <% request.getSession().removeAttribute("mensaje");%>

                    <div class="alert alert-danger alert-dismissible">
                        <button type="button" class="close" data-dismiss="alert">×</button>
                        <strong>Informe:</strong> <%= mensaje%>
                    </div>
                    <% }%>

                    <!-- DataTables Example card mb-3  -->
                    <div class="card card-producto mx-auto mt-auto">
                        <div class="card-header text-center" style="background-color: #38cc82; color: #fff"><span class="fas fa-fw fa-shopping-cart"></span><strong> VENTAS</strong></div>


                        <div class="container-fluid">

                            <form action="ventas" method="post">
                                <input type="hidden" name="accion" value="terminarVenta">

                                <div class="form-group">
                                    <br>
                                    <div class="form-row text-center">
                                        <div class="col-md-6 col-lg-3">
                                            <strong>Fecha:</strong>
                                            <input type="text" value="<%= new java.sql.Date(new java.util.Date().getTime())%>"
                                                   name="fechaPedido" class="form-control" disabled>
                                        </div>
                                        <div class="col-md-6 col-lg-3">
                                            <strong>Tipo de pago:</strong>
                                            <select name="tipoPago" class="form-control">
                                                <option value="Cheque" selected="true">Cheque</option>
                                                <option value="Contado">Contado</option>
                                                <option value="Targeta">Targeta</option>
                                            </select>
                                        </div>
                                        <div class="col-md-6 col-lg-3">
                                            <strong>Tipo de venta:</strong>
                                            <select name="tipoVenta" class="form-control">
                                                <option value="Factura" selected="true">Factura</option>
                                                <option value="Crédito">Crédito</option>
                                            </select>
                                        </div>


                                        <div class="col-md-6 col-lg-3">
                                            <p></p>
                                            <button type="submit" class="btn btn-outline-info btn-lg">
                                                <span class="fas fa-fw fa-shopping-cart"></span> Crear venta
                                            </button>
                                            <p></p>
                                            <h1 style="color:#009966; vertical-align: baseline; font-family: fantasy; ">
                                                <strong>$ ${orden.importeRedondeado}</strong> 
                                            </h1>
                                        </div>
                                    </div>
                                </div>
                            </form>
                            <hr>
                            <div class="form-group">
                                <br>
                                <div class="form-row text-center">
                                    <div class="col-md-6 col-lg-3">
                                        <form action="ventas" method="post">
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
                                        <form action="ventas" method="post">
                                            <input type="hidden" name="accion" value="addProducto">
                                            <strong>Seléccione un producto:</strong>
                                            <select name="idProd" class="form-control">
                                                <% for (Producto prod : listaProductos) {%>
                                                <option value="<%= prod.getIdProducto()%>"><%= prod.getNombre()%></option>
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


                            <div class="card-body">
                                <div class="table-responsive">
                                    <table class="table table-striped">
                                        <thead>

                                            <tr>
                                                <th>Código</th>
                                                <th>Producto</th>
                                                <th>Cantidad</th>
                                                <th>Precio Unitario</th>
                                                <th>Importe</th>
                                                <th></th>
                                            </tr>

                                        </thead>
                                        <tbody>
                                            <% for (DetalleOrdenes detalle : orden.getDetalle()) {%>
                                            <tr>
                                                <td><%= detalle.getProducto().getIdProducto()%></td>
                                                <td><%= detalle.getProducto().getNombre()%></td>
                                                <td><%= detalle.getCantidad()%></td>
                                                <td><%= detalle.getProducto().getPrecioCompra()%></td>
                                                <td><%= detalle.getImporte()%></td>

                                        <form action="ventas" method="post">
                                            <input type="hidden" name="accion" value="eliminarProducto">
                                            <input type="hidden" name="idProd" value="<%= detalle.getProducto().getIdProducto()%>">
                                            <input type="hidden" name="cantidad" value="<%= detalle.getCantidad()%>">
                                            <td style="width: 40px;">
                                                <button type="submit" class="btn btn-outline-danger">
                                                    <span class="fas fa-fw fa-trash"></span> Borrar
                                                </button>
                                            </td>
                                        </form>
                                        </tr>
                                        <%}%>
                                        </tbody>
                                    </table>

                                </div>
                            </div>


                            <div class="col-md-6 col-sm-6 col-lg-4">

                                <form action="ventas" method="post">
                                    <input type="hidden" name="accion" value="cancelarVenta">
                                    <button type="submit" class="btn btn-outline-danger btn-block">
                                        <span class="fas fa-fw fa-exclamation-circle"></span> Cancelar venta
                                    </button>
                                </form>
                                <br>
                            </div>


                        </div>
                    </div>

                    <br>
                    <br>
                    <!-- fin de data tables-->

                </div>
            </div>
        </div>


        <!-- /.container-fluid -->

        <!-- se incluye el footer-->
        <jsp:include page="../layouts/footer.jsp"></jsp:include>

    </body>
</html>


