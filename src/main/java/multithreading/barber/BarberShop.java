package multithreading.barber;

import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by Alex Plate on 10.10.2018.
 */
public class BarberShop {

    private static Semaphore barberReady = new Semaphore(1);
    private static Semaphore clientReady = new Semaphore(1);
    private static Semaphore chairLock = new Semaphore(1);
    private static AtomicInteger availableChairs = new AtomicInteger(10);

    public static void main(String[] args) throws InterruptedException {
        Barber barber = new Barber();

        barber.start();

        Client client1 = new Client();
        client1.start();

        Thread.sleep(100);

        Client client2 = new Client();
        Client client3 = new Client();

        client2.start();
        client3.start();
    }

    private static class Barber extends Thread {
        @Override
        public void run() {
            while (true) {
                try {
                    clientReady.acquire();
                    chairLock.acquire();
                    availableChairs.incrementAndGet();
                    barberReady.release();
                    chairLock.release();

                    Thread.sleep(10);
                    System.out.println("Barb");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }
    }

    private static class Client extends Thread {
        @Override
        public void run() {
            while (true) {
                try {
                    chairLock.acquire();
                    if (availableChairs.intValue() > 0) {
                        availableChairs.decrementAndGet();
                        System.out.println("Get chair: " + availableChairs);
                        clientReady.release();
                        chairLock.release();
                        barberReady.acquire();
                    } else {
                        chairLock.release();
                    }

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }
    }
}
