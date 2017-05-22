package mycallable;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

class test implements Callable<Object> {

    @Override
    public Object call() throws Exception {
        //return the thread name executing this callable task
        Object ob=new Object();
        Thread.sleep(100);
        return ob;
    }
}
public class MyCallable{   
    public static void main(String[] args){
        //Get ExecutorService from Executors utility class, thread pool size is 10
        ExecutorService executor = Executors.newFixedThreadPool(5);
        //create a list to hold the Future object associated with Callable
        Queue<Future<Object>> list = new LinkedList<>();
        //Create MyCallable instance
        Callable<Object> callable = new test();
        for(int i=0; i< 100; i++){
            //submit Callable tasks to be executed by thread pool
            Future<Object> future = executor.submit(callable);
            //add Future to the list, we can get return value using Future
            list.add(future);
        }
        while(!list.isEmpty()){
            try {
                //print the return value of Future, notice the output delay in console
                // because Future.get() waits for task to get completed
                Future fut=list.poll();
                System.out.println(fut.get());
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }
        //shut down the executor service now
        executor.shutdown();
    }
}
