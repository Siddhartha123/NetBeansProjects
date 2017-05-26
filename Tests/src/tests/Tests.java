package tests;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
public class Tests {

    public static void main(String[] args) throws InterruptedException {

        int NUM_OF_THREADS = 8;
        int NUM_OF_INCREMENTS = 1000000;
        //ExecutorService service = Executors.newWorkStealingPool();
        ExecutorService service = Executors.newFixedThreadPool(NUM_OF_THREADS);
        final Counter counter = new StupidCounter();

        long before = System.currentTimeMillis();
        for (int i = 0; i < NUM_OF_INCREMENTS; i++) {
            service.submit(new CounterClient(counter, i));
        }
        service.shutdown();
        service.awaitTermination(1, TimeUnit.MINUTES);
        long end = System.currentTimeMillis();
        System.out.println(end - before);
        System.out.println(counter.getCounter());
    }


    static class CounterClient implements Runnable {
        private Counter counter;
        private int num;

        public CounterClient(Counter counter, int num) {
            this.counter = counter;
            this.num = num;
        }

        @Override
        public void run() {
            counter.increment();
        }
    }

    static interface Counter {
        void increment();

        long getCounter();
    }

    static class StupidCounter implements Counter {
        long i = 0;

        @Override
        public void increment() {
            i++;
        }

        @Override
        public long getCounter() {
            return i;
        }
    }

}