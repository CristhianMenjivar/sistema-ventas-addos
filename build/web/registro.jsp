<%-- 
    Document   : registro
    Created on : 18-mar-2019, 14:18:25
    Author     : Diego
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="icon" href="js/dev1.png">
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <meta name="description" content="">
        <meta name="author" content="">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Registro</title>
        <link href="vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

        <!-- Custom fonts for this template-->
        <link href="vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">

        <!-- Page level plugin CSS-->
        <link href="vendor/datatables/dataTables.bootstrap4.css" rel="stylesheet">

        <!-- Custom styles for this template-->
        <link href="css/sb-admin.css" rel="stylesheet">
        <link href="css/login.css" rel="stylesheet">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    </head>
    <%

        //recurperar el mensaje
        String mensaje = "";
        if (request.getSession().getAttribute("error") != null) {
            mensaje = (String) request.getSession().getAttribute("error");
        }
    %>
    <body class="login-bg" style="background-color: #666666">

        <div class="container">
            <div class="card card-register mx-auto mt-5">
                <div class="card-header" style="background-color: #38cc82; color: #fff">Crear una cuenta</div>
                <div class="card-body">

                    <%--vamos a usar un alert de boostrap para el mensaje de alerta al usuario--%>
                    <% if (!mensaje.isEmpty()) {%>
                    <% request.getSession().removeAttribute("error");%>

                    <div class="alert alert-success alert-dismissible">
                        <a href="<%request.getContextPath();%>" >  <button type="button" class="close" data-dismiss="alert">×</button></a>
                        <strong style="color:  #ff3333">Alerta:</strong> <%= mensaje%>
                    </div>
                    <% }%>

                    <form action="Usuarios" method="post">
                        <input type="hidden" name="accion" value="crearUsuario">

                        <div class="form-group">
                            <div class="form-label-group">
                                <input type="text" id="usuario" name="usuario" class="form-control" placeholder="escribe tu usuario..." required="required" autofocus="autofocus">
                                <label for="usuario">Usuario</label>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="form-label-group">
                                <input type="password" id="password" name="password" class="form-control" placeholder="escribe tu contraseña..." required="required">
                                <label for="password">Contraseña</label>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="form-label-group">
                                <input type="text" id="tipo"  name="tipo" class="form-control" placeholder="Tipo de usuario..." required="required">
                                <label for="tipo">Tipo de usuario:</label>
                            </div>
                        </div>

                        <p></p>
                            <input type="submit" value="Registrarse" class="btn btn-primary btn-block">
                        
                    </form>

                    <div class="text-center">
                        <a class="d-block small mt-3" href="index.jsp">Ir a login</a>

                    </div>
                </div>
            </div>
        </div>


    </body>
</html>
