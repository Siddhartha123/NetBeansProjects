package jdbccon;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author I Sidhartha Kumar
 */
import java.sql.*;
import JdbcConn.*;
public class main {
    public static void main(String[] args){
        Connection connection1=JdbcConn("com.mysql.jdbc.Driver","jdbc:mysql://localhost/cyborg","root","");
    }
}
