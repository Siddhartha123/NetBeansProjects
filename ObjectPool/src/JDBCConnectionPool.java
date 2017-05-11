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
import ObjectPool.*;
public class JDBCConnectionPool extends ObjectPool{
  private String dsn, usr, pwd;
   public JDBCConnectionPool( String driver, String dsn, String usr, String pwd )
{
   try
   {
      Class.forName( driver ).newInstance();
   }
   catch( Exception e )
   {
      e.printStackTrace();
   }
   this.dsn = dsn;
   this.usr = usr;
   this.pwd = pwd;
}
   Object create()
{
   try
   {
      return( DriverManager.getConnection( dsn, usr, pwd ) );
   }
   catch( SQLException e )
   {
      e.printStackTrace();
      return( null );
   }
}
   boolean validate( Object o )
{
   try
   {
      return( ! ( ( Connection ) o ).isClosed() );
   }
   catch( SQLException e )
   {
      e.printStackTrace();
      return( false );
   }
}
  void expire( Object o )
{
   try
   {
      ( ( Connection ) o ).close();
   }
   catch( SQLException e )
   {
      e.printStackTrace();
   }
}
  public Connection borrowConnection()
{
   return( ( Connection ) super.checkOut() );
}
public void returnConnection( Connection c )
{
   super.checkIn( c );
}
}
