/*

* Scheduler.java

*/
import java.util.*;

public class Scheduler extends Thread

{

private CircularList queue;

private int timeSlice;

private static final int DEFAULT_TIME_SLICE = 1000; // 1 second

public Scheduler() {

timeSlice = DEFAULT_TIME_SLICE;

queue = new CircularList();

}

public Scheduler(int quantum) {

timeSlice = quantum;

queue = new CircularList();

}

/*

* adds a thread to the queue

*/

public void addThread(Thread t) {

queue.addItem(t);

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

current = (Thread)queue.getNext();

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

/*

* TestScheduler.java

*/

public class TestScheduler

{

public static void main(String args[]) {

/**

* This must run at the highest priority to ensure that

* it can create the scheduler and the example threads.

* If it did not run at the highest priority, it is possible

* that the scheduler could preempt this and not allow it to

* create the example threads.

*/

Thread.currentThread().setPriority(Thread.MAX_PRIORITY);

Scheduler CPUScheduler = new Scheduler();

CPUScheduler.start();

TestThread t1 = new TestThread("Thread 1");

t1.start();

CPUScheduler.addThread(t1);

TestThread t2 = new TestThread("Thread 2");

t2.start();

CPUScheduler.addThread(t2);

TestThread t3 = new TestThread("Thread 3");

t3.start();

CPUScheduler.addThread(t3);

}

}