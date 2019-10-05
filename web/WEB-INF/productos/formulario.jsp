<%-- 
    Document   : index
    Created on : 01-01-2019, 07:05:47 PM
    Author     : Cristhian menjivar

    Esta pagina index.jsp es para mostrar contenido de categorias y se ha incluido
    dentro de wen-inf/categorias porque es más seguro y solo se podria tener acceso a traves 
    de Servlets(Controladores)...
--%>

<%@page import="devs.com.sistema.ventas.modelos.Proveedor"%>
<%@page import="devs.com.sistema.ventas.modelos.Categoria"%>
<%@page import="devs.com.sistema.ventas.modelos.Marca"%>
<%@page import="devs.com.sistema.ventas.modelos.Producto"%>
<%@page import="devs.com.sistema.ventas.modelos.Cliente"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Productos</title>
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
        Producto producto = null;
        if (tipoForm.equalsIgnoreCase("actualizar")) {
            producto = (Producto) request.getAttribute("producto");
        }

        List<Marca> listaMarca = (List<Marca>) request.getAttribute("marcas");
        List<Categoria> listaCategoria = (List<Categoria>) request.getAttribute("categorias");
        List<Proveedor> listaProveedor = (List<Proveedor>) request.getAttribute("proveedores");
    %>
    <body id="page-top" style="background-color: #666666">
        <!-- se incluye la barra de navigacion de heading -->
        <jsp:include page="../layouts/header.jsp"></jsp:include>

            <div id="wrapper">

                <!-- se incluye el nav bar de menu-->
            <jsp:include page="../layouts/sliderbar.jsp"></jsp:include>

                <div class="container">
                    <div class="card card-producto mx-auto mt-5">
                        <div class="card-header" style="background-color: #38cc82; color: #fff"><%= tipoForm%> producto</div>
                    <div class="card-body">
                        <form action="productos" method="post">

                            <%--este input esta oculto para las acciones crear o actualizar--%>
                            <input type="hidden" name="accion" value="<%= tipoForm%>">
                            <% if (tipoForm.equalsIgnoreCase("actualizar")) {%>
                            <input type="hidden" name="idActualizar" value="<%= producto.getIdProducto()%>">
                            <% }%>


                            <div class="form-group">
                                <div class="form-row">
                                    <div class="col-md-6">
                                        <div class="form-label-group">
                                            <input type="number" id="idproducto" name="idproducto" class="form-control" placeholder="Clave" required="required" autofocus="autofocus" 
                                                   value="<% if (tipoForm.equalsIgnoreCase("actualizar")) {
                                                           out.print(producto.getIdProducto());
                                                       }%>" <% if (tipoForm.equalsIgnoreCase("actualizar")) { %>
                                                   disabled=""
                                                   <% }%>>
                                            <label for="idproducto">Clave</label>
                                        </div>
                                        <p></p>
                                    </div>
                                    <div class="col-md-6">
                                        <div class="form-label-group">
                                            <input type="text" id="medida" name="medida" class="form-control" placeholder="Medida" required="required"
                                                   value="<% if (tipoForm.equalsIgnoreCase("actualizar")) {
                                                           out.print(producto.getMedida());
                                                       }%>">
                                            <label for="medida">Medida</label>
                                        </div>
                                    </div>
                                </div>
                            </div>

                            <div class="form-group">
                                <div class="form-label-group">
                                    <input type="text" id="nombre" name="nombre" class="form-control" placeholder="Nombre" required="required" 
                                           value="<% if (tipoForm.equalsIgnoreCase("actualizar")) {
                                                   out.print(producto.getNombre());
                                               }%>">
                                    <label for="nombre">Nombre</label>
                                </div>
                            </div>


                            <div class="form-group">
                                <div class="form-row">
                                    <div class="col-md-6">
                                        <div class="form-label-group">
                                            <input type="text" id="precioCompra" name="precioCompra" class="form-control" placeholder="Precio compra" required="required" 
                                                   value="<% if (tipoForm.equalsIgnoreCase("actualizar")) {
                                                           out.print(producto.getPrecioCompra());
                                                       }%>">
                                            <label for="precioCompra">Precio de compra</label>
                                        </div>
                                        <p></p>
                                    </div>
                                    <div class="col-md-6">
                                        <div class="form-label-group">
                                            <input type="text" id="precioVenta" name="precioVenta" class="form-control" placeholder="Precio venta" required="required" 
                                                   value="<% if (tipoForm.equalsIgnoreCase("actualizar")) {
                                                           out.print(producto.getPrecioVenta());
                                                       }%>">
                                            <label for="precioVenta">Precio de venta</label>
                                        </div>
                                    </div>
                                </div>
                            </div>


                            <div class="form-group">
                                <div class="form-row">
                                    <div class="col-md-4">
                                        <div class="form-label-group">
                                            <input type="text" id="color" name="color" class="form-control" placeholder="Color.." required="required" 
                                                   value="<% if (tipoForm.equalsIgnoreCase("actualizar")) {
                                                           out.print(producto.getColor());
                                                       }%>">
                                            <label for="color">Color</label>
                                        </div>
                                        <p></p>
                                    </div>
                                    <div class="col-md-4">
                                        <div class="form-label-group">
                                            <input type="text" id="serie" name="serie" class="form-control" placeholder="Confirm password" required="required" 
                                                   value="<% if (tipoForm.equalsIgnoreCase("actualizar")) {
                                                           out.print(producto.getSerie());
                                                       }%>">
                                            <label for="serie">Serie</label>
                                        </div>
                                        <p></p>
                                    </div>
                                    <div class="col-md-4">
                                        <div class="form-label-group">
                                            <input type="text" id="modelo" name="modelo" class="form-control" placeholder="modelo" required="required"
                                                   value="<% if (tipoForm.equalsIgnoreCase("actualizar")) {
                                                           out.print(producto.getModelo());
                                                       }%>">
                                            <label for="modelo">Modelo</label>
                                        </div>
                                    </div>
                                </div>
                            </div>

                            <div class="form-group">
                                <div class="form-row">
                                    <div class="col-md-4">
                                        <div class="form-label-group">
                                            <input type="text" id="existencia" name="existencia" class="form-control" placeholder="Last name" required="required" 
                                                   value="<% if (tipoForm.equalsIgnoreCase("actualizar")) {
                                                           out.print(producto.getExistencias());
                                                       }%>">
                                            <label for="existencia">Existencia</label>
                                        </div>
                                        <p></p>
                                    </div>
                                    <div class="col-md-4">
                                        <div class="form-label-group">
                                            <input type="text" id="averiado" name="averiado" class="form-control" placeholder="Dañado" required="required" 
                                                   value="<% if (tipoForm.equalsIgnoreCase("actualizar")) {
                                                           out.print(producto.getAveriado());
                                                       }%>">
                                            <label for="averiado">Productos dañados</label>
                                        </div>
                                        <p></p>
                                    </div>
                                    <div class="col-md-4">
                                        <div class="form-label-group">
                                            <input type="text" id="stock" name="stock" class="form-control" placeholder="stock" required="required" 
                                                   value="<% if (tipoForm.equalsIgnoreCase("actualizar")) {
                                                           out.print(producto.getStocks());
                                                       }%>">
                                            <label for="stock">Limite de producto</label>
                                        </div>
                                    </div>
                                </div>
                            </div>


                            <div class="form-group">
                                <div class="form-row">

                                    <div class="col-md-4">
                                        <label>Selecione una marca</label>
                                        <select name="idMarca" class="custom-select">
                                            <% for (Marca marca : listaMarca) { %>
                                            <option value="<% out.print(marca.getIdMarca()); %>"
                                                    <% if (tipoForm.equals("actualizar") && producto.getMarca().getIdMarca() == marca.getIdMarca()) {
                                                            out.print("selected");
                                                        }
                                                    %> > <%= marca.getMarca()%> </option>    
                                            <% }%>
                                        </select>
                                        <p></p>
                                    </div>
                                    <div class="col-md-4">
                                        <label>Selecione un proveedor</label>
                                        <select name="idProveedor" class="custom-select">
                                            <% for (Proveedor prov : listaProveedor) { %>
                                            <option value="<% out.print(prov.getIdProveedor()); %>"
                                                    <% if (tipoForm.equals("actualizar") && producto.getProveedor().getIdProveedor() == prov.getIdProveedor()) {
                                                            out.print("selected");
                                                        }
                                                    %> > <%= prov.getEmitidopor()%> </option>    
                                            <% }%>
                                        </select>
                                        <p></p>
                                    </div>
                                    <div class="col-md-4">
                                        <label>Selecione una categoria</label>
                                        <select name="idCategoria" class="custom-select">
                                            <% for (Categoria cat : listaCategoria) { %>
                                            <option value="<% out.print(cat.getCategoriaId()); %>"
                                                    <% if (tipoForm.equals("actualizar") && producto.getCategoria().getCategoriaId() == cat.getCategoriaId()) {
                                                            out.print("selected");
                                                        }
                                                    %> > <%= cat.getNombreCat()%> </option>    
                                            <% }%>
                                        </select>
                                    </div>
                                </div>
                            </div>

                            <div class="custom-control custom-checkbox">
                                <input type="checkbox" class="custom-control-input" id="enLinea" name="enLinea"  
                                       <% if (tipoForm.equals("actualizar") && producto.getEnLinea() == 1) {
                                               out.print("checked");
                                           }
                                       %> >
                                <label class="custom-control-label" for="enLinea">Producto activo?</label>
                            </div>

                            <br>

                            <div class="text-center">
                                <div class="col-sm-12 col-md-12 col-auto-12">
                                    <a href="productos" class="btn btn-success"><span class="fas fa-fw fa-angle-double-left"></span> Atras</a>

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

