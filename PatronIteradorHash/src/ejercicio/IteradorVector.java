package ejercicio;

import java.util.HashMap;

import ejercicio.HashMap2;

public class IteradorVector {

	 private HashMap<Integer, Integer> hashmap = new HashMap<Integer, Integer>();
	 private int posicion;
	 private int valor;

	public IteradorVector (HashMap2 hashmap2){ 

	hashmap = hashmap2.coleccion;
	posicion=0;
	}
	
	public boolean hasNext(){
	 
	if (posicion < hashmap.size())

		return true;

		else

			return false;

	}

	public Object next(){

	int valor = hashmap.getOrDefault(posicion, 0); 
	posicion++; 
	return valor;
	}

	}


