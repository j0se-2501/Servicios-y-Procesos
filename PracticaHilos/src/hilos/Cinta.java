package hilos;

import java.util.concurrent.Semaphore;

public class Cinta extends Thread {
    int totalPaquetes = 15;
    int tamannoCinta;
    int[] paquetes;
    Semaphore semCinta, semOperario1, semOperario2, semOperario3;
    boolean quedanPaquetes = true;
    int cont;
    int contadorPaquetes[] = new int[3];

    public Cinta(int tamannoCinta, Semaphore semCinta, Semaphore semOperario1, Semaphore semOperario2,Semaphore semOperario3) {
        this.tamannoCinta = tamannoCinta;
        this.paquetes = new int[this.tamannoCinta];
        for (int i = 0; i < this.tamannoCinta; i++) {
            this.paquetes[i] = 0;
        }
        this.semCinta = semCinta;
        this.semOperario1 = semOperario1;
        this.semOperario2 = semOperario2;
        this.semOperario3 = semOperario3;
    }

    public void run() {
        while (this.quedanPaquetes) {
            try {
                // Espera a que el semáforo permita el acceso
                semCinta.acquire();
                llenarCinta();
                // Libera el semáforo después de llenar la cinta
                Thread.sleep(2000); // Simula el tiempo que tarda en reponer los paquetes
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void cogerPaquete(Operario operario) {
        for (int i = 0; i < this.tamannoCinta; i++) {
            if (this.paquetes[i] == operario.id) {
                this.paquetes[i] = 0;
                this.contadorPaquetes[operario.id-1]++;
                System.out.println("Operario " + operario.id + " recoge un paquete.");
                break;
            }
        }
    }

    public void llenarCinta() {
        System.out.println("SE RELLENA LA CINTA");
        
        for (int i = 0; i < this.tamannoCinta; i++) {
            if (this.paquetes[i] == 0 && this.totalPaquetes > 0) {
                int nuevoPaquete = (int) (Math.random() * 3 + 1);
                this.paquetes[i] = nuevoPaquete;
                this.totalPaquetes--;
                // Notifica al operario correspondiente para que pueda tomar un paquete de su tipo
            }
        }
        pintarCinta();
        semOperario1.release();
        semOperario2.release();
        semOperario3.release();
    }

    
    public void pintarCinta() {
    	cont=0;
    	for (int i=0; i<this.tamannoCinta; i++) {
    		if (paquetes[i]==0) cont++;
    		System.out.print("["+paquetes[i]+"], ");
    	}
    	System.out.println("");
    	if (cont==tamannoCinta&&totalPaquetes==0) {
    		quedanPaquetes=false;
    		System.out.println("SE ACABARON LOS PAQUETES");
    		for (int i=0; i<3; i++) {
    			System.out.println("Operario "+(i+1)+": "+contadorPaquetes[i]+" paquetes recogidos.");
    		}
    	}
    }
}
