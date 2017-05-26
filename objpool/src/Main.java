
import java.util.concurrent.Callable;

public class Main {
    
    public static void main(String[] args) {
        Objpool o=new Objpool(3);
        thread t1=new thread();
        Objpool.completionService.submit(t1,new Object());
        MyCallable process=new MyCallable(t1);
    }
}

class thread implements Runnable{
    
    @Override
    public void run(){
        System.out.println("test");
    }
}

class MyCallable implements Callable<Object>{
    Runnable r;
    Object o;
    MyCallable(Runnable r){
        this.r = r;
        this.o=new Object();
    }
     
    @Override
    public Object call(){
       r.run();
       // check status of runnable
     return this.o;
    }
}