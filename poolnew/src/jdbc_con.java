import java.sql.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
public class jdbc_con implements Runnable{
    int num_conn;
    boolean is_connected=false;
    Connection connection;
    jdbc_con(String driver,String url,String user, String pass)
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
    Connection getConnection(jdbc_con db){
        
        return connection;
        
    }
    public static void main(String[] args) throws SQLException, ClassNotFoundException{
       List<Connection> connections=new ArrayList<>(); 
        jdbc_con db=new jdbc_con("com.mysql.jdbc.Driver","jdbc:mysql://localhost/cyborg","root","");
        if(db.is_connected){
            Connection con=db.getConnection(db);
            connections.add(con);
            System.out.println(connections);
        }
    }

    @Override
    public void run() {
        try {
            Thread.sleep(10000); //To change body of generated methods, choose Tools | Templates.
        } catch (InterruptedException ex) {
            Logger.getLogger(jdbc_con.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
