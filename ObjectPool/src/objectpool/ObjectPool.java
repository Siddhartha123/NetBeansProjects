package objectpool;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.*;
public abstract class ObjectPool {  
    private Hashtable locked, unlocked;        
    abstract Object create();
    abstract boolean validate( Object o );
    abstract void expire( Object o );
    synchronized Object checkOut(){
    long now = System.currentTimeMillis();
   Object o;        
   if( unlocked.size() > 0 )
   {
      Enumeration e = unlocked.keys();  
      while( e.hasMoreElements() )
      {
         o = e.nextElement();           
         if( ( now - ( ( Long ) unlocked.get( o ) ).longValue() ) >
expirationTime )
         {
            // object has expired
            unlocked.remove( o );
            expire( o );
            o = null;
         }
         else
         {
            if( validate( o ) )
            {
               unlocked.remove( o );
               locked.put( o, new Long( now ) );                
               return( o );
            }
            else
            {
               // object failed validation
               unlocked.remove( o );
               expire( o );
               o = null;
            }
         }
      }
   }        
   // no objects available, create a new one
   o = create();        
   locked.put( o, new Long( now ) ); 
   return( o );
    }
    synchronized void checkIn( Object o ){
    locked.remove( o );
   unlocked.put( o, new Long( System.currentTimeMillis() ) );
    }
    /**
     * @param args the command line arguments
     */
    ObjectPool()
    {
    expirationTime = 30000; // 30 seconds
    locked = new Hashtable();         
    unlocked = new Hashtable();
    }
    public static void main(String[] args) {
        // TODO code application logic here
    }
    
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
    
}


