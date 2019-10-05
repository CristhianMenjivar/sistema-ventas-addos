<%-- 
    Document   : index1
    Created on : 22-mar-2019, 13:29:13
    Author     : Diego
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

  <head>
<link rel="icon" href="js/dev1.png">
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Inicio Sesion</title>

    <!-- Bootstrap core CSS-->
    <link href="vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom fonts for this template-->
    <link href="vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">

    <!-- Custom styles for this template-->
    <link href="css/sb-admin.css" rel="stylesheet">

  </head>

  <body class="bg-dark">
 <%
 
        //recurperar el mensaje
        String mensaje = "";
        if (request.getSession().getAttribute("error") != null) {
            mensaje = (String) request.getSession().getAttribute("error");
        }
    %>
    <div class="container">
      <div class="card card-login mx-auto mt-5">
        <div class="card-header" style="background-color: #38cc82; color: #fff">Login</div>
        <div class="card-body">

  <%--vamos a usar un alert de boostrap para el mensaje de alerta al usuario--%>
                    <% if (!mensaje.isEmpty()) {%>
                    <% request.getSession().removeAttribute("error");%>
                    
                    <div class="alert alert-danger alert-dismissible">
                        <a href="<%request.getContextPath();%>" >  <button type="button" class="close" data-dismiss="alert">×</button></a>
                        Alerta: <%= mensaje%>
                    </div>
                    <% }%>

          <form action="Usuarios" method="post">
              <input type="hidden" name="accion" value="validarUsuario">
            <div class="form-group">
              <div class="form-label-group">
                  <input type="text" id="usuario" id="usuario" name="usuario" class="form-control" placeholder="Escribe tú usuario..." required="required" autofocus="autofocus">
                <label for="usuario">Usuario:</label>
              </div>
            </div>
            <div class="form-group">
              <div class="form-label-group">
                  <input type="password" id="password" name="password" class="form-control" placeholder="Escribe tú contraseña..." required="required">

                <label for="password">contraseña:</label>
              </div>
            </div>
             <input type="submit" class="btn btn-primary btn-block" value="Entrar">
          </form>




          <div class="text-center">
            <a class="d-block small mt-3" href="registro.jsp">Crear una cuenta</a>
           
          </div>
        </div>
      </div>
    </div>

    <!-- Bootstrap core JavaScript-->
    <script src="vendor/jquery/jquery.min.js"></script>
    <script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

    <!-- Core plugin JavaScript-->
    <script src="vendor/jquery-easing/jquery.easing.min.js"></script>

  </body>

</html>

