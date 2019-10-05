<%-- 
    Document   : show
    Created on : ene 21, 2019, 10:02:33 p.m.
    Author     : usuario
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!-- comentarios
-->
<html lang="en">
    <head>
      <title>mostrar</title>
      
       <meta charset="utf-8">
       <meta name="viewport" content="width=device-width, initial-scale=1">
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

            <div id="content-wrapper">

                <div class="container-fluid">
                    <div class="card card-producto mx-5 mt-5">
                        <div class="card-header text-center" style="background-color: #38cc82; color: #fff"><span class="fas fa-fw fa-calendar-plus"></span><strong> Actividad</strong></div>
                        <div class="container-fluid">

                            <br><br>

                            <div class="card-body">

                                <div class="row">
                                    <div class="col-md-4">
                                        <h5>Actividad: ${notificacion.nombreNoti}</h5>
                                    </div>
                                    <div class="col-md-4">
                                        <h5>Emisor: ${notificacion.user.usuario}</h5>
                                    </div>
                                    <div class="col-md-4">
                                        <h5>Fecha: ${notificacion.fechaNoti}</h5>
                                    </div>

                                </div>
                                    <hr>

                                <div class="row">
                                    <div class="col-md-12">
                                        <p>${notificacion.asuntoNoti}</p>
                                    </div>
                                </div>


                                <br>
                                <div class="text-center">
                                    <div class="col-sm-12 col-md-12 col-auto-12">
                                        <a href="notificaciones" class="btn btn-success"><span class="fas fa-fw fa-angle-double-left"></span> Regresar</a>
                                        <button type="submit" class="btn btn-info"><span class="fas fa-fw fa-download"></span> Imprimir</button>

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