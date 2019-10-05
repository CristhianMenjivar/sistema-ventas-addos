
<%@page import="devs.com.sistema.ventas.modelos.DetalleCredito"%>
<%@page import="devs.com.sistema.ventas.modelos.Credito"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>créditos</title>
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
        String mensajeForm = "";
        String mensajeTabla = "";
        Credito credito = null;
        if (request.getSession().getAttribute("credito") != null) {
            credito = (Credito) request.getSession().getAttribute("credito");
        }

        if (request.getSession().getAttribute("mensajeForm") != null) {
            mensajeForm = (String) request.getSession().getAttribute("mensajeForm");
        }

        if (request.getSession().getAttribute("mensajeTabla") != null) {
            mensajeTabla = (String) request.getSession().getAttribute("mensajeTabla");
        }
    %>

    <body id="page-top" style="background-color: #666666">

        <jsp:useBean class="devs.com.sistema.ventas.modelos.Orden" id="ordenCreada" scope="session"></jsp:useBean>
            <!-- se incluye la barra de navigacion de heading -->
        <jsp:include page="../layouts/header.jsp"></jsp:include>

            <div id="wrapper">

                <!-- se incluye el nav bar de menu-->
            <jsp:include page="../layouts/sliderbar.jsp"></jsp:include>

                <div id="content-wrapper">

                    <div class="container-fluid">

                        <!-- DataTables Example card mb-3  -->
                        <div class="card card-producto mx-4 mt-4">
                            <div class="card-header text-center" style="background-color: #38cc82; color: #fff"><span class="fas fa-fw fa-shopping-cart"></span><strong> CRÉDITO: <%= credito.getIdCredito()%></strong></div>

                        <div class="container-fluid">

                            <div class="card-body">

                                <br>

                                <div class="row">
                                    <div class="col-md-3">
                                        <p>Cliente : <strong><%= credito.getCliente().getNombre()%> <%= credito.getCliente().getApellido()%></strong> </p>
                                    </div>
                                    <div class="col-md-3">
                                        <p>Fecha del crédito :<strong> <%= credito.getFecha()%></strong></p>
                                    </div>
                                    <div class="col-md-3">
                                        <p>Crédito : <strong> $ <%= credito.getMontoCredito()%></strong></p>
                                    </div>
                                    <div class="col-md-3">
                                        <p>Pendiente : <strong> $ <%= credito.getDeuda()%></strong></p>
                                    </div>
                                </div>

                                <hr>
                                <br>

                                <div class="row">
                                    <div class="col-md-6">

                                        <h6>   Pagar cuotas:</h6>
                                        <br>

                                        <%--vamos a usar un alert de boostrap para el mensaje--%>
                                        <% if (!mensajeForm.isEmpty()) {%>
                                        <% request.getSession().removeAttribute("mensajeForm");%>

                                        <div class="alert alert-info alert-dismissible">
                                            <button type="button" class="close" data-dismiss="alert">×</button>
                                            <strong>Informe:</strong> <%= mensajeForm%>
                                        </div>
                                        <% }%>

                                        <div class="container-fluid" style="background-color: #666666; color: #fff">

                                            <br>

                                            <form action="creditos" method="POST">
                                                <input type="hidden" name="accion" value="addDetalleCredito">
                                                <input type="hidden" name="idcredito" value="<%= credito.getIdCredito()%>">
                                                <div class="form-group">
                                                    <div class="form-row">
                                                        <label>ID Crédito</label>
                                                        <input type="text"  class="form-control" value="<%= credito.getIdCredito()%>" disabled="">
                                                    </div>
                                                    <br>
                                                    <div class="form-row">
                                                        <label>fecha</label>
                                                        <input type="date" name="fecha" class="form-control" value="<%= new java.sql.Date(new java.util.Date().getTime())%>">
                                                    </div>
                                                    <br>
                                                    <div class="form-row">
                                                        <label>Total a pagar:</label>
                                                        <input type="text" name="cuota" class="form-control" placeholder="$ 0.00">
                                                    </div>
                                                    <br>
                                                    <div class="form-row">
                                                        <button type="submit" class="btn btn-primary btn-block btn-outline-warning" ><span class="fas fa-fw fa-money-bill"></span> Pagar cuota</button>
                                                    </div>
                                                    <br>
                                                </div>

                                            </form>
                                        </div>

                                        <div class="row">
                                            <div class="col-md-6">
                                                <a href="creditos" class="btn btn-success"><span class="fas fa-fw fa-angle-double-left"></span> Atras</a>
                                            </div>

                                        </div>


                                    </div>

                                    <br>
                                    <div class="col-md-6">
                                        <h6>Pagos realizados:</h6>
                                        <br>

                                        <%--vamos a usar un alert de boostrap para el mensaje--%>
                                        <% if (!mensajeTabla.isEmpty()) {%>
                                        <% request.getSession().removeAttribute("mensajeTabla");%>

                                        <div class="alert alert-success alert-dismissible">
                                            <button type="button" class="close" data-dismiss="alert">×</button>
                                            <strong>Informe:</strong> <%= mensajeTabla%>
                                        </div>
                                        <% }%>

                                        <div class="table-responsive-lg table-responsive-md table-responsive-sm">
                                            <table class="table table-hover">
                                                <thead>
                                                    <tr>
                                                        <th>fecha</th>
                                                        <th>Pago</th>
                                                        <th></th>
                                                    </tr>
                                                </thead>
                                                <tbody>
                                                    <% for (DetalleCredito detalle : credito.getListaDetalles()) {%>
                                                    <tr>
                                                        <td><%= detalle.getFecha()%></td>
                                                        <td><%= detalle.getCuota()%></td>
                                                        <td style="width: 40px;">
                                                            <form action="creditos" method="POST">
                                                                <input type="hidden" name="accion" value="eliminarDetalle">
                                                                <input type="hidden" name="idDetalle" value="<%= detalle.getIdDetalle()%>">
                                                                <input type="hidden" name="idcredito" value="<%= detalle.getIdCredito()%>">
                                                                <input type="submit" class="btn btn-danger btn-sm" value="Eliminar">
                                                            </form>
                                                        </td>
                                                    </tr>
                                                    <% }%>
                                                </tbody>
                                            </table>

                                        </div>


                                    </div>

                                </div>



                            </div>


                        </div>



                    </div>
                </div>

                <br>
                <br>
                <!-- fin de data tables-->

            </div>
        </div>


        <!-- /.container-fluid -->

        <!-- se incluye el footer-->
        <jsp:include page="../layouts/footer.jsp"></jsp:include>

    </body>
</html>
