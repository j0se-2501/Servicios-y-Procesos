package Ejercicio;

public class Ejercicio2 {

	public static void main(String[] args) {
		
		//prueba cuadratica
		//hash = longitud del almacenamiento
		
		int datos[] = {147,237,121,724,100};
		int casilla;
		int almacenamiento[] = {0,0,0,0,0,0,0,0};
		
		for(int i=0; i<datos.length; i++) {
			int j=0;
			int incrementoModulo=datos[i];
			casilla=datos[i]%almacenamiento.length;
			while (almacenamiento[casilla]!=0) {
				
				System.out.println("COLISION! Casilla "+casilla+" ocupada.");
				casilla=(incrementoModulo+(j*j))%almacenamiento.length;
				j++;
			if (casilla>almacenamiento.length-1) casilla=0;
			}
			j=0;
			
			almacenamiento[casilla]=datos[i];
			
		}
		
		for(int i=0; i<almacenamiento.length; i++)
			System.out.println(almacenamiento[i]);

	}

}
