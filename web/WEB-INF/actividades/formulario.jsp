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
        <title>actividad</title>
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
        //recibimos el tipo form
        String tipoForm = (String) request.getAttribute("tipoForm");
        Notificacion noti = null;
        if (tipoForm.equalsIgnoreCase("actualizar")) {
            noti = (Notificacion) request.getAttribute("notificacion");
        }
    %>
    <body id="page-top" style="background-color: #666666">
        <!-- se incluye la barra de navigacion de heading -->
        <jsp:include page="../layouts/header.jsp"></jsp:include>

            <div id="wrapper">

                <!-- se incluye el nav bar de menu-->
            <jsp:include page="../layouts/sliderbar.jsp"></jsp:include>

                <div class="container">
                    <div class="card card-register mx-auto mt-5">
                        <div class="card-header" style="background-color: #38cc82; color: #fff"><%= tipoForm%> actividad</div>
                    <div class="card-body">
                        <form action="notificaciones" class="form-horizontal" role="form" method="post">

                            <div class="form-group">
                                <%--este input esta oculto para las acciones crear o actualizar--%>
                                <input type="hidden" name="accion" value="<%= tipoForm%>">
                                <% if (tipoForm.equalsIgnoreCase("actualizar")) {%>
                                <input type="hidden" name="idActualizar" value="<%= noti.getIdNotificacion()%>">
                                <% } %>
                            </div>

                            <div class="form-group">
                                <div class="col-sm-12">
                                    <label for="nombre">Titulo:</label>
                                    <input type="text" class="form-control" name="nombre" placeholder="Titulo..."
                                           required="" minlength="4" maxlength="20" value="<% if (tipoForm.equalsIgnoreCase("actualizar")) {
                                                   out.print(noti.getNombreNoti());
                                               }%>">
                                </div>
                            </div>

                            <div class="form-group">
                                <div class="col-sm-12">
                                    <label for="asunto">Asunto</label>
                                    <textarea name="asunto" rows="5" class="form-control" required="" maxlength="700">
                                        <% if (tipoForm.equalsIgnoreCase("actualizar")) {
                                                out.print(noti.getAsuntoNoti());
                                            }%>
                                    </textarea>
                                </div>
                            </div>

                            <div class="form-group text-left">
                                <div class="col-md-6">
                                    <label for="fecha">Fecha:</label>
                                    <input type="date"  name="fecha" class="form-control"  required="required"
                                           value="<% if (tipoForm.equalsIgnoreCase("actualizar")) {
                                                   out.print(noti.getFechaNoti());
                                               } else {
                                                   out.print(new java.sql.Date(new java.util.Date().getTime()));
                                               }%>">
                                    <p></p>
                                </div>
                                <div class="col-md-6">
                                    <div class="custom-control custom-checkbox">
                                        <input type="checkbox" class="custom-control-input" id="Nvisto" name="visto"  
                                               <% if (tipoForm.equals("actualizar") && noti.getVisto() == 1) {
                                                       out.print("checked");
                                                   }
                                               %> >
                                        <label class="custom-control-label" for="Nvisto">Actividad activa?</label>
                                    </div>
                                </div>

                            </div>
                            <br>

                            <div class="text-center">
                                <div class="col-sm-12 col-md-12 col-auto-12">
                                    <a href="notificaciones" class="btn btn-success"><span class="fas fa-fw fa-angle-double-left"></span> Atras</a>

                                    <button type="submit" class="btn btn-primary" ><span class="fas fa-fw fa-check-circle"></span> <%= tipoForm%></button>
                                </div>
                            </div>

                        </form>
                    </div>
                </div>
                <br><br><br><br>
            </div>
        </div>
        <!-- /.container-fluid -->

        <!-- se incluye el footer-->
        <jsp:include page="../layouts/footer.jsp"></jsp:include>

    </body>
</html>

