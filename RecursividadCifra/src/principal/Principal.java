package principal;

import java.util.ArrayList;
import java.util.Collections;

public class Principal {
	
	public static ArrayList<Integer> vector = new ArrayList<Integer>();

	public static void main(String[] args) {
		
	SepararCifra(1234567890);
	Collections.reverse(vector);
	System.out.println(vector);

	}
	
	public static void SepararCifra(int cifraUsuario) {
		
		vector.add(cifraUsuario%10);
		
		if(cifraUsuario/10>=1) { 
			
			SepararCifra(cifraUsuario/10);
		
		}
		
	}

}
