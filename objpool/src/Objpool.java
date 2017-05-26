
import java.util.Stack;
import java.util.concurrent.CompletionService;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.Executors;

public class Objpool {
    public static Stack<Object> pool=new Stack<>();
    public static Executor exec;
    public static  CompletionService completionService;
    Objpool(int n){
        this.exec = Executors.newFixedThreadPool(n);
        this.completionService= new ExecutorCompletionService<>(exec);
    }
    
    public Object getObject(){
        Object o;
        while(pool.empty());
        o=pool.pop();
        return o;
    }
    
    public void returnObject(Object o){
        pool.push(o);
    }
    
    
}
