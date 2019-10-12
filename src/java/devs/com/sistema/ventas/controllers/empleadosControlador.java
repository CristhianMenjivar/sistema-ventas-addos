/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package devs.com.sistema.ventas.controllers;

import devs.com.sistema.ventas.dao.EmpleadoJDBC;
import devs.com.sistema.ventas.modelos.Empleado;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author usuario
 */
@WebServlet(name = "empleadosControlador", urlPatterns = {"/empleados"})
public class empleadosControlador extends HttpServlet {
    
    /* variable global, cuando el sistema está en desarrollo en nuestra maquina local se usa a base de glassfish
       para redireccion "/sistema ventas" necesaria para glassfish en Local
     * En heroku u otro hosting se usa la raiz de la app para redirigir "/"
    */
    
    // sistema en desarrollo
    private String SISTEMA_DEVELOPERS = "/sistema-ventas";
    //sistema en producción
    private String SISTEMA_PRODUCCTION = "";
    //direccion de la raiz del sistema
    private String PATH_SISTEMA = SISTEMA_DEVELOPERS;


    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       
        //request.getRequestDispatcher("/WEB-INF/empleados/pruevaSer.jsp").forward(request, response);
        
         //checar si si el parametro esta llegando
        if (request.getParameter("accion") != null) {
            String accion = (String) request.getParameter("accion");
            System.out.println("prueva: "+ accion);
            switch(accion){
                case "nuevo":
                    //desplegar el formulario para nuevas categorias
                    formNuevo(request, response);
                    break;
                    
                case "editar":
                    formEditar(request, response);
                    break;
            }
        } else {
            //para obtener lista de empleado
             EmpleadoJDBC empJDBC = new EmpleadoJDBC();
             
             System.out.println("prueva empJDBC: "+ empJDBC);
        
             List<Empleado> listaEmp = empJDBC.listAll();
             System.out.println("prueva lista: "+ listaEmp);

            //pasamos por atributo
            request.setAttribute("empleados", listaEmp);
            //redirigimos a la vista
            request.getRequestDispatcher("/WEB-INF/empleados/index.jsp").forward(request, response);
        }
        
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
          //checar si si el parametro esta llegando
        if (request.getParameter("accion") != null) {
            String accion = (String) request.getParameter("accion");
            switch(accion){
                case "crear":
                    //desplegar el formulario para nuevas categorias
                    insertarEmpleado(request, response);
                    break;
                    
                case "borrar":
                    borrarEmpleado(request, response);
                    break;
                    
                case "actualizar":
                    actualizarEmpleado(request,response);
                    break;
            }
        }
        
    }

    private void formNuevo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
       EmpleadoJDBC empleadoDao = new EmpleadoJDBC();
       List<Empleado> listaEmpleados = empleadoDao.listAll();
       request.setAttribute("empleados", listaEmpleados);
       request.setAttribute("tipoForm", "crear");
       request.getRequestDispatcher("/WEB-INF/empleados/formulario.jsp").forward(request, response);
   
    
    }

    private void formEditar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
       int idEmp = Integer.parseInt(request.getParameter("idEmp"));
        EmpleadoJDBC empleadoDao = new EmpleadoJDBC();
        
        //busca 1 emp
        Empleado empleado = empleadoDao.findById(idEmp);
        
        if (empleado !=null) {
            
            request.setAttribute("empleado", empleado);
            request.setAttribute("tipoForm", "actualizar");
            request.getRequestDispatcher("/WEB-INF/empleados/formulario.jsp").forward(request, response);

        }
   
    }

    private void insertarEmpleado(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        try {
            //obtenemos los valores de los controles de form
            //long claveCat = Long.parseLong(request.getParameter("idEmpleado"));
            String nombre = request.getParameter("nombre");
            String apellido = request.getParameter("apellido");
            String direccion = request.getParameter("direccion");
            String telefono = request.getParameter("telefono");
            String cargo = request.getParameter("cargo");
            double salario = Double.parseDouble(request.getParameter("salario"));
            double comision = Double.parseDouble(request.getParameter("comision"));
            
            //vamos a preparar la fecha que se insertara a la BD
//            java.util.Date fechaDate = new SimpleDateFormat("yyyy-MM-dd").parse(fechaNac);
//            java.sql.Date fechaSQL = new java.sql.Date(fechaDate.getTime());
            
            //creamos el obj base para insertar datos
            EmpleadoJDBC base = new EmpleadoJDBC();
            String mensaje= base.insert(new Empleado(0, nombre, apellido, direccion, telefono, cargo, salario, comision));
            
            //redirigimos al index con todo y mensaje
            //con sendRedirect para que los datos insertados no se sigan reeviando
            request.getSession().setAttribute("opEmp", mensaje); // lo enviamos por la secion
            response.sendRedirect( this.PATH_SISTEMA + "/empleados");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void borrarEmpleado(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
          
            //obtenemos los valores de los controles de form
            int claveCat = Integer.parseInt(request.getParameter("idEmpleado"));
            //creamos el obj base para insertar datos
            EmpleadoJDBC base = new EmpleadoJDBC();
            String mensaje= base.delete(claveCat);
            
            //redirigimos al index con todo y mensaje
            //con sendRedirect para que los datos insertados no se sigan reeviando
            request.getSession().setAttribute("opEmp", mensaje); // lo enviamos por la secion
            response.sendRedirect( this.PATH_SISTEMA + "/empleados");
       
    }

    private void actualizarEmpleado(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
          try {
            //obtenemos los valores de los controles de form
            long claveCat = Long.parseLong(request.getParameter("idEmpleado"));
             String nombre = request.getParameter("nombre");
            String apellido = request.getParameter("apellido");
            String direccion = request.getParameter("direccion");
            String telefono = request.getParameter("telefono");
            String cargo = request.getParameter("cargo");
            double salario = Double.parseDouble(request.getParameter("salario"));
            double comision = Double.parseDouble(request.getParameter("comision"));
            //creamos el obj base para actualizar datos
            EmpleadoJDBC base = new EmpleadoJDBC();
            String mensaje= base.update(new Empleado(claveCat, nombre, apellido, direccion, telefono, cargo, salario, comision));
            
            //redirigimos al index con todo y mensaje
            //con sendRedirect para que los datos insertados no se sigan reeviando
            request.getSession().setAttribute("opEmp", mensaje); // lo enviamos por la secion
            response.sendRedirect( this.PATH_SISTEMA + "/empleados");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


}
