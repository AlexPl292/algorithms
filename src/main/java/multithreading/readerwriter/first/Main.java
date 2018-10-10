package multithreading.readerwriter.first;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Alex Plate on 09.10.2018.
 */
public class Main {
    private static Lock resourceLock = new ReentrantLock();
    private static Lock readerLock = new ReentrantLock();
    private static int readerCount = 0;

    public static void main(String[] args) {
        while (true) {
            Writer writer = new Writer();
            Reader reader1 = new Reader();
            Reader reader2 = new Reader();
            Reader reader3 = new Reader();

            writer.start();
            reader1.start();
            reader2.start();
            reader3.start();
        }
    }


    private static class Writer extends Thread {
        @Override
        public void run() {
            resourceLock.lock();

            try {
                System.out.println("Writing");
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            resourceLock.unlock();
        }
    }

    private static class Reader extends Thread {
        @Override
        public void run() {
            readerLock.lock();
            readerCount++;
            if (readerCount == 1) {
                resourceLock.lock();
            }
            readerLock.unlock();

            try {
                System.out.println("Read. Readers: " + readerCount);
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            readerLock.lock();
            readerCount--;
            if (readerCount == 0) {
                resourceLock.unlock();
            }
            readerLock.unlock();
        }
    }
}
