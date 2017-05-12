public class TestScheduler

{

public static void main(String args[]) {

Thread.currentThread().setPriority(Thread.MAX_PRIORITY);

Scheduler CPUScheduler = new Scheduler();

CPUScheduler.start();

Thread t1 = new Thread("Thread 1");

t1.start();

CPUScheduler.addThread(t1);

Thread t2 = new Thread("Thread 2");

t2.start();

CPUScheduler.addThread(t2);

Thread t3 = new Thread("Thread 3");

t3.start();

CPUScheduler.addThread(t3);

}

}