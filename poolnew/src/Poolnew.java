import java.util.LinkedList;
import java.util.Queue;
import java.util.Vector;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.Stack;

public class Poolnew {
    int n,free,i=0;
    ExecutorService executor;
    static Stack<Object> ObjectPool = new Stack<Object>();
    static Vector<Integer> completed=new Vector<>();
    Queue<Thread> queue;
    @SuppressWarnings("empty-statement")
    void getPoolObject(){
        int j;
        i++;
        queue.add(Thread.currentThread());
        //completed.add(1);
        while(free==0);
        
        Runnable worker = new WorkerThread("p" + i,ObjectPool);
        //free--;
        /*
        for(j=0;j<i-1;j++){
            while(completed.elementAt(j)==0);
        }
        */
        executor.execute(worker);
        //System.out.println(executor);
    }
    
    Poolnew(int num,Object obj){
        n=num;
        free=n;
        int j;
        //completed.add(1);
        executor= Executors.newFixedThreadPool(num);
        for(j=0;j<n;j++)
        ObjectPool.add(obj);
        queue =new LinkedList<>();
    }
    
}

class WorkerThread implements Runnable{
    private String message;
    Object object;
    public boolean isComplete=false;
    public WorkerThread(String s,Stack obj){
        while(obj.empty());
        this.object=obj.pop();
        this.message=s;
    }
 
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName()+" (Start)"+message);
        System.out.println(Thread.currentThread().getName()+" (End)");
        this.isComplete=true;
        this.release(object);
        //p.free++;
    }
    public void release(Object object)
    {
        Poolnew.ObjectPool.push(object);
    }
 
    
    }
   



