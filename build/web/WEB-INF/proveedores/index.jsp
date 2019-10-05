<%-- 
    Document   : index
    Created on : 01-01-2019, 07:05:47 PM
    Author     : Cristhian menjivar

    Esta pagina index.jsp es para mostrar contenido de categorias y se ha incluido
    dentro de wen-inf/categorias porque es más seguro y solo se podria tener acceso a traves 
    de Servlets(Controladores)...
--%>

<%@page import="java.text.SimpleDateFormat"%>
<%@page import="devs.com.sistema.ventas.modelos.Proveedor"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Proveedores</title>
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
        List<Proveedor> listaProveedores = (List<Proveedor>) request.getAttribute("proveedores");

        //recurperar el mensaje
        String mensaje = "";
        if (request.getSession().getAttribute("operacionProveedor") != null) {
            mensaje = (String) request.getSession().getAttribute("operacionProveedor");
        }


    %>



    <body id="page-top">
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
                    <% request.getSession().removeAttribute("operacionProveedor"); %>
                    <% }%>



                    <!-- DataTables Example -->
                    <div class="card mb-3">
                        <div class="card-header">
                            <i class="fas fa-table"></i>
                            Lista de proveedores
                        </div>
                        <div class="card-body">
                            <div class="table-responsive">
                                <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                                    <thead style="background-color: #007bff; color: white">
                                        <tr>
                                            <th>Clave</th>
                                            <th>Emitido Por</th>
                                            <th>Moneda</th>
                                            <th>Tipo Documento</th>
                                            <th>Fecha</th>
                                            <th>Periodo Declarado</th>
                                            <th>Comentario</th>
                                            <th>Mtoaf</th>
                                            <th>Mtoe</th>
                                            <th>Iva</th>
                                            <th>Total</th>
                                            <th></th>
                                            <th></th>
                                        </tr>
                                    </thead>
                                    <tfoot>
                                        <tr>
                                            <th>Clave</th>
                                            <th>Emitido Por</th>
                                            <th>Moneda</th>
                                            <th>Tipo Documento</th>
                                            <th>Fecha</th>
                                            <th>Periodo Declarado</th>
                                            <th>Comentario</th>
                                            <th>Mtoaf</th>
                                            <th>Mtoe</th>
                                            <th>Iva</th>
                                            <th>Total</th>
                                            <th></th>
                                            <th></th>
                                        </tr>
                                    </tfoot>
                                    <tbody>
                                        <% for (Proveedor p : listaProveedores) {%>
                                        <tr>
                                            <td><%= p.getIdProveedor()%></td>
                                            <td><%= p.getEmitidopor()%></td>
                                            <td><%= p.getMoneda()%></td>
                                            <td><%= p.getTipodocumento()%></td>
                                                <td><%= new SimpleDateFormat("dd-MM-yyyy").format(p.getFecha())%></td>
                                            <td><%= p.getPeriododeclarado()%></td>
                                            <td><%= p.getComentario()%></td>
                                            <td><%= p.getMtoaf()%></td>
                                            <td><%= p.getMtoe()%></td>
                                            <td><%= p.getIva()%></td>
                                            <td><%= p.getTotal()%></td>

                                            <td style="width: 40px;">
                                                <a href="proveedores?accion=editar&idProv=<%= p.getIdProveedor()%>" class="btn btn-primary btn-sm">Editar
                                                </a>
                                            </td>
                                            <td style="width: 40px;">
                                                <form action="proveedores" method="post">
                                                    <input type="hidden" name="accion" value="borrar">
                                                    <input type="hidden" name="idProv" value="<%= p.getIdProveedor()%>">
                                                    <input type="submit" class='btn btn-danger btn-sm' value="borrar">
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
                                <a href="proveedores?accion=nuevo" class="btn btn-info col-xs-12 col-sm-12  col-md-6 col-lg-3"><span class="fas fa-fw fa-plus-circle"></span> Nuevo proveedor</a>

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
