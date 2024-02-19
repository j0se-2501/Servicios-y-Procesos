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
		System.out.println("Corredor " +this.corredor.id+ " suelta el relevo.");
		this.contador++;
		try {
			corredor.hilo.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (contador==5) finalCarrera=true;
		else notify();
	}

}
