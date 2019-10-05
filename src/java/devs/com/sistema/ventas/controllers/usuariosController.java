/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package devs.com.sistema.ventas.controllers;

import devs.com.sistema.ventas.dao.UsuarioJDBC;
import devs.com.sistema.ventas.exepciones.ErrorLoginException;
import devs.com.sistema.ventas.exepciones.ErrorRegistroException;
import devs.com.sistema.ventas.modelos.Usuario;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.codec.digest.DigestUtils;

@WebServlet(name = "usuariosController", urlPatterns = {"/Usuarios"})
public class usuariosController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (request.getParameter("accion") != null) {
            String accion = request.getParameter("accion");
            switch (accion) {
                case "cerrarSesion":
                    cerrarSesion(request, response);
                    break;
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (request.getParameter("accion") != null) {

            String accion = request.getParameter("accion");

            switch (accion) {
                case "crearUsuario":
                    crearUsuario(request, response);
                    break;
                case "validarUsuario":
                    validarUsuario(request, response);
                    break;
                case "cerrarSesion":
                    cerrarSesion(request, response);
                    break;
                case "cambiarPassword":
                    cambiarPassword(request, response);
                    break;
            }
        }
    }

    public String encriptarTexto(String texto) {
        return DigestUtils.md5Hex(texto);
    }

    private void cambiarPassword(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Usuario usuarioSesion = (Usuario) request.getSession().getAttribute("user");
        String passwordActual = (String) request.getParameter("passwordActual");
        String passwordNueva = (String) request.getParameter("passwordNueva");
        String passwordNueva2 = (String) request.getParameter("passwordNueva2");

        String mensaje = "";
        if (passwordNueva.isEmpty() || passwordNueva2.isEmpty() || passwordActual.isEmpty()) {
            mensaje = "Hay campos vacios";
        } else if (passwordNueva.equals(passwordNueva2)) {
            //continuar

            String passEncript = encriptarTexto(passwordActual);

            if (usuarioSesion.getPass().equals(passEncript)) {
                //continuar
                String passEncriptNueva = encriptarTexto(passwordNueva);
                UsuarioJDBC userDao = new UsuarioJDBC();

                usuarioSesion.setPass(passEncriptNueva);
                if (userDao.cambiarContraseña(usuarioSesion)) {
                    //cambiado...
                    mensaje = "La contraseña ha sido cambiada";
                } else {
                    mensaje = "La contraseña no se cambio";
                }
            } else {
                mensaje = "La contraseña actual no es correcta";
            }
        } else {
            mensaje = "Las contraseñas no son iguales";
        }

        request.getSession().setAttribute("mensaje", mensaje);
        response.sendRedirect(request.getContextPath() + "/resultadosAccion.jsp");
    }

    private void cerrarSesion(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.getSession().removeAttribute("user");
        response.sendRedirect(request.getContextPath() + "/index.jsp");
    }

    private void crearUsuario(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        try {

            System.out.println("Craer usuario");

            UsuarioJDBC daoUsuario = new UsuarioJDBC();

            String user = request.getParameter("usuario");
            String pass = request.getParameter("password");
            String tipo = request.getParameter("tipo");

            if (user.isEmpty() || pass.isEmpty() || tipo.isEmpty()) {
                request.getSession().setAttribute("error", "Hay campos vacios!");
                throw new ServletException(new ErrorRegistroException("Hay campos vacios!"));
            }

            //verificar si el usuario ya existe en la BD
            if (daoUsuario.findByUser(user) != null) {
                request.getSession().setAttribute("error", "Este usuario ya existe!");
                throw new ServletException(new ErrorRegistroException("Este usuario ya existe!"));
            }

            //password encryptado
            String passEncript = encriptarTexto(pass);
            Usuario usuario = new Usuario();
            usuario.setUsuario(user);
            usuario.setPass(passEncript);
            usuario.setTipoUsuario(tipo);

            Usuario usuarioSesion;
            usuarioSesion = daoUsuario.crearUsuario(usuario);
            request.getSession().setAttribute("user", usuarioSesion);
            request.getSession().setAttribute("mensaje", "Cuenta creada con exito!");
            response.sendRedirect("productos");

        } catch (Exception e) {
            response.sendRedirect("registro.jsp");
        }

    }

    private void validarUsuario(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        try {
            String user = request.getParameter("usuario");
            String pass = request.getParameter("password");
            //encriptar pass
            String passEncript = encriptarTexto(pass);
            UsuarioJDBC daoUsuario = new UsuarioJDBC();
            Usuario usuarioSesion = daoUsuario.validarUsuario(user, passEncript);

            if (usuarioSesion != null) {
                request.getSession().setAttribute("user", usuarioSesion);
                response.sendRedirect(request.getContextPath() + "/productos");
            } else {
                throw new ServletException(new ErrorLoginException("No existe ningun usuario con estas credenciales."));
            }
        } catch (Exception e) {
            response.sendRedirect("index.jsp");
        }

    }

}
