import static java.lang.Thread.sleep;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

class Objectpoolnew extends Thread{

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
       //ThreadObject th=new ThreadObject();
        if(free==0){
            //add thread to queue
            //th.start();
            if(!process.isEmpty()){
                Thread t1=new Thread(this);
                t1.start();
                synchronized(this.ObjectPool.elementAt(free)){
            }
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
   
       
}
