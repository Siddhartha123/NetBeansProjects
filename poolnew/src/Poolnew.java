import java.util.Vector;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 *
 * @author I Sidhartha Kumar
 */
public class Poolnew {
    int n,free,i=0;
    ExecutorService executor;
    static Vector<Object> ObjectPool = new Vector<Object>();
    Poolnew(int num){
        n=num;
        executor= Executors.newFixedThreadPool(num);
    }
    void getPoolObject(){
        i++;
        Runnable worker = new WorkerThread("p" + i);
        executor.execute(worker);
    }
    
}

class WorkerThread implements Runnable{
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
   



