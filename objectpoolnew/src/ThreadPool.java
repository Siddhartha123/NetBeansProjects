import static java.lang.Thread.sleep;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class WorkerThread implements Runnable {
    private String message;
    public WorkerThread(String s){
        this.message=s;
    }
 
    @Override
    public void run() {
        
        System.out.println(Thread.currentThread().getName()+" (Start) message = "+message);
        System.out.println(Thread.currentThread().getName()+ " running");
        System.out.println(Thread.currentThread().getName()+" (End)");
    }
 
    
    }
   



public class ThreadPool {
 
    public static void main(String[] args) throws SQLException, ClassNotFoundException, InterruptedException {
            jdbc_con db;
            db = new jdbc_con("com.mysql.jdbc.Driver","jdbc:mysql://localhost/cyborg","root","");
            if(db.is_connected){
                Connection con=db.getConnection(db);
                Object con1=(Object)con;
                Objectpoolnew m1=new Objectpoolnew();  
                m1.initialize(3,con1);
                try {
                    sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        ExecutorService executor = Executors.newFixedThreadPool(5);
        for (int i = 1; i <= 15; i++) {
            Runnable worker = new WorkerThread("p" + i);
            executor.execute(worker);
          }
        executor.shutdown();
        while (!executor.isTerminated()) {
        }
        System.out.println("Finished all threads");
    }
 
}