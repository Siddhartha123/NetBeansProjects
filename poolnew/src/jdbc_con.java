import java.sql.*;
public class jdbc_con {
    int num_conn;
    boolean is_connected=false;
    Connection connection;
    jdbc_con(String driver,String url,String user, String pass)
      throws SQLException, ClassNotFoundException {
    // Load the JDBC driver
     Class.forName(driver);
    
    // Connect to a database
    connection = DriverManager.getConnection(url, user,pass);
    System.out.print("Database connected");
    System.out.println(this.connection);
    is_connected=true;
  }
    Connection getConnection(jdbc_con db){
        
        return this.connection;
        
    }
      
}
