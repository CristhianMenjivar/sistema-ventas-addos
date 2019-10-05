<%-- 
    Document   : index
    Created on : 01-01-2019, 07:05:47 PM
    Author     : Cristhian menjivar

    Esta pagina index.jsp es para mostrar contenido de categorias y se ha incluido
    dentro de wen-inf/categorias porque es más seguro y solo se podria tener acceso a traves 
    de Servlets(Controladores)...
--%>

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
        //recibimos el tipo form
        String tipoForm = (String) request.getAttribute("tipoForm");
        Proveedor proveedor = null;
        if (tipoForm.equalsIgnoreCase("actualizar")) {
            proveedor = (Proveedor) request.getAttribute("proveedor");
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
                        <div class="card-header" style="background-color: #38cc82; color: #fff"><%= tipoForm%> proveedor</div>
                    <div class="card-body">
                        <form action="proveedores" class="form-horizontal" role="form" method="post">

                            <div class="form-group">
                                <%--este input esta oculto para las acciones crear o actualizar--%>
                                <input type="hidden" name="accion" value="<%= tipoForm%>">
                                <% if (tipoForm.equalsIgnoreCase("actualizar")) {%>
                                <input type="hidden" name="idProv" value="<%= proveedor.getIdProveedor()%>">
                                <% } %>
                                <div class="col-sm-12">
                                    <input type="number" class="form-control" name="id" placeholder="Clave..." 
                                           required="" disabled="true"
                                           value="<% if (tipoForm.equalsIgnoreCase("actualizar")) {
                                                        out.print(proveedor.getIdProveedor());
                                                    } %>">
                                </div>
                            </div>

                            <div class="form-group">
                                <div class="col-sm-12">
                                    <input type="text" class="form-control" name="emitidopor" placeholder="Emitodo por..."
                                           required="" value="<% if (tipoForm.equalsIgnoreCase("actualizar")) {
                                                   out.print(proveedor.getEmitidopor());
                                               }%>">
                                </div>
                            </div>

                            <div class="form-group">
                                <div class="col-sm-12">
                                    <input type="text" class="form-control" name="moneda" placeholder="Moneda..."
                                           required="" value="<% if (tipoForm.equalsIgnoreCase("actualizar")) {
                                                   out.print(proveedor.getMoneda());
                                               }%>">
                                </div>
                            </div>

                            <div class="form-group">
                                <div class="col-sm-12">
                                    <input type="text" class="form-control" name="tipodocumento" placeholder="Tipo Documento..."
                                           value="<% if (tipoForm.equalsIgnoreCase("actualizar")) {
                                                   out.print(proveedor.getTipodocumento());
                                               }%>">
                                </div>
                            </div>

                            <div class="form-group">
                                <div class="col-sm-12">
                                    <input type="date" class="form-control" name="fecha"
                                           value="<% if (tipoForm.equalsIgnoreCase("actualizar")) {
                                                   out.print(proveedor.getFecha());
                                               }%>">
                                </div>
                            </div> 

                            <div class="form-group">
                                <div class="col-sm-12">
                                    <input type="text" class="form-control" name="periododeclarado" placeholder="Periodo Declarado..."
                                           value="<% if (tipoForm.equalsIgnoreCase("actualizar")) {
                                                   out.print(proveedor.getPeriododeclarado());
                                               }%>">
                                </div>
                            </div>

                            <div class="form-group">
                                <div class="col-sm-12">
                                    <input type="text" class="form-control" name="comentario" placeholder="Comentario..."
                                           value="<% if (tipoForm.equalsIgnoreCase("actualizar")) {
                                                   out.print(proveedor.getComentario());
                                               }%>">
                                </div>
                            </div> 

                            <div class="form-group">
                                <div class="col-sm-12">
                                    <input type="text" class="form-control" name="mtoaf" placeholder="Mtoaf..."
                                           value="<% if (tipoForm.equalsIgnoreCase("actualizar")) {
                                                   out.print(proveedor.getMtoaf());
                                               }%>">
                                </div>
                            </div> 

                            <div class="form-group">
                                <div class="col-sm-12">
                                    <input type="text" class="form-control" name="mtoe" placeholder="Mtoe..."
                                           value="<% if (tipoForm.equalsIgnoreCase("actualizar")) {
                                                   out.print(proveedor.getMtoe());
                                               }%>">
                                </div>
                            </div>  

                            <div class="form-group">
                                <div class="col-sm-12">
                                    <input type="text" class="form-control" name="iva" placeholder="Iva..."
                                           value="<% if (tipoForm.equalsIgnoreCase("actualizar")) {
                                                   out.print(proveedor.getIva());
                                               }%>">
                                </div>
                            </div> 

                            <div class="form-group">
                                <div class="col-sm-12">
                                    <input type="text" class="form-control" name="total" placeholder="Total..."
                                           value="<% if (tipoForm.equalsIgnoreCase("actualizar")) {
                                                   out.print(proveedor.getTotal());
                                               }%>">
                                </div>
                            </div>     
                            <div class="text-center">
                                <div class="col-sm-12 col-md-12 col-auto-12">
                                    <a href="proveedores" class="btn btn-success"><span class="fas fa-fw fa-angle-double-left"></span> Atras</a>

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

