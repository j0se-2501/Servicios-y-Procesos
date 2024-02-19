package hilos;

import java.util.concurrent.Semaphore;

public class Principal {

    public static void main(String[] args) {

        Semaphore semCinta = new Semaphore(1); // Semáforo para controlar el acceso a la cinta
        Semaphore semOperario1 = new Semaphore(0); // Semáforo para controlar el acceso del operario 1 a la cinta
        Semaphore semOperario2 = new Semaphore(0); // Semáforo para controlar el acceso del operario 2 a la cinta
        Semaphore semOperario3 = new Semaphore(0); // Semáforo para controlar el acceso del operario 3 a la cinta

        Cinta cinta = new Cinta(5, semCinta, semOperario1, semOperario2, semOperario3);
        Operario operario1 = new Operario(1, cinta, semOperario1, semCinta);
        Operario operario2 = new Operario(2, cinta, semOperario2, semCinta);
        Operario operario3 = new Operario(3, cinta, semOperario3, semCinta);

        cinta.start();
        operario1.start();
        operario2.start();
        operario3.start();
    }
}

