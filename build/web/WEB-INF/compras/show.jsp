<%-- 
    Document   : show
    Created on : ene 21, 2019, 10:02:33 p.m.
    Author     : usuario
--%>
<%@page import="devs.com.sistema.ventas.modelos.DetalleCompra"%>
<%@page import="devs.com.sistema.ventas.modelos.Compra"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>ver compra</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <!-- Bootstrap core CSS-->
        <link href="<%= request.getContextPath()%>/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

        <!-- Custom fonts for this template-->
        <link href="<%= request.getContextPath()%>/vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">

        <!-- Page level plugin CSS-->
        <link href="<%= request.getContextPath()%>/vendor/datatables/dataTables.bootstrap4.css" rel="stylesheet">

        <!-- Custom styles for this template-->
        <link href="<%= request.getContextPath()%>/css/sb-admin.css" rel="stylesheet">
    </head>
    <body id="page-top" style="background-color: #666666">
        <!-- se incluye la barra de navigacion de heading -->
        <jsp:include page="../layouts/header.jsp"></jsp:include>

            <div id="wrapper">

                <!-- se incluye el nav bar de menu-->
            <jsp:include page="../layouts/sliderbar.jsp"></jsp:include>
            
            <% Compra compra = (Compra) request.getAttribute("compra");%>

            <div id="content-wrapper">

                <div class="container-fluid">
                    <div class="card card-producto mx-auto mt-auto">
                        <div class="card-header text-center" style="background-color: #38cc82; color: #fff"><span class="fas fa-fw fa-shopping-cart"></span><strong> COMPRA N° ${compra.compraId}</strong></div>
                        <div class="container-fluid">

                            <div class="form-group">
                                <br>
                                <div class="form-row text-center">
                                    <div class="col-md-6 col-lg-3">
                                        <strong>fecha dela compra</strong>
                                        <p><%= new SimpleDateFormat("dd/MM/yyyy").format(compra.getFecha())%></p>
                                    </div>

                                    <div class="col-md-6 col-lg-3">
                                        <strong>Total:</strong>
                                        <h2 style="color:#009966; vertical-align: baseline; font-family: fantasy; ">
                                            <strong>$ <%=compra.getMonto() %></strong> 
                                        </h2>
                                    </div>
                                </div>
                            </div>


                            <div class="card-body">
                                <div class="table-responsive">
                                    <table class="table table-striped">
                                        <thead>
                                            <tr>
                                                <th>Producto</th>
                                                <th>Cantidad</th>
                                                <th>Precio Unitario</th>
                                                <th>Importe</th>
                                            </tr>

                                        </thead>
                                        <tbody>
                                            <% for (DetalleCompra detalle : compra.getDetalle()) { %>
                                            <tr>
                                                <td><%=detalle.getProducto().getNombre() %></td>
                                                <td><%=detalle.getCantidad() %></td>
                                                <td><%=detalle.getProducto().getPrecioCompra()%></td>
                                                <td><%=detalle.getImporte() %></td>
                                            </tr>
                                            <%}%>
                                        </tbody>
                                    </table>
                                </div>
                                <br>
                                <div class="text-center">
                                    <div class="col-sm-12 col-md-12 col-auto-12">
                                        <form action="#" method="post">
                                            <a href="compras" class="btn btn-success"><span class="fas fa-fw fa-angle-double-left"></span> Regresar</a>
                                        </form>
                                    </div>
                                </div>
                            </div>

                        </div>
                    </div>
                    <br>
                    <br>

                </div>  

            </div>    

        </div>


        <%--En este apartado va colocado el footer y las librerias de jQuery--%>
        <jsp:include page="../layouts/footer.jsp"></jsp:include>

    </body>
</html>