package devs.com.sistema.ventas.coneccion;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author cristhian
 */

//conecion a postgrest sql
public class BaseDatosPG {

    private Connection conn = null; // variable coneccion

    // constructor para crear coneccion
    public BaseDatosPG() {

        //url para el jdbc
        String urlDataBase = "jdbc:postgresql://localhost:5432/sistema-ventas";

        try {
            //cargando el driver de postgres
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection(urlDataBase, "postgres", "1234");

        } catch (Exception ex) {
            System.out.println("Excepción: " + ex.getMessage());
        }
    }

    //metodo para obtener la coneccion
    public Connection getConeccion() {
        return conn;
    }

    //metodo para desconectar la coneccion
    public void desconectraBD() {
        System.out.println("Cerrar coneccion a base de datos");
        if (conn != null) {
            try {
                conn.close();
            } catch (Exception ex) {
                System.out.println("No se realizo la conección: " + ex.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        BaseDatosPG obj = new BaseDatosPG();
        obj.getConeccion();
        obj.desconectraBD();
    }
    
}
