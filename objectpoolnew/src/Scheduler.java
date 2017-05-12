
import java.util.LinkedList;
import java.util.Queue;

/*

* Scheduler.java

*/

public class Scheduler extends Thread

{
Queue<Thread> queue;


private int timeSlice;

private static final int DEFAULT_TIME_SLICE = 1000; // 1 second

public Scheduler() {

timeSlice = DEFAULT_TIME_SLICE;

queue = new LinkedList<>();

}

public Scheduler(int quantum) {

timeSlice = quantum;

queue = new LinkedList<>();

}

/*

* adds a thread to the queue

*/

public void addThread(Thread t) {

queue.offer(t);

}

/*

* this method puts the scheduler to sleep for a time quantum

*/

private void schedulerSleep() {

try {

Thread.sleep(timeSlice);

} catch (InterruptedException e) { };

}

public void run() {

Thread current;

// set the priority of the scheduler to the highest priority

this.setPriority(6);

while (true) {

try {

current = (Thread)queue.poll();

if ( (current != null) && (current.isAlive()) ) {

current.setPriority(4);

schedulerSleep();

System.out.println("* * * Context Switch * * * ");

current.setPriority(2);

}

} catch (NullPointerException e3) { } ;

}

}

}



