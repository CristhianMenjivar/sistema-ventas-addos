<%@page import="devs.com.sistema.ventas.modelos.Orden"%>
<%@page import="java.util.List"%>
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
        //recibimos la lista y l aparciamos
        List<Orden> listaVenta = (List<Orden>) request.getAttribute("ordenes");
     %>

    <body id="page-top">

        <jsp:useBean class="devs.com.sistema.ventas.modelos.Orden" id="ordenCreada" scope="session"></jsp:useBean>
            <!-- se incluye la barra de navigacion de heading -->
        <jsp:include page="../layouts/header.jsp"></jsp:include>

            <div id="wrapper">

                <!-- se incluye el nav bar de menu-->
            <jsp:include page="../layouts/sliderbar.jsp"></jsp:include>

                <div id="content-wrapper">

                    <div class="container-fluid">

                    <%-- mensaje de la orden creada--%>
                    <% if (ordenCreada.getOrdenId() != 0) {%>
                    <div class="alert alert-success alert-dismissible">
                        <button type="button" class="close" data-dismiss="alert">×</button>
                        <strong>Informe:</strong> Se ha creado la venta con folio ${ordenCreada.getOrdenId()}
                         <hr>
                        <a href="ventas?accion=verDetalle&idPedido=${ordenCreada.ordenId}" class="btn btn-outline-success"><span class="fas fa-fw fa-list"></span> Ver detalles</a>
                    </div>
                    <% request.getSession().removeAttribute("ordenCreada");
                        request.getSession().removeAttribute("orden");%>
                    <% }%>

                    <!-- DataTables Example -->
                    <div class="card mb-3">
                        <div class="card-header">
                            <i class="fas fa-table"></i>
                            Lista de ventas
                        </div>
                        <div class="card-body">
                            <div class="table-responsive">
                                <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                                    <thead style="background-color: #007bff; color: white;">
                                        <tr>
                                            <th>ID</th>
                                            <th>Fecha pedido</th>
                                            <th>Monto</th>
                                            <th>Tipo venta</th>
                                            <th>Tipo de pago</th>
                                            <th>Acciones</th>
                                        </tr>
                                    </thead>
                                    <tfoot>
                                        <tr>
                                            <th>ID</th>
                                            <th>Fecha pedido</th>
                                            <th>Monto</th>
                                            <th>Tipo venta</th>
                                            <th>Tipo de pago</th>
                                            <th>Acciones</th>

                                        </tr>
                                    </tfoot>
                                    <tbody>
                                            <% for (Orden orden : listaVenta) {%>
                                            <tr>
                                                <td><%= orden.getOrdenId() %></td>
                                                <td><%= orden.getFecha()%></td>
                                                <td>$ <%= orden.getMonto()%></td>
                                                <td><%= orden.getTipoVenta()%></td>
                                                <td><%= orden.getTipoPago() %></td>
                                                <td style="width: 80px;"><a href="ventas?accion=verDetalle&idPedido=<%= orden.getOrdenId() %>" class="btn btn-success btn-sm">Ver detalles</a></td>
                                            </tr>
                                        <%}%>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>

                </div>
            </div>


            <!-- /.container-fluid -->

            <!-- se incluye el footer-->
            <jsp:include page="../layouts/footer.jsp"></jsp:include>

    </body>
</html>

