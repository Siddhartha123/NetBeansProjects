import java.sql.Connection;
import java.sql.SQLException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

class Objectpoolnew implements Runnable{

    static Vector<Object> ObjectPool = new Vector<Object>();
    Queue<Thread> process = new LinkedList<>();
    int n,free;
    void initialize(int num,Object obj){
        free=n=num;
        int i;
        for(i=n;i>0;i--){
          ObjectPool.add(obj);
        }
    }
    
    Object getPoolObject() throws InterruptedException{
       ThreadObject th=new ThreadObject();
        if(free==0){
            //add thread to queue
            th.start();
            if(!process.isEmpty()){
                Thread t1=new Thread(this);
                t1.start();
                t1.wait();
            }
            else{
                Thread t=new Thread(this);
                t.start();  
            }
        }
        else{
            free--;
            System.out.print("Object removed from collection: ");
            System.out.println(free+" objects free");
            return ObjectPool.get(free);
        }
    
        return null;
     
    }
    
    void releasePoolObject(Object obj){

        ObjectPool.add(obj);
        free++;
        System.out.print("Object added to collection: ");
        System.out.println(free+" objects free");
        
    }
    
    @Override
    public void run(){  
        while(free==0){
           //do nothing
        }
        try {
            Object ob=this.getPoolObject();
        } catch (InterruptedException ex) {
            Logger.getLogger(Objectpoolnew.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }  
  
   /* public static void main(String args[]) throws SQLException, ClassNotFoundException, InterruptedException{ 

            jdbc_con db;
            db = new jdbc_con("com.mysql.jdbc.Driver","jdbc:mysql://localhost/cyborg","root","");
            if(db.is_connected){
                Connection con=db.getConnection(db);
                Object con1=(Object)con;
                Objectpoolnew m1=new Objectpoolnew();  
                m1.initialize(3,con1);
                Object con2=m1.getPoolObject();
                Object con3=m1.getPoolObject();
                Object con4=m1.getPoolObject();
                Object con5=m1.getPoolObject();
               
                m1.releasePoolObject(con2);
                //m1.releasePoolObject(con3);
            }
     }  */
       
}
