package multithreading.readerwriter.second;

import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by Alex Plate on 10.10.2018.
 */
public class Main {

    private static Semaphore resourceLock = new Semaphore(1);
    private static Semaphore readerLock = new Semaphore(1);
    private static Semaphore writerLock = new Semaphore(1);
    private static Semaphore readDenyLock = new Semaphore(1);

    private static volatile AtomicInteger writerCount = new AtomicInteger(0);
    private static volatile AtomicInteger readerCount = new AtomicInteger(0);


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
            reader3.start();

            writer2.start();
            writer3.start();

            Thread.sleep(1000);
        }
    }

    private static class Writer extends Thread {

        @Override
        public void run() {
            try {
                writerLock.acquire();
                writerCount.incrementAndGet();
                if (writerCount.intValue() == 1) {
                    readDenyLock.acquire();
                }
                writerLock.release();

                resourceLock.acquire();

                Thread.sleep(10);
                System.out.println("Writing. Writers: " + writerCount);

                resourceLock.release();

                writerLock.acquire();
                writerCount.decrementAndGet();
                if (writerCount.intValue() == 0) {
                    readDenyLock.release();
                }
                writerLock.release();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private static class Reader extends Thread {

        @Override
        public void run() {
            try {
                readDenyLock.acquire();
                readerLock.acquire();
                readerCount.incrementAndGet();
                if (readerCount.intValue() == 1) {
                    resourceLock.acquire();
                }
                readerLock.release();
                readDenyLock.release();

                Thread.sleep(10);
                System.out.println("Read. Readers: " + readerCount);

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
