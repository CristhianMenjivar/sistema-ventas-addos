<%-- 
    Document   : index
    Created on : 01-01-2019, 07:05:47 PM
    Author     : Cristhian menjivar

    Esta pagina index.jsp es para mostrar contenido de categorias y se ha incluido
    dentro de wen-inf/categorias porque es más seguro y solo se podria tener acceso a traves 
    de Servlets(Controladores)...
--%>

<%@page import="devs.com.sistema.ventas.modelos.Boleta"%>
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
        //recibimos el tipo form
        String tipoForm = (String) request.getAttribute("tipoForm");
        Boleta bole = null;
        if (tipoForm.equalsIgnoreCase("actualizar")) {
            bole = (Boleta) request.getAttribute("boleta");
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
                        <div class="card-header" style="background-color: #38cc82; color: #fff"><%= tipoForm%> boletas</div>
                    <div class="card-body">
                        <form action="boleta" class="form-horizontal" role="form" method="post">

                            <div class="form-group">
                                <%--este input esta oculto para las acciones crear o actualizar--%>
                                <input type="hidden" name="accion" value="<%= tipoForm%>">
                                <% if (tipoForm.equalsIgnoreCase("actualizar")) {%>
                                <input type="hidden" name="idActualizar" value="<%= bole.getIdboleta()%>">
                                <% } %>
                                <div class="col-sm-12">
                                    <input type="number" class="form-control" name="idBoleta" placeholder="Clave..." 
                                           required="" disabled="true"
                                           value="<% if (tipoForm.equalsIgnoreCase("actualizar")) {
                                                        out.print(bole.getIdboleta());
                                                    } %>">
                                </div>
                            </div>


                            <div class="form-group">
                                <div class="col-sm-12">
                                    <input type="Text" class="form-control" name="boleta" placeholder="Factura..."
                                           value="<% if (tipoForm.equalsIgnoreCase("actualizar")) {
                                                   out.print(bole.getBoleta());
                                               }%>">
                                </div>
                            </div>  

                            <div class="form-group">
                                <div class="col-sm-12">
                                    <select type="text" class="form-control" name="tipoboleta"
                                            <option value="Manual">Manual</option> 
                                        <option value="Manual">Manual</option>
                                        <option value="Automatica">Automatica</option>
                                        value="<% if (tipoForm.equalsIgnoreCase("actualizar")) {
                                                   out.print(bole.getTipoboleta());
                                               }%>"
                                    </select>
                                </div>
                            </div> 

                            <div class="form-group">
                                <div class="col-sm-12">
                                    <select type="text" class="form-control" name="estadoboleta"
                                            <option value="Cancelado">Cancelado</option> 
                                        <option value="Cancelado">Cancelado</option>
                                        <option value="Pendiente">Pendiente</option>
                                        value="<% if (tipoForm.equalsIgnoreCase("actualizar")) {
                                                   out.print(bole.getEstadoboleta());
                                               }%>"
                                    </select>
                                </div>
                            </div> 

                            <div class="form-group">
                                <div class="col-sm-12">
                                    <input type="text" class="form-control" name="total" placeholder="Total..."
                                           value="<% if (tipoForm.equalsIgnoreCase("actualizar")) {
                                                   out.print(bole.getTotal());
                                               }%>">
                                </div>
                            </div>   

                            <div class="form-group">
                                <div class="col-sm-12">
                                    <select type="text" class="form-control" name="formapago"
                                        <option value="Efectivo">Efectivo</option> 
                                        <option value="Efectivo">Efectivo</option>
                                        <option value="Debito">Debito</option>
                                        <option value="Credito">Credito</option>
                                        <option value="Cheque">Cheque</option>
                                        <option value="Transferencia">Transferencia</option>
                                        value="<% if (tipoForm.equalsIgnoreCase("actualizar")) {
                                                out.print(bole.getFormapago());
                                            }%>"
                                    </select>
                                </div>
                            </div>    

                            <div class="form-group">
                                <div class="col-sm-12">
                                    <input type="text" class="form-control" name="numdocumento" placeholder="Número Documento..."
                                           value="<% if (tipoForm.equalsIgnoreCase("actualizar")) {
                                                   out.print(bole.getNumdocumento());
                                               }%>">
                                </div>
                            </div>    

                            <div class="form-group">
                                <div class="col-sm-12">
                                    <input type="text" class="form-control" name="banco" placeholder="Banco..."
                                           value="<% if (tipoForm.equalsIgnoreCase("actualizar")) {
                                                   out.print(bole.getBanco());
                                               }%>">
                                </div>
                            </div>     

                            <div class="form-group">
                                <div class="col-sm-12">
                                    <input type="date" class="form-control" name="fecha"
                                           value="<% if (tipoForm.equalsIgnoreCase("actualizar")) {
                                                   out.print(bole.getFecha());
                                               }%>">
                                </div>
                            </div>

                            <div class="form-group">
                                <div class="col-sm-12">
                                    <input type="text" class="form-control" name="monto" placeholder="Valor a Pagar..."
                                           value="<% if (tipoForm.equalsIgnoreCase("actualizar")) {
                                                   out.print(bole.getMonto());
                                               }%>">
                                </div>
                            </div>    

                            <div class="text-center">
                                <div class="col-sm-12 col-md-12 col-auto-12">
                                    <a href="boleta" class="btn btn-success"><span class="fas fa-fw fa-angle-double-left"></span> Atras</a>

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

