
package Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class Conexion {

private static final String DATABASE= "inventariodb";
private static final String DRIVER= "com.mysql.jdbc.driver";
private static final String USER="root";
private static final String PASSWORD="rufus2199$";
private static final String URL = "jdbc:mysql://localhost:3306/" + DATABASE + "?user=" + USER + "&password=" + PASSWORD + "&useSSL=false";
private Connection con;

public Conexion(){
con = null;
    try {
    con = (Connection) DriverManager.getConnection(URL);
    if(con != null){
        System.out.println("Conexion se completo correctamente");
   }
   } catch (SQLException ex) {
        System.out.println(ex);
    }
}

public Connection getConnection(){
return con;
}

public void desconectar (){
    try {
       con.close();
       System.exit(0);
    } catch (Exception e) {
        System.out.println("Imposible");
    }

}    
}
