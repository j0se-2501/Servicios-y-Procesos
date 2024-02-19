package principal;

public class Principal {

	public static void main(String[] args) {
		
		Testigo testigo = new Testigo(1);
		
		Relevos relevos = new Relevos (new Corredor(1, testigo),new Corredor(2, testigo),new Corredor(3, testigo),new Corredor(4, testigo));

	}

}
