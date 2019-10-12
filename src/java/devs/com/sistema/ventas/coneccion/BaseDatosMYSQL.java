package devs.com.sistema.ventas.coneccion;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author cristhian
 */

//conecion a postgrest sql
public class BaseDatosMYSQL {
    
    //usuario por defecto en la base de datos (se debe ingresar manual)
    //usuario admin
    //password 1234  => encriptado: 81dc9bdb52d04dc20036dbd8313ed055

    private Connection conn = null; // variable coneccion

    // constructor para crear coneccion
    public BaseDatosMYSQL() {

        //url para el jdbc
        //String urlDataBase = "jdbc:mysql://localhost:3306/bd_sistema_addon";  //Mysql
        String urlDataBase = "jdbc:mysql://us-cdbr-iron-east-05.cleardb.net/heroku_e340c3b8b3902f9?reconnect=true";  //Heroku

        try {
            //cargando el driver de mysql
            Class.forName("com.mysql.jdbc.Driver");
            //conn = DriverManager.getConnection(urlDataBase, "cristian", "1234");
            conn = DriverManager.getConnection(urlDataBase, "ba94ed262dbbf7", "480881c9");  //heroku

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
        System.out.println("Cerrar conección a base de datos");
        if (conn != null) {
            try {
                conn.close();
            } catch (Exception ex) {
                System.out.println("No se realizo la conección: " + ex.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        BaseDatosMYSQL obj = new BaseDatosMYSQL();
        obj.getConeccion();
        obj.desconectraBD();
    }
    
}
