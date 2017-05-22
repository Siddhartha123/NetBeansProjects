
import java.util.LinkedList;
import java.util.Queue;
import java.util.Vector;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.Stack;
import java.lang.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class PoolCallable {
    
    int n,i=0;
    static int free=0;
    ExecutorService executor;
    static Stack<Object> ObjectPool = new Stack<Object>();
    static Vector<Integer> completed=new Vector<>();
    Queue<Thread> queue;
    
    
    Object getPoolObject(int duration) throws InterruptedException, ExecutionException{
        i++;
        Object ob;
        //completed.add(1);
        /*Thread worker = new WorkerThread("p" + i,ObjectPool,duration);
        queue.add(worker);
        while(free==0);
        queue.poll().start();*/
        free--;
        /*
        for(j=0;j<i-1;j++){
            while(completed.elementAt(j)==0);
        }
        */
        //executor.execute(worker);
        //System.out.println(executor);
        Callable<Object> callable = new WorkerThread(""+i,ObjectPool,duration);
        Future<Object> future = executor.submit(callable);
        ob=future.get();
        return ob;
    }
    
    PoolCallable(int num) throws SQLException, ClassNotFoundException{
        n=num;
        free=n;
        int j;
        //completed.add(1);
        executor= Executors.newFixedThreadPool(num);
        for(j=0;j<n;j++)
        {
            jdbc_con db;
            db = new jdbc_con("com.mysql.jdbc.Driver","jdbc:mysql://localhost/cyborg","root","");
            if(db.is_connected){
                Connection con=db.getConnection(db);
                Object con1=(Object)con;
                ObjectPool.push(con1);
            }
        }
        queue =new LinkedList<>();
    }
   
    public static void main(String[] args) throws SQLException, SQLException,InterruptedException, ClassNotFoundException, ExecutionException{
        
            PoolCallable p=new PoolCallable(7);
            p.getPoolObject(500);
            p.getPoolObject(1000);
            p.getPoolObject(1500);
            p.getPoolObject(1300);
            p.getPoolObject(700);
            p.getPoolObject(1700);
            p.getPoolObject(900);
            p.getPoolObject(1600);
            p.getPoolObject(2000);

            p.executor.shutdown();
            /*while (!p.executor.isTerminated()) {
            }*/
    }
}
    




class WorkerThread implements Callable{
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
    public Object call() throws Exception {
        long current_time=System.currentTimeMillis();  
        //System.out.println(current_time);
        System.out.println(Thread.currentThread().getName()+" (Start)"+message);
        istime(current_time);
        System.out.println(Thread.currentThread().getName()+" (End)");
        this.isComplete=true;
        this.release(object);
        //p.free++;
        return object;
    }
    public void istime(long start_time){
        while((System.currentTimeMillis()-start_time)<duration);
        //System.out.println(System.currentTimeMillis());
    }
    public void release(Object object){
        PoolCallable.ObjectPool.push(object);
        //System.out.println("objects free: "+Poolnew.ObjectPool.size());
        PoolCallable.free++;
    }
 
    
}