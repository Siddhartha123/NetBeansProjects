import java.sql.*;
import java.util.*;
public class jdbc{
    int num_conn;
    boolean is_connected=false;
    Connection connection;
    jdbc(String driver,String url,String user, String pass)
      throws SQLException, ClassNotFoundException {
    // Load the JDBC driver
     Class.forName(driver);
    System.out.println("Driver loaded");
    
    // Connect to a database
    connection = DriverManager.getConnection
      (url, user,pass);
    System.out.println("Database connected");
    is_connected=true;
  }
    Connection getConnection(jdbc db){
        return connection;
        
    }

       
    
}
