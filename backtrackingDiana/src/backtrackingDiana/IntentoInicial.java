package backtrackingDiana;

import java.util.ArrayList;

public class IntentoInicial {
	
	//intento inicial del código por cuenta, no funcionay no esta terminado
	
	static int[] puntuaciones = {10, 25, 50, 75};
	static int tiro = 0;
	static int limiteTiros = 4;
	static int objetivo=100;
	static int sumaTiros=0;
	
	static ArrayList<int[]> soluciones = new ArrayList<int[]>();

	public static void main(String[] args) {
		
		for (int zonaDiana : puntuaciones) {
			tirar(zonaDiana, tiro);
		}
		
		System.out.println(soluciones);
	}
	
	static void tirar(int zonaDiana, int tiro) {
		int[] combinacion = {0, 0, 0, 0, 0};
		sumaTiros+=zonaDiana;
		combinacion[tiro]=zonaDiana;
		if ((sumaTiros==objetivo)||(tiro==limiteTiros&&sumaTiros<objetivo)) { 
			soluciones.add(combinacion);
			tiro=0;
		} else if (tiro<limiteTiros) {
			tirar(zonaDiana, tiro++);
			
		}
		
	}

}
