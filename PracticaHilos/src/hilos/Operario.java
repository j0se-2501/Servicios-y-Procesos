package hilos;

import java.util.concurrent.Semaphore;

public class Operario extends Thread {
    int id;
    Cinta cinta;
    Semaphore semOperario, semCinta;
    int contadorPaquetes;

    public Operario(int id, Cinta cinta, Semaphore semOperario, Semaphore semCinta) {
        this.id = id;
        this.cinta = cinta;
        this.semOperario = semOperario;
        this.semCinta = semCinta;
        this.contadorPaquetes=0;
    }

    public void run() {
        while (cinta.quedanPaquetes) {
            try {
                // Espera a que haya un paquete de su tipo disponible en la cinta
                semOperario.acquire();
                cinta.cogerPaquete(this);
                
                // Simula el tiempo que tarda en procesar el paquete
                Thread.sleep(1000);
                semCinta.release();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
