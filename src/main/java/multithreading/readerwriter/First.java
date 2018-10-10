package multithreading.readerwriter;

import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by Alex Plate on 09.10.2018.
 */
public class First {
    private static Semaphore resourceLock = new Semaphore(1);
    private static Semaphore readerLock = new Semaphore(1);
    private static volatile AtomicInteger readerCount = new AtomicInteger(0);

    public static void main(String[] args) throws InterruptedException {
        while (true) {
            Writer writer = new Writer();
            Reader reader1 = new Reader();
            Reader reader2 = new Reader();
            Reader reader3 = new Reader();

            writer.start();
            reader1.start();
            reader2.start();
            reader3.start();

            Thread.sleep(1000);
        }
    }


    private static class Writer extends Thread {
        @Override
        public void run() {
            try {
                resourceLock.acquire();

                System.out.println("Writing");
                Thread.sleep(1);

                resourceLock.release();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private static class Reader extends Thread {
        @Override
        public void run() {
            try {
                readerLock.acquire();
                readerCount.incrementAndGet();
                if (readerCount.intValue() == 1) {
                    resourceLock.acquire();
                }
                readerLock.release();

                System.out.println("Read. Readers: " + readerCount);
                Thread.sleep(1);

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
}
