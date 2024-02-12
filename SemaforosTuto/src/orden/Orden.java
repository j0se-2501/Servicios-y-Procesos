package orden;

import java.util.concurrent.Semaphore;

public class Orden implements Runnable {
    Semaphore semSegundo;
    Semaphore semPrimero;

    public Orden() {
        semSegundo = new Semaphore(0);
        semPrimero = new Semaphore(0);
    }

    @Override
    public void run() {
        try {
            if (Thread.currentThread().getName().equals("hilo1")) {
                while (true) {
                    semPrimero.acquire();
                    System.out.println("Soy el primer hilo");
                    Thread.sleep(1000);
                    semSegundo.release();
                }
            } else if (Thread.currentThread().getName().equals("hilo2")) {
                while (true) {
                    System.out.println("Soy el segundo hilo");
                    semPrimero.release();
                    semSegundo.acquire();
                    Thread.sleep(1000);
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Orden orden = new Orden();
        Thread hilo1 = new Thread(orden, "hilo1");
        Thread hilo2 = new Thread(orden, "hilo2");

        // Iniciamos el segundo hilo primero
        hilo2.start();
        hilo1.start();
    }
}

