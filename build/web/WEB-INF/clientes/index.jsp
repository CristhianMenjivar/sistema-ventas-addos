<%-- 
    Document   : index
    Created on : 01-01-2019, 07:05:47 PM
    Author     : Cristhian menjivar

    Esta pagina index.jsp es para mostrar contenido de categorias y se ha incluido
    dentro de wen-inf/categorias porque es más seguro y solo se podria tener acceso a traves 
    de Servlets(Controladores)...
--%>

<%@page import="devs.com.sistema.ventas.modelos.Cliente"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>clientes</title>
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
        List<Cliente> listaClientes = (List<Cliente>) request.getAttribute("clientes");

        //recurperar el mensaje
        String mensaje = "";
        if (request.getSession().getAttribute("operacionCliente") != null) {
            mensaje = (String) request.getSession().getAttribute("operacionCliente");
        }
    %>



    <body id="page-top">
        <!-- se incluye la barra de navigacion de heading -->
        <jsp:include page="../layouts/header.jsp"></jsp:include>

            <div id="wrapper">

                <!-- se incluye el nav bar de menu-->
            <jsp:include page="../layouts/sliderbar.jsp"></jsp:include>

                <div id="content-wrapper">

                    <div class="container-fluid">

                    <%--vamos a usar un alert de boostrap para el mensaje--%>
                    <% if (!mensaje.isEmpty()) {%>
                    <% request.getSession().removeAttribute("operacionCliente");%>

                    <div class="alert alert-success alert-dismissible">
                        <button type="button" class="close" data-dismiss="alert">×</button>
                        <strong>Informe:</strong> <%= mensaje%>
                    </div>
                    <% }%>

                    <!-- DataTables Example -->
                    <div class="card mb-3">
                        <div class="card-header">
                            <i class="fas fa-table"></i>
                            Lista de clientes
                        </div>
                        <div class="card-body">
                            <div class="table-responsive">
                                <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                                    <thead style="background-color: #007bff; color: white;">
                                        <tr>
                                            <th>Clave</th>
                                            <th>Nombre</th>
                                            <th>Apellido</th>
                                            <th>Dirección</th>
                                            <th>Teléfono</th>
                                            <th>Rut Cliente</th>
                                            <th>Número Factura</th>
                                            <th>Tipo de Factura</th>
                                            <th>Estado Factura</th>
                                            <th>Total</th>
                                            <th>Forma de Pago</th>
                                            <th>Número Documento</th>
                                            <th>Banco</th>
                                            <th>Fecha Documento</th>
                                            <th>Monto</th>
                                            <th></th>
                                            <th></th>
                                            <th></th>
                                        </tr>
                                    </thead>
                                    <tfoot>
                                        <tr>
                                            <th>Clave</th>
                                            <th>Nombre</th>
                                            <th>Apellido</th>
                                            <th>Dirección</th>
                                            <th>Telèfono</th>
                                            <th>Rut Cliente</th>
                                            <th>Número Factura</th>
                                            <th>Tipo de Factura</th>
                                            <th>Estado Factura</th>
                                            <th>Total</th>
                                            <th>Forma de Pago</th>
                                            <th>Número Documento</th>
                                            <th>Banco</th>
                                            <th>Fecha Documento</th>
                                            <th>Monto</th>
                                            <th></th>
                                            <th></th>
                                            <th></th>
                                        </tr>
                                    </tfoot>
                                    <tbody>
                                        <% for (Cliente c : listaClientes) {%>
                                        <tr>
                                            <td><%= c.getIdCliente()%></td>
                                            <td><%= c.getNombre()%></td>
                                            <td><%= c.getApellido()%></td>
                                            <td><%= c.getDireccion()%></td>
                                            <td><%= c.getTelefono()%></td>
                                            <td><%= c.getRut()%></td>
                                            <td><%= c.getNumfactura()%></td>
                                            <td><%= c.getTipofactura()%></td>
                                            <td><%= c.getEstadofactura()%></td>
                                            <td><%= c.getTotal()%></td>
                                            <td><%= c.getFormapago()%></td>
                                            <td><%= c.getNumdocumento()%></td>
                                            <td><%= c.getBanco()%></td>
                                            <td><%= c.getFecha()%></td>
                                            <td><%= c.getMonto()%></td>
                                            <td style="width: 40px;">
                                                <a href="clientes?accion=editar&idCliente=<%= c.getIdCliente()%>" class="btn btn-primary btn-sm">Editar
                                                </a>
                                            </td>
                                            <td style="width: 40px;">
                                                <form action="clientes" method="post">
                                                    <input type="hidden" name="accion" value="borrar">
                                                    <input type="hidden" name="idCliente" value="<%= c.getIdCliente()%>">
                                                    <input type="submit" class='btn btn-danger btn-sm' value="borrar">
                                                </form>
                                            </td>
                                            <td style="width: 40px;">
                                                <form action="Cliente.jsp" method="post" target="black">
                                                    <input type="hidden" name="idCliente" value="<%= c.getIdCliente()%>">
                                                    <input type="submit" class='btn btn-success btn-sm' value="Factura">
                                                </form>
                                            </td>
                                        </tr>
                                        <% }%>
                                    </tbody>
                                </table>
                            </div>
                            <br>
                            <hr>
                            <div class="container-fluid">
                                <a href="clientes?accion=nuevo" class="btn btn-info col-xs-12 col-sm-12  col-md-6 col-lg-3"><span class="fas fa-fw fa-plus-circle"></span> Nuevo cliente</a>

                            </div>
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
