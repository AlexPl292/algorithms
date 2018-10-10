package multithreading.readerwriter;

import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by Alex Plate on 10.10.2018.
 */
public class Third {

    public static Semaphore readerLock = new Semaphore(1);
    public static Semaphore resourceLock = new Semaphore(1);
    public static Semaphore queue = new Semaphore(1);

    public static AtomicInteger readerCount = new AtomicInteger(0);

    public static void main(String[] args) throws InterruptedException {
        while (true) {
            Writer writer1 = new Writer();
            Writer writer2 = new Writer();
            Writer writer3 = new Writer();
            Reader reader1 = new Reader();
            Reader reader2 = new Reader();
            Reader reader3 = new Reader();

            writer1.start();

            reader1.start();
            reader2.start();

            writer2.start();

            reader3.start();

            writer3.start();

            Thread.sleep(1000);
        }
    }

    private static class Reader extends Thread {
        @Override
        public void run() {
            try {
                queue.acquire();
                readerLock.acquire();
                readerCount.incrementAndGet();
                if (readerCount.intValue() == 1) {
                    resourceLock.acquire();
                }
                readerLock.release();
                queue.release();

                Thread.sleep(10);
                System.out.println("Read. Readers:" + readerCount);

                readerLock.acquire();
                readerCount.decrementAndGet();
                if (readerCount.intValue() == 0) {
                    resourceLock.release();
                }
                readerLock.release();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private static class Writer extends Thread {
        @Override
        public void run() {
            try {
                queue.acquire();
                resourceLock.acquire();
                queue.release();

                Thread.sleep(10);
                System.out.println("Write");

                resourceLock.release();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
}
