package Ejercicio;

public class Ejercicio1 {

	public static void main(String[] args) {
		
		//prueba lineal
		//hash = longitud del almacenamiento
		
		int datos[] = {5,15,25,35,45,55};
		int casilla;
		int almacenamiento[] = {0,0,0,0,0,0,0,0,0,0};
		
		for(int i=0; i<datos.length; i++) {
			int incrementoModulo=datos[i];
			casilla=datos[i]%almacenamiento.length;
			while (almacenamiento[casilla]!=0) {
				System.out.println("COLISION! Casilla "+casilla+" ocupada.");
				casilla=(incrementoModulo++)%almacenamiento.length;
			if (casilla>almacenamiento.length-1) casilla=0;
			}
			
			
			almacenamiento[casilla]=datos[i];
			
		}
		
		for(int i=0; i<almacenamiento.length; i++)
			System.out.println(almacenamiento[i]);

	}

}
