package principal;

public class Corredor extends Thread {
	
	int id;
	public Thread hilo;
	Testigo testigo;
	
	public Corredor() {
		
	}
	
	public Corredor(int id, Testigo testigo) {
		
		this.id=id;
		this.testigo=testigo;
		this.hilo= new Thread(this);
		hilo.start();
        
	}
	
	public void run() {
		
		while(!testigo.finalCarrera) {
			testigo.cogerTestigo(this);
		}
		 
		
		 
	}

}
