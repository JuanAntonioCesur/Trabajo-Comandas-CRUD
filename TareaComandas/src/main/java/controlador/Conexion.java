
package controlador;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Conexion {
    
    private static final String USER = "root";
    private static final String PASSWORD = "jumaca";
    private static final String URL = "jdbc:mysql://localhost:3306/comandas";
    
    private static Connection conexion;
    
   
    
    static{
//        try {
//            
//            
//            conexion = DriverManager.getConnection(URL, USER, PASSWORD);
//            Logger.getLogger(ProductoDAOmySQL.class.getName()).info("Conexión establecida con exito");
//        } catch (SQLException ex) {
//            Logger.getLogger(ProductoDAOmySQL.class.getName()).log(Level.SEVERE, null, ex);
//        }
        
        try {
            
            
            conexion = DriverManager.getConnection(URL, USER, PASSWORD);
            Logger.getLogger(PedidoDAOmySQL.class.getName()).info("Conexión establecida con exito");
        } catch (SQLException ex) {
            Logger.getLogger(PedidoDAOmySQL.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    public static Connection getConexion() {
        return conexion;
    }
    
    
    
}
