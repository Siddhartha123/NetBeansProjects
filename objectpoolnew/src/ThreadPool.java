import static java.lang.Thread.sleep;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Stack;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;



public class ThreadPool {
    public static LinkedBlockingQueue<Runnable> queue=new LinkedBlockingQueue<Runnable>();
    public static ExecutorService executor =new ThreadPoolExecutor(3,3,0,TimeUnit.MILLISECONDS,queue);
    public static void main(String[] args) throws SQLException, ClassNotFoundException, InterruptedException {
            jdbc_con db;
            db = new jdbc_con("com.mysql.jdbc.Driver","jdbc:mysql://localhost/cyborg","root","");
            if(db.is_connected){
                Connection con=db.getConnection(db);
                Object con1=(Object)con;
                Objectpoolnew m1=new Objectpoolnew();  
                /*m1.initialize(3,con1);
                try {
                    sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }*/
            }
        
        for (int i = 1; i <= 15; i++) {
            Runnable worker = new WorkerThread("p" + i, (int) ((Math.random()*10000)%5000));
            //Thread.sleep(1000);
            executor.execute(worker);
          }
        executor.shutdown();
        while (!executor.isTerminated()) {
        }
        System.out.println("Finished all threads");
    }
 
}

class WorkerThread implements Runnable{
    private String message;
    Object object;
    public boolean isComplete=false;
    int duration;   
    @SuppressWarnings("empty-statement")
    public WorkerThread(String s,int duration){
        this.duration=duration;
        //System.out.println("objects free: "+Poolnew.ObjectPool.size());
        this.message=s;
    }
 
    @Override
    public void run() {
        System.out.println(ThreadPool.queue.size()+" threads waiting in queue.");
        long current_time=System.currentTimeMillis();  
        //System.out.println(current_time);
        System.out.print(Thread.currentThread().getName()+" (Start)"+message);
        System.out.println("-"+this.duration);
        istime(current_time);
        System.out.println(Thread.currentThread().getName()+" (End)");
        this.isComplete=true;
        //p.free++;
    }
    @SuppressWarnings("empty-statement")
    public void istime(long start_time){
        while((System.currentTimeMillis()-start_time)<duration);
        //System.out.println(System.currentTimeMillis());
    }
    
 
    
}