<%-- 
    Document   : index
    Created on : 01-01-2019, 07:05:47 PM
    Author     : Cristhian menjivar

    Esta pagina index.jsp es para mostrar contenido de categorias y se ha incluido
    dentro de wen-inf/categorias porque es más seguro y solo se podria tener acceso a traves 
    de Servlets(Controladores)...
--%>

<%@page import="devs.com.sistema.ventas.modelos.Notificacion"%>
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
        List<Notificacion> listaNoti = (List<Notificacion>) request.getAttribute("notificaciones");

        //recurperar el mensaje
        String mensaje = "";
        if (request.getSession().getAttribute("operacionNoti") != null) {
            mensaje = (String) request.getSession().getAttribute("operacionNoti");
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
                    <% request.getSession().removeAttribute("operacionNoti");%>

                    <div class="alert alert-success alert-dismissible">
                        <button type="button" class="close" data-dismiss="alert">×</button>
                        <strong>Informe:</strong> <%= mensaje%>
                    </div>
                    <% }%>

                    <!-- DataTables Example -->
                    <div class="card mb-3">
                        <div class="card-header">
                            <i class="fas fa-table"></i>
                            Lista de actividades
                        </div>
                        <div class="card-body">
                            <div class="table-responsive">
                                <table class="table table-hover">
                                    <thead style="background-color: #007bff; color: white;">
                                        <tr>
                                            <th>Actividad</th>
                                            <th>fecha</th>
                                            <th>Estado</th>
                                            <th></th>
                                            <th></th>
                                            <th></th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <% for (Notificacion n : listaNoti) {%>
                                        <tr>
                                            <td><%= n.getNombreNoti()%></td>
                                            <td><%= n.getFechaNoti()%></td>
                                            <td><% if (n.getVisto() == 1) {
                                                    out.print("Activa");
                                                } else {
                                                    out.print("Desactivada");
                                                }%></td>
                                            <td style="width: 40px;">
                                                <a href="notificaciones?accion=mostrar&idNoti=<%= n.getIdNotificacion()%>" class="btn btn-info btn-sm">Ver actividad
                                                </a>
                                            </td>
                                            <td style="width: 40px;">
                                                <a href="notificaciones?accion=editar&idNoti=<%= n.getIdNotificacion()%>" class="btn btn-primary btn-sm">Editar
                                                </a>
                                            </td>
                                            <td style="width: 40px;">
                                                <form action="notificaciones" method="post">
                                                    <input type="hidden" name="accion" value="borrar">
                                                    <input type="hidden" name="idNoti" value="<%= n.getIdNotificacion()%>">
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
                                <a href="notificaciones?accion=nuevo" class="btn btn-info col-xs-12 col-sm-12  col-md-6 col-lg-3"><span class="fas fa-fw fa-plus-circle"></span> Nueva actividad</a>

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
