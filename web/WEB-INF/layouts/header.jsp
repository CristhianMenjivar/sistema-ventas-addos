<%-- 
    Document   : header
    Created on : 01-01-2019, 06:43:11 PM
    Author     : cristhian menjivar
    Esta es la cabesera de la pagina que se incluiran y otras paginas jsp
--%>
<%@page import="devs.com.sistema.ventas.modelos.Notificacion"%>
<%@page import="java.util.List"%>
<%@page import="devs.com.sistema.ventas.dao.NotificacionJBDC"%>

<% int nuevaSNotif = 0;

    NotificacionJBDC notiDAO = new NotificacionJBDC();
    nuevaSNotif = notiDAO.NuevasNotificaciones();

    List<Notificacion> listaNoti = notiDAO.listActivas();
%>

<div class="header">
    <nav class="navbar navbar-expand navbar-dark bg-dark static-top">

        <a class="navbar-brand mr-1" href="#">Sistema de ventas</a>

        <button class="btn btn-link btn-sm text-white order-1 order-sm-0" id="sidebarToggle" href="#">
            <i class="fas fa-bars"></i>
        </button>

        <!-- Navbar Search -->
        <form class="d-none d-md-inline-block form-inline ml-auto mr-0 mr-md-3 my-2 my-md-0">
            <div class="input-group">
                <input type="text" class="form-control" placeholder="Buscar..." aria-label="Search" aria-describedby="basic-addon2">
                <div class="input-group-append">
                    <button class="btn btn-primary" type="button">
                        <i class="fas fa-search"></i>
                    </button>
                </div>
            </div>
        </form>

        <!-- Navbar -->
        <ul class="navbar-nav ml-auto ml-md-0">
            <li class="nav-item dropdown no-arrow mx-1">
                <a class="nav-link dropdown-toggle" href="#" id="alertsDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                    <i class="fas fa-bell fa-fw"></i>
                    <% if (nuevaSNotif > 0) {%>
                    <span class="badge badge-danger">
                        <%= nuevaSNotif%>
                    </span>
                    <% }%>
                </a>
                <% if (nuevaSNotif > 0) {%>

                
                <div class="dropdown-menu dropdown-menu-right" aria-labelledby="alertsDropdown">
                    <h6 class="dropdown-item"><strong>Notificaciones:</strong></h6>
                    <div class="dropdown-divider"></div>
                    <% for (Notificacion a : listaNoti) {%>
                    <a class="dropdown-item" href="notificaciones?accion=mostrar&idNoti=<%= a.getIdNotificacion()%>"><span class="fas fa-bell fa-fw"></span> <%= a.getNombreNoti()%></a>
                    <%}%>
                </div>
                
                <% }else{%>
                    <div class="dropdown-menu dropdown-menu-right" aria-labelledby="alertsDropdown">
                    <h6 class="dropdown-item"><strong>No hay Notificaciones</strong></h6>
                    </div>
                <%}%>
            </li>



            <li class="nav-item dropdown no-arrow mx-1">
                <a class="nav-link dropdown-toggle" href="#" id="messagesDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                    <i class="fas fa-envelope fa-fw"></i>

                    <% if (nuevaSNotif > 0) {%>
                    <span class="badge badge-danger">
                        <%= nuevaSNotif%>
                    </span>
                    <% }%>

                </a>
                <div class="dropdown-menu dropdown-menu-right" aria-labelledby="messagesDropdown">
                    <a class="dropdown-item" href="#">Action</a>
                    <a class="dropdown-item" href="#">Another action</a>
                    <div class="dropdown-divider"></div>
                    <a class="dropdown-item" href="#">Something else here</a>
                </div>
            </li>

            <li class="nav-item dropdown no-arrow">
                <a class="nav-link dropdown-toggle" href="#" id="userDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                    <i class="fas fa-user-circle fa-fw"></i>
                </a>
                <div class="dropdown-menu dropdown-menu-right" aria-labelledby="userDropdown">
                    <a class="dropdown-item" href="#">
                        <% if (request.getSession().getAttribute("user") != null) { %>
                        <strong> ${user.usuario} </strong>
                        <% } else {%>
                        <a class="dropdown-item" href="Usuarios?accion=cerrarSesion">Iniciar Sesión</a>
                        <% }%>
                    </a>
                    <div class="dropdown-divider"></div>
                    <a class="dropdown-item" data-toggle="modal" data-target="#modalCambiarContraseña" href="#">Cambiar contraseña</a>
                    <div class="dropdown-divider"></div>
                    <a class="dropdown-item" href="#" data-toggle="modal" data-target="#modalCerrarSesion">Cerrar sesión</a>
                </div>
            </li>
        </ul>

    </nav>
</div>
                    
<!-- Logout Modal-->
<div class="modal fade" id="modalCerrarSesion" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header alert-info">
                <h5 class="modal-title" id="exampleModalLabel">Estas listo para Cerrar Sesion?</h5>
                <button class="close" type="button" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">×</span>
                </button>
            </div>
            <div class="modal-body">Seleccione "Salir" si deseas cerrar sesion</div>
            <div class="modal-footer">
                <button class="btn btn-secondary" type="button" data-dismiss="modal">Cancelar</button>
                <a class="btn btn-primary" href="Usuarios?accion=cerrarSesion">Salir</a>
            </div>
        </div>
    </div>
</div>



<!-- Logout Modal-->
<div class="modal fade" id="modalCambiarContraseña" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header alert-info">
                <h5 class="modal-title" id="exampleModalLabel">¿Quieres cambiar la contraseña?</h5>
                <button class="close" type="button" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">×</span>
                </button>
            </div>

            <div class="container-fluid">
                <div class="modal-body">Usuario: ${user.usuario}</div>
                <form action="Usuarios" method="post">
                    <input type="hidden" name="accion" value="cambiarPassword">
                    <div class="form-group">
                        <div class="col-md-12">
                            <input class="form-control" type="password" name="passwordActual" placeholder="Contraseña actual.." >
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-md-12">
                            <input class="form-control" type="password" name="passwordNueva" placeholder="Contraseña nueva.." >
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-md-12">
                            <input class="form-control" type="password" name="passwordNueva2" placeholder="Verifique la contraseña.." >
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button class="btn btn-secondary" type="button" data-dismiss="modal">Cancelar</button>
                        <button  type="submit" class="btn btn-primary">Cambiar contraseña</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>