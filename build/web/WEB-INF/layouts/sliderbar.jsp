<%-- 
    Document   : sliderbar
    Created on : 01-01-2019, 06:45:30 PM
    Author     : Usuario
    Este el el menu desplegable que ira en la parte izquierda
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%--Este sidebar va dentro de una columna de 2--%>
<!-- Sidebar -->
<ul class="sidebar navbar-nav">
    <li class="nav-item active">
        <a class="nav-link" href="index.jsp">
            <i class="fas fa-fw fa-tachometer-alt"></i>
            <span>INICIO</span>
        </a>
    </li>
    
    <!--- ventas --->
    <li class="nav-item dropdown">
        <a class="nav-link dropdown-toggle" href="#" id="pagesDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
            <i class="fas fa-fw fa-shopping-cart"></i>
            <span>VENTAS</span>
        </a>
        <div class="dropdown-menu" aria-labelledby="pagesDropdown">
            <h6 class="dropdown-header">VENTAS</h6>
            <a class="dropdown-item" href="ventas?accion=hacerPedido"><span class="fas fa-fw fa-shopping-cart"></span>  Nueva venta</a>
            <a class="dropdown-item" href="ventas?accion=verPedido"><span class="fas fa-fw fa-list"></span> Listados</a>
        </div>
    </li>

    <!--- compras --->
    <li class="nav-item dropdown">
        <a class="nav-link dropdown-toggle" href="#" id="pagesDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
            <i class="fas fa-fw fa-shopping-cart"></i>
            <span>COMPRAS</span>
        </a>
        <div class="dropdown-menu" aria-labelledby="pagesDropdown">
            <h6 class="dropdown-header">COMPRAS</h6>
            <a class="dropdown-item" href="compras?accion=compras"><span class="fas fa-fw fa-shopping-cart"></span>  Nueva compra</a>
            <a class="dropdown-item" href="compras"><span class="fas fa-fw fa-list"></span> Listados</a>
        </div>
    </li>
    <!--- Inventario,créditos y gastos --->
    <li class="nav-item dropdown">
        <a class="nav-link dropdown-toggle" href="#" id="pagesDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
            <i class="fas fa-fw fa-folder"></i>
            <span>CONTROL</span>
        </a>
        <div class="dropdown-menu" aria-labelledby="pagesDropdown">
            <h6 class="dropdown-header">Control</h6>
            <a class="dropdown-item" href="gastos">Gastos</a>
            <a class="dropdown-item" href="creditos">Créditos</a>
        </div>
    </li>

    <!--- Reuniones --->
    <li class="nav-item dropdown">
        <a class="nav-link dropdown-toggle" href="#" id="pagesDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
            <i class="fas fa-fw fa-folder"></i>
            <span>AGENDA</span>
        </a>
        <div class="dropdown-menu" aria-labelledby="pagesDropdown">
            <h6 class="dropdown-header">Agenda</h6>
            <a class="dropdown-item" href="notificaciones">Actividades</a>
        </div>
    </li>
    
    <li class="nav-item">
        <a class="nav-link" href="productos">
            <i class="fas fa-fw fa-calculator"></i>
            <span>Productos</span></a>
    </li>

    <li class="nav-item">
        <a class="nav-link" href="categorias">
            <i class="fas fa-fw fa-address-book"></i>
            <span>Categorias</span></a>
    </li>
    <li class="nav-item">
        <a class="nav-link" href="marcas">
            <i class="fas fa-fw fa-map"></i>
            <span>Marcas</span></a>
    </li>
    
    <!--- Clientes y Facturas --->
    <li class="nav-item dropdown ">
        <a class="nav-link dropdown-toggle" href="#" id="pagesDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
            <i class="fas fa-fw fa-address-card"></i>
            <span>CLIENTES</span>
        </a>
        <div class="dropdown-menu" aria-labelledby="pagesDropdown">
            <h6 class="dropdown-header">Clientes</h6>
            <a class="dropdown-item" href="boleta">Boletas</a>
            <a class="dropdown-item" href="guias">Guías</a>
            <a class="dropdown-item" href="clientes"><span class="fas fa-fw fa-list"></span> Listados</a>
        </div>
    </li>
    <li class="nav-item">
        <a class="nav-link" href="empleados">
            <i class="fas fa-fw fa-address-book"></i>
            <span>Empleados</span></a>
    </li>
    <!--- Proveedres --->
    <li class="nav-item dropdown">
        <a class="nav-link dropdown-toggle" href="#" id="pagesDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
            <i class="fas fa-fw fa-address-card"></i>
            <span>Proveedores</span>
        </a>
        <div class="dropdown-menu" aria-labelledby="pagesDropdown">
            <h6 class="dropdown-header">Proveedores</h6>
            <a class="dropdown-item" href="cuentas">Cuentas</a>
            <a class="dropdown-item" href="proveedores"><span class="fas fa-fw fa-list"></span> Listados</a>
    </li>

</ul>


