package principal;

public class Testigo {
	
	Corredor corredor;
	int contador;
	boolean finalCarrera=false;
	
	public Testigo(int contador) {
		this.contador=contador;
	}
	
	public synchronized void cogerTestigo(Corredor corredor) {
		
		if (corredor.id-this.contador==0) {
		
		
		this.corredor=corredor;
		System.out.println("Corredor " +this.corredor.id+ " toma el relevo.");
		soltarTestigo();
		
		}
		
	}
	
	public synchronized void soltarTestigo() {
		try {
			corredor.hilo.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Corredor " +this.corredor.id+ " suelta el relevo.");
		this.contador++;
		
		if (contador==5) {
			System.out.println("Final de la carrera");
			finalCarrera=true;
		}
		else notify();
	}

}
