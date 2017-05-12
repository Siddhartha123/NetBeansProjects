
package multithreadingtest;

import java.util.ArrayList;
import java.util.List;
 
public class MultithreadingTest {
 
    public static void main(String[] args) {
        Fruit f = new Fruit();
        Producer p = new Producer(f, "Producer-1");
        Consumer c = new Consumer(f, "Consumer-1");
        Producer p1 = new Producer(f, "Producer-2");
        Consumer c1 = new Consumer(f, "Consumer-2");
        p.start();
        c.start();
        p1.start();
        c1.start();
    }
}
 
class Fruit {
    List<String> fruits = new ArrayList<String>(0);
    boolean available = false;
    int quantity;
 
    public void produce(String fruit) {
        synchronized (fruits) {
            // If the fruit is available, producer thread has to wait until the
            // consumer thread consumes it and notify.
            while (available) {
                try {
                    fruits.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
 
            fruits.add(fruit);
            System.out.println(Thread.currentThread().getName()
                    + " has produced fruit: " + fruit);
            available = true;
            fruits.notifyAll();
        }
    }
 
    public void consume() {
        synchronized (fruits) {
            // If the fruit is not available for consumption, wait until
            // producer thread has produced a fruit and notify
            while (!available) {
                try {
                    fruits.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println(Thread.currentThread().getName()
                    + " has consumed fruit: " + fruits.get(0));
            fruits.remove(0);
            available = false;
            fruits.notifyAll();
        }
    }
 
    public int getQuantity() {
        return quantity;
    }
 
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
 
class Producer extends Thread {
    private Fruit fruit;
 
    public Producer(Fruit f, String name) {
        this.fruit = f;
        this.setName(name);
    }
 
    public void run() {
        List<String> fruits = new ArrayList<String>(0);
        fruits.add("Apple");
        fruits.add("Orange");
        fruits.add("Grapes");
        fruits.add("Mango");
        fruits.add("Pineapple");
        fruit.setQuantity(fruits.size());
        for (String f : fruits) {
            fruit.produce(f);
            try {
                sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
 
        }
    }
}
 
class Consumer extends Thread {
    private Fruit fruit;
 
    public Consumer(Fruit f, String name) {
        this.fruit = f;
        this.setName(name);
    }
 
    public void run() {
        int i = 0;
        do {
            fruit.consume();
            try {
                sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            i++;
        } while (i < fruit.getQuantity());
    }
}