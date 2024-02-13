package hilos;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.Semaphore;

public class OrdenarPaquetes implements Runnable {
	
	Queue<Integer> colaPaquetes;
	int totalPaquetes=15;
	int paquetesRecogidos=0;
	int paquete=0;
	
	Thread operarioCola;
	Thread operario1;
	Thread operario2;
	Thread operario3;
	
	Semaphore semCola = new Semaphore(0);
	Semaphore sem1 = new Semaphore(0);
	Semaphore sem2 = new Semaphore(0);
	Semaphore sem3 = new Semaphore(0);
	
	
	public void generarPaquetes() {
		
		this.colaPaquetes = new LinkedList<>();
		
		for (int i=0; i<this.totalPaquetes; i++) {
			int nuevoPaquete=(int) (Math.random()*3+1);
			colaPaquetes.add(nuevoPaquete);
			System.out.println("Paquete añadido: "+nuevoPaquete+".");
		}
		System.out.println("//////////////////////\n//////////////////////\n//////////////////////\n\n\n");
		semCola.release();
	}
	
	
	@Override
	public void run() {
		
		
			
			
			
			if (Thread.currentThread().getName().equals("operario cola")) {
                while (!colaPaquetes.isEmpty()) {
                	try {
                		
                	
                	switch (this.colaPaquetes.peek()) {
                		case(1): 
                		semCola.acquire();
						System.out.println("Paquete entrante: 1.");
						paquete = this.colaPaquetes.remove();
                		break;
                		
                		case(2): 
                		semCola.acquire();
						System.out.println("Paquete entrante: 2.");
						paquete = this.colaPaquetes.remove();
                		break;
                		
                		case(3): 
                		semCola.acquire();
						System.out.println("Paquete entrante: 3.");
						paquete = this.colaPaquetes.remove();
                		break;
                		
                		default: System.out.println("Como llegaste aqui?");
                		break;
                		}
                	
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
                	}
                
					
				
                try {
					semCola.acquire();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				paquete=-1;
                System.out.println("\n\n\nTodos los paquetes fueron entregados.");
				semCola.release();
			}
               else if (Thread.currentThread().getName().equals("operario 1")) {
                while (paquete>-1) {
                	try {
                		
                	if (paquete==1) {
                		
                		System.out.println("Operario 1: Paquete 1 recogido.\n");
                		paquete=0;
                		
						//Thread.sleep(1000);
						semCola.release();
						
                	}
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
                
            }
                
            }
               else if (Thread.currentThread().getName().equals("operario 2")) {
            	    while (paquete>-1) {
            	    	
            	        try {
            	            if (paquete == 2) {
            	            	
            	                System.out.println("Operario 2: Paquete 2 recogido.\n");
            	                paquete=0;
            	               
            	                //Thread.sleep(1000);
            	                semCola.release();
            	            }
            	        } catch (Exception e) {
            	            e.printStackTrace();
            	        }
            	    }
            	}
            	else if (Thread.currentThread().getName().equals("operario 3")) {
            	    while (paquete>-1) {
            	    	
            	        try {
            	            if (paquete == 3) {
            	            	
            	                System.out.println("Operario 3: Paquete 3 recogido.\n");
            	                paquete=0;
            	                
            	                //Thread.sleep(1000);
            	                semCola.release();
            	            }
            	        } catch (Exception e) {
            	            e.printStackTrace();
            	        }
            	    }
            	}

			
		
		
		
		
		
			
	}
		
	public static void main(String[] args) {
		
		OrdenarPaquetes ordenarPaquetes = new OrdenarPaquetes();
		
		ordenarPaquetes.generarPaquetes();
		ordenarPaquetes.operarioCola = new Thread (ordenarPaquetes, "operario cola");
		ordenarPaquetes.operario1 = new Thread (ordenarPaquetes, "operario 1");
		ordenarPaquetes.operario2 = new Thread (ordenarPaquetes, "operario 2");
		ordenarPaquetes.operario3 = new Thread (ordenarPaquetes, "operario 3");
		
		ordenarPaquetes.operarioCola.start();
		ordenarPaquetes.operario1.start();
		ordenarPaquetes.operario2.start();
		ordenarPaquetes.operario3.start();
		
		
		
	}

	

}
