import java.util.LinkedList;
import java.util.Queue;
import java.util.Vector;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.Stack;
import java.lang.*;
import java.sql.Connection;
import java.sql.SQLException;
public class Poolnew {
    int n,i=0;
    static int free=0;
    //ExecutorService executor;
    static Stack<Object> ObjectPool = new Stack<Object>();
    static Vector<Integer> completed=new Vector<>();
    Queue<Thread> queue;
    @SuppressWarnings("empty-statement")
    void getPoolObject(int duration) throws InterruptedException{
        int j;
        i++;
        //completed.add(1);
        Thread worker = new WorkerThread("p" + i,ObjectPool,duration);
        queue.add(worker);
        while(free==0);
        queue.poll().start();
        free--;
        /*
        for(j=0;j<i-1;j++){
            while(completed.elementAt(j)==0);
        }
        */
        //executor.execute(worker);
        //System.out.println(executor);
    }
    
    Poolnew(int num,String driver,String url,String user,String pass) throws SQLException, ClassNotFoundException{
        n=num;
        free=n;
        int j;
        //completed.add(1);
        //executor= Executors.newFixedThreadPool(num);
        for(j=0;j<n;j++)
        {
            jdbc_con db;
            db = new jdbc_con(driver,url,user,pass);
            if(db.is_connected){
                Connection con=db.getConnection(db);
                Object con1=(Object)con;
                ObjectPool.add(con1);
            }
        }
            
        queue =new LinkedList<>();
    }
    
}

class WorkerThread extends Thread{
    private String message;
    Object object;
    public boolean isComplete=false;
    int duration;   
    @SuppressWarnings("empty-statement")
    public WorkerThread(String s,Stack obj,int duration){
        this.duration=duration;
        while(obj.empty());
        this.object=obj.pop();
        //System.out.println("objects free: "+Poolnew.ObjectPool.size());
        this.message=s;
    }
 
    @Override
    public void run() {
        long current_time=System.currentTimeMillis();  
        //System.out.println(current_time);
        System.out.println(Thread.currentThread().getName()+" (Start)"+message);
        istime(current_time);
        System.out.println(Thread.currentThread().getName()+" (End)");
        this.isComplete=true;
        this.release(object);
        //p.free++;
    }
    public void istime(long start_time){
        while((System.currentTimeMillis()-start_time)<duration);
        //System.out.println(System.currentTimeMillis());
    }
    public void release(Object object){
        Poolnew.ObjectPool.push(object);
        //System.out.println("objects free: "+Poolnew.ObjectPool.size());
        Poolnew.free++;
    }
 
    
}
   



